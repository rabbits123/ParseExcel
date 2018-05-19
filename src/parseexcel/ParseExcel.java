/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Phu
 */
public class ParseExcel {

    private static Map<String, List<String>> gv_feedback = new HashMap<>();
    private static Map<String, List<String>> gv_other = new HashMap<>();

    private static List<GiangVien> gvList = new ArrayList<>();
    
    private static GiangVien getGV(List<GiangVien> giangVien, String name){
        for(GiangVien gv : giangVien){
            if (gv.getGvName().equals(name)) {
                return gv;
            }
        }
        return null;
    }

    public static void getData(String path) {
        File src = new File(path);
        XSSFWorkbook wb = null;
        try {
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
            XSSFSheet sheet1 = wb.getSheetAt(0);
            int end = sheet1.getLastRowNum();
            for (int i = 0; i < end; i++) {
                List<String> otherAttributes = new ArrayList<String>();

                String GVName = sheet1.getRow(i).getCell(4).getStringCellValue();
                String subjectCode = sheet1.getRow(i).getCell(5).getStringCellValue();
                String subjectName = sheet1.getRow(i).getCell(6).getStringCellValue();
                String sentence = sheet1.getRow(i).getCell(9).getStringCellValue()
                        + sheet1.getRow(i).getCell(10).getStringCellValue();

                String acaStreight = sheet1.getRow(i).getCell(7).getStringCellValue();
                String present = sheet1.getRow(i).getCell(8).getStringCellValue();

                int aca = -1;
                switch (acaStreight) {
                    case "Giỏi":
                        aca = 0;
                        break;
                    case "Khá":
                        aca = 1;
                        break;
                    case "Trung bình":
                        aca = 2;
                        break;
                    case "Trung bình - Khá":
                        aca = 3;
                        break;
                    case "Yếu":
                        aca = 4;
                        break;
                }

                int pre = -1;
                switch (present) {
                    case "<50%":
                        pre = 0;
                    case "50-80%":
                        pre = 1;
                    case ">80%":
                        pre = 2;
                }

                // Nếu chưa có GV trong gvList
                if (getGV(gvList, GVName) == null) {
                    GiangVien gv = new GiangVien();
                    
                    List<String> feedback = Arrays.asList(sentence.split("\\.|\n"));
                    Map<String, String> subject = new HashMap<>();
                    subject.put(subjectCode, subjectName);
                    
                    gv.setGvName(GVName);
                    gv.setFeedBack(feedback);
                    gv.setSubject((HashMap<String, String>) subject);
                    gv.getAcademicStreight()[aca]++;
                    gv.getPresent()[pre]++;
                    
                    gvList.add(gv);
                } else{
                    GiangVien gv = getGV(gvList, GVName);
                    
                    List<String> feedback = gv.getFeedBack();
                    feedback.addAll(Arrays.asList(sentence.split("\\.|\n")));
                    gv.setFeedBack(feedback);
                    
                    // kiểm tra môn đó đã có chưa?
                    if(!gv.getSubject().containsKey(subjectCode)){
                        gv.getSubject().put(subjectCode, subjectName);
                    }
                    gv.getAcademicStreight()[aca]++;
                    gv.getPresent()[pre]++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy file");
        } catch (IOException ex) {
            System.out.println("Lỗi");
        } finally {
            try {
                wb.close();
            } catch (IOException ex) {
                System.out.println("Lỗi");
            }
        }
    }

    public static void main(String[] args) {
        getData("C:\\Users\\user\\ParseExcel\\KhaoSatMH.xls");
    }

}
