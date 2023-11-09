package cn.com.zhanss.io.server;

import cn.com.zhanss.io.server.step1.IHandlerInterface;
import cn.com.zhanss.io.server.step1.Request;
import cn.com.zhanss.io.server.step1.Response;
import cn.hutool.crypto.digest.MD5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @desc 服务端
 * @author zhanshuchan
 * @date 2020/9/29
 */
public class Step1Server {

    private ServerSocket server;

    private IHandlerInterface handler;

    public Step1Server(IHandlerInterface handler) {
        this.handler = handler;
    }

    public void listen(int port) throws IOException {
        server = new ServerSocket(port);
        while (true) {
            this.accept();
        }
    }

    private void accept() throws IOException {
        Socket socket = server.accept();

        new Thread(() -> {
            try {
                this.handler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handler(Socket socket) throws IOException {
        Request request = new Request(socket);
        Response response = new Response(socket);
        this.handler.handler(request, response);
    }

    public static void main(String[] args) throws IOException {
        Step1Server step1Server = new Step1Server(((request, response) -> {
            response.send(200, "Hello");
        }));
        // 请求拦截...
        // 控制器...
        // 业务逻辑省略...
        step1Server.listen(8001);

    }

}
