package com.savvycom.gametank.Entity;

import com.savvycom.gametank.Common.TypeItem;

import javax.swing.*;
import java.awt.*;

public enum Orientation {
    LEFT(0), RIGHT(1), UP(2), DOWN(3);
    int value;

    Orientation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int setValue() {
        return this.value = value;
    }

    public static Orientation getOrientation(int orientation) {
        switch (orientation) {
            case 0:
                return LEFT;
            case 1:
                return RIGHT;
            case 2:
                return UP;
            case 3:
                return DOWN;
            default:
                return DOWN;
        }
    }

    public static Image getImageEnemyTank(Orientation orientation) {
        switch (orientation) {
            case LEFT:
                return new ImageIcon(Orientation.class.getResource("/imgs/bossyellow_left.png")).getImage();
            case UP:
                return new ImageIcon(Orientation.class.getResource("/imgs/bossyellow_up.png")).getImage();
            case RIGHT:
                return new ImageIcon(Orientation.class.getResource("/imgs/bossyellow_right.png")).getImage();
            case DOWN:
                return new ImageIcon(Orientation.class.getResource("/imgs/bossyellow_down.png")).getImage();
            default:
                return null;
        }
    }

    public static Image getImageTank(Orientation orientation) {
        switch (orientation) {
            case UP:
                return new ImageIcon(Orientation.class.getResource("/imgs/player_green_up.png")).getImage();
            case LEFT:
                return new ImageIcon(Orientation.class.getResource("/imgs/player_green_left.png")).getImage();
            case RIGHT:
                return new ImageIcon(Orientation.class.getResource("/imgs/player_green_right.png")).getImage();
            case DOWN:
                return new ImageIcon(Orientation.class.getResource("/imgs/player_green_down.png")).getImage();
            default:
                return null;
        }
    }
}
