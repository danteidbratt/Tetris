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

                break;
            case REVERSE_L:
                
                break;
            case Z:
                
                break;
            case REVERSE_Z:
                
                break;
            case T:
                
                break;
        }
        
        
    }
}