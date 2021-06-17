package com.onelogin.fractioncalculator.logic;

import com.onelogin.fractioncalculator.utils.OperandType;
import com.onelogin.fractioncalculator.utils.ProjectUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class FractionOperationManager {
    private FractionOperationManager() {
    }

    //Only fraction is accepted. Mixed numbers should be converted
    public static OperationType<Operand, Operand, Operand> addition() {
        return (operandA, operandB) -> {
            log.debug("Calculating: {} + {}", operandA.toString(), operandB.toString());
            validateOperandsAreFractions(operandA, operandB);

            Map<String, Integer> commonOperands = commonOperands(operandA, operandB);

            int result = (operandA.getFractionNumerator() * commonOperands.get("multiplierA")) +
                    (operandB.getFractionNumerator() * commonOperands.get("multiplierB"));

            log.debug("* Addition Result = {}/{}", result, commonOperands.get("lcm"));

            return new Operand(result + "/" + commonOperands.get("lcm"));
        };
    }

    public static OperationType<Operand, Operand, Operand> subtraction() {
        return (operandA, operandB) -> {
            log.debug("Calculating: {} - {}", operandA.toString(), operandB.toString());
            validateOperandsAreFractions(operandA, operandB);

            Map<String, Integer> commonOperands = commonOperands(operandA, operandB);

            int result = (operandA.getFractionNumerator() * commonOperands.get("multiplierA")) -
                    (operandB.getFractionNumerator() * commonOperands.get("multiplierB"));

            log.debug("* Addition Result = {}/{}", result, commonOperands.get("lcm"));

            return new Operand(result + "/" + commonOperands.get("lcm"));
        };
    }

    public static OperationType<Operand, Operand, Operand> multiplication() {
        return (operandA, operandB) -> {
            log.debug("Calculating: {} * {}", operandA.toString(), operandB.toString());
            validateOperandsAreFractions(operandA, operandB);

            int numeratorResult = operandA.getFractionNumerator() * operandB.getFractionNumerator();
            int denominatorResult = operandA.getFractionDenominator() * operandB.getFractionDenominator();

            log.debug("* Multiplication Result = {}/{}", numeratorResult, denominatorResult);

            return new Operand(numeratorResult + "/" + denominatorResult);
        };
    }

    public static OperationType<Operand, Operand, Operand> division() {
        return (operandA, operandB) -> {
            log.debug("Calculating: {} / {}", operandA.toString(), operandB.toString());
            validateOperandsAreFractions(operandA, operandB);

            int numeratorResult = operandA.getFractionNumerator() * operandB.getFractionDenominator();
            int denominatorResult = operandA.getFractionDenominator() * operandB.getFractionNumerator();

            log.debug("* Division Result = {}/{}", numeratorResult, denominatorResult);

            return new Operand(numeratorResult + "/" + denominatorResult);
        };
    }

    @SneakyThrows
    private static void validateOperandsAreFractions(Operand operandA, Operand operandB) {
        log.debug("Validating Operands");
        if (operandA.getType() != OperandType.FRACTION || operandB.getType() != OperandType.FRACTION)
            throw new Exception("Operator should be Fraction type. Mixed or Integers not accepted");
    }

    private static Map<String, Integer> commonOperands(Operand operandA, Operand operandB) {
        Map<String, Integer> commonOperands = new HashMap<>();

        int lcm = ProjectUtils.getLeastCommonMultiple(operandA.getFractionDenominator(), operandB.getFractionDenominator());
        int multiplierA = lcm / operandA.getFractionDenominator();
        int multiplierB = lcm / operandB.getFractionDenominator();

        commonOperands.put("lcm", lcm);
        commonOperands.put("multiplierA", multiplierA);
        commonOperands.put("multiplierB", multiplierB);

        log.debug("Common Operands for subtraction and addition : {}", commonOperands);

        return commonOperands;
    }
}
