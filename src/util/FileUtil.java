/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class FileUtil {
    public static void writeTxtFile(String content,String path) throws IOException{
        RandomAccessFile mm=null;  
        FileOutputStream o=null;
        try{
            File file = new File(path);
            o = new FileOutputStream(file);  
            o.write(content.getBytes("UTF-8"));  
            o.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(mm!=null){  
                mm.close();  
            }  
        }
    }
    
    public static String readFile(String filePath){
        StringBuffer content = new StringBuffer();
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    content.append(lineTxt);
                }
                read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return content.toString();
    }
}
