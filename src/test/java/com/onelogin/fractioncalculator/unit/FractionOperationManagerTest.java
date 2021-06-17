package com.onelogin.fractioncalculator.unit;


import com.onelogin.fractioncalculator.logic.FractionOperationManager;
import com.onelogin.fractioncalculator.logic.Operand;
import com.onelogin.fractioncalculator.utils.OperandType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FractionOperationManagerTest {
    @Test
    public void validateExceptionA() {
        Operand operandA = new Operand("6_4/5");
        Operand operandB = new Operand("5/10");
        boolean exception = false;

        try {
            FractionOperationManager.addition().calculate(operandA, operandB);
        } catch (Exception e) {
            exception = true;
        }

        Assert.assertTrue(exception);
    }

    @Test
    public void validateExceptionB() {
        Operand operandA = new Operand("4/5");
        Operand operandB = new Operand("10_5/10");
        boolean exception = false;

        try {
            FractionOperationManager.addition().calculate(operandA, operandB);
        } catch (Exception e) {
            exception = true;
        }

        Assert.assertTrue(exception);
    }

    @Test
    public void validateAddition() {
        Operand operandA = new Operand("5/10");
        Operand operandB = new Operand("5/11");

        Operand result = FractionOperationManager.addition().calculate(operandA, operandB);
        Assert.assertEquals(result.getIntComponent(), 0);
        Assert.assertEquals(result.getFractionNumerator(), 105);
        Assert.assertEquals(result.getFractionDenominator(), 110);
        Assert.assertEquals(result.getType(), OperandType.FRACTION);
    }

    @Test
    public void validateSubtraction() {
        Operand operandA = new Operand("5/10");
        Operand operandB = new Operand("10/20");

        Operand result = FractionOperationManager.subtraction().calculate(operandA, operandB);
        Assert.assertEquals(result.getIntComponent(), 0);
        Assert.assertEquals(result.getFractionNumerator(), 0);
        Assert.assertEquals(result.getFractionDenominator(), 20);
        Assert.assertEquals(result.getType(), OperandType.FRACTION);
    }

    @Test
    public void validateSubtractionB() {
        Operand operandA = new Operand("5/10");
        Operand operandB = new Operand("40/20");

        Operand result = FractionOperationManager.subtraction().calculate(operandA, operandB);
        Assert.assertEquals(result.getIntComponent(), 0);
        Assert.assertEquals(result.getFractionNumerator(), -30);
        Assert.assertEquals(result.getFractionDenominator(), 20);
        Assert.assertEquals(result.getType(), OperandType.FRACTION);
    }

    @Test
    public void validateMultiplication() {
        Operand operandA = new Operand("4/99");
        Operand operandB = new Operand("5/10");

        Operand result = FractionOperationManager.multiplication().calculate(operandA, operandB);
        Assert.assertEquals(result.getIntComponent(), 0);
        Assert.assertEquals(result.getFractionNumerator(), 20);
        Assert.assertEquals(result.getFractionDenominator(), 990);
        Assert.assertEquals(result.getType(), OperandType.FRACTION);
    }

    @Test
    public void validateDivision() {
        Operand operandA = new Operand("4/99");
        Operand operandB = new Operand("5/10");

        Operand result = FractionOperationManager.division().calculate(operandA, operandB);
        Assert.assertEquals(result.getIntComponent(), 0);
        Assert.assertEquals(result.getFractionNumerator(), 40);
        Assert.assertEquals(result.getFractionDenominator(), 495);
        Assert.assertEquals(result.getType(), OperandType.FRACTION);
    }
}
