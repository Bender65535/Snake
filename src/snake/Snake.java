package snake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {

    Node head,tail;
    Dir dir=Dir.L;

    Snake(){
        head=new Node(20,20);
        tail=head;
    }

    public void addToHead(){
        Node n =null;
        switch (dir){
            case L:
                n=new Node(head.row,head.col-1);
                break;
            case U:
                n=new Node(head.row-1,head.col);
                break;
            case R:
                n=new Node(head.row,head.col+1);
                break;
            case D:
                n=new Node(head.row+1,head.col);
                break;
        }

        //将新的蛇头加在原先的蛇上
        n.next=head;
        head.prev=n;
        head=n;
    }

    public void paint(Graphics g) {
        Node n=head;
        while(n!=null){
            n.paint(g);
            n=n.next;
        }
        move();
    }

    private void move() {
        addToHead();
        deleteTail();
        //边界检测
        boundaryCheck();
    }

    private void boundaryCheck() {
        if(head.row<0) head.row=Yard.NodeCount-1;
        if(head.row>Yard.NodeCount-1) head.row=0;
        if(head.col<0) head.col=Yard.NodeCount-1;
        if(head.col>Yard.NodeCount-1) head.col=0;

    }

    private void deleteTail() {
        if(tail==null)
            return;
        tail=tail.prev;
        //打断引用,以防发生内存泄漏
        tail.next.prev=null;
        tail.next=null;
    }

    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                dir=Dir.L;
                break;
            case KeyEvent.VK_UP:
                dir=Dir.U;
                break;
            case KeyEvent.VK_RIGHT:
                dir=Dir.R;
                break;
            case KeyEvent.VK_DOWN:
                dir=Dir.D;
                break;

        }
    }

    public void eat(Egg e) {
        //判断是否吃到
        if(head.row==e.row&&head.col==e.col){
            addToHead();
            //吃完后重新显示
            e.reAppear();
        }
    }
}
