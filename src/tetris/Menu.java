package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Menu extends JPanel {
    
    JLabel logoSpace = new JLabel("- TETRIS -");
    JLabel leftSpace = new JLabel(" ");
    JLabel rightSpace = new JLabel(" ");
    JLabel botSpace = new JLabel(" ");
    JLabel[] spaces = new JLabel[4];
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start Game");
    JButton exitButton = new JButton("Exit");
    Color backgroundColor = new Color(50, 50, 50);

    public void setPanel(){
        setLayout(new BorderLayout(0, 20));
        setBackground(backgroundColor);
        
        
        spaces[0] = logoSpace;
        spaces[1] = leftSpace;
        spaces[2] = rightSpace;
        spaces[3] = botSpace;
        
        for (int i = 0; i < spaces.length; i++) {
            spaces[i].setBackground(backgroundColor);
            spaces[i].setOpaque(true);
            spaces[i].setPreferredSize(new Dimension(50, 50));
        }
        logoSpace.setPreferredSize(new Dimension(0, 90));
        logoSpace.setFont(new Font("SansSerif", 1, 50));
        logoSpace.setForeground(new Color(255, 0, 50));
        logoSpace.setHorizontalAlignment(SwingConstants.CENTER);
        
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10));
        buttonPanel.setBackground(backgroundColor);
        startButton.setFont(new Font("SansSerif", 3, 30));
        exitButton.setFont(new Font("SansSerif", 3, 30));
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        
        
        add(logoSpace, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botSpace, BorderLayout.SOUTH);
    }
}