<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item List</title>
</head>

<body>
	<h1>Item List</h1>
	<table border="1">
		<tr>
			<th>No</th><th>Name</th><th>Price</th><th>Order</th>
		</tr>
		<c:forEach var="item" items="${itemList}">
			<tr>
				<td>${item.no}</td><!--item.getNo()  -->
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td><a href="${pageContext.request.contextPath}/OrderController?itemNo=${item.no}">주문하기</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan = "4" align="center">
				<c:if test="${pagingInfo.currentScreen > 1}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${(pagingInfo.currentScreen - 1) * pagingInfo.pagePerScreen}"><<</a>
				</c:if>
				<c:if test="${pagingInfo.currentPage > 1}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${pagingInfo.currentPage - 1}"><</a>
				</c:if>
				<c:forEach var="i" begin="${pagingInfo.startScreenPage}" end="${pagingInfo.startScreenPage + pagingInfo.currentScreenPage - 1}" step="1">
					<c:if test="${pagingInfo.currentPage == i}">
						${i}
					</c:if>
					<c:if test="${pagingInfo.currentPage != i}">
						<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${i}">${i}</a>
					</c:if>
				</c:forEach>
				<c:if test="${pagingInfo.currentPage < pagingInfo.lastPage}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${pagingInfo.currentPage + 1}">></a>
				</c:if>
				<c:if test="${pagingInfo.currentScreen < pagingInfo.lastScreen}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${pagingInfo.currentScreen * pagingInfo.pagePerScreen + 1}">>></a>
				</c:if>
			</td>
		</tr>
	</table>

</body>
</html>