package cn.com.zhanss.io.server.step1;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Data
public class Request {

    private Socket socket;

    public Request(Socket socket) {
        this.socket = socket;
    }

    public String receive() throws IOException {
        // DataInputStream 包含各种java基础类型
        // InputStream 只有byte类型
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        for(;;) {
            String line = bufferedReader.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            stringBuilder.append(line);
        }
        String request = stringBuilder.toString();
        System.out.println(request);

        bufferedReader.close();
        dataInputStream.close();
        return request;
    }
}
