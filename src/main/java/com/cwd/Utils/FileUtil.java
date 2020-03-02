package com.cwd.Utils;

import com.cwd.Entity.GlobalConfig;
import com.cwd.Service.LostAndFound.LostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Component
public class FileUtil {
    private final Logger logger= LoggerFactory.getLogger(LostService.class);
    @Autowired
    private GlobalConfig globalConfig;
    //读取媒体文件保存到服务器
    public String writeFileToDirectory(MultipartFile multipartFile){
        String imageName="";
        if(multipartFile!=null){
            //获取文件名及后缀名
            String fileName=multipartFile.getOriginalFilename();
            String suffixName=fileName.substring(fileName.lastIndexOf('.'));
            //处理图片类型的文件
            imageName= UUID.randomUUID()+suffixName;
            logger.info(imageName);
            File file=new File(this.globalConfig.getLocalFilePath()+imageName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
                logger.info("------文件夹创建成功...");
            }
            try {
                multipartFile.transferTo(file);
                logger.info("-----"+imageName+"保存到本地成功");
            } catch (IOException e) {
                new IOException("LostService------文件保存错误"+e.getMessage());
            }
        }
        return imageName;
    }
    //读取本地文件并产生输出流接口
    public void readLocalFile(String filePath,String fileName,OutputStream outputStream){
        File file=new File(filePath+fileName);
        if(file.exists()){
            try {
                FileInputStream fileInputStream=new FileInputStream(file);//文件读取流
                byte[] readBytes=new byte[1024];//一次读取1024字节
                BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
                while ((bufferedInputStream.read(readBytes))!=-1){
                    outputStream.write(readBytes);
                }
                outputStream.flush();
                bufferedInputStream.close();
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
