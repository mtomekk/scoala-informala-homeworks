package ro.sci.finalTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tamas on 3/24/2017.
 */
public class PackageRepository {
    private List<Package> packages = new ArrayList<>();

    public void addPackage(Package aPackage) {
        if (aPackage != null) {
            packages.add(aPackage);
        }
    }

    public Map<String, List<Package>> sortPackagesByDate(LocalDate targetDate) {
        Map<String, List<Package>> sortedPackagesByDate = new HashMap<>();
        for(Package aPackage: packages) {
            if(aPackage.getDeliveryDate().equals(targetDate)) {
                if(!sortedPackagesByDate.keySet().contains(aPackage.getTargetLocation())) {
                    sortedPackagesByDate.put(aPackage.getTargetLocation(), new ArrayList<>());
                }
                sortedPackagesByDate.get(aPackage.getTargetLocation()).add(aPackage);
            }
        }
        return sortedPackagesByDate;
    }

    public void deliverOnDate(LocalDate targetDate) {
        Map<String, List<Package>>  packagesToBeDeliveredOnTargetDate = sortPackagesByDate(targetDate);
        for(Map.Entry<String,List<Package>> entry: packagesToBeDeliveredOnTargetDate.entrySet()) {
            DeliveryCar deliveryCar = new DeliveryCar(entry.getValue());
            Thread thread = new Thread(deliveryCar);
            thread.start();
        }
    }


}
