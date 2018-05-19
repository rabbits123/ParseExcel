/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseexcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Phu
 */
public class GiangVien {
    private String gvName;
    
    private List<String> feedBack;
    private HashMap<String, String> subject;
    private int [] academicStreight;
    private int [] present;
    
    public GiangVien(){
        gvName = "";
        feedBack = new ArrayList<>();
        subject = new HashMap<String,String>();
        academicStreight = new int [3]; 
        present = new int [3]; 
    }

    public void setAcademicStreight(int[] academicStreight) {
        this.academicStreight = academicStreight;
    }

    public void setPresent(int[] present) {
        this.present = present;
    }

    
    public int[] getAcademicStreight() {
        return academicStreight;
    }

    public int[] getPresent() {
        return present;
    }
    
    

    public String getGvName() {
        return gvName;
    }

    public void setGvName(String gvName) {
        this.gvName = gvName;
    }

    public void setFeedBack(List<String> feedBack) {
        this.feedBack = feedBack;
    }

    public List<String> getFeedBack() {
        return feedBack;
    }

    public HashMap<String, String> getSubject() {
        return subject;
    }

    public void setSubject(HashMap<String, String> subject) {
        this.subject = subject;
    }

    
}
