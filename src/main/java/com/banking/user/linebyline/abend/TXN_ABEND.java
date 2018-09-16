package com.banking.user.linebyline.abend;

import com.banking.user.linebyline.abend.TxtColumnNameAndData;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TXN_ABEND {

    // \\\\GTMFSERVER\\ftp\\TIS\\TXN_ABEND_PX11.TXT
    // \\\\GTMFSERVER\\ftp\\TIS\\TXN_ABEND_PX12.TXT

    static final String PATH = "Z:\\devtools\\TEST";
    static final String FILE_PATTERN = "TXN_ABEND_PX";
    //static final String LINE_PREFIX = "CICSPA";

    static final ArrayList<String> buildNames;

    static {
        buildNames = new ArrayList<>(Arrays.asList("CICSPA11", "CICSPA13", "CICSPA15", "CICSPA17",
                "CICSPA31", "CICSPA33", "CICSPA14", "CICSPA16",
                "CICSPA18", "CICSPA32", "CICSPA34"));
    }

    static final ArrayList<String> linePrefixNames;

    static {
        linePrefixNames = new ArrayList<>(Arrays.asList("CICSPA", "CICSPI", "CICSPT", "YSPCICS", "WEBCICS"));
    }

    public static void main(String[] args) {

        File filePath = new File(PATH);
            /*
            if(!TxtColumnNameAndDataDao.DatabaseConnection()){
                return;
            }
            */
        ArrayList<TxtColumnNameAndData> txtColumnNameAndDataList = findDirectoryName(filePath);
            /*
            if(txtColumnNameAndDataList!=null && txtColumnNameAndDataList.size()>0){

                for(TxtColumnNameAndData txtColumnName : txtColumnNameAndDataList){
                    TxtColumnNameAndDataDao.insertTable(txtColumnName);
                }

            }
            */
        findDirectoryName(filePath);
    }

    public static ArrayList<TxtColumnNameAndData> findDirectoryName(File filePath) {

        ArrayList<TxtColumnNameAndData> txtColumnNameAndDataList = new ArrayList<TxtColumnNameAndData>();

        try {
            File[] fileList = filePath.listFiles();

            for (File file : fileList) {
                if (file.getName().indexOf(FILE_PATTERN) != -1) {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = simpleDateFormat.format(file.lastModified() - 24 * 60 * 60 * 1000);

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        for (String linePrefix : linePrefixNames) {
                            int index = line.indexOf(linePrefix);
                            if (index != -1) {
                                line = line.substring(1);
                                String[] lineArr = cleanArr(line.split(" "));
                                if (lineArr.length == 8) {
                                    if (buildNames.contains(lineArr[0])) {

                                        TxtColumnNameAndData txtColumnNameAndData = new TxtColumnNameAndData();

                                        txtColumnNameAndData.setApplid(lineArr[0]);
                                        txtColumnNameAndData.setAbor(lineArr[1].equals("-") ? null : lineArr[1]);
                                        txtColumnNameAndData.setTransaction(lineArr[2].equals("-") ? null : lineArr[2]);
                                        txtColumnNameAndData.setProgram(lineArr[3].equals("-") ? null : lineArr[3]);
                                        txtColumnNameAndData.setTasks(lineArr[4]);
                                        txtColumnNameAndData.setAvgResponseTime((lineArr[4].substring(0, 1).equals(".")) ? lineArr[4].replace(".", "0.") : lineArr[4]);
                                        txtColumnNameAndData.setMaxResponseTime((lineArr[5].substring(0, 1).equals(".")) ? lineArr[5].replace(".", "0.") : lineArr[5]);
                                        txtColumnNameAndData.setAvgDispatchTime((lineArr[6].substring(0, 1).equals(".")) ? lineArr[6].replace(".", "0.") : lineArr[6]);
                                        txtColumnNameAndData.setAvgDispatchCount(lineArr[7]);
                                        txtColumnNameAndData.setAvgUserCpuTime((lineArr[8].substring(0, 1).equals(".")) ? lineArr[8].replace(".", "0.") : lineArr[8]);
                                        txtColumnNameAndData.setAvgSuspendTime((lineArr[9].substring(0, 1).equals(".")) ? lineArr[8].replace(".", "0.") : lineArr[9]);
                                        txtColumnNameAndData.setMaxSuspendTime((lineArr[10].substring(0, 1).equals(".")) ? lineArr[8].replace(".", "0.") : lineArr[10]);
                                        txtColumnNameAndData.setAvgDispwaitTime((lineArr[11].substring(0, 1).equals(".")) ? lineArr[8].replace(".", "0.") : lineArr[11]);

                                        txtColumnNameAndData.setDate(date);

                                        txtColumnNameAndDataList.add(txtColumnNameAndData);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return txtColumnNameAndDataList;
    }

    static String[] cleanArr(String[] arr) {
        String line = "";
        String tempLine = "";
        int stringCount = 0;
        int numberCount = 0;
        ArrayUtils.reverse(arr);
        for (int i = 0; i < arr.length; i++) {
            String txt = arr[i].replaceAll(" ", "");
            if (txt.length() > 0) {
                if (numberCount < 9) {
                    line += txt + ";";
                    numberCount++;
                } else {
                    tempLine += txt + ";";
                    stringCount++;
                }
            }
        }

        if (stringCount == 4) {
            line += tempLine + ";";
        } else if (stringCount == 3) {
            line += ("-;" + tempLine);
        } else if (stringCount == 2) {
            line += ("-;-;" + tempLine);
        } else if (stringCount == 1) {
            line += ("-;-;-;" + tempLine);
        }

        System.out.println(line);
        String[] result = line.split(";");
        ArrayUtils.reverse(result);

        return result;
    }

    static String[] cleanArr2(String[] arr) {

        String line = "";
        //int stringCount = 0;
        for (int i = 0; i < arr.length; i++) {
            String text = arr[i].replaceAll(" ", "");
            // if(text.length() > 0){
            if (StringUtils.isEmpty(text)) {
                line += "-;";
            }
            line += text + ";";
            //}
        }

        System.out.println(line);

        return line.split(";");

    }
}
