package tetris;

public class FigureFactory {

    Block[] blocks;
    Figure figure;

    public FigureFactory() {
        blocks = new Block[4];

    }

    public void createFigure(int x) {
        int preset = (int) (Math.random() * 7 + 1);

        switch (preset) {
            case 1:
                blocks[0] = new Block(1, 5);
                blocks[2] = new Block(1, 6);
                blocks[1] = new Block(2, 5);
                blocks[3] = new Block(2, 6);
                break;
            case 2:
                blocks[0] = new Block(1, 4);
                blocks[2] = new Block(1, 5);
                blocks[1] = new Block(1, 6);
                blocks[3] = new Block(1, 7);

                break;
            case 3:
                blocks[0] = new Block(1, 6);
                blocks[2] = new Block(2, 4);
                blocks[1] = new Block(2, 5);
                blocks[3] = new Block(2, 6);

                break;
            case 4:
                blocks[0] = new Block(1, 4);
                blocks[2] = new Block(2, 4);
                blocks[1] = new Block(2, 5);
                blocks[3] = new Block(2, 6);

                break;
            case 5:
                blocks[0] = new Block(1, 4);
                blocks[2] = new Block(1, 5);
                blocks[1] = new Block(2, 5);
                blocks[3] = new Block(2, 6);

                break;
            case 6:
                blocks[0] = new Block(1, 5);
                blocks[2] = new Block(1, 6);
                blocks[1] = new Block(2, 4);
                blocks[3] = new Block(2, 5);

                break;
            case 7:
                blocks[0] = new Block(1, 5);
                blocks[2] = new Block(2, 4);
                blocks[1] = new Block(2, 5);
                blocks[3] = new Block(2, 6);

                break;
        }
        figure = new Figure(blocks);
    }

    public Figure getFigure() {
        return figure;
    }
}
