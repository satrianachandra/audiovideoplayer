/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin2;
import org.gstreamer.swing.VideoComponent;

/**
 *
 * @author chandra
 */
public class AudioVideoPlayer {
    public static void main(String[] args) {
        args = Gst.init("AudioPlayer", args);
        final PlayBin2 playbin = new PlayBin2("AudioPlayer");
        playbin.setInputFile(new File(args[0]));
        /*
        playbin.setVideoSink(ElementFactory.make("fakesink", "videosink"));
        playbin.setState(State.PLAYING);
        Gst.main();
        playbin.setState(State.NULL);
         */
        
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                VideoComponent videoComponent = new VideoComponent();
                playbin.setVideoSink(videoComponent.getElement());

                JFrame frame = new JFrame("VideoPlayer");
                frame.getContentPane().add(videoComponent, BorderLayout.CENTER);
                frame.setPreferredSize(new Dimension(640, 480));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                playbin.setState(State.PLAYING);
            }
        });
        Gst.main();
        playbin.setState(State.NULL);

    }
}
