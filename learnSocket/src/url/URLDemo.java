package url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anybody on 2017/1/8.
 */
public class URLDemo {
    public static void main(String [] args){
        getUrlInfo();
        getUrlResource("http://www.baidu.com");
    }

    public static void getUrlInfo(){
        try {
            URL imooc = new URL("http://www.imooc.com");
            URL url = new URL(imooc,"/index.html?username=tom#test");
            System.out.println("已解码的授权组成部分：" + url.getAuthority());
            System.out.println("主机：" + url.getHost());
            System.out.println("协议:" + url.getProtocol());
            System.out.println("端口：" + url.getPort());
            System.out.println("文件路径：" + url.getPath());
            System.out.println("文件名：" + url.getFile());
            System.out.println("相对路径：" + url.getRef());
            System.out.println("查询字符串：" + url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void getUrlResource(String urlname){
        String outPath = "out/file.html";


        URL url = null;
        try {
            File file = new File(outPath);
            if(!file.exists()){
                file.createNewFile();
            }
            //创建一个URL实例
            url = new URL(urlname);

            //通过url的openStream获取URL所表示资源的字节输入流
            InputStream is = url.openStream();

            //将字节输入流转换为字符输入流
            InputStreamReader isr = new InputStreamReader(is);

            //为字符流添加缓冲区
            BufferedReader br = new BufferedReader(isr);

//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            String line;
            while((line = br.readLine())!=null){
                bw.write(line);
            }
            bw.close();
//            fw.close();
            br.close();
            isr.close();
            is.close();
            fos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
