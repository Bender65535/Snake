package snake;

import java.awt.*;

/**
 * 双向链表
 */
public class Node {
    int row,col;


    Node prev,next;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void paint(Graphics g) {
        //初始位置
        int x=Yard.x+col*Yard.NodeSize;
        int y=Yard.y+row*Yard.NodeSize;
        Color c=g.getColor();
        g.setColor(Color.black);
        g.fillRect(x,y,Yard.NodeSize,Yard.NodeSize);

        //恢复现场
        g.setColor(c);

    }
}
