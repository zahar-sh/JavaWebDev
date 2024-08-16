package by.epam.task4.trucktask;

public class UnloadTack extends TruckTack {
    public UnloadTack(int servicePriority) {
        super(servicePriority);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("UnloadTack{")
                .append("servicePriority=").append(servicePriority)
                .append('}')
                .toString();
    }
}
