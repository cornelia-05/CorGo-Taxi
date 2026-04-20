package com.cortaxi.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class RoutePlan implements IRoutePlanPrototype {
    private String riderName;
    private List<Waypoint> waypoints = new ArrayList<>();
    private double baseFare;
    private double perKm;
    private double surgeMultiplier;
    private String notes;

    public RoutePlan(String riderName,
                     List<Waypoint> waypoints,
                     double baseFare,
                     double perKm,
                     double surgeMultiplier,
                     String notes) {
        this.riderName = riderName;
        if (waypoints != null) {
            this.waypoints.addAll(waypoints);
        }
        this.baseFare = baseFare;
        this.perKm = perKm;
        this.surgeMultiplier = surgeMultiplier;
        this.notes = notes;
    }

    // Shallow copy: shares waypoint list reference
    @Override
    public IRoutePlanPrototype cloneShallow() {
        return new RoutePlan(riderName, waypoints, baseFare, perKm, surgeMultiplier, notes);
    }

    // Deep copy: clones list and each waypoint
    @Override
    public IRoutePlanPrototype cloneDeep() {
        List<Waypoint> copied = new ArrayList<>();
        for (Waypoint wp : waypoints) {
            copied.add(new Waypoint(wp));
        }
        return new RoutePlan(riderName, copied, baseFare, perKm, surgeMultiplier, notes);
    }

    public RoutePlan copyWithAdjustment(double newSurgeMultiplier, String appendedNotes) {
        RoutePlan copy = (RoutePlan) cloneDeep();
        copy.surgeMultiplier = newSurgeMultiplier;
        copy.notes = this.notes + " " + appendedNotes;
        return copy;
    }

    public List<Waypoint> getWaypoints() { return waypoints; }
    public String getRiderName() { return riderName; }
    public double getBaseFare() { return baseFare; }
    public double getPerKm() { return perKm; }
    public double getSurgeMultiplier() { return surgeMultiplier; }
    public String getNotes() { return notes; }

    @Override
    public String toString() {
        return "RoutePlan{" +
                "riderName='" + riderName + '\'' +
                ", waypoints=" + waypoints +
                ", baseFare=" + baseFare +
                ", perKm=" + perKm +
                ", surgeMultiplier=" + surgeMultiplier +
                ", notes='" + notes + '\'' +
                '}';
    }
}
