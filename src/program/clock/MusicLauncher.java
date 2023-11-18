package program.clock;

import java.io.*;
import javax.sound.sampled.*;

public class MusicLauncher extends Thread {

    public String fileName;
    public Position curPosition;

    public enum Position {
        LEFT, RIGHT, NORMAL
    };
    public MusicLauncher(String songName) {
        fileName = songName;
        curPosition = Position.NORMAL;
    }
    public MusicLauncher(String songFile, Position p) {
        fileName = songFile;
        curPosition = p;
    }
    final Object lock = new Object();
    volatile boolean paused = false;

    public void userPressedPause() {
        paused = true;
    }

    public void userPressedPlay() {
        synchronized(lock) {
            paused = false;
            lock.notifyAll();
        }
    }

    @Override
    public void run() {
        AudioInputStream din = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.err.println("Song file not found: " + fileName);
                return;
            }
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            if(line != null) {
                line.open(decodedFormat);
                // 128Kb
                int EXTERNAL_BUFFER_SIZE = 524288;
                byte[] data = new byte[EXTERNAL_BUFFER_SIZE];
                // Start
                line.start();
                int nBytesRead;
                while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
                    line.write(data, 0, nBytesRead);
                    synchronized (lock) {
                        while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
                            while (paused) {
                                if(line.isRunning()) {
                                    line.stop();
                                }
                                try {
                                    lock.wait();
                                } catch(InterruptedException ignored) {
                                }
                            }
                            if(!line.isRunning()) {
                                line.start();
                            }
                            line.write(data, 0, nBytesRead);
                        }
                    }
                }
                // Stop
                line.drain();
                line.stop();
                line.close();
                din.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(din != null) {
                try {
                    din.close();
                } catch(IOException ignored) {
                }
            }
        }
    }
}