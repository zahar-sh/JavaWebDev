package by.epam.task4._main;

import by.epam.task4.logistics.LogisticsCenter;
import by.epam.task4.logistics.Product;
import by.epam.task4.logistics.ProductType;
import by.epam.task4.logistics.Truck;
import by.epam.task4.trucktask.LoadTack;
import by.epam.task4.trucktask.UnloadTack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        LogisticsCenter logisticsCenter = new LogisticsCenter(2, 4);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(logisticsCenter);
        Truck[] trucks = {
                createLoadTruck(ProductType.BREAD, 3),
                createLoadTruck(ProductType.SAUSAGE, 6),
                createUnloadTruck(ProductType.BREAD, 4),
                createUnloadTruck(ProductType.SAUSAGE, 5),
                createLoadTruck(ProductType.CEREAL, 2),
                createUnloadTruck(ProductType.CEREAL, 3),
                createUnloadTruck(ProductType.SAUSAGE, 4)
        };
        for (Truck truck : trucks) {
            logisticsCenter.truckService(truck);
        }

        //logisticsCenter.close();
       // boolean terminationResult = logisticsCenter.awaitTermination();
       // System.out.println(terminationResult);
    }

    private static Truck createUnloadTruck(ProductType type, int count) {
        Truck truck = new Truck(new UnloadTack(UnloadTack.servicePriorityOf(type)));
        for (int i = 0; i < count; i++) {
            Product product = new Product(type);
            truck.getProducts().add(product);
        }
        return truck;
    }

    private static Truck createLoadTruck(ProductType type, int count) {
        return new Truck(new LoadTack(type, count));
    }
}
