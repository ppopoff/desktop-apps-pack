package com.doingfp.desktop.graphed.components;

import javax.swing.*;
import java.awt.*;

public class ToolBox extends JPanel {
    private final Font BUTTON_FONT = new Font("Sans", Font.PLAIN, 20);

    private final JButton pencil = new JButton("✎");
    private final JButton palette = new JButton("🎨");

    public ToolBox() {
        initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(150, 30));
        setSize(new Dimension(150, 30));
        setLayout(new GridLayout(0, 2));
        initButtons();
        add(pencil);
        add(palette);
    }

    private void initButtons() {
        pencil.setFont(BUTTON_FONT);
        palette.setFont(BUTTON_FONT);
    }
}
