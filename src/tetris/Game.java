package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import static tetris.Direction.*;

public class Game extends JPanel implements Runnable {

    FigureFactory ff;
    Figure figure;
    JPanel field;
    gridSpace[][] grid;
    Thread gravity = new Thread(this);

    Color backgroundColor = new Color(50, 50, 50);

    public Game() {
        this.grid = new gridSpace[22][12];
        this.field = new JPanel();
        this.ff = new FigureFactory();
        this.figure = ff.createFigure();
    }

    public void setPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 700));
        field.setLayout(new GridLayout(20, 10));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0 && i < grid.length - 1 && j > 0 && j < grid[i].length - 1) {
                    grid[i][j] = new gridSpace();
                    field.add(grid[i][j]);
                } else {
                    grid[i][j] = new gridSpace();
                    grid[i][j].isOccupied = true;
                }
            }
        }
        add(field, BorderLayout.CENTER);
    }

    public void placeFigure() {
        for (Block block : figure.blocks) {
            grid[block.y][block.x].block = block;
            grid[block.y][block.x].adaptToBlock();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(140);
                moveBlocks(DOWN);
                revalidate();
                repaint();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void moveBlocks(Direction direction) {
        int y = 0;
        int x = 0;
        switch (direction) {
            case DOWN:
                y = 1;
                x = 0;
                break;
            case LEFT:
                y = 0;
                x = -1;
                break;
            case RIGHT:
                y = 0;
                x = 1;
                break;
        }

        if (isMovable(y, x)) {
            for (Block block : figure.blocks) {
                grid[block.y][block.x].removeBlock();
                grid[block.y][block.x].adaptToBlock();
                block.y += y;
                block.x += x;
            }
            placeFigure();
        }
        else if (direction == DOWN) {
            for (Block block : figure.blocks) {
                grid[block.y][block.x].block = null;
                grid[block.y][block.x].isOccupied = true;
            }
            checkForFullLines();
            figure = ff.createFigure();
            placeFigure();
        }
    }

    public boolean isMovable(int y, int x) {
        for (Block block : figure.blocks) {
            if (grid[block.y + y][block.x + x].isOccupied) {
                return false;
            }
        }
        return true;
    }
    
    public void removeFigure(){
        for (Block block : figure.blocks) {
            grid[block.y][block.x].setBackground(backgroundColor);
        }
    }
    
    public void checkForFullLines(){
        for (int i = 1; i < grid.length-1; i++) {
            boolean clear = true;
            for (int j = 1; j < grid[i].length-1; j++) {
                if (!grid[i][j].isOccupied) {
                    clear = false;
                }
            }
            if (clear) {
                clearLine(i);
            }
        }
    }
    
    public void clearLine(int y){
        for (int i = 1; i < grid[y].length-1; i++) {
            grid[y][i].isOccupied = false;
            grid[y][i].block = null;
            grid[y][i].setBackground(backgroundColor);
        }
        fall(y);
    }
    
    public void fall(int y){
        for (; y > 1; y--) {
            for (int j = 1; j < grid[y].length-1; j++) {
                System.out.println("hej");
                if (grid[y-1][j].block == null){
                    grid[y][j].block = null;
                    grid[y][j].isOccupied = false;
                    grid[y][j].setBackground(backgroundColor);
                }
                else {
                    grid[y][j].block = grid[y-1][j].block;
                    grid[y][j].isOccupied = true;
                    grid[y][j].adaptToBlock();
                }
                grid[y-1][j].block = null;
                grid[y-1][j].isOccupied = false;
                grid[y-1][j].setBackground(backgroundColor);
            }
        }
    }
}
