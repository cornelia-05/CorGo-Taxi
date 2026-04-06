package com.cortaxi;

import com.cortaxi.domain.*;
import com.cortaxi.patterns.creational.factorymethod.*;
import com.cortaxi.patterns.creational.builder.*;
import com.cortaxi.patterns.creational.prototype.*;
import com.cortaxi.patterns.creational.singleton.*;
import com.cortaxi.patterns.structural.adapter.*;
//import com.cortaxi.patterns.structural.composite.*;
import com.cortaxi.patterns.structural.facade.*;
import com.cortaxi.patterns.structural.facade.services.*;
import com.cortaxi.patterns.structural.flyweight.*;
import com.cortaxi.patterns.structural.decorator.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static void main(String[] ignored) {
        CarTypeFactory factory = new CarTypeFactory();
        Random rnd = new Random(42);

        // Intrinsic "templates" (puține)
        String[][] carTypes = new String[][] {
                {"Toyota", "Prius", "White", "Hybrid"},
                {"Toyota", "Prius", "Black", "Hybrid"},
                {"Dacia", "Logan", "White", "Gasoline"},
                {"Skoda", "Octavia", "Gray", "Diesel"}
        };

        // Multe taxi-uri (contexts) care refolosesc puține flyweights
        int taxiCount = 50_000;
        List<TaxiContext> taxis = new ArrayList<>(taxiCount);

        for (int i = 0; i < taxiCount; i++) {
            int t = rnd.nextInt(carTypes.length);
            CarTypeFlyweight flyweight = factory.getOrCreate(
                    carTypes[t][0], carTypes[t][1], carTypes[t][2], carTypes[t][3]
            );

            String plate = "B-" + (10000 + i);
            String driver = "driver-" + (i % 2000);

            double x = rnd.nextDouble() * 100;
            double y = rnd.nextDouble() * 100;
            String status = (i % 10 == 0) ? "ON_TRIP" : "AVAILABLE";

            taxis.add(new TaxiContext(plate, driver, x, y, status, flyweight));
        }

        System.out.println("Created contexts (taxis): " + taxis.size());
        System.out.println("Flyweight pool size (car types): " + factory.poolSize());

        // Exemplu: afișăm câteva
        for (int i = 0; i < 3; i++) {
            System.out.println(taxis.get(i).render());
        }



        IFareComponent a = new BaseFare(12.0, 3.2, 8.0);              // ConcreteComponent
        IFareComponent b = new NightTariffDecorator(a, 1.2);          // Decorator 1
        IFareComponent c = new SurgeDecorator(b, 1.5);                // Decorator 2
        IFareComponent d = new DiscountDecorator(c, 5.0);             // Decorator 3 (opțional)

        System.out.println("Base: " + a.execute());
        System.out.println("Night: " + b.execute());
        System.out.println("Night+Surge: " + c.execute());
        System.out.println("Night+Surge-Discount: " + d.execute());

    }


}