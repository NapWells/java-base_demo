package udpSocket;

import java.io.IOException;
import java.net.*;

/**
 * Created by anybody on 2017/1/10.
 */
public class UDPClient {
    public static void main(String [] args) throws IOException {
        /**
         * 客户端向服务端发送消息
         */
        //1、定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("localhost");//因为服务器是本机，所以才能用“localhost”
        int port = 8800;
        byte[] data = "用户名：ljsu；密码：245245".getBytes();

        //2、创建数据报，包含需要发送的数据
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);

        //3、创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();

        //4、想服务器发送数据
        socket.send(packet);

        /**
         * 客户端接受服务端响应信息
         */
        //1、创建数据报，用于接受服务器响应信息
        byte[] data1 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(data1,data.length);

        //2、接收服务端端响应的数据
        System.out.println("-----等待服务端响应消息----");
        socket.receive(packet1);

        //3、读取数据
        String info = new String(data1,0,packet1.getLength());
        System.out.println("服务端响应的信息为：" + info);

        //4、关闭资源
        socket.close();
    }
}
