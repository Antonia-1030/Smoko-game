package game;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameBoard board=new GameBoard();

    public GameFrame(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setTitle("Smoko Adventures");
        this.add(board);
        this.pack();

        this.setVisible(true);
    }
}
