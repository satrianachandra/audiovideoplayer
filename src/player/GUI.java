/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.MediaInfo;
import org.gstreamer.ClockTime;
import org.gstreamer.State;

/**
 *
 * @author chandra
 */
public class GUI extends javax.swing.JFrame {

    AudioVideoPlayer myPlayer;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        tablePlayList.setColumnSelectionAllowed(false);
        buttonPause.setLocation(buttonPlay.getLocation());
        buttonPause.setVisible(false);
        unmutebutton.setLocation(buttonMute.getLocation());
        unmutebutton.setVisible(false);

        buttontwoxrewind.setLocation(buttonRewind.getLocation());
        buttontwoxrewind.setVisible(false);
        buttonnormalspeed.setLocation(buttonRewind.getLocation());
        buttonnormalspeed.setVisible(false);

        buttontwoxforward.setLocation(buttonFastForward.getLocation());
        buttontwoxforward.setVisible(false);
        buttonnormalspeed2.setLocation(buttonFastForward.getLocation());
        buttonnormalspeed2.setVisible(false);

        setVisible(true);

        sliderVolume.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider) ce.getSource();
                //if (!source.getValueIsAdjusting()) {
                int volume = (int) source.getValue();
                myPlayer.setVolume(volume);
                // }

            }
        });

        sliderTime.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
                myPlayer.animationMode = false;
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        sliderTime.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider) ce.getSource();

                if (!source.getValueIsAdjusting()) {

                    if (!myPlayer.animationMode) {
                        int time = (int) source.getValue();

                        myPlayer.seekTo(time);
                        myPlayer.animationMode = true;
                    }

                }

            }
        });

        tablePlayList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    myPlayer.play(myPlayer.playList.get(row).getTitle());
                    buttonPause.setVisible(true);
                    buttonPlay.setVisible(false);
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonPlay = new javax.swing.JButton();
        buttonStop = new javax.swing.JButton();
        buttonRewind = new javax.swing.JButton();
        buttonFastForward = new javax.swing.JButton();
        buttonMute = new javax.swing.JButton();
        sliderVolume = new javax.swing.JSlider();
        buttonFullScreen = new javax.swing.JButton();
        sliderTime = new javax.swing.JSlider();
        buttonOpenFile = new javax.swing.JButton();
        panelVideo = new javax.swing.JPanel();
        buttonPause = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTime = new javax.swing.JLabel();
        scrollPanePlayList = new javax.swing.JScrollPane();
        tablePlayList = new javax.swing.JTable();
        unmutebutton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buttontwoxrewind = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        buttonnormalspeed = new javax.swing.JButton();
        buttontwoxforward = new javax.swing.JButton();
        buttonnormalspeed2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/play.jpg"))); // NOI18N
        buttonPlay.setBorderPainted(false);
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayActionPerformed(evt);
            }
        });

        buttonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/stop.jpg"))); // NOI18N
        buttonStop.setBorderPainted(false);
        buttonStop.setContentAreaFilled(false);
        buttonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStopActionPerformed(evt);
            }
        });

        buttonRewind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/rewind.jpg"))); // NOI18N
        buttonRewind.setBorderPainted(false);
        buttonRewind.setContentAreaFilled(false);
        buttonRewind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRewindActionPerformed(evt);
            }
        });

        buttonFastForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/forward.jpg"))); // NOI18N
        buttonFastForward.setBorderPainted(false);
        buttonFastForward.setContentAreaFilled(false);
        buttonFastForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFastForwardActionPerformed(evt);
            }
        });

        buttonMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/mute.jpg"))); // NOI18N
        buttonMute.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonMute.setBorderPainted(false);
        buttonMute.setContentAreaFilled(false);
        buttonMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMuteActionPerformed(evt);
            }
        });

        sliderVolume.setValue(100);
        sliderVolume.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sliderVolume.setName("volume"); // NOI18N

        buttonFullScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/fullscreen-12-512_converted.jpg"))); // NOI18N
        buttonFullScreen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonFullScreen.setBorderPainted(false);
        buttonFullScreen.setContentAreaFilled(false);
        buttonFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFullScreenActionPerformed(evt);
            }
        });

        sliderTime.setValue(0);
        sliderTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonOpenFile.setText("Add File");
        buttonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenFileActionPerformed(evt);
            }
        });

        panelVideo.setLayout(new java.awt.BorderLayout());

        buttonPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/pause.jpg"))); // NOI18N
        buttonPause.setBorderPainted(false);
        buttonPause.setContentAreaFilled(false);
        buttonPause.setName(""); // NOI18N
        buttonPause.setVerifyInputWhenFocusTarget(false);
        buttonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPauseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(84, 116, 176));
        jLabel1.setText("The Coolest Audio/Video Player Ever");
        jLabel1.setPreferredSize(new java.awt.Dimension(434, 67));

        labelTime.setText("00:00:00");

        tablePlayList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Length"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePlayList.setColumnSelectionAllowed(true);
        tablePlayList.getTableHeader().setReorderingAllowed(false);
        scrollPanePlayList.setViewportView(tablePlayList);
        tablePlayList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        unmutebutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/unmute.jpg"))); // NOI18N
        unmutebutton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        unmutebutton.setBorderPainted(false);
        unmutebutton.setContentAreaFilled(false);
        unmutebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unmutebuttonActionPerformed(evt);
            }
        });

        jLabel2.setText("Volume");

        buttontwoxrewind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/twoxrewind.jpg"))); // NOI18N
        buttontwoxrewind.setBorderPainted(false);
        buttontwoxrewind.setContentAreaFilled(false);
        buttontwoxrewind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontwoxrewindActionPerformed(evt);
            }
        });

        jLabel3.setText("Time");

        buttonnormalspeed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/stopblue.jpg"))); // NOI18N
        buttonnormalspeed.setBorderPainted(false);
        buttonnormalspeed.setContentAreaFilled(false);
        buttonnormalspeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonnormalspeedActionPerformed(evt);
            }
        });

        buttontwoxforward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/twoxforward.jpg"))); // NOI18N
        buttontwoxforward.setBorderPainted(false);
        buttontwoxforward.setContentAreaFilled(false);
        buttontwoxforward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontwoxforwardActionPerformed(evt);
            }
        });

        buttonnormalspeed2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/player/image/stopblue.jpg"))); // NOI18N
        buttonnormalspeed2.setBorderPainted(false);
        buttonnormalspeed2.setContentAreaFilled(false);
        buttonnormalspeed2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonnormalspeed2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelVideo, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                            .addComponent(sliderTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(scrollPanePlayList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(sliderVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(buttonOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonFullScreen))
                        .addContainerGap(169, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonPause)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPlay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonStop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRewind)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttontwoxrewind)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonnormalspeed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonFastForward)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttontwoxforward)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonnormalspeed2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonMute)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unmutebutton))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonOpenFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPanePlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addComponent(panelVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sliderVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonFullScreen))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelTime)
                                .addGap(25, 25, 25)))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonPause)
                            .addComponent(buttonPlay)
                            .addComponent(buttonStop)
                            .addComponent(buttonRewind)
                            .addComponent(buttontwoxrewind)
                            .addComponent(buttonnormalspeed)
                            .addComponent(buttonnormalspeed2)
                            .addComponent(buttonFastForward)
                            .addComponent(buttonMute)
                            .addComponent(unmutebutton)
                            .addComponent(buttontwoxforward, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayActionPerformed

        //myPlayer.play();
        if (myPlayer.playbin2.getState() == State.PAUSED) {
            myPlayer.play();
        } else {
            if (myPlayer.playList.size() > 0) {
                //myPlayer.play(myPlayer.playList.get(0).getTitle());
                if (tablePlayList.getSelectedRow() != -1) {
                    myPlayer.play(myPlayer.playList.get(tablePlayList.getSelectedRow()).getTitle());
                } else {
                    myPlayer.play(myPlayer.playList.get(0).getTitle());
                }
            }
        }

        if (myPlayer.absFileName != null) {
            buttonPause.setVisible(true);
            buttonPlay.setVisible(false);
        }
    }//GEN-LAST:event_buttonPlayActionPerformed

    private void buttonOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenFileActionPerformed
        final JFileChooser fc = new JFileChooser();
        FileFilter fileFilter = new FileNameExtensionFilter(
                "Audio/Video files(mp3,mp4,ogg,ogx,ogv,avi)", "mp3", "mp4", "ogg", "ogx", "ogv", "avi");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(fileFilter);

        int returnVal = fc.showOpenDialog(GUI.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String absPathFile = fc.getSelectedFile().getAbsolutePath();
            String fileName = fc.getSelectedFile().getName();
            
            
            //ClockTime mediaFileDuration = myPlayer.getMediaFileDuration(absPathFile);
            myPlayer.playList.add(new MediaInfo(absPathFile, null,absPathFile));
            DefaultTableModel model = (DefaultTableModel) tablePlayList.getModel();
            model.addRow(new Object[]{fileName, ""});
            myPlayer.updateMediaInfo();
            //myPlayer.play(absPathFile);
            //buttonPlay.setVisible(false);
            //buttonPause.setVisible(true);
        }

    }//GEN-LAST:event_buttonOpenFileActionPerformed

    private void buttonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStopActionPerformed
        if (myPlayer.absFileName != null) {
            myPlayer.stop();
            buttonPause.setVisible(false);
            buttonPlay.setVisible(true);
        }
    }//GEN-LAST:event_buttonStopActionPerformed

    private void buttonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPauseActionPerformed
        myPlayer.pause();
        buttonPause.setVisible(false);
        buttonPlay.setVisible(true);
    }//GEN-LAST:event_buttonPauseActionPerformed

    private void buttonMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMuteActionPerformed
        myPlayer.mute();
        //buttonMute.setText("UnMute");
        buttonMute.setVisible(false);
        unmutebutton.setVisible(true);

    }//GEN-LAST:event_buttonMuteActionPerformed

    private void buttonFastForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFastForwardActionPerformed

        myPlayer.fastForward();
        buttonFastForward.setVisible(false);
        buttontwoxforward.setVisible(true);


    }//GEN-LAST:event_buttonFastForwardActionPerformed

    private void buttonRewindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRewindActionPerformed

        myPlayer.rewind();
        buttonRewind.setVisible(false);
        buttontwoxrewind.setVisible(true);

    }//GEN-LAST:event_buttonRewindActionPerformed

    private void buttonFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFullScreenActionPerformed
        myPlayer.goFullScreen();

    }//GEN-LAST:event_buttonFullScreenActionPerformed

    private void buttontwoxrewindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontwoxrewindActionPerformed
        // TODO add your handling code here:
        myPlayer.twoxrewind();
        buttontwoxrewind.setVisible(false);
        buttonnormalspeed.setVisible(true);
    }//GEN-LAST:event_buttontwoxrewindActionPerformed

    private void buttonnormalspeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonnormalspeedActionPerformed
        // TODO add your handling code here:
        myPlayer.normalSpeed();
        buttonnormalspeed.setVisible(false);
        buttonRewind.setVisible(true);
    }//GEN-LAST:event_buttonnormalspeedActionPerformed

    private void buttontwoxforwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontwoxforwardActionPerformed
        // TODO add your handling code here:
        myPlayer.twoxfastForward();
        buttontwoxforward.setVisible(false);
        buttonnormalspeed2.setVisible(true);
    }//GEN-LAST:event_buttontwoxforwardActionPerformed

    private void buttonnormalspeed2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonnormalspeed2ActionPerformed
        // TODO add your handling code here:
        myPlayer.normalSpeed();
        buttonnormalspeed2.setVisible(false);
        buttonFastForward.setVisible(true);
    }//GEN-LAST:event_buttonnormalspeed2ActionPerformed

    private void unmutebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unmutebuttonActionPerformed
        myPlayer.unMute();
        unmutebutton.setVisible(false);
        buttonMute.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_unmutebuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI theGUI = new GUI();
                theGUI.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFastForward;
    private javax.swing.JButton buttonFullScreen;
    private javax.swing.JButton buttonMute;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JButton buttonPause;
    private javax.swing.JButton buttonPlay;
    private javax.swing.JButton buttonRewind;
    private javax.swing.JButton buttonStop;
    private javax.swing.JButton buttonnormalspeed;
    private javax.swing.JButton buttonnormalspeed2;
    private javax.swing.JButton buttontwoxforward;
    private javax.swing.JButton buttontwoxrewind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelTime;
    private javax.swing.JPanel panelVideo;
    private javax.swing.JScrollPane scrollPanePlayList;
    private javax.swing.JSlider sliderTime;
    private javax.swing.JSlider sliderVolume;
    private javax.swing.JTable tablePlayList;
    private javax.swing.JButton unmutebutton;
    // End of variables declaration//GEN-END:variables

    public void setPlayer(AudioVideoPlayer player) {
        this.myPlayer = player;
    }

    public void setPanelVideo(JComponent c) {
        panelVideo.removeAll();
        panelVideo.add(c);
        revalidate();
        repaint();
    }

    public void streamFinished() {
        buttonPause.setVisible(false);
        buttonPlay.setVisible(true);
    }

    public void setSliderTime(int time) {
        sliderTime.setValue(time);
    }

    public void setSliderTimeMax(int maxTime) {
        sliderTime.setMaximum(maxTime);
    }

    public void setLabelTime(String time) {
        labelTime.setText(time);
    }
    
    public void updatePlayListTable(String fileName,String duration,int position){
       System.out.println("dur"+duration);
       DefaultTableModel model = (DefaultTableModel) tablePlayList.getModel();
       model.setValueAt(duration, position, 1);
       model.fireTableDataChanged();
    }

}
