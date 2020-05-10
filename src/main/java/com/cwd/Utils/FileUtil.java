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
            String fileName=multipartFile.getOriginalFilename();//文件名
            String suffixName=fileName.substring(fileName.lastIndexOf('.'));//文件名称后缀
            //处理图片类型的文件
            imageName= UUID.randomUUID()+suffixName;//生成唯一识别码作为文件名称
            logger.info(imageName);
            File file=new File(this.globalConfig.getLocalFilePath()+imageName);
            if(!file.getParentFile().exists()){//上一级文件夹不存在
                file.getParentFile().mkdir();
                logger.info("------文件夹创建成功...");
            }
            try {
                multipartFile.transferTo(file);//写入
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
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
