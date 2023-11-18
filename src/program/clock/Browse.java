package program.clock;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/*
 * Browse.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class Browse extends JPanel {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
	FrameWork fw;

    public Browse() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();
    }

    @Override
	public String getName() {
		File file = fc.getSelectedFile();
		return file.getName();
	}

	public String getSong() {
		File file = fc.getSelectedFile();
		return "" + fc.getSelectedFile();
	}

    public void browse() {
        int returnVal = fc.showOpenDialog(Browse.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getName());
            System.out.println("File " + fc.getSelectedFile());
        } else {
            log.append("Open command cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());
	}
}