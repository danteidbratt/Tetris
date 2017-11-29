package tetris;

import java.awt.Color;

public class Block {
    
    Color color;
    int x;
    int y;

    public Block(int y, int x, Color color) {
        this.color = color;
        this.y = y;
        this.x = x;
    }
}