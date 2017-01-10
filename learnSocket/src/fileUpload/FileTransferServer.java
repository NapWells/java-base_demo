package fileUpload;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * Created by anybody on 2017/1/10.
 */
public class FileTransferServer extends ServerSocket{

    private DataInputStream dataInputStream;
    private FileOutputStream fileOutputStream;
    private static DecimalFormat df = null;

    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public FileTransferServer(int port) throws IOException {
        super(port);
    }

    public void recieve() throws IOException {
        Socket socket = this.accept();
        dataInputStream = new DataInputStream(socket.getInputStream());

        String filename = dataInputStream.readUTF();
        long fileLength = dataInputStream.readLong();

        File directory = new File("C:\\Users\\anybody\\Desktop\\test");
        if(!directory.exists())
            directory.mkdir();
        File file = new File(directory.getAbsolutePath()+File.separatorChar+filename);

        fileOutputStream = new FileOutputStream(file);

        //开始接受文件
        byte[] bytes = new byte[1024];
        int length = 0;
        while((length = dataInputStream.read(bytes,0,bytes.length)) != -1){
            fileOutputStream.write(bytes,0,length);
            fileOutputStream.flush();
        }

        System.out.println("文件接收成功！");
        System.out.println("file-name:" + file.getName()+"  size:" + getFormatFileSize(fileLength));

        socket.shutdownInput();
        socket.shutdownOutput();
        dataInputStream.close();
        fileOutputStream.close();
        socket.close();
    }

    private String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if(size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) length) / (1 << 20);
        if(size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) length) / (1 << 10);
        if(size >= 1) {
            return df.format(size) + "KB";
        }
        return length + "B";
    }
    public static void main(String [] args) throws IOException {
        FileTransferServer fileTransferServer = new FileTransferServer(9999);
        fileTransferServer.recieve();
    }

}
