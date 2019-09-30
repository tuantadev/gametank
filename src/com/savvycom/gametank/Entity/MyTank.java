package com.savvycom.gametank.Entity;

import com.savvycom.gametank.Common.Item;
import com.savvycom.gametank.Common.TypeItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyTank extends Item {
    private Orientation orientation;
      List<Bullet> bullets;
    private Rectangle rectTank;
    private Rectangle rectBird;
    private boolean isInteract;

    public MyTank(TypeItem typeItem, int x, int y, int size, Orientation orientation) {
        super(typeItem, x, y, size);
        this.orientation = orientation;
        bullets = new ArrayList<>();
    }

    public void move(Orientation orientation, List<Item> items) {
        this.orientation = orientation;
        setImage(Orientation.getImageTank(orientation));
        switch (orientation) {
            case LEFT:
                x -= 1;
               boolean isInteract = interactWithItem(items);
                if (isInteract) {
                    x += 1;
                }
                break;
            case RIGHT:
                x += 1;
                isInteract = interactWithItem(items);
                if (isInteract) {
                    x -= 1;
                }
                break;
            case UP:
                y -= 1;
                isInteract = interactWithItem(items);
                if (isInteract) {
                    y += 1;
                }
                break;
            case DOWN:
                y += 1;
                isInteract = interactWithItem(items);
                if (isInteract) {
                    y -= 1;
                }
                break;
        }
    }

    public void fireBullet() {
        int xBullet, yBullet;
        Orientation orBullet = orientation;
        int sizeBullet = 7;
        switch (orientation) {
            case RIGHT:
                xBullet = x + size;
                yBullet = y + (size - sizeBullet) / 2;
                break;
            case LEFT:
                xBullet = x - sizeBullet;
                yBullet = y + (size - sizeBullet) / 2;
                break;
            case UP:
                xBullet = x + (size - sizeBullet) / 2;
                yBullet = y - sizeBullet;
                break;
            default:
                xBullet = x + (size - sizeBullet) / 2;
                yBullet = y + size;
                break;
        }
        Bullet bullet = new Bullet(TypeItem.BULLET, xBullet, yBullet, sizeBullet, orientation);
        bullets.add(bullet);
    }

    public void drawAllBullet(Graphics2D g2d) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g2d);
        }
    }

    public void moveAllBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.moveBullet();
        }
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

    public boolean killEnemyTank(EnemyTank enemyTank) {
        Rectangle rectEnemyTank = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.getSize(), enemyTank.getSize());
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle RectBullet = new Rectangle(bullet.getX(), enemyTank.getY(), enemyTank.getSize(), enemyTank.getSize());
            if (rectEnemyTank.intersects(RectBullet)) {
                return true;
            }
        }
        return false;
    }

    public boolean killMyTank(MyTank myTank) {
        rectTank = new Rectangle(myTank.getX(), myTank.getY(), myTank.getSize(), myTank.getSize());
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Rectangle rectBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getSize(), bullet.getSize());
            if (rectTank.intersects(rectBullet)) {
                return true;
            }
        }
        return false;
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
                Rectangle rectItem = new Rectangle(item.getX(), item.getY(), item.getSize(), item.getSize());
                if (rectBullet.intersects(rectItem)) {
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

    public boolean interactWithItem(List<Item> items) {
        Rectangle recItem = new Rectangle(getX(), getY(), getSize(), getSize());
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getTypeItem() == TypeItem.TREE) {
                continue;
            }
            Rectangle rectItems = new Rectangle(item.getX(), item.getY(), item.getSize(), item.getSize());
            if (recItem.intersects(rectItems)) {
                return true;
            }
        }
        return false;
    }
}
