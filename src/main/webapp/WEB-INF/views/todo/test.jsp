<%--
  Created by IntelliJ IDEA.
  User: dlstj
  Date: 2022-10-04
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
${dtoList}

<hr/>
${pageDTO}

<style>
  .paging {
    list-style: none;
    display: flex;
  }
  .paging li {
    margin: 0.5em;
    border: 1px solid black;
  }
</style>

<ul class="paging">
  <c:if test="${pageDTO.prev}">
    <li> <a href="/todo/list?page=${pageDTO.start -1}">Prev</a></li>
  </c:if>
  <c:forEach begin="${pageDTO.start}" end="${pageDTO.end}" var="num">
    <li> <a href="/todo/list?page=${num}"> ${num} </a></li>
  </c:forEach>
  <c:if test="${pageDTO.next}">
    <li><a href="/todo/list?page=${pageDTO.end  + 1}"> Next</a></li>
  </c:if>
</ul>

</body>
</html>
