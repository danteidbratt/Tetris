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
                color = Color.RED;
                blocks[0] = new Block(1, 5, color);
                blocks[1] = new Block(1, 6, color);
                blocks[2] = new Block(2, 5, color);
                blocks[3] = new Block(2, 6, color);
                shape = BOX;
                break;
            case 2:
                color = Color.ORANGE;
                blocks[0] = new Block(1, 4, color);
                blocks[1] = new Block(1, 5, color);
                blocks[2] = new Block(1, 6, color);
                blocks[3] = new Block(1, 7, color);
                shape = STICK;
                break;
            case 3:
                color = Color.CYAN;
                blocks[0] = new Block(2, 6, color);
                blocks[1] = new Block(1, 6, color);
                blocks[2] = new Block(2, 4, color);
                blocks[3] = new Block(2, 5, color);
                shape = L;
                origoIndex = 3;
                break;
            case 4:
                color = Color.GREEN;
                blocks[0] = new Block(1, 4, color);
                blocks[1] = new Block(2, 4, color);
                blocks[2] = new Block(2, 5, color);
                blocks[3] = new Block(2, 6, color);
                shape = REVERSE_L;
                origoIndex = 2;
                break;
            case 5:
                color = Color.YELLOW;
                blocks[0] = new Block(1, 4, color);
                blocks[1] = new Block(1, 5, color);
                blocks[2] = new Block(2, 5, color);
                blocks[3] = new Block(2, 6, color);
                shape = Z;
                break;
            case 6:
                color = new Color(153, 50, 255);
                blocks[0] = new Block(1, 5, color);
                blocks[1] = new Block(1, 6, color);
                blocks[2] = new Block(2, 4, color);
                blocks[3] = new Block(2, 5, color);
                shape = REVERSE_Z;
                break;
            case 7:
                color = Color.MAGENTA;
                blocks[0] = new Block(2, 6, color);
                blocks[1] = new Block(1, 5, color);
                blocks[2] = new Block(2, 5, color);
                blocks[3] = new Block(2, 4, color);
                shape = T;
                origoIndex = 2;
                break;
        }
        return new Figure(blocks, shape, origoIndex);
    }
}
