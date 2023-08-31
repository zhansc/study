package cn.com.zhanss.io.server.step1;

import lombok.Data;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpParser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Request {

    private static Pattern methodRegex = Pattern.compile("GET|POST|PUT|DELETE|OPTIONS|TRACE|HEAD");

    private String requestMethod;

    private HashMap<String, String> headers = new HashMap<>();

    private String body;

    public Request(Socket socket) throws IOException {
        // DataInputStream 包含各种java基础类型
        // InputStream 只有byte类型
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        // 解析请求方法
        String methodLine = HttpParser.readLine(dataInputStream, "utf-8");
        Matcher matcher = methodRegex.matcher(methodLine);
        matcher.find();
        requestMethod = matcher.group();

        // 解析请求头
        Header[] headerArr = HttpParser.parseHeaders(dataInputStream, "utf-8");
        for (Header header : headerArr) {
            headers.put(header.getName(), header.getValue());
        }

        // 解析请求body
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        for(;dataInputStream.available() > 0;) {
            bufferedReader.read(buffer);
            stringBuilder.append(buffer);
        }
        body = stringBuilder.toString();
//
//        bufferedReader.close();
//        dataInputStream.close();
    }

}
