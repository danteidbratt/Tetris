package tetris;

import static tetris.Shape.*;

public class Figure {
    
    Block[] blocks = new Block[4];
    Shape shape;
    boolean standing;
    

    public Figure(Block[] blocks, Shape shape) {
        this.blocks = blocks;
        this.shape = shape;
        if (shape == STICK)
            standing = true;
        else
            standing = false;
    }
    
    public void rotate(){
        switch (shape) {
            case BOX:
                
                break;
            case STICK:
                if (standing) {
                    blocks[0].x -= 1;
                    blocks[0].y += 1;
                    blocks[2].x += 1;
                    blocks[2].y -= 1;
                    blocks[3].x += 2;
                    blocks[2].y -= 2;
                }
                else {
                    blocks[0].x += 1;
                    blocks[0].y -= 1;
                    blocks[2].x -= 1;
                    blocks[2].y += 1;
                    blocks[3].x -= 2;
                    blocks[2].y += 2;
                }
                break;
            case L:

                break;
            case REVERSE_L:
                
                break;
            case Z:
                if (standing) {
                    blocks[0].x -= 2;
                    blocks[3].y += 2;
                }
                else {
                    blocks[0].x += 2;
                    blocks[3].y -= 2;
                }
                break;
            case REVERSE_Z:
                if (standing) {
                    blocks[1].x += 2;
                    blocks[2].y += 2;
                }
                else {
                    blocks[1].x -= 2;
                    blocks[2].y -= 2;
                }
                break;
            case T:
                
                break;
        }
        standing = !standing;
    }
}