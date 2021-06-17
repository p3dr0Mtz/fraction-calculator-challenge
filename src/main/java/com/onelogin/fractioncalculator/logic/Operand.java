package com.onelogin.fractioncalculator.logic;


import com.onelogin.fractioncalculator.utils.OperandType;
import com.onelogin.fractioncalculator.utils.ProjectUtils;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class Operand {
    @Getter
    private int intComponent = 0;
    // determine the numerator and denominator of the fraction: numerator-> 1/4 <-denominator
    @Getter
    private int fractionNumerator = 0;
    @Getter
    private int fractionDenominator = 0;
    @Getter
    private OperandType type;
    @Getter
    private String originOperand;

    public Operand(String operand) {
        log.debug("Splitting up elements of operand : {}", operand);
        this.originOperand = operand;
        setOperandComponents(operand);
    }

    private void setOperandComponents(String operand) {
        if (operand.contains("_")) {
            log.debug("Type: Mixed.");
            type = OperandType.MIXED;
            List<String> operandComponents = Arrays.asList(operand.split("_"));
            setOperandInteger(operandComponents.get(0));
            setFractionComponents(operandComponents.get(1));
        } else if (operand.contains("/")) {
            log.debug("Type: Fraction.");
            type = OperandType.FRACTION;
            setFractionComponents(operand);
        } else {
            log.debug("Type: Integer.");
            type = OperandType.INTEGER;
            setOperandInteger(operand);
        }

        log.debug("Operand separated into -  Integer: {}, Numerator : {}, Denominator : {}",
                this.intComponent, this.fractionNumerator, this.fractionDenominator);
    }

    private void setFractionComponents(String operand) {
        List<String> fractionComponents = Arrays.asList(operand.split("/"));
        this.fractionNumerator = Integer.parseInt(fractionComponents.get(0));
        this.fractionDenominator = Integer.parseInt(fractionComponents.get(1));
    }

    private void setOperandInteger(String operand) {
        this.intComponent = Integer.parseInt(operand);
    }

    public Operand convertToFraction() {
        log.debug("Converting Operand to fraction.");
        if (type == OperandType.MIXED) {
            Operand intToFraction = convertIntComponentToFraction();
            Operand fraction = new Operand(fractionNumerator + "/" + fractionDenominator);

            return FractionOperationManager.addition().calculate(intToFraction, fraction);
        }

        if (type == OperandType.INTEGER)
            return convertIntComponentToFraction();

        return this;
    }

    private Operand convertIntComponentToFraction() {
        if (type == OperandType.MIXED) {
            log.debug("Converted MIXED to {}.", ((intComponent * fractionDenominator) + "/" + fractionDenominator));
            return new Operand((intComponent * fractionDenominator) + "/" + fractionDenominator);
        }

        if (type == OperandType.INTEGER) {
            log.debug("Converted INTEGER to {}.", (intComponent + "/" + 1));
            return new Operand(intComponent + "/" + 1);
        }

        log.debug("Nothing to convert. Already Fraction.");

        return this;
    }

    public String toString() {
        return (intComponent + "_" + fractionNumerator + "/" + fractionDenominator);
    }

    public String formattedOperand() {
        if (type == OperandType.INTEGER)
            return Integer.toString(intComponent);

        int formattedInt = Math.abs(fractionNumerator / fractionDenominator);
        int formattedNum = 0;
        int formattedDen = 0;
        int gdc = 1;

        if (fractionNumerator % fractionDenominator != 0) {
            formattedNum = Math.abs(fractionNumerator) - (formattedInt * fractionDenominator);
            gdc = ProjectUtils.getGreatestCommonDivisor(formattedNum, fractionDenominator);
        }

        formattedInt = formattedInt + Math.abs(intComponent);
        formattedNum = formattedNum / gdc;
        formattedDen = fractionDenominator / gdc;

        return (intComponent < 0 || fractionNumerator < 0 ? "-" : "") +
                (formattedInt == 0 && formattedNum == 0 ? "0" : "") +
                (formattedInt == 0 ? "" : formattedInt) +
                (formattedNum == 0 ? "" : (formattedInt == 0 ? "" : "_") + formattedNum + "/" + formattedDen);
    }
}
