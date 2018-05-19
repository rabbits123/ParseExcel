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

    private static List<GiangVien> gvList = new ArrayList<>();

    private static GiangVien getGV(List<GiangVien> giangVien, String name) {
        for (GiangVien gv : giangVien) {
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
            for (int i = 1; i < end; i++) {
                System.out.println("Line  " + i);
                List<String> otherAttributes = new ArrayList<String>();

                String GVName = sheet1.getRow(i).getCell(4).getStringCellValue();
                String subjectCode = sheet1.getRow(i).getCell(5).getStringCellValue();
                String subjectName = sheet1.getRow(i).getCell(6).getStringCellValue();

                String sentence1 = "";
                String sentence2 = "";

                try {
                    sentence1 = sheet1.getRow(i).getCell(9).getStringCellValue();
                    sentence2 = sheet1.getRow(i).getCell(10).getStringCellValue();
                } catch (java.lang.NullPointerException e) {
                    sentence1 = "";
                    sentence2 = "";
                } catch (java.lang.IllegalStateException e) {
                    sentence1 = "";
                    sentence2 = "";
                }

                String present = sheet1.getRow(i).getCell(8).getStringCellValue();
                System.out.println(present);
                int pre = -1;

                if (present.equals("<50%")) {
                    pre = 0;
                } else {
                    if (present.equals("50-80%")) {
                        pre = 1;
                    } else {
                        if (present.equals(">80%")) {
                            pre = 2;
                        }
                    }
                }

                // Nếu chưa có GV trong gvList
                if (getGV(gvList, GVName) == null) {
                    GiangVien gv = new GiangVien();

                    ArrayList<String> feedback1 = new ArrayList<String>(Arrays.asList(sentence1.split("\\.|\n")));
                    ArrayList<String> feedback2 = new ArrayList<String>(Arrays.asList(sentence2.split("\\.|\n")));
                    feedback1.addAll(feedback2);

                    Map<String, String> subject = new HashMap<>();
                    subject.put(subjectCode, subjectName);

                    gv.setGvName(GVName);
                    if (feedback1.size() != 0) {
                        gv.setFeedBack(feedback1);
                    }
                    gv.setSubject((HashMap<String, String>) subject);
                    //gv.getAcademicStreight()[aca]++;
                    gv.getPresent()[pre]++;

                    gvList.add(gv);
                } else {
                    GiangVien gv = getGV(gvList, GVName);

                    ArrayList<String> feedback = gv.getFeedBack();

                    ArrayList<String> feedback1 = new ArrayList<String>(Arrays.asList(sentence1.split("\\.|\n")));
                    ArrayList<String> feedback2 = new ArrayList<String>(Arrays.asList(sentence2.split("\\.|\n")));
                    feedback1.addAll(feedback2);

                    if (feedback1.size() != 0) {
                        feedback.addAll(feedback1);
                    }

                    gv.setFeedBack(feedback);

                    // kiểm tra môn đó đã có chưa?
                    if (!gv.getSubject().containsKey(subjectCode)) {
                        gv.getSubject().put(subjectCode, subjectName);
                    }
                    //gv.getAcademicStreight()[aca]++;
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
        getData("KhaoSat.xlsx");
        System.out.println(gvList.size());

        for (GiangVien gv : gvList) {
            gv.printName();
            gv.printSubject();
            gv.printPresent();
            gv.printFeedback();
        }

    }

}
