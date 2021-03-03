package game;

import message.Message;
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
    JPanel rightPanel=new JPanel();
    JLabel pointNumber=new JLabel("Points: "+points+"/300");
    public static int points=0;
    public static boolean isGameOver=false;
    public static final int Width=8;
    public static final int Height=6;


    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    public GameBoard(){
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Smoko Adventures");
        this.setLayout(null);
        this.add(rightPanel);

        rightPanel.add(pointNumber);
        rightPanel.setSize(200,30);
        rightPanel.setLocation(10,620);

        this.setVisible(true);
    }

    public void paint(Graphics g) {
        //visualize board
        super.paint(g);
        for (int row = 0; row < Width; row++) {
            for (int col = 0; col < Height; col++) {
                GameTile tile = new GameTile(row,col);
                tile.render(g);
            }
        }

    }

    public boolean isItOver(){
        if (points==300){
            isGameOver=true;
            Message.render(this,"Congrats!!","You win the game! You are winner! :D");
        }
        if (points!=300 && isGameOver==true){
            Message.render(this,"Game Over!!","Too bad, better luck next time. :(");
        }
        return false;
    }

}
