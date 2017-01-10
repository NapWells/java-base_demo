package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by anybody on 2017/1/9.
 */
public class Client {
    public static void main(String [] args){
        try {
            //1、创建客户端socket，指定服务器地址和端口号
            Socket socket = new Socket("localhost",8888);

            //2、获取输出流，向服务器发送信息
            OutputStream outputStream = socket.getOutputStream();//字节流
            PrintWriter printWriter = new PrintWriter(outputStream);//将字节流包装为打印流
            printWriter.write("用户名：liuhassssoyu; 密码：123");
            printWriter.flush();
            socket.shutdownOutput();

            //3、获取输入流，读取客户端响应的消息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            socket.shutdownInput();

            //4、关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
