package tetris;

public class Figure {
    
    Block[] blocks = new Block[4];
    Shape shape;
    

    public Figure(Block[] blocks, Shape shape) {
        this.blocks = blocks;
        this.shape = shape;
    }
    
    public void rotate(){
        
    }
}