<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jstl 추가 코드--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../includes/header.jsp"%>
      <div class="container-fluid px-4">
        <h1 class="mt-4">Tables</h1>
        <ol class="breadcrumb mb-4">
          <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
          <li class="breadcrumb-item active">Tables</li>
        </ol>
        <div class="card mb-4">
          <div class="card-body">
            DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the
            <a target="_blank" href="https://datatables.net/">official DataTables documentation</a>
            .
          </div>
        </div>
        <div class="card mb-4">
          <div class="card-header">
            <i class="fas fa-table me-1"></i>
            DataTable Example
          </div>
          <div class="card-body">
            <table class="table">
              <thead>
              <tr>
                <th scope="col"> # </th>
                <th scope="col"> Title</th>
                <th scope="col"> Due Date</th>
                <th scope="col"> Complete</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${dtoList}" var="todo">
                <tr>
                  <th scope="row">${todo.tno}</th>
                  <td><a href="/todo/detail?tno=${todo.tno}" style="text-decoration: none; color: black;"><c:out value="${todo.title}"/></a></td>
                  <td>${todo.dueDate}</td>
                  <td>${todo.complete ? "YES" : "NOT YET"}</td>
                </tr>
              </c:forEach>
              </tbody>
              <tfoot>
              <tr>
                <td colspan="4">
                  <nav aria-label="...">
                    <ul class="pagination pagination-sm justify-content-center">
                      <c:forEach var="pageNum" begin="1" end="${paging}" step="1">
                        <li class="page-item ${pageNum == page ? 'active' : ''}"><a class="page-link" href="/todo/list?page=${pageNum}">${pageNum}</a></li>
                      </c:forEach>
                    </ul>
                  </nav>
                </td>
              </tr>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
      <div>

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
          <c:if test="${pageDTO.prev}">
            <li class="page-item">
              <a class="page-link" href="/todo/list?page=${pageDTO.start - 1}">Previous</a>
            </li>
          </c:if>

          <c:forEach begin="${pageDTO.start}" end="${pageDTO.end}" var="num">
            <li class="page-item"><a class="page-link" href="/todo/list?page=${num}">${num}</a></li>
          </c:forEach>

          <c:if test="${pageDTO.next}">
            <li class="page-item">
              <a class="page-link" href="/todo/list?page=${pageDTO.end + 1}">Next</a>
            </li>
          </c:if>
          </ul>
        </nav>

<%--        <ul class="paging">--%>
<%--          <c:if test="${pageDTO.prev}">--%>
<%--            <li> <a href="/todo/list?page=${pageDTO.start -1}">Prev</a></li>--%>
<%--          </c:if>--%>

<%--          <c:forEach begin="${pageDTO.start}" end="${pageDTO.end}" var="num">--%>
<%--            <li> <a href="/todo/list?page=${num}"> ${num} </a></li>--%>
<%--          </c:forEach>--%>

<%--          <c:if test="${pageDTO.next}">--%>
<%--            <li><a href="/todo/list?page=${pageDTO.end  + 1}"> Next</a></li>--%>
<%--          </c:if>--%>
<%--        </ul>--%>
      </div>
    </main>

<%@ include file="../includes/footer.jsp"%>