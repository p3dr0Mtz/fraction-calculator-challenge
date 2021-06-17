package com.onelogin.fractioncalculator.utils;

import lombok.SneakyThrows;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectUtils {
    private static final String OPERATION_REGEX = "^((\\d+_\\d+\\/\\d+)|(\\d+)|(\\d+\\/\\d+))\\s+[*/+-]\\s+((\\d+_\\d+\\/\\d+)|(\\d+)|(\\d+\\/\\d+))$";

    private ProjectUtils() {
    }

    public static boolean validateOperationFormat(String operation) {
        Pattern patterCompile = Pattern.compile(OPERATION_REGEX);
        Matcher matcher = patterCompile.matcher(operation);

        return matcher.find();
    }

    public static int getLeastCommonMultiple(int numberA, int numberB) {
        gcdAndlcmValidateNumbers(numberA, numberB);

        return (numberA * numberB) / getGreatestCommonDivisor(numberA, numberB);
    }

    public static int getGreatestCommonDivisor(int numberA, int numberB) {
        gcdAndlcmValidateNumbers(numberA, numberB);

        return BigInteger.valueOf(numberA)
                .gcd(BigInteger.valueOf(numberB))
                .intValue();
    }

    @SneakyThrows
    public static void gcdAndlcmValidateNumbers(int numberA, int numberB) {
        if (numberA <= 0 || numberB <= 0)
            throw new Exception("Error calculating GCD. Denominator cannot be negative or equals to 0.");
    }
}
