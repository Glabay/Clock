package program.clock;

import javax.swing.*;

public class Clock {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Clock::CreateAndRunGUI);
    }

    public static void CreateAndRunGUI() {
        JFrame clock = new FrameWork();
        clock.setSize(323, 500);
        clock.setLocation(100, 100);
        clock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clock.setVisible(true);
        clock.setResizable(false);
    }
}
