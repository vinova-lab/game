package com.arcade.brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private boolean running = true;
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        paddle = new Paddle(250, 450, 100, 10);
        ball = new Ball(300, 300, 10);
        bricks = new ArrayList<>();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 10; c++) {
                bricks.add(new Brick(c * 60 + 5, r * 25 + 50, 50, 20));
            }
        }
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            paddle.draw(g);
            ball.draw(g);
            for (Brick b : bricks) b.draw(g);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over", 200, 250);
        }
    }

    private void update() {
        paddle.update(600);
        ball.update();

        if (ball.x <= 0 || ball.x + ball.diameter >= 600) ball.vx *= -1;
        if (ball.y <= 0) ball.vy *= -1;
        if (ball.y >= 500) { running = false; timer.stop(); }

        if (new Rectangle((int)ball.x, (int)ball.y, ball.diameter, ball.diameter)
                .intersects(new Rectangle(paddle.x, paddle.y, paddle.width, paddle.height))) {
            ball.vy = -Math.abs(ball.vy);
        }

        for (Brick b : bricks) {
            if (!b.destroyed && b.getRect().intersects(new Rectangle((int)ball.x, (int)ball.y, ball.diameter, ball.diameter))) {
                b.destroyed = true;
                score += 10;
                ball.vy *= -1;
            }
        }
        bricks.removeIf(b -> b.destroyed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) paddle.left = true;
        if (k == KeyEvent.VK_RIGHT) paddle.right = true;
        if (k == KeyEvent.VK_R && !running) initGame();
    }

    @Override public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) paddle.left = false;
        if (k == KeyEvent.VK_RIGHT) paddle.right = false;
    }
    @Override public void keyTyped(KeyEvent e) {}
}
