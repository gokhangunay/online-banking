package com.banking.user.linebyline.respcpu;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class TXN_RESP_CPU {

        // \\\\GTMFSERVER\\ftp\\TIS\\TXN_RESP_CPU_PX11.TXT
        // \\\\GTMFSERVER\\ftp\\TIS\\TXN_RESP_CPU_PX12.TXT

        static final String PATH = "Z:\\devtools\\TEST";
        static final String FILE_PATTERN = "TXN_RESP_CPU_PX";
        static final String LINE_PREFIX = "CICSPA";

        static final ArrayList<String> buildNames;
        static {
            buildNames = new ArrayList<>(Arrays.asList("CICSPA11","CICSPA13","CICSPA15","CICSPA17",
                    "CICSPA31","CICSPA33","CICSPA14","CICSPA16",
                    "CICSPA18","CICSPA32","CICSPA34"));
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

                for(File file : fileList){
                    if (file.getName().indexOf(FILE_PATTERN) != -1) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = simpleDateFormat.format(file.lastModified() - 24 * 60 * 60 * 1000);

                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null){
                            int index = line.indexOf(LINE_PREFIX);
                            if (index != -1) {
                                line = line.substring(1);
                                String[] lineArr = cleanArr(line.split(" "));
                                if (lineArr.length == 8) {
                                    if (buildNames.contains(lineArr[0])) {

                                        TxtColumnNameAndData txtColumnNameAndData = new TxtColumnNameAndData();

                                        txtColumnNameAndData.setApplid(lineArr[0] );
                                        txtColumnNameAndData.setTransaction(lineArr[1].equals("-") ? null : lineArr[1]);
                                        txtColumnNameAndData.setProgram(lineArr[2].equals("-") ? null : lineArr[2]);
                                        txtColumnNameAndData.setTasks(lineArr[3]);
                                        txtColumnNameAndData.setAvgResponseTime((lineArr[4].substring(0,1).equals(".")) ? lineArr[4].replace(".","0.") : lineArr[4]);
                                        txtColumnNameAndData.setMaxResponseTime((lineArr[5].substring(0,1).equals(".")) ? lineArr[5].replace(".","0.") : lineArr[5]);
                                        txtColumnNameAndData.setAvgUserCpuTime((lineArr[6].substring(0,1).equals(".")) ? lineArr[6].replace(".","0.") : lineArr[6]);
                                        txtColumnNameAndData.setMaxUserCpuTime((lineArr[7].substring(0,1).equals(".")) ? lineArr[7].replace(".","0.") : lineArr[7]);
                                        txtColumnNameAndData.setDate(date);

                                        txtColumnNameAndDataList.add(txtColumnNameAndData);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  txtColumnNameAndDataList;
        }

        static String[] cleanArr(String[] arr) {
            String line = "";
            int stringCount = 0;
            for (int i = 0; i < arr.length; i++) {
                String txt = arr[i].replaceAll(" ", "");
                if (txt.length() > 0) {
                    try {
                        Double.parseDouble(txt);
                        if (stringCount == 1) {
                            line += "-;-;";
                            stringCount = 3;
                        } else if (stringCount == 2){
                            line += "-;";
                            stringCount = 3;
                        }
                        line += txt + ";";
                    } catch (NumberFormatException e) {
                        line += txt + ";";
                        stringCount++;
                        //e.printStackTrace();
                    }
                }
            }

            System.out.println(line);

            return line.split(";");
        }

        static String[] cleanArr2(String[] arr){

            String line = "";
            //int stringCount = 0;
            for (int i = 0; i < arr.length; i++) {
                String text = arr[i].replaceAll(" ", "");
               // if(text.length() > 0){
                    if(StringUtils.isEmpty(text)){
                        line += "-;";
                    }
                    line += text + ";";
                //}
            }

            System.out.println(line);

            return line.split(";");

        }
    }
