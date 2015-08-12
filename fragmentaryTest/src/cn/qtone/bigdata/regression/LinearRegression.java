package cn.qtone.bigdata.regression;

import java.util.Random;

public class LinearRegression {

    public static void main(String[] args) {
        double alpha = 0.01;
        int m = 5;
        int n = 2;
        double[][] x = initX(m, n);
        double[] y = initY(m);
        double[] theta = initTheta(n);
        int times = 10000;
        double beta = 1e-10;
//		theta = sgd(x, theta, y, alpha, times);
//		theta = bgd(x, theta, y, alpha, times, beta);
        theta = bfgs(x, theta, y, times, beta);
        System.out.println(theta[0] + "," + theta[1]);
    }

    private static double[] sgd(double[][] x, double[] theta, double[] y, double alpha, int times) {
        int i = 0;
        for (i = 0; i < times; i++) {
            for (int j = 0; j < x.length; j++) {
                double a = y[j] - h_theta(theta, x[j]);
                for (int k = 0; k < theta.length; k++) {
                    theta[k] += alpha * a * x[j][k];
                }
            }
        }
//		System.out.println(i);
        return theta;
    }

    private static double[] bgd(double[][] x, double[] theta, double[] y, double alpha, int times, double beta) {
        int i = 0;
        for (i = 0; i < times; i++) {
            double[] a = new double[x.length];
            double[] theta_new = new double[theta.length];
            for (int j = 0; j < x.length; j++) {
                a[j] = y[j] - h_theta(theta, x[j]);
            }
            for (int k = 0; k < theta.length; k++) {
                double sum = 0d;
                for (int j = 0; j < a.length; j++) {
                    sum += a[j] * x[j][k];
                }
                theta_new[k] = theta[k] + alpha * sum;
            }
            if (canBreak(theta, theta_new, beta)) {
                theta = theta_new;
                break;
            } else {
                theta = theta_new;
            }
        }
//		System.out.println(i);
        return theta;
    }

    private static double[] bfgs(double[][] x, double[] theta, double[] y, int times, double beta) {
        double[][] b = unitMatrix(theta.length);
        int i = 0;
        for (i = 0; i < times; i++) {
            double[] theta_new = vectorMinus(theta, matrixTimesVector(b, getGradientVector(theta, x, y)));

            if (canBreak(theta, theta_new, beta)) {
                theta = theta_new;
                break;
            }

            double[] s = vectorMinus(theta_new, theta);
            double[] t = vectorMinus(getGradientVector(theta_new, x, y), getGradientVector(theta, x, y));
            double rho = vectorDotTimes(t, s);
            double[][] v = matrixMinus(unitMatrix(theta.length), matrixDivideNumber(vectorOuterProduct(t, s), rho));
            double[][] p = matrixDivideNumber(vectorOuterProduct(s, s), rho);
            b = matrixPlus(matrixTimesMatrix(matrixTimesMatrix(transpose(v), b), transpose(v)), p);

            theta = theta_new;
        }
        System.out.println(i);
        return theta;
    }

    private static double[][] initX(int m, int n) {
        double[][] x = new double[m][n];
        x[0][0] = 1d;
        x[0][1] = 1d;
        x[1][0] = 1d;
        x[1][1] = 2d;
        x[2][0] = 1d;
        x[2][1] = 3d;
        x[3][0] = 1d;
        x[3][1] = 4d;
        x[4][0] = 1d;
        x[4][1] = 5d;
        return x;
    }

    private static double[] initY(int m) {
        double[] y = new double[m];
        y[0] = 1d;
        y[1] = 2d;
        y[2] = 3d;
        y[3] = 4d;
        y[4] = 5d;
        return y;
    }

    private static double[] initTheta(int n) {
        double[] theta = new double[n];
        Random r = new Random();
        for (int i = 0; i < theta.length; i++) {
            theta[i] = r.nextGaussian();
        }
        return theta;
    }

    private static double h_theta(double[] theta, double[] xi) {
        return vectorDotTimes(theta, xi);
    }

    private static double vectorDotTimes(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("vectors dimention is not the same!");
        }
        double sum = 0d;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    private static double[] vectorMinus(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("vectors dimention is not the same!");
        }
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    private static double[][] matrixMinus(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new RuntimeException("vectors dimention is not the same!");
        }
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    private static boolean canBreak(double[] t, double[] t_n, double beta) {
        double sum = 0d;
        for (int i = 0; i < t.length; i++) {
            sum += (t[i] - t_n[i]) * (t[i] - t_n[i]);
        }
        if (Math.sqrt(sum) < beta) {
            return true;
        }
        return false;
    }

    private static double[][] unitMatrix(int n) {
        double[][] b = new double[n][n];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (i == j) {
                    b[i][j] = 1;
                }
            }
        }
        return b;
    }

    private static double[][] vectorOuterProduct(double[] a, double[] b) {
        double[][] result = new double[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = a[i] * b[j];
            }
        }
        return result;
    }

    private static double[] matrixTimesVector(double[][] m, double[] v) {
        double[] result = new double[m.length];
        for (int i = 0; i < m.length; i++) {
            result[i] = vectorDotTimes(m[i], v);
        }
        return result;
    }

    private static double[][] matrixDivideNumber(double[][] m, double num) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                result[i][j] = m[i][j] / num;
            }
        }
        return result;
    }

    private static double[][] matrixTimesMatrix(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new RuntimeException("matrixs can't be timesed!");
        }
        double[][] _b = transpose(b);
        double[][] result = new double[a.length][_b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < _b.length; j++) {
                result[i][j] = vectorDotTimes(a[i], b[j]);
            }
        }
        return result;
    }

    private static double[][] transpose(double[][] m) {
        double[][] w = new double[m[1].length][m.length];
        for (int i = 0; i < m[1].length; i++) {
            double[] a = getCol(m, i);
            w[i] = a;
        }
        return w;
    }

    private static double[] getCol(double[][] m, int col) {
        double[] colArr = new double[m.length];
        for (int i = 0; i < m.length; i++) {
            colArr[i] = m[i][col];
        }
        return colArr;
    }

    private static double[][] matrixPlus(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new RuntimeException("matrix dimention is not the same!");
        }
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private static double[] getGradientVector(double[] theta, double[][] x, double[] y) {
        double[] gradientVector = new double[theta.length];
        double[] a = new double[x.length];
        for (int j = 0; j < x.length; j++) {
            a[j] = h_theta(theta, x[j]) - y[j];
        }
        for (int k = 0; k < gradientVector.length; k++) {
            double sum = 0d;
            for (int j = 0; j < a.length; j++) {
                sum += a[j] * x[j][k];
            }
            gradientVector[k] = sum;
        }
        return gradientVector;
    }

}
