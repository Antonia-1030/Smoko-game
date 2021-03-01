package fields;

import java.awt.*;
public class GameBoard {
    private int row;
    private int col;

    public GameBoard(int row,int col){
        this.row=row;
        this.col=col;
    }

    public void render(Graphics g){

        //boardGrid(g);
    }

    //draw border between different squares
    private void boardGrid(Graphics g){
        g.setColor(Color.BLACK);

    }
}
