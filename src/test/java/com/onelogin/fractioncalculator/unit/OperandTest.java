package com.onelogin.fractioncalculator.unit;


import com.onelogin.fractioncalculator.logic.Operand;
import com.onelogin.fractioncalculator.utils.OperandType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OperandTest {
    @Test
    public void checkMixedOperand() {
        Operand operand = new Operand("3_0/99902");

        Assert.assertEquals(operand.getIntComponent(), 3);
        Assert.assertEquals(operand.getFractionNumerator(), 0);
        Assert.assertEquals(operand.getFractionDenominator(), 99902);
        Assert.assertEquals(operand.getType(), OperandType.MIXED);
    }

    @Test
    public void checkFractionOperand() {
        Operand operand = new Operand("10/99918");

        Assert.assertEquals(operand.getIntComponent(), 0);
        Assert.assertEquals(operand.getFractionNumerator(), 10);
        Assert.assertEquals(operand.getFractionDenominator(), 99918);
        Assert.assertEquals(operand.getType(), OperandType.FRACTION);
    }

    @Test
    public void checkIntegerOperand() {
        Operand operand = new Operand("99912");

        Assert.assertEquals(operand.getIntComponent(), 99912);
        Assert.assertEquals(operand.getFractionNumerator(), 0);
        Assert.assertEquals(operand.getFractionDenominator(), 0);
        Assert.assertEquals(operand.getType(), OperandType.INTEGER);

    }

    @Test
    public void checkMixedToFraction() {
        Operand original = new Operand("99_10/9");
        Operand operand = original.convertToFraction();

        Assert.assertEquals(operand.getIntComponent(), 0);
        Assert.assertEquals(operand.getFractionNumerator(), 901);
        Assert.assertEquals(operand.getFractionDenominator(), 9);
        Assert.assertEquals(operand.getType(), OperandType.FRACTION);
    }

    @Test
    public void checkFractionToFraction() {
        Operand original = new Operand("10/99918");
        Operand operand = original.convertToFraction();

        Assert.assertEquals(operand.getIntComponent(), 0);
        Assert.assertEquals(operand.getFractionNumerator(), 10);
        Assert.assertEquals(operand.getFractionDenominator(), 99918);
        Assert.assertEquals(operand.getType(), OperandType.FRACTION);
    }

    @Test
    public void checkIntegerToFraction() {
        Operand original = new Operand("999");
        Operand operand = original.convertToFraction();

        Assert.assertEquals(operand.getIntComponent(), 0);
        Assert.assertEquals(operand.getFractionNumerator(), 999);
        Assert.assertEquals(operand.getFractionDenominator(), 1);
        Assert.assertEquals(operand.getType(), OperandType.FRACTION);
    }

    @Test
    public void checkToString() {
        Operand operand = new Operand("999/0");

        Assert.assertEquals(operand.toString(), "0_999/0");
    }


    @Test
    public void checkFormattedMixed() {
        Operand operand = new Operand("99_62/3");
        Assert.assertEquals(operand.formattedOperand(), "119_2/3");
    }

    @Test
    public void checkFormattedFractionA() {
        Operand operand = new Operand("5/65");
        Assert.assertEquals(operand.formattedOperand(), "1/13");
    }

    @Test
    public void checkFormattedFractionB() {
        Operand operand = new Operand("65/5");
        Assert.assertEquals(operand.formattedOperand(), "13");
    }

    @Test
    public void checkFormattedFractionC() {
        Operand operand = new Operand("-65/4");
        Assert.assertEquals(operand.formattedOperand(), "-16_1/4");
    }

    @Test
    public void checkFormattedInteger() {
        Operand operand = new Operand("999");
        Assert.assertEquals(operand.formattedOperand(), "999");
    }

}
