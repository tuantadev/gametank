package com.savvycom.gametank.Common;

import javax.swing.*;
import java.awt.*;

public enum TypeItem {
    ROCK(5), BRICK(1), WATER(2), TREE(4), BIRD(9), LEFTTANK(6), RIGHT_TANK(7), UP_TANK(8), DOWN_TANK(10), LEFT_ENEMY_TANK(11),
    RIGHT_ENEMY_TANK(12), UP_ENEMY_TANK(13), DOWN_ENEMY_TANK(14), BULLET(15);
    private int value;

    TypeItem(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TypeItem findTypeFromValue(int value) {
        switch (value) {
            case 5:
                return ROCK;
            case 1:
                return BRICK;
            case 4:
                return TREE;
            case 2:
                return WATER;
            case 9:
                return BIRD;
            case 6:
                return LEFTTANK;
            case 7:
                return RIGHT_TANK;
            case 8:
                return UP_TANK;
            case 10:
                return DOWN_TANK;
            case 11:
                return LEFT_ENEMY_TANK;
            case 12:
                return RIGHT_ENEMY_TANK;
            case 13:
                return UP_ENEMY_TANK;
            case 14:
                return DOWN_ENEMY_TANK;
            case 15:
                return BULLET;
            default:
                return null;
        }
    }

    public static Image getImageFromType(TypeItem typeItem) {
        switch (typeItem) {
            case ROCK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/rock.png")).getImage();
            case BRICK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/brick.png")).getImage();
            case WATER:
                return new ImageIcon(TypeItem.class.getResource("/imgs/water.png")).getImage();
            case TREE:
                return new ImageIcon(TypeItem.class.getResource("/imgs/tree.png")).getImage();
            case BIRD:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bird.png")).getImage();
            case LEFTTANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/player_green_left.png")).getImage();
            case RIGHT_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/player_green_right.png")).getImage();
            case UP_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/player_green_up.png")).getImage();
            case DOWN_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/player_green_down.png")).getImage();
            case LEFT_ENEMY_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bossyellow_left.png")).getImage();
            case RIGHT_ENEMY_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bossyellow_right.png")).getImage();
            case UP_ENEMY_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bossyellow.up.png")).getImage();
            case DOWN_ENEMY_TANK:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bossyellow_down.png")).getImage();
            case BULLET:
                return new ImageIcon(TypeItem.class.getResource("/imgs/bullet.png")).getImage();
            default:
                return null;

        }
    }
}
