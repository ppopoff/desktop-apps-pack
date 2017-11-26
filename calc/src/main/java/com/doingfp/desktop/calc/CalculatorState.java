package com.doingfp.desktop.calc;

import javax.swing.*;

import static com.doingfp.desktop.calc.BinaryOperator.*;
import static com.doingfp.desktop.calc.Constants.CLEAN_DISPLAY;
import static com.doingfp.desktop.calc.Constants.MAX_DISPLAYED_NUMBERS;

public class CalculatorState {

    // todo: use big decimal instead
    final JTextField display;
    BinaryOperator currentOperator;
    double state;
    double memory;

    public CalculatorState(final JTextField display) {
        this.display = display;
        allClear();
    }

    /**
     * Изменяет состояния калькулятора, позволяя ему подготовиться
     * к выпонению бинарной операции
     *
     * @param operation - название операции
     * @return - сам объект для возможности совершать дальнейшие
     * композиционные действия
     */
    public CalculatorState prepareForBinaryOperation(final BinaryOperator operation) {
        return setBinaryOperator(operation)
                .preserveDisplayState().resetDisplay();
    }

    public CalculatorState setBinaryOperator(final BinaryOperator operator) {
        this.currentOperator = operator;
        return this;
    }

    public CalculatorState resetDisplay() {
        display.setText(CLEAN_DISPLAY);
        return this;
    }

    public void allClear() {
        resetDisplay();
        state = 0;
        memory = 0;
        currentOperator = NONE;
    }

    public void memoryStore() {
        this.memory = Double.parseDouble(display.getText());
    }

    public void clearMemory() {
        this.memory = 0;
    }

    public void readFromMemory() {
        prettyPrint(memory);
    }

    // todo: add flag that memory is enabled
    // todo: display this flag somewhere
    public void memoryPlus() {
        memory += Double.parseDouble(display.getText());
    }

    public void negate() {
        if (!display.getText().equals("0")) {
            if (display.getText().startsWith("-"))
                display.setText(display.getText().substring(1));
            else
                display.setText("-" + display.getText());
        }
    }

    public void equalsPressed() {
        // read display value
        switch (currentOperator) {
            case NONE: {
                break;
            }
            case PLUS:
                final double additionResult = state + Double.parseDouble(display.getText());
                prettyPrint(additionResult);
                break;

            case MINUS:
                final double substiturionResult = state - Double.parseDouble(display.getText());
                prettyPrint(substiturionResult);
                break;

            case MULTIPLY:
                final double mulplicationResult = state * Double.parseDouble(display.getText());
                prettyPrint(mulplicationResult);
                break;

            case DIVIDE:
                final double divisor = Double.parseDouble(display.getText());
                if (divisor == 0)
                    display.setText("E");
                else
                    prettyPrint(state / divisor);
                break;

            case PERCENT:
                final double percentResult = state / 100 * Double.parseDouble(display.getText());
                prettyPrint(percentResult);
                break;

            default:
        }
    }

    public String getDisplayState() {
        return String.valueOf(state);
    }

    public CalculatorState setState(final String displayState) {
        this.state = Double.parseDouble(displayState);
        return this;
    }

    public CalculatorState preserveDisplayState() {
        setState(display.getText());
        return this;
    }

    // todo String -> Double conversion
    public double getMemory() {
        return memory;
    }

    public CalculatorState setMemory(double memory) {
        this.memory = memory;
        return this;
    }

    public void appendDigitToDisplay(final String digit) {
        if (currentlyDisplayedNumbersCount() <= MAX_DISPLAYED_NUMBERS)
            if (getDisplayedNumber() == 0)
                display.setText(digit);
            else
                display.setText(display.getText() + digit);
    }

    public void removeLastDisplayDigit() {
        final int lastCharacterIndex = currentlyDisplayedNumbersCount() - 1;

        final String displayTextWithoutLastCharacter =
                display.getText().substring(0, lastCharacterIndex);

        display.setText(displayTextWithoutLastCharacter);
    }

    public void addDecimalSeparator() {
        if (!display.getText().contains("."))
            display.setText(display.getText() + ".");
    }

    public int currentlyDisplayedNumbersCount() {
        return display.getText().length();
    }

    public double getDisplayedNumber() {
        return Double.parseDouble(display.getText());
    }

    /**
     * Удаляет последний символ на дисплее, если таковой
     * имеется. Если символ один-единственный, заменяет
     * его нулем.
     */
    public void backspace() {
        if (currentlyDisplayedNumbersCount() == 1)
            resetDisplay();
        else
            removeLastDisplayDigit();
    }

    /**
     * Красиво и правильно отображает введенное значение (с плавающей
     * точкой) на дисплее калькулятора.
     * <p>
     * В случае, если число целое, отсекает плавающую часть
     *
     * @param value - значение которое следует вывести на дислпее
     */
    public void prettyPrint(final double value) {
        final String prerenderedText = String.valueOf(value);
        final boolean isInteger = prerenderedText.endsWith(".0");

        final String prettyPrinted = isInteger
                ? prerenderedText.substring(0, prerenderedText.length() - 2)
                : prerenderedText;

        // todo: разобраться с числами вроде: 1.444443925E7

        display.setText(prettyPrinted);
    }
}
