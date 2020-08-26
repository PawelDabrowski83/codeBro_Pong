package code.bro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int CENTER_WIDTH = GAME_WIDTH / 2;
    static final int GAME_HEIGHT = (int) ((GAME_WIDTH * 0.5555));
    static final int CENTER_HEIGHT = GAME_HEIGHT / 2;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final double AMOUNT_OF_TICKS = 60.0;
    static final String HIGHSCORE_FILE = "src/main/resources/highscore.txt";
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        prepareNewStage();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        Score.HIGHSCORE = readHighscoreFromFile();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    protected void prepareNewStage() {
        newPaddles();
        newBall();
    }

    protected int readHighscoreFromFile() {
        File file = new File(HIGHSCORE_FILE);
        byte[] highscoreByte = new byte[0];
        try {
            highscoreByte = FileUtils.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String highscoreAsString = new String(highscoreByte);
        int highscore = 0;
        try {
            highscore = Integer.parseInt(highscoreAsString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return highscore;
    }

    protected void writeHighscoreToFile() {
        File file = new File(HIGHSCORE_FILE);
        String message = String.valueOf(Score.HIGHSCORE);
        byte[] highscoreByte = message.getBytes();
        try {
            FileUtils.writeFile(file, highscoreByte);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void newBall() {
        ball = new Ball(
                CENTER_WIDTH - (BALL_DIAMETER / 2),
                ThreadLocalRandom.current().nextInt(GAME_HEIGHT - BALL_DIAMETER),
                BALL_DIAMETER,
                BALL_DIAMETER
        );
    }

    public void newPaddles() {
        paddle1 = new Paddle(
                0,
                CENTER_HEIGHT - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                1
        );
        paddle2 = new Paddle(
                GAME_WIDTH - PADDLE_WIDTH,
                CENTER_HEIGHT - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                2
        );
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        ball.checkBoundaries();
        ball.checkForBounce(paddle1);
        ball.checkForBounce(paddle2);
        ball.checkForPoint(score, this);
        paddle1.checkBoundaries();
        paddle2.checkBoundaries();
    }

    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double ns = 1000000000 / AMOUNT_OF_TICKS;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            keyPressedToQuit(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }

    }

    protected void keyPressedToQuit(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

}
