package program.clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.swing.*;

public class FrameWork extends javax.swing.JFrame {

    Browse br = new Browse();

    static String title = "Java Clock ";
    private String AMPM = "AM";

    static String[] hour = {"Hrs", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    static String[] minute = {"Min", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    static String[] ampm = {"AM", "PM"};

    private int hrs;
    private int mins;

    public boolean buzzerActive = false;
    public boolean musicActive = false;

    public FrameWork() {
        super(title);
        initComponents();
        javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                int h = now.get(Calendar.HOUR_OF_DAY);
                int m = now.get(Calendar.MINUTE);
                int s = now.get(Calendar.SECOND);
                if (h > 12) {
                    h = now.get(Calendar.HOUR_OF_DAY) - 12;
                    time.setText("Time: " + h + ":" + m + ":" + s + " PM");
                }
                if (h < 1) {
                    h = 12;
                    time.setText("Time: " + h + ":" + m + ":" + s + " AM");
                } else {
                    time.setText("Time: " + h + ":" + m + ":" + s + " AM");
                }
                date.setText("Date: " + getDate());
            }
        });
        t.start();
        minBox.setSize(45, 20);
        hourBox.setSize(45, 20);
        ampmBox.setSize(45, 20);

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(buzzerButton);
        bGroup.add(musicButton);
    }

    private void initComponents() {

        JLayeredPane jLayeredPane1 = new JLayeredPane();
        time = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        hourBox = new JComboBox<>();
        JLabel hourLabel = new JLabel();
        JLabel minLabel = new JLabel();
        minBox = new JComboBox<>();
        ampmBox = new JComboBox<>();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        JToggleButton activateButton = new JToggleButton();
        buzzerButton = new javax.swing.JRadioButton();
        musicButton = new javax.swing.JRadioButton();
        browseField = new javax.swing.JTextField();
        JLabel fileLabel = new JLabel();
        browseButton = new javax.swing.JButton();
        JLabel background = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        time.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        time.setBounds(90, 20, 140, 20);
        jLayeredPane1.add(time, javax.swing.JLayeredPane.DEFAULT_LAYER);

        date.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        date.setBounds(70, 60, 180, 23);
        jLayeredPane1.add(date, javax.swing.JLayeredPane.DEFAULT_LAYER);

        hourBox.setModel(new DefaultComboBoxModel<>(hour));
        hourBox.addActionListener(this::hourBoxActionPerformed);
        hourBox.setBounds(50, 160, 28, 20);
        jLayeredPane1.add(hourBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        hourLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        hourLabel.setForeground(new java.awt.Color(255, 255, 255));
        hourLabel.setText("Hour");
        hourLabel.setBounds(10, 160, 30, 15);
        jLayeredPane1.add(hourLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        minLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        minLabel.setForeground(new java.awt.Color(255, 255, 255));
        minLabel.setText("Min");
        minLabel.setBounds(130, 160, 30, 20);
        jLayeredPane1.add(minLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        minBox.setModel(new DefaultComboBoxModel<>(minute));
        minBox.addActionListener(this::minBoxActionPerformed);
        minBox.setBounds(160, 160, 28, 20);
        jLayeredPane1.add(minBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ampmBox.setModel(new DefaultComboBoxModel<>(ampm));
        ampmBox.addActionListener(this::ampmBoxActionPerformed);
        ampmBox.setBounds(250, 160, 28, 20);
        jLayeredPane1.add(ampmBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        activateButton.setText("Start!");
        activateButton.addActionListener(this::activateButtonActionPerformed);
        activateButton.setBounds(130, 450, 70, 23);
        jLayeredPane1.add(activateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buzzerButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        buzzerButton.setText("Buzzer");
        buzzerButton.addActionListener(this::buzzerButtonActionPerformed);
        buzzerButton.setBounds(50, 220, 70, 23);
        jLayeredPane1.add(buzzerButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        musicButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        musicButton.setText("Music");
        musicButton.addActionListener(this::musicButtonActionPerformed);
        musicButton.setBounds(50, 250, 70, 23);
        jLayeredPane1.add(musicButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        browseField.setBounds(50, 290, 160, 20);
        jLayeredPane1.add(browseField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fileLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
        fileLabel.setForeground(new java.awt.Color(255, 255, 255));
        fileLabel.setText("File:");
        fileLabel.setBounds(10, 290, 27, 17);
        jLayeredPane1.add(fileLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        browseButton.setText("Browse...");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });
        browseButton.setBounds(213, 290, 90, 23);
        jLayeredPane1.add(browseButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        background.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/program/clock/bin/background.jpg")))); // NOI18N
        background.setBounds(0, 0, 330, 500);
        jLayeredPane1.add(background, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );

        pack();
    }

    private void activateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int Hour = hrs;
        int Minute = mins;
        String ampm = AMPM;
        if (Hour != 0 && ampm != null) {
            System.out.println("Starting" +
                    "\nHour: " + Hour +
                    "\nMin: " + Minute +
                    "\nAmpm: " + ampm +
                    "\nMusic: " + musicActive +
                    "\nBuzzer: " + buzzerActive +
                    "\nAlarm to sound at: " + Hour + ":" + Minute + " " + ampm);
            activate(Hour, Minute, ampm);
        } else {
            JOptionPane.showMessageDialog(this, "You must have a valid time selected", "", JOptionPane.INFORMATION_MESSAGE);
            System.err.println("Error Happened.");
        }
    }

    private void hourBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (evt.getSource() == hourBox) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                String box = (String) cb.getSelectedItem();
                System.out.println("Used: " + box);
                hrs = Integer.parseInt(box);
                System.out.println("Hour: " + hrs);
            }
        } catch (Exception ex) {
            System.out.println("Error happened \n" + ex);
        }
    }

    private void minBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (evt.getSource() == minBox) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                String box = (String) cb.getSelectedItem();
                System.out.println("Used: " + box);
                mins = Integer.parseInt(box);
                System.out.println("Minute: " + mins);
            }
        } catch (Exception ex) {
            System.out.println("Error happened \n" + ex);
        }
    }

    private void ampmBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (evt.getSource() == ampmBox) {
                JComboBox<String> cb = (JComboBox<String>) evt.getSource();
                String box = (String) cb.getSelectedItem();
                System.out.println("Used: " + box);
                AMPM = box;
                System.out.println("AMPM: " + AMPM);
            }
        } catch (Exception ex) {
            System.out.println("Error happened \n" + ex);
        }
    }//GEN-LAST:event_ampmBoxActionPerformed

    private void buzzerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (!buzzerActive) {
            musicActive = false;
            buzzerActive = true;
        }
    }

    private void musicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicButtonActionPerformed
        if (!musicActive) {
            buzzerActive = false;
            musicActive = true;
        }
    }

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        if (evt.getSource() == browseButton) {
            br.browse();
            browseField.setText(br.getName());
        }
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd, yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void activate(int Hour, int Minute, String ampm) {
        boolean started = true;
        while (started) {
            Calendar now = Calendar.getInstance();
            int h = now.get(Calendar.HOUR_OF_DAY);
            int m = now.get(Calendar.MINUTE);
            int s = now.get(Calendar.SECOND);
            if (Hour == h && Minute == m && s == 0) {
                String str = time.getText();
                if (str.endsWith(ampm)) {
                    if (buzzerActive) {
                        //Make Buzzer
                    }
                    if (musicActive) new MusicLauncher(br.getSong()).start();
                    started = false;
                }
            }
        }
    }

    private JComboBox<String> ampmBox;
    private JButton browseButton;
    private JTextField browseField;
    private JRadioButton buzzerButton;
    private JTextField date;
    private JComboBox<String> hourBox;
    private JComboBox<String> minBox;
    private JRadioButton musicButton;
    private JTextField time;

}