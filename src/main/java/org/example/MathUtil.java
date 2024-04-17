package org.example;

public class MathUtil {
    public static final double epsilon = 1e-7;

    @SuppressWarnings({"unused", "SameParameterValue"})
    static double[] solve(double a, double b, double c) {
        if ((Math.abs(a - epsilon) < epsilon)
                || !Double.isFinite(a)
                || !Double.isFinite(b)
                || !Double.isFinite(c)
        ) {
            throw new IllegalArgumentException();
        }

        double discriminant = b*b - 4*a*c;
        if (discriminant < -epsilon) {
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
