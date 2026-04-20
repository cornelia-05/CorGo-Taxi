package com.cortaxi.patterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class CachedTaxiService implements ITaxiService {
    private final ITaxiService service;
    private final Map<String, String> driverCache = new HashMap<>();
    private boolean needReset;

    public CachedTaxiService(ITaxiService service) {
        this.service = Objects.requireNonNull(service);
    }

    public void resetCache() {
        driverCache.clear();
        needReset = false;
    }

    public void markNeedReset() {
        needReset = true;
    }

    @Override
    public void requestRide(String location) {
        service.requestRide(location);
    }

    @Override
    public String getDriverInfo(String id) {
        if (needReset) driverCache.clear();
        return driverCache.computeIfAbsent(id, service::getDriverInfo);
    }
}