package com.doingfp.desktop.picview;

import static javax.swing.SwingUtilities.invokeLater;

class Application {
    public static void main(final String[] args) {
        invokeLater(() -> ViewerFrame.create().setVisible(true));
    }
}
