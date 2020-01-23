<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 2019-07-26
  Time: 01:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset = "UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<div id="ajaxGetUserServletResponse">
<h1>This is index.jsp</h1>


<form action="loadServlet" method="post" enctype="multipart/form-data">
    Заголовок: <input type="text" name="title" /><br/>
    Текст новости: <input type="text" name="body">
    <input type="file" name="file" />
    Описание картинки: <input type="text" name="description">
    <input type="submit" value="upload" />
</form>
    <select id="select" name="count">
        <option value ="10">10</option>
        <option value ="20">20</option>
        <option value ="50">50</option>
    </select>

<div id="navigation"></div>
    <div><span id="page">1</span> / <%=request.getAttribute("page")%></div>
<div ><%=request.getAttribute("html")%></div>
</div>

</body>
<script src="js/navigation.js"></script>
<script src="js/app-ajax.js"></script>
</html>