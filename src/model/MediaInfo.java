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
    
    public MediaInfo(String title,String duration){
        this.title = title;
        this.duration = duration;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDuration(){
        return this.duration;
    }
}
