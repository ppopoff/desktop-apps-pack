package com.doingfp.desktop.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.doingfp.desktop.calc.Constants.*;


public class CalculatorWindow extends JFrame {
    private JTextField display = new JTextField();
    private CalculatorState calculatorState = new CalculatorState(display);
    private Map<String, JButton> buttons = new HashMap<>();

    public Map<String, JButton> getButtons() {
        return Collections.unmodifiableMap(buttons);
    }

    public static CalculatorWindow create() {
        return new CalculatorWindow();
    }

    private CalculatorWindow() {
        // Инициализируем, только если находимся внутри Dispatch thread
        if (SwingUtilities.isEventDispatchThread())
            initComponents();
    }

    private void initComponents() {
        setJMenuBar(createMenuBar());

        // Главное установить раслкадку до того как мы будем что-либо
        // куда-либо добавлять
        setLayout(new GridBagLayout());
        final GridBagConstraints mainPanelConstraints = new GridBagConstraints();

        // setup display and put it to the right position
        createDisplay();
        mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanelConstraints.weightx = 0.5;
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridy = 0;
        mainPanelConstraints.gridwidth = 3; // у нас три панели с кнопками
        add(display, mainPanelConstraints);

        final JPanel numberSystemsPanel = initNumeralSystemPanel();
        mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanelConstraints.weightx = 0.5;
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridy = 1;
        mainPanelConstraints.gridwidth = 2;
        add(numberSystemsPanel, mainPanelConstraints);

        mainPanelConstraints.fill = GridBagConstraints.BOTH;

        // tools like C , -> , shift and etc
        final JPanel toolsPanel = initToolPanel();
        mainPanelConstraints.weightx = 0.5;
        mainPanelConstraints.gridx = 0;
        mainPanelConstraints.gridy = 2;
        mainPanelConstraints.gridwidth = 1;
        add(toolsPanel, mainPanelConstraints);

        // Setting up numeric panel
        final JPanel standardPanel = initStandardPanel();
        mainPanelConstraints.weightx = 0.5;
        mainPanelConstraints.gridx = 2;
        mainPanelConstraints.gridy = 2;
        mainPanelConstraints.gridwidth = 1;
        add(standardPanel, mainPanelConstraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE);
        setPreferredSize(WINDOW_SIZE);
        setResizable(true);

        pack();
    }

    private void createDisplay() {
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(DISPLAY_FONT);
        display.setText(CLEAN_DISPLAY);
        display.setEditable(false);
    }

    private JPanel initToolPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        final JButton clearButton = new JButton(ButtonLabels.CLEAR_BUTTON);
        buttons.put(ButtonLabels.CLEAR_BUTTON, clearButton);
        clearButton.setFont(DEFAULT_FONT);
        clearButton.setSize(DEFAULT_BUTTON_SIZE);
        clearButton.addActionListener(evt -> calculatorState.resetDisplay());
        panel.add(clearButton);

        final JButton backspaceButton = new JButton(ButtonLabels.BACKSPACE_BUTTON);
        buttons.put(ButtonLabels.BACKSPACE_BUTTON, backspaceButton);
        backspaceButton.setFont(DEFAULT_FONT);
        backspaceButton.addActionListener(evt -> calculatorState.backspace());
        panel.add(backspaceButton);

        final JButton acButton = new JButton(ButtonLabels.ALL_CLEAR_BUTTON);
        buttons.put(ButtonLabels.ALL_CLEAR_BUTTON, acButton);
        acButton.setFont(DEFAULT_FONT);
        acButton.addActionListener(evt -> calculatorState.allClear());
        panel.add(acButton);

        final JButton msButton = new JButton(ButtonLabels.MEMORY_STORE_BUTTON);
        buttons.put(ButtonLabels.MEMORY_STORE_BUTTON, msButton);
        msButton.setFont(DEFAULT_FONT);
        msButton.addActionListener(evt -> calculatorState.memoryStore());
        panel.add(msButton);

        final JButton openParButton = new JButton(ButtonLabels.OPEN_PAREN_BUTTON);
        buttons.put(ButtonLabels.OPEN_PAREN_BUTTON, openParButton);
        openParButton.setFont(DEFAULT_FONT);
        openParButton.addActionListener(evt -> {
            //todo:
        });
        panel.add(openParButton);

        final JButton mcButton = new JButton(ButtonLabels.MEMORY_CLEAN_BUTTON);
        buttons.put(ButtonLabels.MEMORY_CLEAN_BUTTON, mcButton);
        mcButton.setFont(DEFAULT_FONT);
        mcButton.addActionListener(evt -> calculatorState.clearMemory());
        panel.add(mcButton);

        final JButton closeParButton = new JButton(ButtonLabels.CLOSE_PAREN_BUTTON);
        buttons.put(ButtonLabels.CLOSE_PAREN_BUTTON, closeParButton);
        closeParButton.setFont(DEFAULT_FONT);
        closeParButton.addActionListener(evt -> {
            //todo:
        });
        panel.add(closeParButton);

        final JButton mrButton = new JButton(ButtonLabels.MEMORY_READ_BUTTON);
        buttons.put(ButtonLabels.MEMORY_READ_BUTTON, mrButton);
        mrButton.setFont(DEFAULT_FONT);
        mrButton.addActionListener(evt -> calculatorState.readFromMemory());
        panel.add(mrButton);

        final JButton negateButton = new JButton(ButtonLabels.PLUS_MINUS_BUTTON);
        buttons.put(ButtonLabels.PLUS_MINUS_BUTTON, negateButton);
        negateButton.setFont(DEFAULT_FONT);
        negateButton.addActionListener(evt -> calculatorState.negate());
        panel.add(negateButton);

        final JButton mplusButton = new JButton(ButtonLabels.MEMORY_PLUS_BUTTON);
        buttons.put(ButtonLabels.MEMORY_PLUS_BUTTON, mplusButton);
        mplusButton.setFont(DEFAULT_FONT);
        mplusButton.addActionListener(evt -> calculatorState.memoryPlus());
        panel.add(mplusButton);

        return panel;
    }


    private JPanel initNumeralSystemPanel() {
        final JPanel panel = new JPanel();
        final ButtonGroup numberSystemButtonGroup = new ButtonGroup();

        final JRadioButton hex = new JRadioButton("Hex");
        //todo: no hex buttons created        hex.addActionListener();


        final JRadioButton dec = new JRadioButton("Dec", true);
        dec.addActionListener(evt -> {
            for (int button = 2; button <= 9; ++button) {
                buttons.get(String.valueOf(button)).setEnabled(true);
            }
        });

        // todo: Поменять режим объекту калькулятора
        final JRadioButton oct = new JRadioButton("Oct");
        oct.addActionListener(evt -> {
            // Отключаем кнопки
            buttons.get("8").setEnabled(false);
            buttons.get("9").setEnabled(false);

            // Кнопки 1 и 0 никогда не отключаются
            for (int button = 2; button <= 7; ++button) {
                buttons.get(String.valueOf(button)).setEnabled(true);
            }
        });

        final JRadioButton bin = new JRadioButton("Bin");
        oct.addActionListener(evt -> {
            // Отключаем все кроме 1 и 0
            for (int button = 2; button <= 9; ++button) {
                buttons.get(String.valueOf(button)).setEnabled(false);
            }
        });

        // Для того чтобы RadioButton были взаимоисключающими
        numberSystemButtonGroup.add(hex);
        numberSystemButtonGroup.add(dec);
        numberSystemButtonGroup.add(oct);
        numberSystemButtonGroup.add(bin);

        panel.setLayout(new GridLayout(0, 4));

        panel.add(hex);
        panel.add(dec);
        panel.add(oct);
        panel.add(bin);

        return panel;
    }

    /**
     * NumericSection подразумевает собой панель с цифровыми
     * кнопками, а так же клавишу '.' для десятичного разделителя
     */
    private JPanel initStandardPanel() {
        final JPanel panel = new JPanel();
        for (int number = 0; number <= 9; ++number) {
            buttons.put(String.valueOf(number), createNumericButton(number));
        }

        final GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.BOTH;

        // % button
        final JButton percentButton = new JButton(ButtonLabels.PERCENT_LABEL);
        percentButton.addActionListener(evt ->
            calculatorState.prepareForBinaryOperation(BinaryOperator.PERCENT)
        );
        buttons.put(ButtonLabels.PERCENT_LABEL, percentButton);
        percentButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(percentButton, constraints);

        final JButton divisionButton = new JButton(ButtonLabels.DIVISION_LABEL);
        divisionButton.addActionListener(evt ->
            calculatorState.prepareForBinaryOperation(BinaryOperator.DIVIDE)
        );
        buttons.put(ButtonLabels.DIVISION_LABEL, divisionButton);
        divisionButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(divisionButton, constraints);


        final JButton multiplicationButton = new JButton(ButtonLabels.MULTIPLICATION_LABEL);
        multiplicationButton.addActionListener(evt ->
            calculatorState.prepareForBinaryOperation(BinaryOperator.MULTIPLY));
        buttons.put(ButtonLabels.MULTIPLICATION_LABEL, multiplicationButton);
        multiplicationButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(multiplicationButton, constraints);

        final JButton minusButton = new JButton(ButtonLabels.MINUS_LABEL);
        minusButton.addActionListener(evt ->
            calculatorState.prepareForBinaryOperation(BinaryOperator.MINUS));
        buttons.put(ButtonLabels.MINUS_LABEL, minusButton);
        minusButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 1;
        panel.add(minusButton, constraints);

        // 7, 8, 9
        for (int offset = 0; offset <= 2; ++offset) {
            constraints.weightx = 0.5;
            constraints.gridx = offset;
            constraints.gridy = 2;
            final String buttonName = String.valueOf(7 + offset);
            panel.add(buttons.get(buttonName), constraints);
        }

        // 4, 5, 6
        for (int offset = 0; offset <= 2; ++offset) {
            constraints.weightx = 0.5;
            constraints.gridx = offset;
            constraints.gridy = 3;
            final String buttonName = String.valueOf(4 + offset);
            panel.add(buttons.get(buttonName), constraints);
        }

        // 1, 2, 3
        for (int offset = 0; offset <= 2; ++offset) {
            constraints.weightx = 0.5;
            constraints.gridx = offset;
            constraints.gridy = 4;
            final String buttonName = String.valueOf(1 + offset);
            panel.add(buttons.get(buttonName), constraints);
        }

        // 0
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;  // Две ячейки в ширину
        panel.add(buttons.get("0"), constraints);

        // .
        final JButton dotButton = new JButton(ButtonLabels.DOT_LABEL);
        // dot handler
        dotButton.addActionListener(evt -> {});
        buttons.put(ButtonLabels.DOT_LABEL, dotButton);
        dotButton.setFont(DEFAULT_FONT);
        dotButton.addActionListener(e -> calculatorState.addDecimalSeparator());
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(dotButton, constraints);

        // = button
        final JButton equalsButton = new JButton(ButtonLabels.EQUALS_LABEL);
        equalsButton.addActionListener(evt -> calculatorState.equalsPressed());
        buttons.put(ButtonLabels.EQUALS_LABEL, equalsButton);
        equalsButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        equalsButton.setMnemonic(KeyEvent.VK_ENTER);
        panel.add(equalsButton, constraints);

        // + button
        final JButton plusButton = new JButton(ButtonLabels.PLUS_LABEL);
        plusButton.addActionListener(evt ->
            calculatorState.prepareForBinaryOperation(BinaryOperator.PLUS));
        buttons.put(ButtonLabels.PLUS_LABEL, equalsButton);
        plusButton.setFont(DEFAULT_FONT);
        constraints.weightx = 0.5;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridheight = 2;
        panel.add(plusButton, constraints);

        return panel;
    }

    private JButton createNumericButton(final int fromNumber) {
        final int buttonKey = KeyEvent.VK_0 + fromNumber;
        final String numberLabel = String.valueOf(fromNumber);
        final JButton button = new JButton(numberLabel);
        button.setFont(DEFAULT_FONT);
        button.addActionListener(evt ->
            calculatorState.appendDigitToDisplay(numberLabel));
        button.setMnemonic(buttonKey);
        return button;
    }


    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        // file menu
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem quit = fileMenu.add("Quit");
        quit.addActionListener(evt -> System.exit(0));
        fileMenu.add(quit);
        menuBar.add(fileMenu);

        // edit menu
        final JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        final JMenuItem redo = new JMenuItem("Redo");
        final JMenuItem cut = new JMenuItem("Cut");
        final JMenuItem copy = new JMenuItem("Copy");
        final JMenuItem paste = new JMenuItem("Paste");

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.addSeparator();
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        menuBar.add(editMenu);

        // view menu
        final JMenu viewMenu = new JMenu("View");
        final JMenuItem basicMode = new JRadioButtonMenuItem("Basic", true);
        final JMenuItem programmingMode = new JRadioButtonMenuItem("Programming");
        final JMenuItem scientificMode = new JRadioButtonMenuItem("Scientific");

        final ButtonGroup modesButtonGroup = new ButtonGroup();
        modesButtonGroup.add(basicMode);
        modesButtonGroup.add(programmingMode);
        modesButtonGroup.add(scientificMode);


        viewMenu.add(basicMode);
        viewMenu.add(programmingMode);
        viewMenu.add(scientificMode);
        menuBar.add(viewMenu);


        // todo: add @About jdialog pane
        final JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        return menuBar;
    }
}
