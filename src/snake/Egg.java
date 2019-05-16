package snake;

import java.awt.*;
import java.util.Random;

public class Egg {
    int row,col;
    Random random=new Random();

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void paint(Graphics g) {
        //初始位置
        int x=Yard.x+col*Yard.NodeSize;
        int y=Yard.y+row*Yard.NodeSize;
        Color c=g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,Yard.NodeSize,Yard.NodeSize);

        //恢复现场
        g.setColor(c);
    }

    public void reAppear() {
        this.row=random.nextInt(Yard.NodeCount);
        this.col=random.nextInt(Yard.NodeCount);

    }
}
