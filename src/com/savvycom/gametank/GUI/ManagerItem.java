package com.savvycom.gametank.GUI;

import com.savvycom.gametank.Common.Item;
import com.savvycom.gametank.Common.TypeItem;
import com.savvycom.gametank.Entity.EnemyTank;
import com.savvycom.gametank.Entity.MyTank;
import com.savvycom.gametank.Entity.Orientation;
import com.savvycom.gametank.Entity.Resize;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerItem implements Resize {
    private List<Item> items;
    private MyTank myTank;
    private List<EnemyTank> enemyTanks;
    private Item bird;
    private TypeItem typeItem;
    private long currentTimeMyTank =0;
    private long currentTimeMoveEnemy;
    private long currentFireEnemyTank;

    public ManagerItem() throws IOException {
        items = new ArrayList<>();
        int x = 200;
        int y = 500;
        Orientation orientation = Orientation.UP;
        typeItem = TypeItem.UP_TANK;
        myTank = new MyTank(typeItem,x,y,35,orientation);
        enemyTanks = new ArrayList<>();
        createEnemy();
        readFile();
    }
    public void createEnemy(){
        int x1 =20;
        int y1=20;
        Orientation ori1 = Orientation.DOWN;
        int size1=36;
        EnemyTank enemyTank1 = new EnemyTank(TypeItem.DOWN_ENEMY_TANK,x1,y1,size1,ori1);
        enemyTanks.add(enemyTank1);

        int x2= 8*20;
        int y2=20;
        Orientation ori2 = Orientation.DOWN;
        int size2 =36;
        EnemyTank enemyTank2 = new EnemyTank(TypeItem.DOWN_ENEMY_TANK,x2,y2,size2,ori2);
        enemyTanks.add(enemyTank2);

        int x3=20*20;
        int y3=20;
        Orientation ori3 = Orientation.DOWN;
        int size3 =36;
        EnemyTank enemyTank3 = new EnemyTank(TypeItem.DOWN_ENEMY_TANK,x3,y3,size3,ori3);
        enemyTanks.add(enemyTank3);

        int x4=13*20;
        int y4 = 80;
        Orientation ori4 = Orientation.DOWN;
        int size4 =36;
        EnemyTank enemyTank4 =new EnemyTank(TypeItem.DOWN_ENEMY_TANK,x4,y4,size4,ori4);
        enemyTanks.add(enemyTank4);

        int x5=4*20;
        int y5 = 14*20;
        Orientation ori5 = Orientation.RIGHT;
        int size5 =36;
        EnemyTank enemyTank5 = new EnemyTank(TypeItem.RIGHT_ENEMY_TANK,x5,y5,size5,ori5);
        enemyTanks.add(enemyTank5);

        int x6=21*20;
        int y6=16*20;
        Orientation ori6 = Orientation.RIGHT;
        int size6 = 36;
        EnemyTank enemyTank6 = new EnemyTank(TypeItem.RIGHT_ENEMY_TANK,x6,y6,size6,ori6);
        enemyTanks.add(enemyTank6);
    }
    public void drawEnemyAllTank(Graphics2D graphics2D){
        for (int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.draw(graphics2D);
        }
    }
    private void readFile() throws IOException {
       BufferedReader rd = new BufferedReader(new InputStreamReader(ManagerItem.class.getResource("/maps/maps.txt").openStream()));
       String content = rd.readLine();
        int index = 0;
        while (content !=null){
            for(int i=0; i < content.length();i++){
                int id = content.charAt(i) -'0';
                if(id == 0){
                    continue;
                }
                int x = i *20;
                int y = index *20;
                Item item;
                if(id == TypeItem.BIRD.getValue()){
                    item = new Item(TypeItem.findTypeFromValue(id),x,y,40);
                    bird = item;
                }
                else {
                    item = new Item(TypeItem.findTypeFromValue(id),x,y,20);
                }
                items.add(item);
            }
            index ++;
            content = rd.readLine();
        }

    }
    public void drawAll(Graphics2D graphics2D){
        for(int i =0;i<items.size();i++){
            Item item = items.get(i);
            item.draw(graphics2D);
        }
    }
    public void drawMyTank(Graphics2D graphics2D){
        myTank.draw(graphics2D);
    }
    public void moveMyTank(Orientation orientation){
        myTank.move(orientation,items);
    }
    public void fireBullet(){
        long time = System.currentTimeMillis();
        if(time - currentTimeMyTank >500){
            myTank.fireBullet();;
            currentTimeMyTank =time;
        }
    }
    public void drawBulletMyTank(Graphics2D graphics2D){
        myTank.drawAllBullet(graphics2D);
    }
    public void movebulletMytank(){
        myTank.moveAllBullet();
    }
    public void interactBulletMyTank(){
        myTank.interactBullet(items);
    }
    public void interactBulletAllEnemyTank(){
        for(int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.interactBullet(items);
        }
    }
    public void moveAllEnemyTank(){
        long current = System.currentTimeMillis();
        if(current - currentTimeMoveEnemy <25){
            return;
        }
        currentTimeMoveEnemy = current;
        for (int i =0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.moveEnemyTank(items);
        }
    }
    public void fireEnemyTank(){
        long current = System.currentTimeMillis();
        if(current - currentFireEnemyTank <3500){
            return;
        }
        currentFireEnemyTank = current;
        for (int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.fireBullet();
        }
    }
    public void drawAllBulletEnemyTank(Graphics2D graphics2D){
        for(int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.drawAllbullet(graphics2D);
        }
    }
    public void moveAllBulletEnemyTank(){
        for (int i = 0;i< enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.moveAllBullet();
        }
    }
    public void killEnemyTank(){
        for (int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
           boolean isKill = myTank.killEnemyTank(enemyTank);
           if(isKill) {
               enemyTanks.remove(i);
               i--;
           }
        }
    }
    public boolean killBird(){
       boolean killFromMyTank = myTank.killBird(bird);
        if(killFromMyTank){
            return true;
            }
        for (int i =0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.killBird(bird)){
                return true;
            }
        }
        return false;
    }
    public boolean checkGameOver(){
        for(int i = 0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            boolean isKill = enemyTank.killMyTank(myTank);
            if(isKill){
                return true;
            }
        }
        if(killBird()){
            return true;
        }
        return false;
    }
    public boolean checkWin(){
        if(enemyTanks.size()==0){
            return true;
        }else {
            return false;
        }
    }
}
