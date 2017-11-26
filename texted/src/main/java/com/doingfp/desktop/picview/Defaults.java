package com.doingfp.desktop.picview;

import java.awt.Dimension;
import java.awt.Font;

public class Defaults {
    public static final int MENU_PREFERRED_WIDTH  = 150;

    public static final int MENU_PREFERRED_HEIGHT = 150;

    public static final Dimension MENU_PREFERRED_SIZE =
        new Dimension(MENU_PREFERRED_WIDTH, MENU_PREFERRED_HEIGHT);

    public static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 22);



    // Operates as singleton
    private Defaults() {}
}
