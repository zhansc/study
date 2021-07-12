<%--
  Created by IntelliJ IDEA.
  User: zhanss
  Date: 2019/10/17
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
    请输入指定指令：id-name-gender-password：
    <form action="/converter" method="post">
      <input type="text" name="user">
      <br/>
      <input type="submit" value="提交">
    </form>
  </body>
</html>
