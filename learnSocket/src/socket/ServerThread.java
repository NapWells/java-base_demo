package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by anybody on 2017/1/9.
 */
public class ServerThread extends Thread{
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    //
    @Override
    public void run() {
        try {
            //3、获取输入流,读取客户端信息
            InputStream inputStream = socket.getInputStream();//获取输入流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//将字节流转换字符流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//为字符流建立缓冲区
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            socket.shutdownInput();

            //4、获取输出流，响应客户端
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("欢迎你！");
            printWriter.flush();
            socket.shutdownOutput();

            //5、关闭socket
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
