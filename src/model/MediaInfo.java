/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author chandra
 */
public class MediaInfo {
    private String title;
    private String duration;
    private String fullPath;
    
    public MediaInfo(String title,String duration, String fullPath){
        this.title = title;
        this.duration = duration;
        this.fullPath = fullPath;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getFullPath(){
        return this.fullPath;
    }
    
    public String getDuration(){
        return this.duration;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDuration(String duration){
        this.duration =  duration;
    }
}
