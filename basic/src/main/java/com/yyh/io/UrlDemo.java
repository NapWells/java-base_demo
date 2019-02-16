package com.yyh.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: lhy
 * @description:
 * @data: 2019-02-15 15:34
 **/
public class UrlDemo {
    public static String doGet(String httpUrl) throws IOException {
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(1000*10);
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == 200){
                is =connection.getInputStream();
//                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                String line = null;
//                while ((line = br.readLine())!=null){
//                    sb.append(line);
//                }
//                return sb.toString();

                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                return new String(bytes,"utf-8");

            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is != null){
                is.close();
            }
            if (br != null){
                br.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(doGet("https://www.baidu.com"));
    }
}
