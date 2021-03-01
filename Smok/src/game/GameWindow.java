package game;

import message.Message;
import fields.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame{
    public static final int WIDTH = 10;
    public static final int HEIGHT = 7;
    public static boolean isGameOver=false;
    public static int score=0;

    public GameWindow(){
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Smoko Adventures");

        setVisible(true);
    }

    public void paint(Graphics g) {
        //visualize board
        super.paint(g);
        for (int row = 0; row < WIDTH; row++) {
            for (int col = 0; col < HEIGHT; col++) {
                GameBoard tile = new GameBoard(row,col);
                tile.render(g);
            }
        }
    }
}
