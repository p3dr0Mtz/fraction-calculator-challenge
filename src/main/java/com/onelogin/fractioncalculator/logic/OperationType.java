package com.onelogin.fractioncalculator.logic;

public interface OperationType<T, V, P> {
    T calculate(V v, P p);
}
