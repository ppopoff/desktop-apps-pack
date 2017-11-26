package com.doingfp.desktop.graphed;

import com.doingfp.desktop.graphed.components.ColorPanel;
import com.doingfp.desktop.graphed.components.ToolBox;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {

    private final JLabel coordinates = new JLabel();

    // Панель на которой мы рисуем
    private final DrawingPanel drawingPanel = new DrawingPanel(coordinates);

    // Контекстное меню, которое вызывается на панели рисования
    private final JPopupMenu contextMenu = new JPopupMenu();
    private final ColorPanel colorPane = new ColorPanel(drawingPanel);
    private final ToolBox toolbox = new ToolBox();
    private final JPanel statusBar = new JPanel();

    ApplicationWindow() {
        initComponents();
    }

    private void initComponents() {
        // Выполняем инициализацию компонентов, только если находимся
        // внутри DispatchThread
        if (SwingUtilities.isEventDispatchThread()) {
            setupWindowParameters();
            initToolBox();     // Рисует панель с инструментами и добавляет ее на форму
            initStatusBar();   // Рисует статус панель
//        initContextMenu(); // Добавляем контекстное меню

//            drawingPanel.setSize(this.getWidth(), this.getHeight());
            this.add(drawingPanel, BorderLayout.CENTER);

            // добавим меню которое ничерта не делает
            setJMenuBar(createJMenu());

            // Позволяет разместить компоненты так чтобы их размер
            // был наиболее близок к preferred
            pack();
        }
    }

    private void setupWindowParameters() {
        setTitle("Micropaint");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        setBounds(1000, 500, 640, 480);
        final BorderLayout layout = new BorderLayout();
        setLayout(layout);
    }

    private void initContextMenu() {
        contextMenu.add(new JMenuItem("Undo"));
        contextMenu.add(new JMenuItem("Copy"));
        contextMenu.add(new JMenuItem("Paste"));
        contextMenu.add(new JMenuItem("Cut"));
        drawingPanel.setComponentPopupMenu(contextMenu);
    }

    private JMenuBar createJMenu() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");

        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Close"));

        final JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));
        editMenu.add(new JMenuItem("Cut"));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        return menuBar;
    }


    /**
     *  Создает верхннюю панель и размещает на ней
     *   - панель с инструментами (слева)
     *   - панель с цветами (справа)
     */
    private void initToolBox() {
        final JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.setMinimumSize(new Dimension(640, 33));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(toolbox, BorderLayout.WEST);
        topPanel.add(colorPane, BorderLayout.EAST);

        topPanel.setBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    private void initStatusBar() {
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setMinimumSize(new Dimension(640, 20));
        statusBar.setLayout(new BorderLayout());
        statusBar.add(coordinates, BorderLayout.EAST);
        coordinates.setText("x = 0, y = 0 ");

        statusBar.setBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }
}
