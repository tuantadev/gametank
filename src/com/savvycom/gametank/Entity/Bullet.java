package com.savvycom.gametank.Entity;

import com.savvycom.gametank.Common.Item;
import com.savvycom.gametank.Common.TypeItem;

import java.awt.*;

public class Bullet extends Item {
    private Orientation orientation;

    public Bullet(TypeItem typeItem, int x, int y, int size, Orientation orientation) {
        super(typeItem, x, y, size);
        this.orientation = orientation;
    }

    public void moveBullet() {
        switch (orientation) {
            case UP:
                y -= 2;
                break;
            case LEFT:
                x -= 2;
                break;
            case RIGHT:
                x += 2;
                break;
            default:
                y += 2;
                break;
        }
    }

}
