package com.example.asus.part2.kojewang.client.data;

/**
 * Created by asus on 2018/3/23.
 */

public class NetFileData {
    private long fileSize = 0;
    private String fileName = "$erro";
    private String filePath = ".\\";
    private String strFileSize = "0";
    private int isDirectory = 0;
    private String fileModifiedDate = "1970-01-01 00:00:00";
    public NetFileData(String fileInfo, String filePath){
        this.filePath = filePath;
        String []fileInfos =  fileInfo.split(">");
        fileName = fileInfos[0];
        fileModifiedDate = fileInfos[1];
        fileSize = Long.parseLong(fileInfos[2]);
        if((fileInfos[3].equals("1"))){
            isDirectory = 1;
        }else if(fileInfos[3].equals("2")){
            isDirectory = 2;
        }
        else{
            isDirectory = 0;
        }

        setSize();
    }
    private  void  setSize(){
        double B = 1.0;
        double KB = B * 1024.0;
        double MB = KB * 1024.0;
        double GB = MB * 1024.0;
        double ff = (double)fileSize;
        if(fileSize >= GB){
            strFileSize = String.format("%.3fGB",ff / GB);
            return;
        }
        if(fileSize>=MB){
            strFileSize = String.format("%.2fMB",ff / MB);
            return;
        }
        if(fileSize>=KB){
            strFileSize = String.format("%.1fKB",ff/ KB);
            return;
        }

        strFileSize = String.format("%.0fB",ff);
        return;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStrFileSize() {
        return strFileSize;
    }

    public void setStrFileSize(String strFileSize) {
        this.strFileSize = strFileSize;
    }

    public int isDirectory() {
        return isDirectory;
    }

    public void setDirectory(int directory) {
        isDirectory = directory;
    }

    public String getFileModifiedDate() {
        return fileModifiedDate;
    }

    public void setFileModifiedDate(String fileModifiedDate) {
        this.fileModifiedDate = fileModifiedDate;
    }
}
