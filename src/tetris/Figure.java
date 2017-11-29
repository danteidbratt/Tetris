package tetris;

import java.awt.Color;
import static tetris.Shape.*;

public class Figure {
    
    Block[] blocks = new Block[4];
    Shape shape;
    int origoIndex;
    Color color;
    boolean standing;
    
    public Figure(Block[] blocks, Shape shape, int origoIndex) {
        this.blocks = blocks;
        this.shape = shape;
        this.origoIndex = origoIndex;
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
                rotateAroundOrigo();
                break;
            case REVERSE_L:
                rotateAroundOrigo();
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
                rotateAroundOrigo();
                break;
        }
        this.standing = !standing;
    }

    private void rotateAroundOrigo() {
        for (int i = 0; i < blocks.length; i++) {
            if(i != origoIndex){
                int h = blocks[i].x - blocks[origoIndex].x;
                int v = blocks[i].y - blocks[origoIndex].y;
                int x = -(h + v); 
                int y = (h - v); 
                blocks[i].x += x;
                blocks[i].y += y;
            }
        }
    }
}