package com.doingfp.desktop.picview;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.*;


public class ViewerFrame extends JFrame {

    public static JFrame create() {
        return new ViewerFrame();
    }

    private ViewerFrame() {
       setTitle("Picture viewer");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(200, 200, 640, 480);
    }
}