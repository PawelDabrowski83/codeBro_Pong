package code.bro;

import java.awt.*;

import static code.bro.GamePanel.CENTER_WIDTH;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static int HIGHSCORE;
    static final int SCORE_HEIGHT = 50;
    static final int SCORE_MARGIN_LEFT = -85;
    static final int SCORE_MARGIN_RIGHT = 20;
    static final int HIGHSCORE_MARGIN = -200;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(
                GAME_WIDTH / 2,
                0,
                CENTER_WIDTH,
                GAME_HEIGHT
        );
        g.drawString(
                String.format("%02d", player1),
                CENTER_WIDTH + SCORE_MARGIN_LEFT,
                SCORE_HEIGHT
        );
        g.drawString(
                String.format("%02d", player2),
                CENTER_WIDTH + SCORE_MARGIN_RIGHT,
                SCORE_HEIGHT
        );
        g.setColor(Color.gray);
        g.drawString(
                "Highscore: " + String.format("%02d", HIGHSCORE),
                CENTER_WIDTH + HIGHSCORE_MARGIN,
                GAME_HEIGHT - 100
        );
    }

}
