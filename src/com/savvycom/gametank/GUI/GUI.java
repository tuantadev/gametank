package com.savvycom.gametank.GUI;

import com.savvycom.gametank.Entity.Resize;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame implements Resize {
    public GUI(){
        setTitle("GameTank");
        setSize(Resize.WIDTH_FRAME,Resize.HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MyPanel());
    }
}
