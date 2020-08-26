package code.bro;

import java.awt.*;
import java.awt.event.KeyEvent;

import static code.bro.GamePanel.GAME_HEIGHT;
import static code.bro.GamePanel.PADDLE_HEIGHT;

public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;

    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    moveWithSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    moveWithSpeed(speed);
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveWithSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveWithSpeed(speed);
                }
            }
        }
    }

    private void moveWithSpeed(int speed) {
        setYDirection(speed);
        move();
    }

    public void keyReleased(KeyEvent e) {
        setYDirection(0);
        move();
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y = y + yVelocity;

    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);

    }

    protected void checkBoundaries() {
        if (y <= 0) {
            y = 0;
        }
        if (y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
    }
}
