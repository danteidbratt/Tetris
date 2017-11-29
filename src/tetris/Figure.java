package tetris;

public class Figure {
    
    Block[] blocks = new Block[4];
    Shape shape;
    

    public Figure(Block[] blocks, Shape shape) {
        this.blocks = blocks;
        this.shape = shape;
    }
    
    public void rotate(){
        switch (shape) {
            case BOX:
                
                break;
            case STICK:
                
                break;
            case L:
                rotateAroundOrigo(blocks[2]);
                break;
            case REVERSE_L:
                rotateAroundOrigo(blocks[1]);
                break;
            case Z:
                
                break;
            case REVERSE_Z:
                
                break;
            case T:
                rotateAroundOrigo(blocks[2]);
                break;
        }
        
        
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