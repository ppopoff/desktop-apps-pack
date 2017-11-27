package com.doingfp.desktop.calc;

import java.awt.*;


public class Constants {
    // Общие параметры
    public static Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 20);

    // Параметры окна
    private static int WINDOW_HEIGHT = 315;
    public static Dimension WINDOW_SIZE = new Dimension(400, WINDOW_HEIGHT);

    // Дисплей
    public static int MAX_DISPLAYED_NUMBERS = 19;
    public static Font DISPLAY_FONT = new Font("Consolas", Font.BOLD, 36);
    public static String CLEAN_DISPLAY = "0";

    // Кнопки
    public static Dimension DEFAULT_BUTTON_SIZE = new Dimension(20, 20);

    public static class ButtonLabels {
        public static final String CLEAR_BUTTON = "C";
        public static final String BACKSPACE_BUTTON = "←";
        public static final String ALL_CLEAR_BUTTON = "AC";
        public static final String MEMORY_STORE_BUTTON = "MS";
        public static final String MEMORY_CLEAN_BUTTON = "MC";
        public static final String MEMORY_READ_BUTTON = "MR";
        public static final String MEMORY_PLUS_BUTTON = "M+";
        public static final String PLUS_MINUS_BUTTON = "±";

        public static final String PERCENT_LABEL = "%";
        public static final String PLUS_LABEL = "+";
        public static final String DIVISION_LABEL = "÷";
        public static final String MULTIPLICATION_LABEL = "×";
        public static final String MINUS_LABEL = "-";
        public static final String EQUALS_LABEL = "=";

        public static final String DOT_LABEL = ".";
        //    public static final String BACKSPACE_BUTTON_LABEL = "←";
        public static final String OPEN_PAREN_BUTTON = "(";
        public static final String CLOSE_PAREN_BUTTON = ")";

        // Не даем возможности инстанциировать класс
        private ButtonLabels() {}
    }


    // Приватный конструктор не дает возможности создать объект
    private Constants() { }
}
