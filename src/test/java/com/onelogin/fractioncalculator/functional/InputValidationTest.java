package com.onelogin.fractioncalculator.functional;


import com.onelogin.fractioncalculator.utils.ProjectUtils;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class InputValidationTest {
    @Test(dataProvider = "positiveInputs", dataProviderClass = DataProviderClass.class)
    public void validInputs(String operation) {
        log.debug("Validate input: {}", operation);
        boolean isValid = ProjectUtils.validateOperationFormat(operation.trim());

        Assert.assertTrue(isValid);
    }

    @Test(dataProvider = "negativeInputs", dataProviderClass = DataProviderClass.class)
    public void invalidInputs(String operation) {
        log.debug("Validate input: {}", operation);
        boolean isValid = ProjectUtils.validateOperationFormat(operation.trim());

        Assert.assertFalse(isValid);
    }
}
