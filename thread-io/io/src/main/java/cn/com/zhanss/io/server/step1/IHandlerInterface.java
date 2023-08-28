package cn.com.zhanss.io.server.step1;

@FunctionalInterface
public interface IHandlerInterface {

    void handler(Request request, Response response);

}
