package com.doingfp.desktop.filemgr;

import static javax.swing.SwingUtilities.invokeLater;

class Application {
    public static void main(final String[] args) {
        invokeLater(() -> FileManagerWindow.create().setVisible(true));
    }
}
