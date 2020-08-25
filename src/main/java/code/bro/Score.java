package code.bro;

import java.awt.*;

import static code.bro.GamePanel.CENTER_WIDTH;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static final int SCORE_HEIGHT = 50;
    static final int SCORE_MARGIN_LEFT = -85;
    static final int SCORE_MARGIN_RIGHT = 20;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GAME_WIDTH / 2, 0, CENTER_WIDTH, GAME_HEIGHT);
        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), CENTER_WIDTH + SCORE_MARGIN_LEFT, SCORE_HEIGHT);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), CENTER_WIDTH + SCORE_MARGIN_RIGHT, SCORE_HEIGHT);

    }

}
