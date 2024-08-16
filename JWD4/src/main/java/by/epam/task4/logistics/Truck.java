package by.epam.task4.logistics;

import by.epam.task4.trucktask.TruckTack;

import java.util.ArrayList;
import java.util.Collection;

public class Truck {
    private final Collection<Product> products;
    private TruckTack tack;

    public Truck() {
        this.products = new ArrayList<>();
    }

    public Truck(TruckTack tack) {
        this();
        setTack(tack);
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public TruckTack getTack() {
        return tack;
    }

    public void setTack(TruckTack tack) {
        this.tack = tack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return (products == null ? truck.products == null : products.equals(truck.products)) &&
                (tack == null ? truck.tack == null : tack.equals(truck.tack));
    }

    @Override
    public int hashCode() {
        return products.hashCode() * 31 + (tack == null ? 17 : tack.hashCode());
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Truck{")
                .append("tack=").append(tack)
                .append(", products=").append(products)
                .append('}')
                .toString();
    }
}
