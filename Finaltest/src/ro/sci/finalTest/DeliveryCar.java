package ro.sci.finalTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamas on 3/24/2017.
 */
public class DeliveryCar implements Runnable {
    private List<Package> packages = new ArrayList<>();

    public DeliveryCar(List<Package> packages) {
        this.packages = packages;
    }

    private double getTotalValue() {
        int value = 0;
        for(Package aPackage:packages) {
            value += aPackage.getValue();
        }
        return value;
    }

    private double getTotalProfit() {
        int profit = 0;
        for(Package aPackage:packages) {
            profit += aPackage.getDistance() * 2;
        }
        return profit;
    }

    @Override
    public void run() {
        System.out.println("On " + packages.get(0).getDeliveryDate() + ", " + packages.size() +
                            " package/s are on their way to " + packages.get(0).getTargetLocation());
        try {
            Thread.sleep(packages.get(0).getDistance() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(packages.size() + " package/s successfully delivered to " + packages.get(0).getTargetLocation() +
//                          ". Total merchandise value: " + getTotalValue() + ", total profit: " + getTotalProfit());
        System.out.printf("%d package/s successfully delivered to %s. Total merchandise value: %.2f, total profit: %.2f\n",
                            packages.size(),packages.get(0).getTargetLocation(), getTotalValue(), getTotalProfit());
    }
}
