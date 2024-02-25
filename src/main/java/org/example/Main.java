package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final double epsilon = 1e-7;

    public static void main(String[] args) {

        Scanner paramsInput = new Scanner(System.in);
        System.out.println("Input square coefficients: ");


        List<Double> coefficients = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String input = paramsInput.nextLine();
            coefficients.add(Double.parseDouble(input));
        }

        System.out.println("Input square coefficients: " + coefficients);
    }

    static double[] solve(double a, double b, double c) {
        if ((Math.abs(a - epsilon) < epsilon)
                || !Double.isFinite(a)
                || !Double.isFinite(b)
                || !Double.isFinite(c)
        ) {
            throw new IllegalArgumentException();
        }

        double discriminant = b*b - 4*a*c;
        if (discriminant < 0) {
            return new double[]{};
        }
        if (discriminant < epsilon) {
            double root = getRoot(a, b, null);
            return new double[]{root, root};
        } else {
            double sqrt = Math.sqrt(discriminant);
            return new double[]{getRoot(a, b, sqrt), getRoot(a, b, -sqrt)};

        }
    }

    private static double getRoot(double a, double b, Double d) {
        return d == null ? -b / (2*a) : (-b + d) / (2*a);
    }
}