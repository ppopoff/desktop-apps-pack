package com.doingfp.desktop.texted;

import static javax.swing.SwingUtilities.invokeLater;

class Application {
    public static void main(final String[] args) {
        invokeLater(() -> EditorFrame.create().setVisible(true));
    }
}
