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
                Thread.sleep(300);
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
        } else if (direction == DOWN) {
            for (Block block : figure.blocks) {
                grid[block.y][block.x].block = null;
                grid[block.y][block.x].isOccupied = true;
            }
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
                            || grid[figure.blocks[3].y][figure.blocks[3].x - 2].isOccupied) {
                        rotatable = false;
                    }
                } else {
                    if (grid[figure.blocks[3].y - 2][figure.blocks[3].x].isOccupied
                            || grid[figure.blocks[3].y][figure.blocks[3].x + 2].isOccupied) {
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
}
