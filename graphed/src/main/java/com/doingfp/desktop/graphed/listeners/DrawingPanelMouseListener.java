package com.doingfp.desktop.graphed.listeners;

import com.doingfp.desktop.graphed.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import static java.awt.RenderingHints.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


/**
 *
 */
public
class DrawingPanelMouseListener extends DrawingMouseListener {

    /**
     * Конструктор для слушателя
     * @param panel
     */
    public DrawingPanelMouseListener (final DrawingPanel panel) {
        this.thePanel = panel;
    }

    // Храним панель на которой рисуем как часть состояния, для того
    // чтобы иметь к ней доступ. Ссылка на панель передается в конструкторе.
    private final DrawingPanel thePanel;

    // Позиции
    // нужно запоминать текущее и предыдущее положение курсора для того чтобы
    // иметь возможность протянуть линию между точками и иметь цельную ломанную
    // линию которая будет приближена к кривой

    // Отрицательные значения для предыдущих значений означают то, что
    // ранее мы не рисовали, или прекратили рисование

    // Предыдущее положение X и Y
    private int prevX = -1;
    private int prevY = -1;

    /**
     * Устанавливает предыдущие значения в отрицательные, тем самым
     * сообщая программе о том, что рисование закончено
     */
    private void stopDrawing() {
        prevX = -1;
        prevY = -1;
    }

    /**
     * Для большей наглядности алиасим метод
     */
    private void startDrawing(final MouseEvent event) {
        updatePosition(event);
    }

    /**
     * Обновляет текущие значения и позволяет начать рисовать
     */
    private void updatePosition (final MouseEvent event) {
        prevX = event.getX();
        prevY = event.getY();
    }

    /**
     * Если хоть что-то отрицательно: не рисовать
     */
    private boolean wasNotDrawing() {
        return prevX < 0 || prevY < 0;
    }


    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        draw(mouseEvent, MouseEvent.MOUSE_CLICKED);
    }

    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        startDrawing(mouseEvent);
    }

    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        stopDrawing();
    }

    @Override
    public void mouseMoved(final MouseEvent event) {
        thePanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        logCoordinates(event);
    }

    @Override
    public void mouseDragged(final MouseEvent event) {
        draw(event, MouseEvent.MOUSE_DRAGGED);
    }


    private void draw(final MouseEvent event, final int eventType) {
        logCoordinates(event);

        final Graphics2D g = (Graphics2D)thePanel.getGraphics();

        // Включаем антиалиасинг (во избежание ступенчатости только что
        // нарисованной линии
        g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setColor(thePanel.getCurrentColor());

        switch (eventType) {
            case MouseEvent.MOUSE_CLICKED:
                g.drawLine(event.getX(), event.getY(), event.getX(), event.getY());
            break;
            case MouseEvent.MOUSE_DRAGGED:
                if (wasNotDrawing()) {
                    stopDrawing();
                    g.drawLine(event.getX(), event.getY(), event.getX(), event.getY());
                } else {
                    g.drawLine(prevX, prevY, event.getX(), event.getY());
                }
                updatePosition(event);
            break;
        }


        g.dispose();
//        thePanel.setImage()
        // saving that to the panel
//        thePanel.print(g);

    }



    private void generateImage(final DrawingPanel p) {
        final int width  = p.getWidth();
        final int height = p.getHeight();

        final BufferedImage bufferedImage =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//        bufferedImage.setData();
    }


    /**
     * Этот метод берет рисовательную панель, которую мы передали в конструктор,
     * получает у этой панели переданную ей табличку для хранения координат,
     * и перезаписывает в ней текст на текущие координаты.
     * @param e - объект "мышиное" событие
     */
    private void logCoordinates(final MouseEvent e) {
        thePanel.getCoorinates().setText(renderCoordinatesFromMouseEvent(e));
    }


    /**
     * Принимает экземпляр класса MouseEvent, извлекает из него текущие
     * координаты, и формирует строку на их основе
     */
    private String renderCoordinatesFromMouseEvent(final MouseEvent e) {
        return "x = " + e.getX() + ", y = " + e.getY() + " ";
    }
}
