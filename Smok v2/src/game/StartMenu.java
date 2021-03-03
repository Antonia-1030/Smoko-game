package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    JButton startButton;

    public StartMenu(){
        this.setSize(400, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Smoko Adventures");
        this.setLayout(null);

        startButton=new JButton();
        startButton.setText("Start");
        startButton.setSize(100,50);
        startButton.setLocation(120,80);
        startButton.addActionListener(this);

        this.add(startButton);

        this.setVisible(true);
    }

    public void paint(Graphics g) {
        //visualize board
        super.paint(g);
        for (int i=0; i<400;i+=40){
            for (int j=0;j<300;j+=40){
                snake(g);
                g.setColor(Color.GRAY);
                g.fillRect(i,j,40,40);
            }
        }
    }

    public void snake(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(40,40,40,40);
        g.fillRect(80,40,40,40);
        g.fillRect(120,40,40,40);
        g.fillRect(160,40,40,40);
        g.fillRect(200,40,40,40);
        g.fillRect(240,40,40,40);
        //
        g.fillRect(240,80,40,40);
        g.fillRect(240,120,40,40);
        g.fillRect(240,160,40,40);
        g.fillRect(240,200,40,40);
        //
        g.fillRect(200,200,40,40);
        g.fillRect(160,200,40,40);
        g.fillRect(120,200,40,40);
        g.fillRect(80,200,40,40);
        //
        g.setColor(Color.BLACK);
        g.fillOval(90,210,20,20);
        //
        g.setColor(Color.RED);
        g.fillOval(50,220,30,5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton){
            this.repaint();
            new GameBoard();
        }
    }
}
