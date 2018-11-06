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
				<c:if test="${pageMaker.prevPage}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${(pageMaker.currentBlock - 1) * pageMaker.pagePerBlock}"><</a>
				</c:if>
				<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
					<c:if test="${pageMaker.pageNum == i}">
						${i}
					</c:if>
					<c:if test="${pageMaker.pageNum != i}">
						<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${i}">${i}</a>
					</c:if>
				</c:forEach>
				<c:if test="${pageMaker.nextPage}">
					<a href="${pageContext.request.contextPath}/ItemListController?currentPage=${pageMaker.currentBlock * pageMaker.pagePerBlock + 1}">></a>
				</c:if>

			</td>
		</tr>
	</table>

</body>
</html>