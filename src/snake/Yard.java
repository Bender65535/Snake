package snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {
    //每个格子的大小
    public static final int NodeSize=15;
    //格子数量
    public static final int NodeCount=30;
    public static final int AreaSize=NodeSize*NodeCount;

    public static int x= AreaSize/2;
    public static int y =AreaSize/2;

    Egg e=new Egg(10,10);
    Snake snake=new Snake();

    public static void main(String[] args) {
        new Yard();
    }

    Yard(){
        this.setSize(2*AreaSize,2*AreaSize);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });

        while(true){
            //每调用一次,就调用一次paint
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        //重画背景
        Color c=g.getColor();
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(c);
        //画出网格
        for(int i=0;i<=NodeCount;i++){
            g.drawLine(x,y+NodeSize*i,x+ AreaSize,y+NodeSize*i);
            g.drawLine(x+NodeSize*i,y,x+NodeSize*i,y+AreaSize);
        }

        snake.paint(g);
        e.paint(g);

        snake.eat(e);

        g.setColor(c);
    }


    //双缓冲,消除闪烁
    //先将图片画在缓冲中
    Image offScreenImage=null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage=this.createImage(this.getWidth(),this.getHeight());
        }
        Graphics graphics=offScreenImage.getGraphics();
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }
}
