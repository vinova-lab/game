package com.arcade.brick;

import java.awt.*;

public class Ball {
    public double x, y;
    public int diameter;
    public double vx = 3, vy = -3;

    public Ball(double x, double y, int d) {
        this.x = x; this.y = y; this.diameter = d;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, diameter, diameter);
    }
}
