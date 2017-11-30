package tetris;

import java.awt.Color;
import static tetris.Shape.*;

public class FigureFactory {

    Block[] blocks;
    Figure figure;
    Shape shape;
    int origoIndex;
    Color color;

    public FigureFactory() {
        blocks = new Block[4];
        origoIndex = 0;
    }

    public Figure createFigure() {
        switch ((int) (Math.random() * 7 + 1)) {
            case 1:
                shape = BOX;
                color = Color.RED;
                blocks[0] = new Block(1, 5, shape, color);
                blocks[1] = new Block(1, 6, shape, color);
                blocks[2] = new Block(2, 5, shape, color);
                blocks[3] = new Block(2, 6, shape, color);
                break;
            case 2:
                shape = STICK;
                color = Color.ORANGE;
                blocks[0] = new Block(1, 4, shape, color);
                blocks[1] = new Block(1, 5, shape, color);
                blocks[2] = new Block(1, 6, shape, color);
                blocks[3] = new Block(1, 7, shape, color);
                break;
            case 3:
                shape = L;
                origoIndex = 3;
                color = Color.CYAN;
                blocks[0] = new Block(2, 6, shape, color);
                blocks[1] = new Block(1, 6, shape, color);
                blocks[2] = new Block(2, 4, shape, color);
                blocks[3] = new Block(2, 5, shape, color);
                break;
            case 4:
                shape = REVERSE_L;
                origoIndex = 2;
                color = Color.GREEN;
                blocks[0] = new Block(1, 4, shape, color);
                blocks[1] = new Block(2, 4, shape, color);
                blocks[2] = new Block(2, 5, shape, color);
                blocks[3] = new Block(2, 6, shape, color);
                break;
            case 5:
                shape = Z;
                color = Color.YELLOW;
                blocks[0] = new Block(1, 4, shape, color);
                blocks[1] = new Block(1, 5, shape, color);
                blocks[2] = new Block(2, 5, shape, color);
                blocks[3] = new Block(2, 6, shape, color);
                break;
            case 6:
                shape = REVERSE_Z;
                color = new Color(153, 50, 255);
                blocks[0] = new Block(1, 5, shape, color);
                blocks[1] = new Block(1, 6, shape, color);
                blocks[2] = new Block(2, 4, shape, color);
                blocks[3] = new Block(2, 5, shape, color);
                break;
            case 7:
                shape = T;
                origoIndex = 2;
                color = Color.MAGENTA;
                blocks[0] = new Block(2, 6, shape, color);
                blocks[1] = new Block(1, 5, shape, color);
                blocks[2] = new Block(2, 5, shape, color);
                blocks[3] = new Block(2, 4, shape, color);
                break;
        }
        return new Figure(blocks, shape, origoIndex);
    }
}
