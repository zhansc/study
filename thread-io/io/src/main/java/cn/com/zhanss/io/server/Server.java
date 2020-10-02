package cn.com.zhanss.io.server;

import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @desc 服务端
 * @author zhanshuchan
 * @date 2020/9/29
 */
public class Server {

    public static void main(String[] args) throws IOException {

        while (true) {
            ServerSocket server = new ServerSocket(10086);
            Socket socket = server.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String hello = dataInputStream.readUTF();
            System.out.println("say hello---->"+ hello);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // 发送消息
            Scanner scanner = new Scanner(System.in);
            String intput = scanner.next();
            dataOutputStream.writeUTF(intput);

            String name = dataInputStream.readUTF();
            String password = dataInputStream.readUTF();
            String result;
            if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
                result = "账号或密码不能为空";
            }
            if ("zhanss".equals(name) && "zhanss".equals(password)) {
                result = "登录成功";
            } else {
                result = "账号或密码错误";
            }
            dataOutputStream.writeUTF(result);

            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
            server.close();
        }

    }

}
