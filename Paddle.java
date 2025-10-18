package com.arcade.brick;

import java.awt.*;

public class Paddle {
    public int x, y, width, height;
    public boolean left = false, right = false;
    private int speed = 6;

    public Paddle(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public void update(int screenWidth) {
        if (left) x -= speed;
        if (right) x += speed;
        if (x < 0) x = 0;
        if (x + width > screenWidth) x = screenWidth - width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
}
