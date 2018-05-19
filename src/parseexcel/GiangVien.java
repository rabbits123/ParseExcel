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
    
    private ArrayList<String> feedBack;
    private HashMap<String, String> subject;
    //private int [] academicStreight;
    private int [] present;
    
    public GiangVien(){
        gvName = "";
        feedBack = new ArrayList<>();
        subject = new HashMap<String,String>();
        //academicStreight = new int [3]; 
        present = new int [3]; 
    }
    
     public void printName(){
         System.out.println(gvName);
     }
     
     public void printSubject(){
         for(String key : subject.keySet()){
             System.out.println(key + "\t" + subject.get(key));
         }
     }
     
     public void printPresent(){
         for(int i : present){
             System.out.print(i + "\t");
         }
         
         System.out.println("\n");
     }
     
     public void printFeedback(){
         
         System.out.println(feedBack.size());
         for(String str : feedBack){
             System.out.println(str);
         }
         System.out.println("=====================");
     }

//    public void setAcademicStreight(int[] academicStreight) {
//        this.academicStreight = academicStreight;
//    }

    public void setPresent(int[] present) {
        this.present = present;
    }

    
//    public int[] getAcademicStreight() {
//        return academicStreight;
//    }

    public int[] getPresent() {
        return present;
    }
    
    

    public String getGvName() {
        return gvName;
    }

    public void setGvName(String gvName) {
        this.gvName = gvName;
    }

    public void setFeedBack(ArrayList<String> feedBack) {
        this.feedBack = feedBack;
    }

    public ArrayList<String> getFeedBack() {
        ArrayList<String> arr = new ArrayList<>();
        for(String str : feedBack){
            if(str.length()!= 0){
                arr.add(str);
            }
        }
        return arr;
    }

    public HashMap<String, String> getSubject() {
        return subject;
    }

    public void setSubject(HashMap<String, String> subject) {
        this.subject = subject;
    }

    
}
