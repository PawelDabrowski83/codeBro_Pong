package code.bro;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static code.bro.GamePanel.*;

public class Ball extends Rectangle {

    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        int randomXDirection = ThreadLocalRandom.current().nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);
        int randomYDirection = ThreadLocalRandom.current().nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);

    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    protected void checkForBounce(Paddle paddle) {
        if (this.intersects(paddle)) {
            bounce(paddle);
        }
    }

    protected void bounce(Paddle paddle) {
        xVelocity = Math.abs(xVelocity);
        xVelocity++; // speeds up ball
        if (yVelocity > 0) {
            yVelocity++; // speeds up ball
        } else {
            yVelocity--;
        }
        if (paddle.id == 1) {
            setXDirection(xVelocity);
        } else {
            setXDirection(-xVelocity);
        }
        setYDirection(yVelocity);
    }

    protected void checkBoundaries() {
        if (y <= 0) {
            setYDirection(-yVelocity);
        }
        if (y >= GAME_HEIGHT - BALL_DIAMETER) {
            setYDirection(-yVelocity);
        }
    }

    protected void checkForPoint(Score score, GamePanel panel) {
        if (x <= 0) {
            score.player2++;
            panel.prepareNewGame();
        }
        if (x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            panel.prepareNewGame();
        }
    }

}
