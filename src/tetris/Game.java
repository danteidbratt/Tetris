package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import static tetris.Direction.*;

public class Game extends JPanel implements Runnable {

    FigureFactory ff;
    Figure figure;
    JPanel field;
    gridSpace[][] grid;
    
    JPanel sideBar = new JPanel();
    JLabel topSpace = new JLabel("");
    JLabel leftSpace = new JLabel("");
    JLabel rightSpace = new JLabel("");
    JLabel botSpace = new JLabel("");
    
    JPanel centerPanel = new JPanel();
    JPanel nextFigurePanel = new JPanel();
    gridSpace[][] nextFigureGrid = new gridSpace[4][4];
    JPanel statsPanel = new JPanel();
    JLabel levelLabel = new JLabel("Level: 7");
    JLabel scoreLabel = new JLabel("Score: 666");
    JLabel tetrisCounterLabel = new JLabel("Tetris: 3");
    JLabel[] statsLabels = {levelLabel, scoreLabel, tetrisCounterLabel};
    JPanel buttonPanel = new JPanel();
    JButton pauseButton = new JButton("Paus");
    JButton quitButton = new JButton("Quit");
    JLabel[] spaces = {topSpace, leftSpace, rightSpace, botSpace, scoreLabel};
    
    Thread gravity = new Thread(this);

    Color backgroundColor = new Color(50, 50, 50);
    Color sideBarColor = new Color(30, 30, 30);

    public Game() {
        this.grid = new gridSpace[22][12];
        this.field = new JPanel();
        this.ff = new FigureFactory();
        this.figure = ff.createFigure();
    }

    public void setPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(550, 700));
        
        sideBar.setLayout(new BorderLayout());
        sideBar.setBackground(new Color(30, 30, 30));
        sideBar.setPreferredSize(new Dimension(200, 0));
        sideBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        for (int i = 0; i < spaces.length; i++) {
            spaces[i].setBackground(sideBarColor);
            spaces[i].setOpaque(true);
            spaces[i].setPreferredSize(new Dimension(20, 20));
        }
        
        centerPanel.setLayout(new BorderLayout(0, 40));
        centerPanel.setBackground(sideBarColor);
        
        nextFigurePanel.setLayout(new GridLayout(4, 4));
        nextFigurePanel.setBackground(Color.BLACK);
        nextFigurePanel.setPreferredSize(new Dimension(0, 150));
        nextFigurePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        
        for (int i = 0; i < nextFigureGrid.length; i++) {
            for (int j = 0; j < nextFigureGrid[i].length; j++) {
                nextFigureGrid[i][j] = new gridSpace();
                nextFigureGrid[i][j].setLabel();
                nextFigurePanel.add(nextFigureGrid[i][j]);
            }
        }
        
        statsPanel.setLayout(new GridLayout(3, 1, 0, 10));
        statsPanel.setBackground(sideBarColor);
        
        for (int i = 0; i < statsLabels.length; i++) {
            statsLabels[i].setBackground(new Color(60, 60, 60));
            statsLabels[i].setOpaque(true);
            statsLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            statsLabels[i].setFont(new Font("SansSerif", 1 , 20));
            statsLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            statsPanel.add(statsLabels[i]);
        }
        
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 5));
        buttonPanel.setBackground(sideBarColor);
        buttonPanel.setPreferredSize(new Dimension(0, 150));
        pauseButton.setFont(new Font("SansSerif", 3, 20));
        quitButton.setFont(new Font("SansSerif", 3, 20));
        buttonPanel.add(pauseButton);
        buttonPanel.add(quitButton);
        
        centerPanel.add(nextFigurePanel, BorderLayout.NORTH);
        centerPanel.add(statsPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        sideBar.add(centerPanel, BorderLayout.CENTER);
        sideBar.add(topSpace, BorderLayout.NORTH);
        sideBar.add(leftSpace, BorderLayout.WEST);
        sideBar.add(rightSpace, BorderLayout.EAST);
        sideBar.add(botSpace, BorderLayout.SOUTH);
        
        field.setLayout(new GridLayout(20, 10));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0 && i < grid.length - 1 && j > 0 && j < grid[i].length - 1) {
                    grid[i][j] = new gridSpace();
                    grid[i][j].setLabel();
                    field.add(grid[i][j]);
                } else {
                    grid[i][j] = new gridSpace();
                    grid[i][j].isOccupied = true;
                }
            }
        }
        add(sideBar, BorderLayout.WEST);
        add(field, BorderLayout.CENTER);
    }

    public void placeFigure() {
        for (Block block : figure.blocks) {
            grid[block.y][block.x].showBlock(block);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
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
                block.y += y;
                block.x += x;
            }
            placeFigure();
        } else if (direction == DOWN) {
            for (Block block : figure.blocks) {
                grid[block.y][block.x].dropBlock();
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

    public void removeFigure() {
        for (Block block : figure.blocks) {
            grid[block.y][block.x].setBackground(backgroundColor);
        }
    }

    public boolean checkIfRotatable() {
        boolean rotatable = true;
        switch (figure.shape) {
            case BOX:
                break;
            case STICK:
                if (figure.standing) {
                    if (grid[figure.blocks[0].y + 1][figure.blocks[0].x - 1].isOccupied
                            || grid[figure.blocks[2].y - 1][figure.blocks[2].x + 1].isOccupied
                            || grid[figure.blocks[3].y - 2][figure.blocks[3].x + 2].isOccupied) {
                        rotatable = false;
                    }
                } else {
                    if (grid[figure.blocks[0].y - 1][figure.blocks[0].x + 1].isOccupied
                            || grid[figure.blocks[2].y + 1][figure.blocks[2].x - 1].isOccupied
                            || grid[figure.blocks[3].y + 2][figure.blocks[3].x - 2].isOccupied) {
                        rotatable = false;
                    }
                }
                break;
            case L:
                if (!rotateAroundOrigoTest()) {
                    rotatable = false;
                }
                break;
            case REVERSE_L:
                if (!rotateAroundOrigoTest()) {
                    rotatable = false;
                }
                break;
            case Z:
                if (figure.standing) {
                    if (grid[figure.blocks[3].y + 2][figure.blocks[3].x].isOccupied
                            || grid[figure.blocks[0].y][figure.blocks[3].x - 2].isOccupied) {
                        rotatable = false;
                    }
                } else {
                    if (grid[figure.blocks[3].y - 2][figure.blocks[3].x].isOccupied
                            || grid[figure.blocks[3].y - 1][figure.blocks[3].x].isOccupied) {
                        rotatable = false;
                    }
                }
                break;
            case REVERSE_Z:
                if (figure.standing) {
                    if (grid[figure.blocks[2].y + 2][figure.blocks[2].x].isOccupied
                            || grid[figure.blocks[1].y][figure.blocks[1].x + 2].isOccupied) {
                        rotatable = false;
                    }
                } else {
                    if (grid[figure.blocks[2].y - 2][figure.blocks[2].x].isOccupied
                            || grid[figure.blocks[1].y][figure.blocks[1].x - 2].isOccupied) {
                        rotatable = false;
                    }
                }
                break;
            case T:
                if (!rotateAroundOrigoTest()) {
                    rotatable = false;
                }
                break;
        }
        return rotatable;
    }

    private boolean rotateAroundOrigoTest() {
        for (int i = 0; i < figure.blocks.length; i++) {
            if (i != figure.origoIndex) {
                int h = figure.blocks[i].x - figure.blocks[figure.origoIndex].x;
                int v = figure.blocks[i].y - figure.blocks[figure.origoIndex].y;
                int x = -(h + v);
                int y = (h - v);
                if (grid[figure.blocks[i].y + y][figure.blocks[i].x + x].isOccupied) {
                    return false;
                }
            }
        }
        return true;
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
    
    public void clearLine(int lineToClear){
        for (int i = 1; i < grid[lineToClear].length-1; i++) {
            grid[lineToClear][i].removeBlock();
        }
        fall(lineToClear);
    }
    
    public void fall(int clearedLine){
        for (int i = clearedLine; i > 2; i--) {
            for (int j = 1; j < grid[i].length-1; j++) {
                grid[i][j].setBackground(grid[i-1][j].getBackground());
                grid[i][j].isOccupied = grid[i-1][j].isOccupied;
            }
        }
    }
}