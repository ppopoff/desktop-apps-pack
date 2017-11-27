package com.doingfp.desktop.calc.numeral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class NumeralSystemPanel extends JPanel {
    public NumeralSystemPanel(final Map<String, JButton> buttons, final JTextField display) {
        this.buttons = buttons;
        this.display = display;

        initComponents();
    }

    private final Map<String, JButton> buttons;
    private final JTextField display;

    // RadioButtons that define the panel state
    private final JRadioButton hex = new JRadioButton("Hex");
    private final JRadioButton dec = new JRadioButton("Dec", true);
    private final JRadioButton oct = new JRadioButton("Oct");
    private final JRadioButton bin = new JRadioButton("Bin");

    private void initComponents() {
        hex.addActionListener(this::switchToHex);
        dec.addActionListener(this::switchToDec);
        oct.addActionListener(this::switchToOct);
        bin.addActionListener(this::switchToBin);

        setLayout(new GridLayout(0, 4));
        add(hex);
        add(dec);
        add(oct);
        add(bin);

        groupRadioButtons();
    }


    /**
     * Для того чтобы RadioButton были взаимоисключающими
     */
    private void groupRadioButtons() {
        final ButtonGroup numberSystemButtonGroup = new ButtonGroup();
        numberSystemButtonGroup.add(hex);
        numberSystemButtonGroup.add(dec);
        numberSystemButtonGroup.add(oct);
        numberSystemButtonGroup.add(bin);
    }


    private void switchToDec(final ActionEvent event) {
        for (int button = 2; button <= 9; ++button) {
            buttons.get(String.valueOf(button)).setEnabled(true);
        }
        buttons.get(".").setEnabled(true);

        // todo: convert the stuff on a display
    }

    private void switchToOct(final ActionEvent event) {
        // Отключаем кнопки
        buttons.get("8").setEnabled(false);
        buttons.get("9").setEnabled(false);
        buttons.get(".").setEnabled(false);

        // Кнопки 1 и 0 никогда не отключаются
        for (int button = 2; button <= 7; ++button) {
            buttons.get(String.valueOf(button)).setEnabled(true);
        }
        //todo: convert the stuff on a display
    }

    private void switchToBin(final ActionEvent event) {
        buttons.get(".").setEnabled(false);

        // Отключаем все кроме 1 и 0
        for (int button = 2; button <= 9; ++button) {
            buttons.get(String.valueOf(button)).setEnabled(false);
        }
        // todo: convert the stuff on a display
    }

    private void switchToHex(final ActionEvent event) {
        // todo: enable hex panel first
    }
}
