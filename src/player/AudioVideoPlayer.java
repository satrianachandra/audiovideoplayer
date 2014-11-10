/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import model.MediaInfo;
import org.gstreamer.Bus;
import org.gstreamer.ClockTime;
import org.gstreamer.ElementFactory;
import org.gstreamer.Format;
import org.gstreamer.Gst;
import org.gstreamer.GstObject;
import org.gstreamer.SeekFlags;
import org.gstreamer.SeekType;
import org.gstreamer.State;
import org.gstreamer.TagList;
import org.gstreamer.elements.PlayBin2;
import org.gstreamer.lowlevel.GstElementAPI;
import org.gstreamer.lowlevel.GstMessageAPI;
import org.gstreamer.query.SeekingQuery;
import org.gstreamer.swing.VideoComponent;

/**
 *
 * @author chandra
 */
public class AudioVideoPlayer {

    private GUI myGUI;
    PlayBin2 playbin2; //audio video player
    PlayBin2 playbin2MediaInfo; //audio video player media info
    int currentVolume;
    public boolean animationMode = true;

    //long currentTime=-1;
    public List<MediaInfo> playList;

    String absFileName = null;
    PlayBin2.ABOUT_TO_FINISH aboutToFinishListener;
    VideoComponent videoComponent = null;

    public AudioVideoPlayer(String[] args) {
        this.myGUI = new GUI();
        myGUI.setPlayer(this);
        playList = new ArrayList<>(); //list to store audio/video files

        args = Gst.init("AudioVideoPlayer", args);
        playbin2 = new PlayBin2("AudioVideoPlayer");

        //PlayBin is a GstPipeline, it will notify the application
        //of everything that is happening by posting messages on its GstBus
        //notify the application for end of stream
        playbin2.getBus().connect(new Bus.EOS() {
            @Override
            public void endOfStream(GstObject go) {
                System.out.println("Finished playing file");
                //Gst.quit();
                myGUI.streamFinished();
                playbin2.setState(State.NULL);
            }
        });

        //notify the application of errors
        playbin2.getBus().connect(new Bus.ERROR() {
            public void errorMessage(GstObject source, int code, String message) {
                System.out.println("Error occurred: " + message);
                Gst.quit();
            }
        });

        //notify the application of state changes
        playbin2.getBus().connect(new Bus.STATE_CHANGED() {
            public void stateChanged(GstObject source, State old, State current, State pending) {
                if (source == playbin2) {
                    System.out.println("Pipeline state changed from " + old + " to " + current);
                }
            }
        });

        //notify the application of tags found
        /*
         playbin2.getBus().connect(new Bus.TAG() {

         public void tagsFound(GstObject source, TagList tagList) {
         for (String tagName : tagList.getTagNames()) {
         for (Object tagData : tagList.getValues(tagName)) {
         System.out.printf("[%s]=%s\n", tagName, tagData);
         }
         }
         }
         });
         */
    }

    //to play audio/video player 
    public void play(String absFileName) {
        this.absFileName = absFileName;
        playbin2.setState(State.NULL); //set state to NULL
        playbin2.setInputFile(new File(absFileName));
        videoComponent = new VideoComponent();
        playbin2.setVideoSink(videoComponent.getElement());
        myGUI.setPanelVideo(videoComponent);

        //myGUI.getContentPane().add(videoComponent, BorderLayout.CENTER);
        //myGUI.revalidate();
        //myGUI.repaint();
        updateTimeSlider();
        playbin2.setState(State.PLAYING); //change state to PLAYING
    }

    //Playback can be initiated by setting the element to PLAYING state 
    //When playback has finished playbin should be set back to NULL state, 
    //then the “uri” property should be set to the new location
    //and then playbin be set to PLAYING state again. 
    public void play() {
        if (absFileName != null) {
            if (playbin2.getState() == State.PAUSED) {
                updateTimeSlider();
                playbin2.setState(State.PLAYING);
            } else if (playbin2.getState() == State.PLAYING) {
                playbin2.setState(State.NULL);
                play(absFileName);
            } else if (playbin2.getState() == State.NULL) {
                play(absFileName);
            }
        } else {

        }
    }

    //to pause the player

    public void pause() {
        if (playbin2 != null) {
            if (playbin2.getState() == State.PLAYING) {
                playbin2.setState(State.PAUSED);
            }
        }
    }

    //to stop the player

    public void stop() {
        if (playbin2 != null) {
            if (playbin2.getState() == State.PLAYING) {
                playbin2.setState(State.NULL);
            }
        }
    }

    //to mute the volume
    public void mute() {
        currentVolume = playbin2.getVolumePercent();
        playbin2.setVolumePercent(0);
    }

    //to unmute the volume

    public void unMute() {
        playbin2.setVolumePercent(currentVolume);
    }

    //to set the volume

    public void setVolume(int volume) {
        if (playbin2 != null) {
            playbin2.setVolumePercent(volume);
        }
    }

    //to track time information in the slider
    public void updateTimeSlider() {
            
        new Thread(new Runnable() {
            int maxDuration = 0;

            @Override
            public void run() {
                while (playbin2.getState() == State.PLAYING || playbin2.getState() == State.PAUSED) {
                    //getting the length of the stream to track the duration
                    if (maxDuration == 0) {
                        maxDuration = (int) playbin2.queryDuration().toMillis();
                        myGUI.setSliderTimeMax(maxDuration);                
                    }
                    //getting the position of the stream to track the time
                    String theTime = playbin2.queryPosition().toString();
                    myGUI.setLabelTime(theTime);//show the current time on the label
                    if (animationMode) {
                        int currentPosition = (int) playbin2.queryPosition().toMillis();
                        myGUI.setSliderTime(currentPosition);
                    }

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AudioVideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }).start();
    }
    //for time slider
    public void seekTo(int time) {
        if (playbin2 != null) {
            if (playbin2.getState() == State.PLAYING) {
                ClockTime currentPosition = playbin2.queryPosition();
                playbin2.seek(ClockTime.fromMillis(time));
                System.out.println(playbin2.queryPosition().toString());
                //currentTime=playbin2.queryPosition(Format.TIME);
                //System.out.println(currentTime);
            }
        }
    }
    
    
    // For fast-forward and rewind functionality,a seek-event is used.
    // A seek-event contains a playback rate, a seek offset format (unit of the offsets to follow, e.g. time, audio samples, video frames or bytes),
    // optionally a set of seeking-related flags (e.g. whether internal buffers should be flushed), 
    // a seek method (which indicates relative to what the offset was given), and seek offsets. 
    // The first offset (cur) is the new position to seek to; while
    // the second offset (stop) is optional and specifies a position where streaming is supposed to stop. 
    
    //to fast-forward 
    public void fastForward() {
        if (playbin2 != null) {
            ClockTime currentPosition = playbin2.queryPosition();
            playbin2.seek(1 * 1.2, Format.TIME, SeekFlags.FLUSH | SeekFlags.ACCURATE, SeekType.SET, currentPosition.toNanos(), SeekType.NONE, 0);
        }
    }
    //to fast-forward twice the speed
    public void twoxfastForward() {
        if (playbin2 != null) {
            long currentPosition = playbin2.queryPosition(Format.TIME);
            playbin2.seek(2 * 1.2, Format.TIME, SeekFlags.FLUSH | SeekFlags.ACCURATE, SeekType.SET, currentPosition, SeekType.NONE, 0);

        }
    }
    //to rewind
    public void rewind() {
        if (playbin2 != null) {
            long currentPosition = playbin2.queryPosition(Format.TIME);
            playbin2.seek(1 * (-1.2), Format.TIME, SeekFlags.FLUSH | SeekFlags.ACCURATE, SeekType.SET, 0, SeekType.SET, currentPosition);

        }
    }
    //to rewind with twice slower
    public void twoxrewind() {
        if (playbin2 != null) {
            long currentPosition = playbin2.queryPosition(Format.TIME);
            playbin2.seek(2 * (-1.2), Format.TIME, SeekFlags.FLUSH | SeekFlags.ACCURATE, SeekType.SET, 0, SeekType.SET, currentPosition);

        }
    }
    //to return to normal speed
    public void normalSpeed() {
        if (playbin2 != null) {
            long currentPosition = playbin2.queryPosition(Format.TIME);
            long duration = playbin2.queryDuration(Format.TIME);
            playbin2.seek(1, Format.TIME, SeekFlags.FLUSH | SeekFlags.ACCURATE, SeekType.SET, currentPosition, SeekType.SET, duration);

        }
    }
    //to go full-screen
    public void goFullScreen() {
        if (videoComponent != null) {
            //JFrame setup to get a full-screen window
            final JFrame fsFrame = new JFrame();
            fsFrame.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
            fsFrame.getContentPane().add(videoComponent, BorderLayout.CENTER);
            fsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            fsFrame.setUndecorated(true);
            fsFrame.pack();
            fsFrame.setResizable(false);

            // on ESC key close frame
            fsFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
            fsFrame.getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
                public void actionPerformed(ActionEvent e) {
                    fsFrame.setVisible(false);
                    myGUI.setVisible(true);
                    myGUI.setPanelVideo(videoComponent);
                }
            });

            fsFrame.setVisible(true);
            myGUI.setVisible(false);
        }
    }
    //to update file info in playlist
    public void updateMediaInfo() {
        for (int i = 0; i < playList.size(); i++) {
            MediaInfo mi = playList.get(i);
            System.out.println("a:" + mi.getTitle());
            if (mi.getDuration() == null) {
                String duration = getMediaFileDuration(mi.getFullPath()); //get duration
                mi.setDuration(duration);
                System.out.println("media dur: " + mi.getDuration());
                myGUI.updatePlayListTable(mi.getTitle(), mi.getDuration(), i);
            }
        }
    }
    //to get file duration to show in playlist
    public String getMediaFileDuration(String completePath) {
        playbin2MediaInfo = new PlayBin2("MediaInfo");
        playbin2MediaInfo.setInputFile(new File(completePath));
        playbin2MediaInfo.setVideoSink(ElementFactory.make("fakesink", "videosink"));
        playbin2MediaInfo.setState(State.PAUSED);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AudioVideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playbin2MediaInfo.queryDuration().toString();
    }
    //main method
    public static void main(String[] args) {
        AudioVideoPlayer myPlayer = new AudioVideoPlayer(args);
    }

}
