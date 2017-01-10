package udpSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by anybody on 2017/1/10.
 */
public class UDPServer {
    public static void main(String  [] args) throws IOException {

        /**
         * 服务端接收客户端消息
         */
        //1、创建服务端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8800);

        //2、创建数据报，用于接受客户端发送的数据
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);

        //3、接收客户端发送的数据
        System.out.println("-----服务端已经启动，等待客户端发送数据----");
        socket.receive(packet);//等待客户端发送消息

        //4、读取数据
        String info = new String(data,0,packet.getLength());
        System.out.println("从客户端获取到的信息为：" + info);

        /**
         * 服务端响应客户端
         */
        //1、定义客户端的地址、端口、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data1 = "欢迎你！".getBytes();

        //2、创建数据报，包含相应的信息
        DatagramPacket packet1 = new DatagramPacket(data1,data1.length,address,port);

        //3、响应客户端
        socket.send(packet1);

        //4、关闭资源
        socket.close();

    }
}
