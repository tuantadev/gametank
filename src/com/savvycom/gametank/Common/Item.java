package com.savvycom.gametank.Common;

import com.savvycom.gametank.Entity.Orientation;

import java.awt.*;

public class Item {
    private TypeItem typeItem;
    private Image image;
    protected int x,y;
    protected int size;
    private int id;
    private Orientation orientation;

    public Item(TypeItem typeItem, int x, int y, int size) {
        this.typeItem = typeItem;
        this.image = typeItem.getImageFromType(this.typeItem);
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public TypeItem getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(TypeItem typeItem) {
        this.typeItem = typeItem;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y, size, size,null);
    }
}
