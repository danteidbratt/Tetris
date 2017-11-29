package tetris;


public class FigureFactory {
    
    Block[] blocks;
    Figure figure;
    int[][][] presets = new int[7][4][2];
    
    public FigureFactory() {
        blocks = new Block[4];
        
        presets[0][0][0] = 1;
        presets[0][0][1] = 5;
        presets[0][1][0] = 1;
        presets[0][1][1] = 6;
        presets[0][2][0] = 2;
        presets[0][2][1] = 5;
        presets[0][3][0] = 2;
        presets[0][3][1] = 6;
                
        presets[1][0][0] = 1;
        presets[1][0][1] = 5;
        presets[1][1][0] = 2;
        presets[1][1][1] = 5;
        presets[1][2][0] = 3;
        presets[1][2][1] = 5;
        presets[1][3][0] = 4;
        presets[1][3][1] = 5;
                
        presets[2][0][0] = 1;
        presets[2][0][1] = 5;
        presets[2][1][0] = 1;
        presets[2][1][1] = 6;
        presets[2][2][0] = 2;
        presets[2][2][1] = 5;
        presets[2][3][0] = 2;
        presets[2][3][1] = 6;
                
        presets[3][0][0] = 1;
        presets[3][0][1] = 5;
        presets[3][1][0] = 1;
        presets[3][1][1] = 6;
        presets[3][2][0] = 2;
        presets[3][2][1] = 5;
        presets[3][3][0] = 2;
        presets[3][3][1] = 6;
                
        presets[4][0][0] = 1;
        presets[4][0][1] = 5;
        presets[4][1][0] = 1;
        presets[4][1][1] = 6;
        presets[4][2][0] = 2;
        presets[4][2][1] = 5;
        presets[4][3][0] = 2;
        presets[4][3][1] = 6;
                
        presets[5][0][0] = 1;
        presets[5][0][1] = 5;
        presets[5][1][0] = 1;
        presets[5][1][1] = 6;
        presets[5][2][0] = 2;
        presets[5][2][1] = 5;
        presets[5][3][0] = 2;
        presets[5][3][1] = 6;
                
        presets[6][0][0] = 1;
        presets[6][0][1] = 5;
        presets[6][1][0] = 1;
        presets[6][1][1] = 6;
        presets[6][2][0] = 2;
        presets[6][2][1] = 5;
        presets[6][3][0] = 2;
        presets[6][3][1] = 6;
    }
    
    
    public void createFigure(int x){
        blocks[0] = new Block(presets[x][0][0], presets[x][0][1]);
        blocks[2] = new Block(presets[x][1][0], presets[x][1][1]);
        blocks[1] = new Block(presets[x][2][0], presets[x][2][1]);
        blocks[3] = new Block(presets[x][3][0], presets[x][3][1]);
        figure = new Figure(blocks);
    }
    
    public Figure getFigure(){
        return figure;
    }
}
