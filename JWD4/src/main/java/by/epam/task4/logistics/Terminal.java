package by.epam.task4.logistics;

public class Terminal {
    private final LogisticsCenter center;

    Terminal(LogisticsCenter center) {
        this.center = center;
    }

    public Storage getStorage() {
        return center.getStorage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return center.equals(terminal.center);
    }

    @Override
    public int hashCode() {
        return center.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Terminal{")
                .append("center=").append(center)
                .append('}')
                .toString();
    }
}
