package cn.com.zhanss.io.server.step1;

import java.io.IOException;

@FunctionalInterface
public interface IHandlerInterface {

    void handler(Request request, Response response) throws IOException;

}
