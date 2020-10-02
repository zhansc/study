package cn.com.zhanss.io.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @desc 客户端
 * @author zhanshuchan
 * @date 2020/9/29
 */
public class Client {

    public static void main(String[] args) throws IOException {

        while (true) {
            OutputStream outputStream = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;
            Socket client = null;
            try {
                // 发起连接
                client = new Socket("127.0.0.1", 10086);
                outputStream = client.getOutputStream();
                dataOutputStream = new DataOutputStream(outputStream);
                // 发送消息
                Scanner scanner = new Scanner(System.in);
                String intput = scanner.next();
                dataOutputStream.writeUTF(intput);

                // 接受消息
                dataInputStream = new DataInputStream(client.getInputStream());
                String hello = dataInputStream.readUTF();
                System.out.println("say hello too----->"+ hello);

                String name = scanner.next();
                String password = scanner.next();
                dataOutputStream.writeUTF(name);
                dataOutputStream.writeUTF(password);

                String result = dataInputStream.readUTF();
                System.out.println("登录态----->"+ result);

            } catch (Exception e) {

            } finally {
                // 关闭流
                dataOutputStream.close();
                dataInputStream.close();
                outputStream.close();
                client.close();
            }

        }

    }


}
