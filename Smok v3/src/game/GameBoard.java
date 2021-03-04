package game;

import message.Message;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JFrame implements ActionListener{
    private final int WIDTH = 600;
    private final int HEIGHT = 300;
    private final int CELL_SIZE = 10;
    private final int BOARD_SIZE = 1800;
    private final int RAND_POS = 30;
    private final int x[] = new int[BOARD_SIZE];
    private final int y[] = new int[BOARD_SIZE];

    private int cell;
    private int food_row;
    private int food_col;
    public static int points=0;
    public static final int totalPoints=300;

    public static boolean isGameOver=false;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Image body;
    private Image food;
    private Image head;

    JPanel rightPanel=new JPanel();
    JLabel pointNumber=new JLabel("Points: "+points+" / "+totalPoints);

    public GameBoard(){
        this.setSize(800, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Smoko Adventures");
        this.setLayout(null);
        this.addKeyListener(new TAdapter());
        this.setBackground(Color.LIGHT_GRAY);
        this.add(rightPanel);

        rightPanel.add(pointNumber);
        rightPanel.setSize(200,50);
        rightPanel.setLocation(600,50);

        startGame();
        loadComponents();
        this.setVisible(true);
    }

    //start of game
    private void startGame() {
        cell = 1;
        for (int z = 0; z < cell; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        getFood();
    }

    //tried something new
    private void loadComponents() {
        ImageIcon H = new ImageIcon("src/pics/head.png");
        head = H.getImage();
        ImageIcon B = new ImageIcon("src/pics/body.png");
        body = B.getImage();;
        ImageIcon F = new ImageIcon("src/pics/cheese.png");
        food = F.getImage();
    }

    //visualize snake
    public void paint(Graphics g) {
        super.paint(g);
        visualizer(g);
        obstacleGenerator(g);
    }
    private void visualizer(Graphics g) {
        if (inGame) {
            g.drawImage(food, food_row, food_col, this);
            for (int z = 0; z < cell; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(body, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            isItOver();
        }
    }

    //movement of snake
    private void move() {
        for (int z = cell; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (leftDirection) {
            x[0] -= CELL_SIZE;
        }
        if (rightDirection) {
            x[0] += CELL_SIZE;
        }
        if (upDirection) {
            y[0] -= CELL_SIZE;
        }
        if (downDirection) {
            y[0] += CELL_SIZE;
        }
    }

    private void obstacleGenerator(Graphics g){
        int randomGenerator=(int)(Math.random()*3+1);
        g.setColor(Color.RED);
        if (randomGenerator==1){
            g.fillRect(50,50,40,40);
            g.fillRect(150,450,40,40);
            g.fillRect(40,540,40,40);
            g.fillRect(50,520,40,40);
        }
        if (randomGenerator==2){
            g.fillRect(300,50,40,40);
            g.fillRect(500,200,40,40);
            g.fillRect(40,540,40,40);
        }
        if (randomGenerator==3){
            g.fillRect(40,540,40,40);
            g.fillRect(150,450,40,40);
            g.fillRect(550,250,40,40);
            g.fillRect(80,2000,40,40);
            g.fillRect(360,50,40,40);
        }
    }

    //check if the snake has hit something
    private void checkCrash() {
        for (int z = cell; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                isGameOver=false;
            }
        }
        if (y[0] >= HEIGHT) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= WIDTH) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (!inGame) {
            isGameOver=true;
        }
    }

//increase current points and eat food
    private void checkFood() {
        if ((x[0] == food_row) && (y[0] == food_col)) {
            getFood();
            cell++;
            points=points+10;
        }
    }

    //random generation of food
    private void getFood() {
        int r = (int) (Math.random() * RAND_POS);
        food_row = ((r * CELL_SIZE));
        food_col = ((r * CELL_SIZE));
    }
//check and perform different actions
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            checkCrash();
            move();
        }

        repaint();
    }
//message when game is over
    public boolean isItOver(){
        if (points==totalPoints){
            isGameOver=true;
            Message.render(this,"Congrats!!","You win the game! You are winner! :D");
        }
        if (points!=totalPoints && isGameOver==true){
            Message.render(this,"Game Over!!","Too bad, better luck next time. :(");
        }
        return false;
    }
//movement of snake
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
