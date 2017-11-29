package tetris;

import static tetris.Shape.*;

public class Figure {
    
    Block[] blocks = new Block[4];
    Shape shape;
    boolean standing;
    

    public Figure(Block[] blocks, Shape shape) {
        this.blocks = blocks;
        this.shape = shape;
        standing = false;
    }
    
    public void rotate(){
        switch (shape) {
            case BOX:
                
                break;
            case STICK:
                if (standing) {
                    blocks[0].y += 1;
                    blocks[0].x -= 1;
                    blocks[2].y -= 1;
                    blocks[2].x += 1;
                    blocks[3].y -= 2;
                    blocks[3].x += 2;
                }
                else {
                    blocks[0].y -= 1;
                    blocks[0].x += 1;
                    blocks[2].y += 1;
                    blocks[2].x -= 1;
                    blocks[3].y += 2;
                    blocks[3].x -= 2;
                }
                break;
            case L:
                rotateAroundOrigo(blocks[2]);
                break;
            case REVERSE_L:
                rotateAroundOrigo(blocks[1]);
                break;
            case Z:
                if (standing) {
                    blocks[3].y += 2;
                    blocks[0].x -= 2;
                }
                else {
                    blocks[3].y -= 2;
                    blocks[0].x += 2;
                }
                break;
            case REVERSE_Z:
                if (standing) {
                    blocks[2].y += 2;
                    blocks[1].x += 2;
                }
                else {
                    blocks[2].y -= 2;
                    blocks[1].x -= 2;
                }
                break;
            case T:
                rotateAroundOrigo(blocks[2]);
                break;
        }
        this.standing = !standing;
    }

    private void rotateAroundOrigo(Block block) {
        int absolutX;
        int absolutY;
        for (int i = 0; i < blocks.length; i++) {
            absolutX = blocks[i].x - block.x;
            absolutY = blocks[i].y - block.y;
            int tempYvalue = blocks[i].y;
            int tempXvalue = blocks[i].x;
            blocks[i].x = -absolutY + tempYvalue;
            blocks[i].y = absolutX + tempXvalue;
        }
    }
}