package cn.com.zhanss.io.server.step1;

import lombok.Data;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Data
public class Response {

    private Socket socket;

    public Response(Socket socket) {
        this.socket = socket;
    }

    public void send(String httpMethod, Integer status, Integer stateCode) throws IOException {
        String response = httpMethod + " HTTP/1.1 "+ stateCode + " " + status + "\n" ;
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        bufferedWriter.write(response);
        bufferedWriter.flush();
        bufferedWriter.close();
        dataOutputStream.close();
    }
}
