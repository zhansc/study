package cn.com.zhanss.io.server.step1;

import lombok.Data;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

@Data
public class Response {

    private Socket socket;
    private static HashMap<Integer, String> codeMap;

    public Response(Socket socket) {
        this.socket = socket;
        if (codeMap == null || codeMap.isEmpty()) {
            codeMap = new HashMap<>();
            codeMap.put(200, "OK");
        }
    }

    public void send(Integer code, String msg) throws IOException {
        String response = "HTTP/1.1 "+ code + " " + codeMap.get(code) + "\n" + msg+ "\n" ;
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        bufferedWriter.write(response);
        bufferedWriter.flush();

        // http请求，请求一次就关闭掉
        socket.close();
    }
}
