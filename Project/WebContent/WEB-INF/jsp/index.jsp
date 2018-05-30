<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="beans.UserDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/index.css">
<%
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
%>
<meta charset="UTF-8">
<title>TOPページ</title>
</head>
<body>

	<div class="container">
		<ul class="nav justify-content-end">
				<c:if test="${userInfo.loginId == 'admin'}">
					<li class="nav-item"><a class="nav-link username"
						href="UserMasterServlet">商品マスター一覧へはこちら</a></li>
				</c:if>
			<c:choose>
				<c:when test="${userInfo != null }">
					<li class="nav-item"><a class="nav-link username" href="UserAllServlet">ユーザ一覧へはこちら</a></li>
					<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name}さん</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link username" href="UserDetailServlet">${userInfo.name}さん</a></li>
				</c:otherwise>
			</c:choose>
			<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
			<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>

		</ul>
	</div>

	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br> <br>
			<div class="row center">
				<h5 class="header col s12 light" align="right">大人気のエナジードリンクを最短1日でお届け！！※商品金額はすべて税込みです。</h5>
			</div>
			<div class="heading">
				<h4 class="header" align="left" onclick="location.href='IndexServlet'">EnergyXpressService</h4>
			</div>
			<form action="ItemSearchResult">
				<div class="input-group">
					<input type="text" placeholder="検索名" name="search_word"> <span
						class="input-group-btn">
						<button type="submit" class="btn btn-primary">Search</button>
					</span>
				</div>
			</form>
			<div class="cart-in">
				<a href="CartServlet"><img class="incart" src="image/カートのアイコン素材.jpeg" alt="カートのアイコン素材"></a>
			</div>

			<br> <br>

		</div>
	</div>
		<div class="container">
			<div class="row center">
				<h5 class=" col s12 light">おすすめ商品</h5>
			</div>
			<div class="section">
				<!--   おすすめ商品   -->
				<div class="card-deck">
				<%
					for (ItemDataBeans item : itemList) {
				%>
					<div class="card">
						<a href="Item?item_id=<%=item.getId()%>"><img class="monster" src="<%="image/" + item.getFileName()%>" alt="モンスター"></a>
						<div class="card-body">
							<h5 class="card-title"><%=item.getName()%></h5>
							<p class="card-text"><%=item.getPrice()%>[円]（税込）</p>
						</div>
					</div>
				<%
					}
				%>
				</div>
			</div>
		</div>

	</body>
</html>