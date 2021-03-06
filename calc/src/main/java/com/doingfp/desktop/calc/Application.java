package com.doingfp.desktop.calc;

import javax.swing.*;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        // создание GUI должно работать в отдельном потоке, для того
        // чтобы не было дедлоков и состояний гонок
        SwingUtilities.invokeLater(() -> {
            // Устанавливаем внешний вид отображаемых компонентов окна
            setApplicationLookAndFeel();

            // Здесь мы создаем новый экземпляр объекта ApplicationWindow
            // не присваивая его. (А зачем?) И на только что созданном
            // окне вызываем метод setVisible(true) для того чтобы показать
            // его пользователю
            CalculatorWindow.create().setVisible(true);
        });
    }

    /**
     * Устанавливает Look and Feel для приложения. (Способ отображения
     * стандартных элементов окна).
     * https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    private static void setApplicationLookAndFeel() {
        Arrays.stream(UIManager.getInstalledLookAndFeels())
            .forEach(Application::useNimbusLookAndFeel);
    }

    private static void useNimbusLookAndFeel(final UIManager.LookAndFeelInfo info) {
        try {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
            }
        } catch (final Exception e) {
            System.err.println("Unable to setup look and feel: " + e.getMessage());
        }
    }
}
