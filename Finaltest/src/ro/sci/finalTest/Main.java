package ro.sci.finalTest;

import java.time.LocalDate;

/**
 * Created by Tamas on 3/24/2017.
 */

public class Main {
    public static void main(String[] args) {
        Package package1 = new Package("Cluj", 10, 129, LocalDate.of(2017,3, 24));
        Package package2 = new Package("Cluj", 10, 50, LocalDate.of(2017,3, 25));
        Package package3 =  new Package("Cluj", 10, 29.5, LocalDate.of(2017,3, 24));
        Package package4 =  new Package("Oradea", 5, 150, LocalDate.of(2017,3, 24));
        Package package5 =  new Package("Oradea", 5, 200, LocalDate.of(2017,3, 25));
        Package package6 =  new Package("Oradea", 5, 599, LocalDate.of(2017,3, 25));
        Package package7 =  new Package("Satu Mare", 17, 42, LocalDate.of(2017,3, 24));

        PackageRepository packageRepository = new PackageRepository();
        packageRepository.addPackage(package1);
        packageRepository.addPackage(package2);
        packageRepository.addPackage(package3);
        packageRepository.addPackage(package4);
        packageRepository.addPackage(package5);
        packageRepository.addPackage(package6);
        packageRepository.addPackage(package7);

        LocalDate deliveryDate1 = LocalDate.of(2017,3,24);
        LocalDate deliveryDate2 = LocalDate.of(2017,3,25);

//        packageRepository.deliverOnDate(deliveryDate1);
        packageRepository.deliverOnDate(deliveryDate2);
    }

}
