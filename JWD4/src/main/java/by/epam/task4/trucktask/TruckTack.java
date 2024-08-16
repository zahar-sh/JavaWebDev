package by.epam.task4.trucktask;

import by.epam.task4.logistics.ProductType;

public abstract class TruckTack {
    private static final int DEFAULT_SERVICE_PRIORITY = 0;

    public static int servicePriorityOf(ProductType type) {
        switch (type) {
            case BREAD:
                return 1;
            case CEREAL:
                return 2;
            case SAUSAGE:
                return 10;
            default:
                return DEFAULT_SERVICE_PRIORITY;
        }
    }

    protected final int servicePriority;

    public TruckTack(int servicePriority) {
        this.servicePriority = servicePriority;
    }

    public int getServicePriority() {
        return servicePriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckTack truckTack = (TruckTack) o;
        return servicePriority == truckTack.servicePriority;
    }

    @Override
    public int hashCode() {
        return servicePriority;
    }

    @Override
    public String toString() {
        return "TruckTack{" +
                "servicePriority=" + servicePriority +
                '}';
    }
}

