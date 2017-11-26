package com.doingfp.desktop.graphed.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Абстрактные класс позволяет переопределить часть методов в своем
 * теле. Однако, так же как и в случае с интерфейсом экземпляр объекта не
 * сможет быть создан.
 *
 * Данный класс необходим для того, чтобы не оставлять пустые и бездейственные
 * заглушки в рабочих слушателях и не загрязнять их код.
 */
public abstract
class DrawingMouseListener implements MouseListener, MouseMotionListener {

    /**
     * Событие нас не интересует.
     * Поэтому оставим его постым
     */
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) { }


    /**
     * Событие нас не интересует.
     * Поэтому оставим его постым
     */
    @Override
    public void mouseExited(final MouseEvent mouseEvent) { }
}
