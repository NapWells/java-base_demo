package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by anybody on 2017/1/9.
 */
public class Server {
    public static void main(String [ ] args){
        ServerSocket serverSocket = null;
        try {
            //1、建立服务端
            serverSocket  = new ServerSocket(8888);
            System.out.println("-------服务器等待客户端链接--------");

            //2、启动服务端线程
            int count = 0;
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("客户端"+ socket.getInetAddress().getHostAddress()+"链接成功");
                System.out.println("当前客户端的数量："+(++count));
                Thread serverThread = new ServerThread(socket);
                serverThread.start();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //5、关闭相关资源
            if(serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
