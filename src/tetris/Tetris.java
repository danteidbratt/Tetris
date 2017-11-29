package tetris;

import java.awt.event.*;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import static tetris.Direction.*;

public class Tetris extends JFrame implements ActionListener{
    
    Menu menu = new Menu();
    Game game;
    
    public void go(){
        setVisible(true);
        setSize(350, 700);
        addKeyListener(ka);
        menu.setPanel();
        menu.startButton.addActionListener(this);
        menu.exitButton.addActionListener(this);
        add(menu);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Tetris t = new Tetris();
        t.go();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.startButton) {
            remove(menu);
            add(game = new Game());
            game.setPanel();
            game.placeFigure();
            game.gravity.start();
            requestFocus();
        }
        else if (e.getSource() == menu.exitButton) {
            System.exit(0);
        }
    revalidate();
    repaint();
    pack();
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_UP:
                    game.clearFigureSpaces();
                    game.figure.rotate();
                    game.placeFigure();
                    break;
                case VK_LEFT:
                    game.moveBlocks(LEFT);
                    break;
                case VK_DOWN:
                    game.moveBlocks(DOWN);
                    break;
                case VK_RIGHT:
                    game.moveBlocks(RIGHT);
                    break;
            }
        }
    };
}