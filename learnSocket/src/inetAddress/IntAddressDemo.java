package inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by anybody on 2017/1/8.
 */
public class IntAddressDemo {
    public static void main(String [] args) throws UnknownHostException {
        //获取本机的IP实例
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("计算机名：" +  address.getHostName());
        System.out.println("IP地址：" +  address.getHostAddress());
        byte [] bytes = address.getAddress();//获取字节数组形式的IP
        System.out.println("字节数组形式的IP:" + Arrays.toString(bytes));
        System.out.println(address);

        System.out.println("\n------------------------------------\n");
        InetAddress address2 = InetAddress.getByName("DESKTOP-AR6530R");
        System.out.println("计算机名：" +  address2.getHostName());
        System.out.println("IP地址：" +  address2.getHostAddress());

        System.out.println("\n------------------------------------\n");
        InetAddress address3 = InetAddress.getByAddress(new byte[]{-84, 27, -81, 29});
        System.out.println("计算机名：" +  address3.getHostName());
        System.out.println("IP地址：" +  address3.getHostAddress());
    }
}
