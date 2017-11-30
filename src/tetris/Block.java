package tetris;

import java.awt.Color;

public class Block {
    
    int y;
    int x;
    public Color color;
    Shape partOf;

    public Block(int y, int x, Shape shape, Color color) {
        this.y = y;
        this.x = x;
        this.partOf = shape;
        this.color = color;
    }
}