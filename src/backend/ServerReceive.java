package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 文件名：ServerReceive.java
 * 实现功能：作为服务器接收客户端发送的文件
 *
 * 具体实现过程：
 * 1、建立SocketServer，等待客户端的连接
 * 2、当有客户端连接的时候，按照双方的约定，这时要读取一行数据
 * 		其中保存客户端要发送的文件名和文件大小信息
 * 3、根据文件名在本地创建文件，并建立好流通信
 * 4、循环接收数据包，将数据包写入文件
 * 5、当接收数据的长度等于提前文件发过来的文件长度，即表示文件接收完毕，关闭文件
 * 6、文件接收工作结束
 *
 * */


public class ServerReceive {


    public static void main(String[] args) {
        FileManger.fileMangePool.execute(new FileManger());

        /**与服务器建立连接的通信句柄*/
        ServerSocket ss = null;
        Socket s = null;

        System.out.println("等待连接");

        /**建立socekt通信，等待服务器进行连接*/
        try {
            ss = new ServerSocket(1234);
            System.out.println("连接已建立");
            accpeted(ss);
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//public static void main(String[] args)

    static void accpeted(ServerSocket ss) {
        Socket s = null;
        /**定义用于在接收后在本地创建的文件对象和文件输出流对象*/
        File file = null;
        FileOutputStream fos = null;

        /**定义输入流，使用socket的inputStream对数据包进行输入*/
        InputStream is = null;

        /**定义byte数组来作为数据包的存储数据包*/
        byte[] buffer = new byte[4096 * 5];

        /**用来接收文件发送请求的字符串*/
        String comm = null;
        while(true){
            try {
                s = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /**读取一行客户端发送过来的约定信息*/
            try {
                InputStreamReader isr = new InputStreamReader(s.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                comm = br.readLine();
            } catch (IOException e) {
                System.out.println("服务器与客户端断开连接");
            }
            System.out.println("服务器收到请求"+comm);
            if(comm==null){
                continue;
            }
            /**开始解析客户端发送过来的请求命令*/
            String[] index = comm.split("/#");

            /**判断协议是否为发送文件的协议*/
            String xieyi = index[0];
            if(!xieyi.equals("512")){
                System.out.println("服务器收到的协议码不正确");
                continue;
            }

            /**解析出文件的名字和大小*/
            String filename = index[1];
            String filesize = index[2];

            File dir = new File("javaProImgs");
            if(!dir.exists()){
                Boolean tmp = dir.mkdir();
                if(!tmp){
                    System.out.println("服务器端创建文件夹失败");
                }
            }

            /**创建空文件，用来进行接收文件*/
            file = new File("javaProImgs",filename);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {

                    System.out.println("服务器端创建文件失败 "+e.getMessage());
                }
            }else{
                /**在此也可以询问是否覆盖*/
                System.out.println("本路径已存在相同文件");
                continue;
            }

            /**【服务器准备部分】*/



            try {
                /**将文件包装到文件输出流对象中*/
                fos = new FileOutputStream(file);
                long file_size = Long.parseLong(filesize);
                is = s.getInputStream();
                /**size为每次接收数据包的长度*/
                int size = 1024;
                /**count用来记录已接收到文件的长度*/
                long count = 0;

                /**使用while循环接收数据包*/
                while(count < file_size){
                    /**从输入流中读取一个数据包*/
                    size = is.read(buffer);

                    /**将刚刚读取的数据包写到本地文件中去*/
                    fos.write(buffer, 0, size);
                    fos.flush();

                    /**将已接收到文件的长度+size*/
                    count += size;
                    System.out.println("服务器端接收到数据包，大小为" + size);
                }
                System.out.println("图片上传成功，等待下一张图片");
            } catch (FileNotFoundException e) {
                System.out.println("服务器写文件失败");
            } catch (IOException e) {
                System.out.println("服务器：客户端断开连接");
            }finally{
                /**
                 * 将打开的文件关闭
                 * */
                try {
                    if(fos != null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }//catch (IOException e)
            }//finally
        }//while
    }//accpeted
}//public class ServerReceive