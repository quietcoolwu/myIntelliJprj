package cn.qtone.bigdata.regression;

import org.ujmp.core.Matrix;

import java.util.Random;

public class Regression {
    public static void main(String[] args) {
        double alpha = 0.01;
        int m = 5;
        int n = 2;
        Matrix x = initX(m, n);
        Matrix y = initY(m);
        Matrix theta = initTheta(n);
        int times = 1000;
        double beta = 1e-10;
        theta = bfgs(x, theta, y, times, beta);
//        theta = bgd(x, theta, y, alpha, times, beta);
        theta.showGUI();
    }

    private static Matrix bgd(Matrix x, Matrix theta, Matrix y, double alpha, int times, double beta) {
        int i = 0;
        for (i = 0; i < times; i++) {
            Matrix gradient = x.transpose().mtimes(x.mtimes(theta).minus(y));
            Matrix theta_new = theta.minus(gradient.times(alpha));
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

    private static Matrix bfgs(Matrix x, Matrix theta, Matrix y, int times, double beta) {
        long n = theta.getRowCount();
        Matrix hession = initHession(n);
        int i = 0;
        for (i = 0; i < times; i++) {
            Matrix gradient = x.transpose().mtimes(x.mtimes(theta).minus(y));
            Matrix theta_new = theta.minus(hession.mtimes(gradient));
            Matrix gradient_new = x.transpose().mtimes(x.mtimes(theta_new).minus(y));

            if (canBreak(theta, theta_new, beta)) {
                theta = theta_new;
                break;
            }

            Matrix s = theta_new.minus(theta);
            Matrix t = gradient_new.minus(gradient);

            double rho = t.transpose().mtimes(s).getAsDouble(0, 0);
            Matrix v = Matrix.factory.eye(n, n).minus(t.mtimes(s.transpose()).divide(rho));
            Matrix p = s.mtimes(s.transpose()).divide(rho);
            hession = v.transpose().mtimes(hession).mtimes(v).plus(p);

            theta = theta_new;
        }
//		System.out.println(i);
        return theta;
    }

    private static boolean canBreak(Matrix theta, Matrix theta_new, double beta) {
        double[][] t = theta.toDoubleArray();
        double[][] t_n = theta_new.toDoubleArray();
        double sum = 0d;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                sum += (t[i][j] - t_n[i][j]) * (t[i][j] - t_n[i][j]);
            }
        }
        if (Math.sqrt(sum) < beta) {
            return true;
        }
        return false;
    }

    private static Matrix initHession(long n) {
        return Matrix.factory.eye(n, n);
    }

    private static Matrix initX(int m, int n) {
        Matrix x = Matrix.factory.zeros(m, n);
        x.setAsDouble(1, 0, 0);
        x.setAsDouble(1, 0, 1);
        x.setAsDouble(1, 1, 0);
        x.setAsDouble(2, 1, 1);
        x.setAsDouble(1, 2, 0);
        x.setAsDouble(3, 2, 1);
        x.setAsDouble(1, 3, 0);
        x.setAsDouble(4, 3, 1);
        x.setAsDouble(1, 4, 0);
        x.setAsDouble(5, 4, 1);
        return x;
    }

    private static Matrix initY(int m) {
        Matrix y = Matrix.factory.zeros(m, 1);
        y.setAsDouble(1, 0, 0);
        y.setAsDouble(2, 1, 0);
        y.setAsDouble(3, 2, 0);
        y.setAsDouble(4, 3, 0);
        y.setAsDouble(5, 4, 0);
        return y;
    }

    private static Matrix initTheta(int n) {
        Random r = new Random();
        Matrix theta = Matrix.factory.zeros(n, 1);
        theta.setAsDouble(r.nextGaussian(), 0, 0);
        theta.setAsDouble(r.nextGaussian(), 1, 0);
        return theta;
    }
}
