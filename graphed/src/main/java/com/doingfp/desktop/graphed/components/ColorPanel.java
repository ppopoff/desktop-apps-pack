package com.doingfp.desktop.graphed.components;

import com.doingfp.desktop.graphed.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class ColorPanel extends JPanel {

    private final DrawingPanel drawingPanel;

    // Кнопки-палитры
    private final Map<Color, JButton> colorButtons = new HashMap<>();

    // Палетки, которые могут быть заполнены произвольным пользовательским
    // цветом
    private final JButton paletteButton1 = new JButton();
    private final JButton paletteButton2 = new JButton();

    private Color selectedColor = Color.black;

    public ColorPanel(final DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        initComponents();
    }

    private void initComponents() {
        setSize(200, 48);
        setPreferredSize(new Dimension(200, 48));
        setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        setLayout(new GridLayout(2, 0));
        initColorButtons();
    }


    /**
     * Наполняет хеш таблицу кнопками, и их цветами
     */
    private void initColorButtons() {
        colorButtons.put(Color.yellow, new JButton());
        colorButtons.put(Color.black, new JButton());
        colorButtons.put(Color.white, new JButton());
        colorButtons.put(Color.darkGray, new JButton());
        colorButtons.put(Color.lightGray, new JButton());
        colorButtons.put(Color.red, new JButton());
        colorButtons.put(Color.pink, new JButton());
        colorButtons.put(Color.blue, new JButton());
        colorButtons.put(Color.cyan, new JButton());
        colorButtons.put(Color.magenta, new JButton());
        colorButtons.put(Color.green, new JButton());
        colorButtons.put(Color.orange, new JButton());

        colorButtons.forEach((color, button) -> {
            button.setBackground(color);
            button.setMinimumSize(new Dimension(15, 15));
            button.addActionListener(evt -> drawingPanel.setCurrentColor(color));
            this.add(button);
        });

        paletteButton1.setMinimumSize(new Dimension(15, 15));
        this.add(paletteButton1);

        paletteButton2.setMinimumSize(new Dimension(15, 15));
        this.add(paletteButton2);
    }
}
