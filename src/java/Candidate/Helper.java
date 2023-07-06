/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Candidate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.servlet.http.Part;

/**
 *
 * @author flami This class support handle logic in java servlet classes
 */
public class Helper {

    public static String getImageFileName(String candidateID) {
        return "image_" + candidateID + ".jpg";
    }

    public static boolean saveImageToFile(Part file, String filePath) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath);
            is = file.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            fos.close();
        }
        return false;
    }

    private static boolean createDir(String savePath) {
        try {
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getPath(String candidateID, String saveDir) {
        String seperateFile = File.separator;
        String imagePath = saveDir + seperateFile + getImageFileName(candidateID);
        return imagePath;
    }

    public static boolean saveImage(String candidateID, Part file, String path,
            String saveDir) {
        try {
            String separateFile = File.separator;
            String imageFileName = getImageFileName(candidateID);

            String webPath = new File(path).getParentFile().getParentFile().getPath()
                    + separateFile + "web";
            String savePath = webPath + separateFile + saveDir;

            if (!createDir(savePath)) {
                throw new Exception();
            }

            String filePath = savePath + separateFile + imageFileName;
            boolean check = saveImageToFile(file, filePath);
            if (!check) {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String convertToVietnameseDate(LocalDate localDate) {
        String dateString = String.valueOf(localDate); //"2020-11-22"
        String nam = dateString.substring(0, 4);
        String thang = dateString.substring(5, 7);
        String ngay = dateString.substring(8, dateString.length());
        return "ngày " + ngay + ", tháng " + thang + ", năm " + nam;
    }

    public static String getSavePathApplyContract(String webPath, String SAVE_DIR, String staffID) {
        String seperateFile = File.separator;
        String savePathFolder = webPath + seperateFile + SAVE_DIR;
        File tempFileSaveDir = new File(savePathFolder);
        if (!tempFileSaveDir.exists()) {
            tempFileSaveDir.mkdir();
        }
        String savePath = savePathFolder + seperateFile + staffID;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        return savePath;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d");
    }

    public static String generateString(String string) {
        string = removeAccent(string);
        string = string.trim();
        string = string.replaceAll("\\s+", " ");
        string = string.toLowerCase();

        if (string.length() != 0) {
            String[] temp = string.split(" ");
            string = temp[temp.length - 1];

            for (int i = 0; i < temp.length - 1; i++) {
                string += String.valueOf(temp[i].charAt(0));
            }
            return string;
        }
        return null;
    }
}
