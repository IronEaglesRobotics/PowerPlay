package org.firstinspires.ftc.teamcode.drive.opmode.util;

public class Color {
    public double h;
    public double s;
    public double v;

    public Color(double[] hsv) {
        this(hsv[0], hsv[1], hsv[2]);
    }

    public Color(double h, double s, double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public double[] get() {
        return new double[]{h, s, v};
    }

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }

    public String toString() {
        return String.format("h: %.1f, s: %.1f, v: %.1f", this.h, this.s, this.v);
    }
}