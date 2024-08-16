package by.epam.task4.trucktask;

import by.epam.task4.logistics.ProductType;

public class LoadTack extends TruckTack {
    private final ProductType type;
    private final int count;

    public LoadTack(ProductType type, int count) {
        super(servicePriorityOf(type));
        this.type = type;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadTack loadTack = (LoadTack) o;
        return count == loadTack.count &&
                type == loadTack.type;
    }

    @Override
    public int hashCode() {
        return type.hashCode() * 31 + count;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("LoadTack{")
                .append("type=").append(type)
                .append(", count=").append(count)
                .append('}').toString();
    }
}
