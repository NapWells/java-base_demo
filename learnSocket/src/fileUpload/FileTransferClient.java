package fileUpload;

import java.io.*;
import java.net.Socket;

/**
 * Created by anybody on 2017/1/10.
 */
public class FileTransferClient extends Socket{
    private Socket client;
    private FileInputStream fileInputStream;
    private DataOutputStream dataOutputStream;

    public FileTransferClient() throws IOException {
        super("localhost",9999);
        this.client = this;
        System.out.println("-----成功链接服务端-----");
    }

    public void sendFile(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            throw new RuntimeException("不存在"+filePath);
        }
        fileInputStream = new FileInputStream(file);
        dataOutputStream = new DataOutputStream(client.getOutputStream());

        //文件名和长度
        dataOutputStream.writeUTF(file.getName());
        dataOutputStream.flush();
        dataOutputStream.writeLong(file.length());
        dataOutputStream.flush();

        //开始传输
        System.out.println("*************开始传输文件*************");
        byte[] bytes = new byte[1024];
        int length = 0;
        long progress = 0;
        while((length = fileInputStream.read(bytes,0,bytes.length)) != -1){
            dataOutputStream.write(bytes,0,length);
            dataOutputStream.flush();
            progress += length;
            System.out.println("|" + (100*progress)/file.length() + "% |");
        }
        System.out.println("*************文件传输成功*************");

        client.shutdownInput();
        client.shutdownOutput();
        client.close();
    }

    public static void main(String [ ]args){
        try {
            FileTransferClient client = new FileTransferClient();
            client.sendFile("C:\\Users\\anybody\\Desktop\\数据库.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
