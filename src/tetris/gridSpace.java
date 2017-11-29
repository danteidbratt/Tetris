package tetris;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class gridSpace extends JLabel {
    
    public boolean isOccupied;
    public Block block;
    
    Color backgroundColor = new Color(50, 50, 50);

    public gridSpace() {
        this.isOccupied = false;
        this.block = null;
        setBackground(backgroundColor);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    
    public void removeBlock(){
        block = null;
    }
    
    public void adaptToBlock(){
        if (block != null) {
            setBackground(block.color);
        }
        else {
            setBackground(backgroundColor);
        }
    }
}