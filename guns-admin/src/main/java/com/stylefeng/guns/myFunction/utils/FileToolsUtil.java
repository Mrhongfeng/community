package com.stylefeng.guns.myFunction.utils;

import com.stylefeng.guns.common.constant.Const;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileToolsUtil {
    public static String fileUpload(MultipartFile mpf, String targetPath){
        String fName = UUID.randomUUID().toString() + mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
        createDiretory(targetPath);
        String filePath = targetPath + File.separator + fName;
        System.out.println("System: "+ Const.os);
        System.out.println("Upload dictionary: "+ Const.FILE_PATH);
        System.out.println("Upload file: "+filePath);

        try {
            FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));
            System.out.println("upload file success:"+filePath);
        } catch (IOException e) {
            System.err.println("upload file fail:"+filePath);
            e.printStackTrace();
            return null;
        }
        return filePath;
    }

    public static void fileToUpload(String strDirPath,String file) {
        String baseTargerPath = strDirPath;
        File ff = new File(file);
        String fileName = ff.getName();
        String targetPath = baseTargerPath + File.separator + fileName;
        if(new File(targetPath).exists()){
            return;
        }
        File targetFile = new File(baseTargerPath);
        createDiretory(baseTargerPath);
        if (targetFile.list().length > 500) {
            File[] files = targetFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
        //copyFile(file,"E:\\Workspace\\JAVA\\space2\\cecsoft\\guns-admin\\src\\main\\webapp\\static\\upload\\77d640b5-e3d4-4f0a-8256-486a90b341dc.png");
        copyFile(file,targetPath);
    }

    /**
     * 创建文件夹，如果不存在
     * @param path
     */
    public static String createDiretory(String path) {
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        return path;
    }
    /**
            * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                System.out.println("Copy start from "+oldPath);

                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[14440];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                System.out.println("Copy end to "+newPath);

            }
            else {
                System.out.println("要复制的文件不存在");
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param filePath String 原文件路径
     * @return 删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("删除文件失败:" + filePath + "不存在！");
            return false;
        }else{
            if(file.isFile()){
                if(file.delete())
                    return true;
                else{
                    System.out.println("删除单个文件" + filePath + "失败！");
                    return false;
                }
            }
            else{
                System.out.println("删除文件失败:" + filePath + "是一个文件夹！");
                return false;
            }
        }
    }
}
