package com.arcade.brick;

import java.awt.*;

public class Brick {
    public int x, y, width, height;
    public boolean destroyed = false;

    public Brick(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.width = w; this.height = h;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (destroyed) return;
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
