package com.doingfp.desktop.filemgr;

import javax.swing.*;

public class FileManagerWindow extends JFrame {

    public static FileManagerWindow create() {
        return new FileManagerWindow();
    }

    private FileManagerWindow() {
        initComponents();
    }

    void initComponents() {
       setTitle("File manager");
       setBounds(200, 200, 640, 480);
    }
}
