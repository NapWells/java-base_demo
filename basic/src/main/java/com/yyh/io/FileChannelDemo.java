package com.yyh.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: lhy
 * @description:
 * @date: 2019-02-16 14:20
 **/
public class FileChannelDemo {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("C:\\Users\\42515\\Desktop\\test\\file1.txt");
            File outFile = new File("C:\\Users\\42515\\Desktop\\test\\file2.txt");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            fos = new FileOutputStream(outFile, true);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //高效拷贝文件
            inChannel.transferTo(0, inChannel.size(), outChannel);

            ByteBuffer br = ByteBuffer.allocate(48);
            while (inChannel.read(br) != -1) {
                //设置限制位置为当前位置position,也就是读写指针的位置，限制位置limit指的是在该模式下最多能读写多少数据，然后将position设置为0，表示将存缓存头部开始读；
                //调用flip之后，读写指针指到缓存头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)
                br.flip();
                //写入数据
                outChannel.write(br);
                //将position设置为0，limit为缓冲区的大小，也就是初始化指针的位置，实际上并没有清除缓冲区的数据
                br.clear();
                //此处可以继续读取缓冲区的数据
                System.out.println(new String(br.array(), StandardCharsets.UTF_8));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
