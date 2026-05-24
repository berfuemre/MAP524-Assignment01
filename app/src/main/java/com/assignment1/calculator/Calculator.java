/*
Berfu Emre - 170459226
MAP 524-Android Assignment 01
May 23, 2026
 */


package com.assignment1.calculator;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<String> calculatorList;

    public Calculator() {
        calculatorList = new ArrayList<>();
    }

    public void push(String value) {
        calculatorList.add(value);
    }

    public int calculate() {
        if (calculatorList.isEmpty()) {
            return 0;
        }

        int result = Integer.parseInt(calculatorList.get(0));

        for (int i = 1; i < calculatorList.size(); i = i + 2) {
            String operator = calculatorList.get(i);
            int nextNumber = Integer.parseInt(calculatorList.get(i + 1));

            if (operator.equals("+")) {
                result = result + nextNumber;
            } else if (operator.equals("-")) {
                result = result - nextNumber;
            } else if (operator.equals("*")) {
                result = result * nextNumber;
            } else if (operator.equals("/")) {
                result = result / nextNumber;
            }
        }

        return result;
    }

    public void clear() {
        calculatorList.clear();
    }
}