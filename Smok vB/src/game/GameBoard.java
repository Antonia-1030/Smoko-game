package game;

//import message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int CELL_SIZE = 20;
    static final int BOARD_CELLS = (WIDTH*HEIGHT)/CELL_SIZE;
    static final int SPEED=100;
    private final int x[] = new int[BOARD_CELLS];
    private final int y[] = new int[BOARD_CELLS];

    public static int points=0;
    public static final int totalPoints=300;
    public int parts=1;
    private int food_row;
    private int food_col;
    private int block_row;
    private int block_col;
    char direction='R';
    private boolean isGameRunning=false;

    Timer timer;
    Random randomizer;
    FontMetrics size;

    //    private Image body;
    //    private Image food;
    //    private Image head;

    public GameBoard(){
        randomizer=new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new GameKeyAdapter());
        startGame();
    }

    public void startGame(){
        generateFood();
        isGameRunning=true;
        timer=new Timer(SPEED,this);
        timer.start();
    }
//visualize all components on board
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isGameRunning && points!=totalPoints){
            g.setColor(Color.RED);
            g.fillOval(food_row, food_col, CELL_SIZE, CELL_SIZE);
            generateObstacle(g);
            for (int i = 0; i < parts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillOval(x[i], y[i], CELL_SIZE, CELL_SIZE);
                }
            }
            size = getFontMetrics(g.getFont());
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Serif Plain",Font.ITALIC,20));
            g.drawString("Score: "+points+"/"+totalPoints, (WIDTH-size.stringWidth("Score: "+points+"/"+totalPoints))/2,g.getFont().getSize());
            if (points==totalPoints){
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Serif Italic",Font.BOLD,90));
                g.drawString("You win! Congrats!",150,HEIGHT/2);
            }
        }
        else {
            gameOver(g);
        }
    }

//snake movement logic
    public void movement(){
        for (int i=parts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction){
            case 'U':
                y[0]=y[0]-CELL_SIZE;
                break;
            case 'L':
                x[0]=x[0]-CELL_SIZE;
                break;
            case 'R':
                x[0]=x[0]+CELL_SIZE;
                break;
            case 'D':
                y[0]=y[0]+CELL_SIZE;
                break;
        }
    }
//if food is eaten
    public void checkFood(){
        if ((x[0]==food_row)&&(y[0]==food_row)){
            parts++;
            points=points+10;
            generateFood();
        }
    }

//random generate position of food on board
    public void generateFood(){
        food_row=randomizer.nextInt(WIDTH/CELL_SIZE)*CELL_SIZE;
        food_col=randomizer.nextInt(HEIGHT/CELL_SIZE)*CELL_SIZE;
    }

    //random obstacke
    public void generateObstacle(Graphics g){
        block_row=randomizer.nextInt(WIDTH/CELL_SIZE)*CELL_SIZE;
        block_col=randomizer.nextInt(HEIGHT/CELL_SIZE)*CELL_SIZE;

        g.setColor(Color.BLACK);
        g.fillRect(block_row,block_col,CELL_SIZE,CELL_SIZE);
        g.fillRect(block_row,block_col,CELL_SIZE,CELL_SIZE);
        g.fillRect(block_row,block_col,CELL_SIZE,CELL_SIZE);
        g.fillRect(block_row,block_col,CELL_SIZE,CELL_SIZE);
        g.fillRect(block_row,block_col,CELL_SIZE,CELL_SIZE);
    }

//check if snake has crashed into something
    public void checkCrash(){
        for (int i=parts;i>0;i--){
            if ((x[0]==x[i])&&(y[0]==y[i])){
                isGameRunning=false;
            }
        }
        if ((x[0]==block_row)&&(y[0]==block_col)){
            isGameRunning=false;
        }
        if (x[0]<0){
            isGameRunning=false;
        }
        if (x[0]>WIDTH){
            isGameRunning=false;
        }
        if (y[0]<0){
            isGameRunning=false;
        }
        if (x[0]>HEIGHT){
            isGameRunning=false;
        }
        if (!isGameRunning){
            timer.stop();
        }
    }

    //message when game is over
    public void gameOver(Graphics g){
        size = getFontMetrics(g.getFont());
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Serif Plain",Font.ITALIC,50));
        g.drawString("Score: "+points+"/"+totalPoints, 170,g.getFont().getSize());

        if (points!=totalPoints){
            g.setColor(Color.RED);
            g.setFont(new Font("Serif Plain",Font.BOLD,90));
            g.drawString("Game Over!",150,HEIGHT/2);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning){
            movement();
            checkFood();
            checkCrash();
        }
        repaint();
    }

    //get movement from keyboard
    public class GameKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction!='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction!='D'){
                        direction='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction!='U'){
                        direction='D';
                    }
                    break;
            }
        }
    }
}
