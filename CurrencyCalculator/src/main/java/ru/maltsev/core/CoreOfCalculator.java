package ru.maltsev.core;


public class CoreOfCalculator {

    private static final double RATE_OF_CONVERSION = 64.59;

    public static double transferRubTOUsd(double rub){
        return rub / RATE_OF_CONVERSION;
    }

    public static double transferUsdToRub(double usd){
        return usd * RATE_OF_CONVERSION;
    }

}
