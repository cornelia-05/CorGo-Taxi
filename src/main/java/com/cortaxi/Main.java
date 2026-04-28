package com.cortaxi;

//import com.cortaxi.patterns.creational.factorymethod.*;
//import com.cortaxi.patterns.structural.composite.*;
import com.cortaxi.patterns.behavioral.iterator.DriverList;
import com.cortaxi.patterns.behavioral.iterator.IDriverCollection;
import com.cortaxi.patterns.behavioral.iterator.Iterator;
import com.cortaxi.patterns.structural.flyweight.*;
import com.cortaxi.patterns.structural.decorator.*;
import com.cortaxi.patterns.structural.bridge.*;
import com.cortaxi.patterns.structural.proxy.*;
import com.cortaxi.patterns.structural.bridge.IVehicleB;
import com.cortaxi.patterns.structural.bridge.RideControl;
import com.cortaxi.patterns.structural.bridge.StandartTaxiB;
import com.cortaxi.patterns.structural.decorator.*;
import com.cortaxi.patterns.structural.flyweight.CarTypeFactory;
import com.cortaxi.patterns.structural.flyweight.CarTypeFlyweight;
import com.cortaxi.patterns.structural.flyweight.TaxiContext;
import com.cortaxi.patterns.structural.proxy.CachedTaxiService;
import com.cortaxi.patterns.structural.proxy.ITaxiService;
import com.cortaxi.patterns.structural.proxy.RealTaxiService;
import com.cortaxi.patterns.structural.proxy.TaxiApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        IDriverCollection collection = new DriverList();

        Iterator<String> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            String driver = iterator.next();
            System.out.println("Available driver: " + driver);
        }

        // reset + re-iterate
        iterator.reset();

        System.out.println("After reset:");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}