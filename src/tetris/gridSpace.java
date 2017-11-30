package tetris;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class gridSpace extends JLabel {
    
    public boolean isOccupied;
    Color backgroundColor = new Color(50, 50, 50);
    Color occupiedColor;

    public void setLabel(){
        this.isOccupied = false;
        setBackground(backgroundColor);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    
    public void removeBlock(){
        isOccupied = false;
        setBackground(backgroundColor);
    }
    
    public void addBlock(Block block ){
        switch (block.partOf) {
            case BOX:
                occupiedColor = Color.RED;
                break;
            case STICK:
                occupiedColor = Color.ORANGE;
                break;
            case L:
                occupiedColor = Color.CYAN;
                break;
            case REVERSE_L:
                occupiedColor = Color.GREEN;
                break;
            case Z:
                occupiedColor = Color.YELLOW;
                break;
            case REVERSE_Z:
                occupiedColor = new Color(153, 50, 255);
                break;
            case T:
                occupiedColor = Color.MAGENTA;
                break;
        }
        setBackground(occupiedColor);
    }
    
    public void dropBlock(){
        isOccupied = true;
    }
}