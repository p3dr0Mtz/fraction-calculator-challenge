package com.onelogin.fractioncalculator.logic;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class FractionCalculator {
    private Operand operandA;
    private Operand operandB;
    private String operator;

    public FractionCalculator(String operation) {
        List<String> operationComponents = getOperationComponents(operation);

        this.operandA = new Operand(operationComponents.get(0)).convertToFraction();
        this.operandB = new Operand(operationComponents.get(2)).convertToFraction();
        this.operator = operationComponents.get(1);
        validateDenominator();
    }

    @SneakyThrows
    private void validateDenominator() {
        if (operandA.getFractionDenominator() == 0 || operandB.getFractionDenominator() == 0)
            throw new Exception("Denominator cannot be 0.");
    }

    @SneakyThrows
    public String calculate() {
        OperationType<Operand, Operand, Operand> operation;
        switch (operator) {
            case "+":
                operation = FractionOperationManager.addition();
                break;
            case "-":
                operation = FractionOperationManager.subtraction();
                break;
            case "*":
                operation = FractionOperationManager.multiplication();
                break;
            case "/":
                if (operandB.getIntComponent() == 0 && operandB.getFractionNumerator() == 0)
                    throw new Exception("Division by 0 not supported");
                operation = FractionOperationManager.division();
                break;
            default:
                throw new Exception(String.format("Operator %s not supported.", operator));
        }

        Operand calculationResult = operation.calculate(operandA, operandB);

        return calculationResult.formattedOperand();
    }

    private List<String> getOperationComponents(String operation) {
        return Arrays.asList(operation.split("\\s+"));
    }
}
