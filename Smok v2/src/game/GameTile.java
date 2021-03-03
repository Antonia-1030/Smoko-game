package game;

import javax.swing.*;
import java.awt.*;

public class GameTile {
    public static final int TILE_SIZE = 20;
    private int randomizer = (int) (Math.random() * 5 + 1);
    private int row;
    private int col;

    public GameTile(int row, int col){
        this.row=row;
        this.col=col;
    }
//render board
    public void render(Graphics g) {
        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;
        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
        if (randomizer == 1) {
            g.setColor(Color.BLACK);
            obstacleVisualizer(g);
        }
        if (randomizer == 2) {
            g.setColor(Color.YELLOW);
            obstacleVisualizer(g);
        }
        if (randomizer == 3) {
            g.setColor(Color.GRAY);
            obstacleVisualizer(g);
        }
    }

    //get obstacles
    public void obstacleVisualizer(Graphics g){
        g.setColor(Color.RED);
        isObstacle(row,col);
    }
    public boolean isObstacle(int row, int col){
        int shuffler=(int) (Math.random() *4 + 1);

        if (shuffler==1){
            return ((row == 0 && col == 1) ||
                    (row == 5 && col == 2) ||
                    (row == 2 && col == 1) ||
                    (row == 3 && col == 5)||
                    (row == 6 && col == 5)||
                    (row == 7 && col == 5));
        }
        if (shuffler==2){
            return ((row == 0 && col == 6) ||
                    (row == 1 && col == 6) ||
                    (row == 1 && col == 2) ||
                    (row == 6 && col == 0)||
                    (row == 7 && col == 5));
        }
        if (shuffler==3){
            return ((row == 0 && col == 1) ||
                    (row == 1 && col == 0) ||
                    (row == 3 && col == 1) ||
                    (row == 6 && col == 4));
        }
        return ((row == 0 && col == 1) ||
                (row == 1 && col == 2) ||
                (row == 1 && col == 1) ||
                (row == 6 && col == 5)||
                (row == 7 && col == 5));
    }
}
