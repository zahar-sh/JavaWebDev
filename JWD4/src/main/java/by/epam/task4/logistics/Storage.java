package by.epam.task4.logistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final Collection<Product> core;
    private final Lock addLock;
    private final Condition addCondition;

    public Storage() {
        this.core = new ArrayList<>();
        this.addLock = new ReentrantLock();
        this.addCondition = addLock.newCondition();
    }

    public boolean add(Collection<Product> products) {
        try {
            addLock.lock();
            boolean result = core.addAll(products);
            addCondition.signal();
            return result;
        } finally {
            addLock.unlock();
        }
    }

    public Collection<Product> take(int count, ProductType type) {
        try {
            addLock.lock();
            List<Product> products = new ArrayList<>();
            long limit = count;
            loop:
            while (true) {
                for (Product product : core) {
                    if (type.equals(product.getType())) {
                        if (limit-- <= 0)
                            break loop;
                        products.add(product);
                    }
                }
                try {
                    addCondition.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            core.removeAll(products);
            return products;
        } finally {
            addLock.unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage that = (Storage) o;
        return core.equals(that.core);
    }

    @Override
    public int hashCode() {
        return core.hashCode();
    }

    @Override
    public String toString() {
        return core.toString();
    }
}
