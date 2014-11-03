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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin2;
import org.gstreamer.lowlevel.GstElementAPI;
import org.gstreamer.swing.VideoComponent;

/**
 *
 * @author chandra
 */
public class AudioVideoPlayer{
    
    private GUI myGUI;
    PlayBin2 playbin2;
    
    String absFileName=null;
    PlayBin2.ABOUT_TO_FINISH aboutToFinishListener;
    
    public AudioVideoPlayer(String[]args){
        this.myGUI =  new GUI();
        myGUI.setPlayer(this);
        args = Gst.init("AudioVideoPlayer", args);
        playbin2 = new PlayBin2("AudioVideoPlayer");
        
        aboutToFinishListener = new PlayBin2.ABOUT_TO_FINISH() {

            @Override
            public void aboutToFinish(PlayBin2 pb) {
                myGUI.aboutToFinish();
            }
        };
        
        playbin2.connect(aboutToFinishListener);
        
    }
    
    public void play(String absFileName){
        this.absFileName = absFileName;
        playbin2.setInputFile(new File(absFileName));
        VideoComponent videoComponent = new VideoComponent();
        playbin2.setVideoSink(videoComponent.getElement());
        myGUI.setPanelVideo(videoComponent);
        
        //myGUI.getContentPane().add(videoComponent, BorderLayout.CENTER);
        //myGUI.revalidate();
        //myGUI.repaint();
        playbin2.setState(State.PLAYING);
    }
    
    public void play(){
        if (absFileName!=null){
            if (playbin2.getState()==State.PAUSED){
                playbin2.setState(State.PLAYING);
            }else if(playbin2.getState()==State.PLAYING){
                playbin2.setState(State.NULL);
                play(absFileName);
            }else if(playbin2.getState()==State.NULL){
                play(absFileName);
            }
        }else{
            
        }
    }
    
    public void pause(){
        if (playbin2!=null){
            if (playbin2.getState()==State.PLAYING){
                playbin2.setState(State.PAUSED);
            }
        }
    }

    public void stop(){
        if (playbin2!=null){
            if (playbin2.getState()==State.PLAYING){
                playbin2.setState(State.NULL);
            }
        }
    }
    
    public static void main(String[]args){
        AudioVideoPlayer myPlayer = new AudioVideoPlayer(args);
    }
    
}
