package com.doingfp.desktop.graphed;

import com.doingfp.desktop.graphed.listeners.DrawingPanelMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;


public class DrawingPanel extends JPanel {

    private final JLabel coorinates;
    private Color currentColor;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void updateImage(final BufferedImage newImage) {
        this.image = newImage;
    }


    public JLabel getCoorinates() {
        return coorinates;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public DrawingPanel setCurrentColor(final Color currentColor) {
        this.currentColor = currentColor;
        return this;
    }


    public Point getPreviousPosition() {
        return previousPosition;
    }

    public DrawingPanel setPreviousPosition(Point previousPosition) {
        this.previousPosition = previousPosition;
        return this;
    }

    private Point previousPosition = new Point(0,0);

    public DrawingPanel(final JLabel coorinatesPanel) {
        super();
        this.setBackground(Color.white);
        setDoubleBuffered(true);

        // Будем в нее передавать данные о координатах
        this.coorinates = coorinatesPanel;

        final DrawingPanelMouseListener drawingPanelMouseListener =
            new DrawingPanelMouseListener(this);

        this.addMouseListener(drawingPanelMouseListener);
        this.addMouseMotionListener(drawingPanelMouseListener);
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        final Graphics2D g = (Graphics2D)graphics;
        super.paintComponent(g);

        // Каждый раз забираем картинку из состояния и отрисовываем ее
        g.drawImage(image, null, 0, 0);
    }

}
