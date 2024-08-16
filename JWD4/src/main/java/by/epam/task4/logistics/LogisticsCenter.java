package by.epam.task4.logistics;

import by.epam.task4.trucktask.LoadTack;
import by.epam.task4.trucktask.TruckTack;
import by.epam.task4.trucktask.UnloadTack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsCenter implements Callable<Void> {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Semaphore terminalSemaphore;
    private final Deque<Terminal> terminals;
    private final Storage storage;
    private final TruckQueue truckQueue;
    private final Lock truckLock;
    private final Condition truckAddCondition;
    private final ExecutorService service;

    public LogisticsCenter(int terminals, int threads) {
        this.terminalSemaphore = new Semaphore(terminals);
        this.terminals = new ArrayDeque<>(terminals);
        for (int i = 0; i < terminals; i++) {
            this.terminals.add(new Terminal(this));
        }
        this.storage = new Storage();
        this.truckQueue = new TruckQueue();
        this.service = Executors.newFixedThreadPool(threads);
        this.truckLock = new ReentrantLock();
        this.truckAddCondition = truckLock.newCondition();
    }

    private Terminal takeTerminal() {
        LOGGER.info("take terminal, thread=" + Thread.currentThread().getName());
        try {
            terminalSemaphore.acquire();
            return terminals.pop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e);
            return null;
        }
    }

    private void releaseTerminal(Terminal terminal) {
        LOGGER.info("release terminal, thread=" + Thread.currentThread().getName());
        terminalSemaphore.release();
        terminals.push(terminal);
    }

    Storage getStorage() {
        return storage;
    }

    public void truckService(Truck truck) {
        LOGGER.info("add truck in queue, truck=" + truck);
        try {
            truckLock.lock();
            truckQueue.offer(truck);
            truckAddCondition.signal();
        } finally {
            truckLock.unlock();
        }
    }

    @Override
    public Void call() {
        LOGGER.info("start logistics center");
        while (!Thread.currentThread().isInterrupted()) {
            Truck truck;
            try {
                truckLock.lock();
                while (truckQueue.isEmpty())
                    truckAddCondition.await();
                truck = truckQueue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            } finally {
                truckLock.unlock();
            }
            LOGGER.info("begin truck service, truck=" + truck);
            Callable<Void> truckServiceTask = () -> {
                Terminal terminal = takeTerminal();
                if (terminal == null)
                    return null;
                try {
                    Storage storage = terminal.getStorage();
                    TruckTack tack = truck.getTack();
                    if (LoadTack.class == tack.getClass()) {
                        LoadTack loadTack = (LoadTack) tack;
                        ProductType type = loadTack.getType();
                        int count = loadTack.getCount();
                        Collection<Product> products = storage.take(count, type);
                        truck.getProducts().addAll(products);
                    }
                    if (UnloadTack.class == tack.getClass()) {
                        Collection<Product> products = truck.getProducts();
                        storage.add(products);
                        products.clear();
                    }
                } finally {
                    releaseTerminal(terminal);
                }
                return null;
            };
            service.submit(truckServiceTask);
        }
        return null;
    }

    public void close() {
        service.shutdown();
    }

    public boolean awaitTermination() {
        try {
            return service.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error(e);
            return false;
        }
    }
}
