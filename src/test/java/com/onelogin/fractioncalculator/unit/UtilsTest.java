package com.onelogin.fractioncalculator.unit;

import com.onelogin.fractioncalculator.utils.ProjectUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilsTest {
    @Test
    public void validateOperationFormat() {
        boolean validator = ProjectUtils.validateOperationFormat("3_1/9 + 8_9/5888");
        Assert.assertTrue(validator);
    }

    @Test
    public void validateNegativeOperationFormat() {
        boolean validator = ProjectUtils.validateOperationFormat("3_1 + 8_9/5888");
        Assert.assertFalse(validator);
    }

    @Test
    public void testGCD() {
        int greatestCommonDivisor = ProjectUtils.getGreatestCommonDivisor(15, 5);
        Assert.assertEquals(5, greatestCommonDivisor);
    }

    @Test
    public void testLCM() {
        int greatestCommonDivisor = ProjectUtils.getLeastCommonMultiple(18, 10);
        Assert.assertEquals(90, greatestCommonDivisor);
    }

    @Test
    public void checkValidateNumbersExceptionA() {
        boolean exception = false;
        try {
            ProjectUtils.gcdAndlcmValidateNumbers(0, 5);
        } catch (Exception e) {
            exception = true;
        }

        Assert.assertTrue(exception);
    }

    @Test
    public void checkValidateNumbersExceptionB() {
        boolean exception = false;
        try {
            ProjectUtils.gcdAndlcmValidateNumbers(-7, 0);
        } catch (Exception e) {
            exception = true;
        }

        Assert.assertTrue(exception);
    }
}
