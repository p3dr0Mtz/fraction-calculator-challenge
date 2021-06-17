package com.onelogin.fractioncalculator;


import com.onelogin.fractioncalculator.logic.FractionCalculator;
import com.onelogin.fractioncalculator.utils.ProjectUtils;

import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(programIntro());

        do {
            System.out.print("Operation : ");
            String s = in.nextLine();

            if (s.trim().toLowerCase().equals("exit"))
                break;

            try {
                doOperation(s);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            System.out.println();


        } while (true);
    }

    private static void doOperation(String s) {
        if (!ProjectUtils.validateOperationFormat(s.trim()))
            System.out.println("Operation is in wrong format.");
        else {
            FractionCalculator fractionCalculator = new FractionCalculator(s.trim());
            System.out.println("Result : " + fractionCalculator.calculate());
        }
    }

    private static String programIntro() {
        return "Type your operation following the below rules: \n\n" +
                "Legal operators : *, /, +, -\n" +
                "Operands format is whole_numerator/denominator. e.g. \"3_1/4\"\n" +
                "Operands and operators shall be separated by one or more spaces. \n" +
                "Improper fractions and whole numbers are accepted. \n" +
                "Only one Operator per Operation. \n" +
                "Example: \"3_9/4 * 5/4\" \n";
    }
}
