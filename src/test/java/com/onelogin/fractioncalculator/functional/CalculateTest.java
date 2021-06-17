package com.onelogin.fractioncalculator.functional;

import com.onelogin.fractioncalculator.logic.FractionCalculator;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class CalculateTest {
    @Test(dataProvider = "operations", dataProviderClass = DataProviderClass.class)
    public void calculateResults(String operation, String expectedResult) {

        log.debug("Calculating \"{}\". Expected : {}", operation, expectedResult);

        FractionCalculator fractionCalculator = new FractionCalculator(operation.trim());
        String result = fractionCalculator.calculate();

        Assert.assertEquals(result, expectedResult, "Calculation Error, expected result do not match.");
    }

    @Test(dataProvider = "nonValidOperations", dataProviderClass = DataProviderClass.class)
    public void nonValidOperations(String operation) {
        log.debug("Validating Operation: \"{}\".", operation);

        boolean exceptionReturned = false;
        try {
            FractionCalculator fractionCalculator = new FractionCalculator(operation.trim());
            fractionCalculator.calculate();
        } catch (Exception e) {
            exceptionReturned = true;
        }

        Assert.assertTrue(exceptionReturned);
    }
}
