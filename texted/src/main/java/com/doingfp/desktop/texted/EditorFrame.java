package com.doingfp.desktop.texted;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.*;


public class EditorFrame extends JFrame {

    public static JFrame create() {
        return new EditorFrame();
    }

    public static JFrame createWithTabs (final Path... files) {
        if (files.length <= 0) {
            return create();
        } else {
            final JFrame editorFrame = create();

            // todo: Open files
            editorFrame.setTitle(files[0].getFileName().toString());
            return editorFrame;
        }
    }

    private EditorFrame() {
        initComponents();
    }

    // this is the windows guys. Say hi to type aliasing :)
    private final JFrame window = this;
    private JTextArea textArea = new JTextArea();
    private UndoManager undoManager = new UndoManager();

    private void initComponents() {
         // Прошу обратить внимание, приложение дожно закрываться
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("New document");

        // отступ считается от верхнего левого угла монитора
        // отступ по x, отступ по y, длина окна, ширина окна
        window.setBounds(1000, 500, 640, 480);
        window.setLayout(new BorderLayout(15, 15));


        final JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        final JPopupMenu popupMenu = initPopupMenu();

        setTextAreaOptions();
        textArea.setComponentPopupMenu(popupMenu);
        textAreaScrollPane.setPreferredSize(new Dimension(textArea.getWidth(), textArea.getHeight()));
        textAreaScrollPane.createVerticalScrollBar();
        window.add(textAreaScrollPane, BorderLayout.CENTER);

        final JMenuBar menuBar = initMenuBar();
        window.setJMenuBar(menuBar);
        window.setVisible(true);
    }


    private void setTextAreaOptions() {
        textArea.setAutoscrolls(true);
        textArea.getDocument().addUndoableEditListener(undoManager);

        textArea.setSize(window.getWidth(), window.getHeight());
        textArea.setEditable(true);
        textArea.setFont(Defaults.DEFAULT_FONT);
    }


    private JMenuBar initMenuBar() {
        final JMenuBar applicationMenuBar = new JMenuBar();
        final JMenu fileMenu = initFileMenu();
        final JMenu editMenu = initEditMenu();
        final JMenu helpMenu = new JMenu("Help");

        helpMenu.add(new JMenuItem("Хуй"));
        helpMenu.add(new JMenuItem("Пизда"));
        helpMenu.add(new JMenuItem("Джигурда"));

        applicationMenuBar.add(fileMenu);
        applicationMenuBar.add(editMenu);

        // todo: figure out why it throws an exception
//        applicationMenuBar.setHelpMenu(helpMenu);

        return applicationMenuBar;
    }

    private JMenu initFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Close"));

        return fileMenu;
    }



    private JMenu initEditMenu() {
        final JMenu editMenu = new JMenu("Edit");
        final JMenuItem undoButton = new JMenuItem("Undo");
        final JMenuItem redoButton = new JMenuItem("Redo");
        undoButton.addActionListener(evt -> undoManager.undo());
        redoButton.addActionListener(evt -> undoManager.redo());

        editMenu.add(undoButton);
        editMenu.add(redoButton);

        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));
        editMenu.add(new JMenuItem("Cut"));

        return editMenu;
    }

    private JPopupMenu initPopupMenu() {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setPreferredSize(Defaults.MENU_PREFERRED_SIZE);

        final JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.addActionListener(evt -> undoManager.undo());
        undoItem.setMnemonic(KeyEvent.VK_Z);
        popupMenu.add(undoItem);

        final JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.addActionListener(evt -> undoManager.redo());
        redoItem.setMnemonic(KeyEvent.VK_Y);
        popupMenu.add(redoItem);

        final JMenuItem copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        copyItem.setText("Copy");
        copyItem.setMnemonic(KeyEvent.VK_COPY);
        copyItem.addActionListener(evt -> {
            textArea.getSelectedText();
        });
        popupMenu.add(copyItem);

        final JMenuItem pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        pasteItem.setText("Paste");
        pasteItem.setMnemonic(KeyEvent.VK_PASTE);
        popupMenu.add(pasteItem);

        final JMenuItem cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
        cutItem.setText("Cut");
        cutItem.setMnemonic(KeyEvent.VK_CUT);
        popupMenu.add(cutItem);;

        final JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(evt -> textArea.selectAll());
        popupMenu.add(selectAllItem);

        popupMenu.pack();
        return popupMenu;
    }
}
