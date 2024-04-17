package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquationSolver {
    public static void solveQuadratic() {

        Scanner paramsInput = new Scanner(System.in);
        System.out.println("Input square coefficients: ");


        List<Double> coefficients = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String input = paramsInput.nextLine();
            coefficients.add(Double.parseDouble(input));
        }

        System.out.println("Input square coefficients: " + coefficients);
    }


}