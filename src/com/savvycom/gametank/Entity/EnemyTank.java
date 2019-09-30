package com.savvycom.gametank.Entity;

import com.savvycom.gametank.Common.Item;
import com.savvycom.gametank.Common.TypeItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Item {
    private Orientation orientation;
    private List<Bullet> bullets;
    private Rectangle rectMyTank;
    private Rectangle rectBird;

    public EnemyTank(TypeItem typeItem, int x, int y, int size, Orientation orientation) {
        super(typeItem, x, y, size);
        this.orientation = orientation;
        bullets = new ArrayList<>();
    }

    public void moveEnemyTank(List<Item> items) {
        switch (orientation) {
            case LEFT:
                x -= 1;
                boolean isInteract = interactWithItems(items);
                if (isInteract) {
                    x += 1;
                    changeOri(items);
                } else {
                    Random rd = new Random();
                    int rdInt = rd.nextInt(10000);
                    if (rdInt > 9888) {
                        changeOri(items);
                    }
                }
                break;
            case RIGHT:
                x += 1;
                isInteract = interactWithItems(items);
                if (isInteract) {
                    x -= 1;
                    changeOri(items);
                } else {
                    Random rd = new Random();
                    int rdInt = rd.nextInt(10000);
                    if (rdInt > 9888) {
                        changeOri(items);
                    }
                }
                break;
            case UP:
                y -= 1;
                isInteract = interactWithItems(items);
                if (isInteract) {
                    y -= 1;
                    changeOri(items);
                } else {
                    Random rd = new Random();
                    int rdInt = rd.nextInt(10000);
                    if (rdInt > 9888) {
                        changeOri(items);
                    }
                }
                break;
            default:
                y += 1;
                isInteract = interactWithItems(items);
                if (isInteract) {
                    y += 1;
                    changeOri(items);
                } else {
                    Random rd = new Random();
                    int rdInt = rd.nextInt(10000);
                    if (rdInt > 9888) {
                        changeOri(items);
                    }
                }
                break;
        }
    }

    public void fireBullet() {
        int xBullet;
        int yBullet;
        int sizeBullet = 7;
        Orientation oriBullet = orientation;
        switch (orientation) {
            case LEFT:
                xBullet = x - sizeBullet;
                yBullet = y + (size - sizeBullet) / 2;
                break;
            case RIGHT:
                xBullet = x + size;
                yBullet = y + (size - sizeBullet) / 2;
                break;
            case UP:
                xBullet = x + (size - sizeBullet) / 2;
                yBullet = y - size;
                break;
            default:
                xBullet = x + (size - sizeBullet) / 2;
                yBullet = y + size;
                break;
        }
        Bullet bullet = new Bullet(TypeItem.BULLET, xBullet, yBullet, sizeBullet, oriBullet);
        bullets.add(bullet);
    }

    public void changeOri(List<Item> items) {
        Random rd = new Random();
        int i = rd.nextInt(3) + 1;
        int ori = (orientation.getValue() + i) % 4;
        orientation = Orientation.getOrientation(ori);
        setImage(Orientation.getImageEnemyTank(orientation));
    }

    public void drawAllbullet(Graphics2D graphics2D) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(graphics2D);
        }
    }

    public void moveAllBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.moveBullet();
        }
    }

    public boolean killMyTank(MyTank myTank) {
        rectMyTank = new Rectangle(myTank.getX(), myTank.getY(), myTank.getSize(), myTank.getSize());
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle rectBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            if (rectMyTank.intersects(rectBullet)) {
                return true;
            }
        }
        return false;
    }

    public boolean killBird(Item bird) {
        rectBird = new Rectangle(bird.getX(), bird.getY(), bird.getSize(), bird.getSize());
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle rectBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            if (rectBird.intersects(rectBullet)) {
                return true;
            }
        }
        return false;
    }

    public void interactBullet(List<Item> items) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle rectBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            for (int j = 0; j < items.size(); j++) {
                Item item = items.get(j);
                if (item.getTypeItem() == TypeItem.TREE) {
                    continue;
                }
                if (item.getTypeItem() == TypeItem.WATER) {
                    continue;
                }
                Rectangle rectItems = new Rectangle(item.getX(), item.getY(), item.getSize(), item.getSize());
                if (rectBullet.intersects(rectItems)) {
                    if (item.getTypeItem() == TypeItem.ROCK) {
                        bullets.remove(i);
                        return;
                    }
                    if (item.getTypeItem() == TypeItem.BRICK) {
                        bullets.remove(i);
                        items.remove(j);
                        return;
                    }
                }
            }
        }
    }

    public boolean interactWithItems(List<Item> items) {
        Rectangle rectItem = new Rectangle(x, y, getSize(), getSize());
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getTypeItem() == TypeItem.TREE) {
                continue;
            }
            Rectangle rectItems = new Rectangle(item.getX(), item.getY(), item.getSize(), item.getSize());
            if (rectItem.intersects(rectItems)) {
                return true;
            }
        }
        return false;
    }
}
