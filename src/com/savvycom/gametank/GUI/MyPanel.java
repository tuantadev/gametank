package com.savvycom.gametank.GUI;

import com.savvycom.gametank.Entity.Orientation;
import com.savvycom.gametank.Entity.Resize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MyPanel extends JPanel implements Resize, Runnable {
    private ManagerItem managerItem;
    private Thread thread;
    private boolean isLeft, isRight, isDown, isUp, isSpace;


    public MyPanel() {
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setBackground(Color.BLACK);
        try {
            managerItem = new ManagerItem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        isLeft = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRight = true;
                        break;
                    case KeyEvent.VK_UP:
                        isUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDown = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpace = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        isLeft = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRight = false;
                        break;
                    case KeyEvent.VK_UP:
                        isUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDown = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpace = false;
                        break;
                }
            }
        });
        setRequestFocusEnabled(true);
        setFocusable(true);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        managerItem.drawMyTank(graphics2D);
        managerItem.drawEnemyAllTank(graphics2D);
        managerItem.drawBulletMyTank(graphics2D);
        managerItem.drawAllBulletEnemyTank(graphics2D);
        managerItem.drawAll(graphics2D);
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveMyTank();
            moveBulletTank();
            fireMyTank();
            managerItem.interactBulletMyTank();
            managerItem.moveAllEnemyTank();
            managerItem.moveAllBulletEnemyTank();
            managerItem.fireEnemyTank();
            managerItem.interactBulletAllEnemyTank();
            managerItem.killEnemyTank();
            if(managerItem.checkGameOver()){
                JOptionPane.showMessageDialog(MyPanel.this,"Game Over");
                break;
            }
            if(managerItem.checkWin()){
                JOptionPane.showMessageDialog(MyPanel.this,"Win");
                break;
            }
            repaint();
        }
    }
    public void moveMyTank(){
        if(isDown){
            managerItem.moveMyTank(Orientation.DOWN);
        }else {
            if(isUp){
                managerItem.moveMyTank(Orientation.UP);
            }else {
                if(isRight){
                    managerItem.moveMyTank(Orientation.RIGHT);
                }else {
                    if(isLeft){
                        managerItem.moveMyTank(Orientation.LEFT);
                    }
                }
            }
        }
    }
    public void moveBulletTank(){
        managerItem.movebulletMytank();
    }
public void fireMyTank(){
        if(isSpace){
            managerItem.fireBullet();
        }
}

}
