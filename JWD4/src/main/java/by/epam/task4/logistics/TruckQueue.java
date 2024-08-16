package by.epam.task4.logistics;

import by.epam.task4.trucktask.LoadTack;
import by.epam.task4.trucktask.TruckTack;
import by.epam.task4.trucktask.UnloadTack;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TruckQueue {
    private static final Comparator<Truck> TRUCK_COMPARATOR = Comparator.comparing(Truck::getTack,
            Comparator.nullsFirst(Comparator.comparingInt(TruckTack::getServicePriority)));

    private final Queue<Truck> core;

    public TruckQueue() {
        this.core = new PriorityQueue<>(TRUCK_COMPARATOR);
    }

    public TruckQueue(int initialCapacity) {
        this.core = new PriorityQueue<>(initialCapacity, TRUCK_COMPARATOR);
    }

    public boolean isEmpty() {
        return core.isEmpty();
    }

    public boolean offer(Truck truck) {
        return core.offer(truck);
    }

    public Truck poll() {
        return core.poll();
    }

    public Truck peek() {
        return core.peek();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckQueue that = (TruckQueue) o;
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
