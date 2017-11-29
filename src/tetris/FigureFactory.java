package tetris;

import static tetris.Shape.*;

public class FigureFactory {

    Block[] blocks;
    Figure figure;
    Shape shape;
    int origoIndex;

    public FigureFactory() {
        blocks = new Block[4];
        origoIndex = 0;
    }

    public Figure createFigure() {
        switch ((int) (Math.random() * 7 + 1)) {
            case 1:
                blocks[0] = new Block(1, 5);
                blocks[1] = new Block(1, 6);
                blocks[2] = new Block(2, 5);
                blocks[3] = new Block(2, 6);
                shape = BOX;
                break;
            case 2:
                blocks[0] = new Block(1, 4);
                blocks[1] = new Block(1, 5);
                blocks[2] = new Block(1, 6);
                blocks[3] = new Block(1, 7);
                shape = STICK;
                break;
            case 3:
                blocks[0] = new Block(2, 6);
                blocks[1] = new Block(1, 6);
                blocks[2] = new Block(2, 4);
                blocks[3] = new Block(2, 5);
                shape = L;
                origoIndex = 3;
                break;
            case 4:
                blocks[0] = new Block(1, 4);
                blocks[1] = new Block(2, 4);
                blocks[2] = new Block(2, 5);
                blocks[3] = new Block(2, 6);
                shape = REVERSE_L;
                origoIndex = 2;
                break;
            case 5:
                blocks[0] = new Block(1, 4);
                blocks[1] = new Block(1, 5);
                blocks[2] = new Block(2, 5);
                blocks[3] = new Block(2, 6);
                shape = Z;
                break;
            case 6:
                blocks[0] = new Block(1, 5);
                blocks[1] = new Block(1, 6);
                blocks[2] = new Block(2, 4);
                blocks[3] = new Block(2, 5);
                shape = REVERSE_Z;
                break;
            case 7:
                blocks[0] = new Block(2, 6);
                blocks[1] = new Block(1, 5);
                blocks[2] = new Block(2, 5);
                blocks[3] = new Block(2, 4);
                shape = T;
                origoIndex = 2;
                break;
        }
        return new Figure(blocks, shape, origoIndex);
    }
}
