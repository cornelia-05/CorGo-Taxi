package com.cortaxi.patterns.creational.prototype;

public interface IRoutePlanPrototype {
    IRoutePlanPrototype cloneShallow();
    IRoutePlanPrototype cloneDeep();
}