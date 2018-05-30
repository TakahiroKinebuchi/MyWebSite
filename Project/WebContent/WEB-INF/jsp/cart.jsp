<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/cart.css">

<meta charset="UTF-8">
<title>ショッピングカート</title>
<%
	ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
	String cartActionMessage = (String) request.getAttribute("cartActionMessage");
%>
</head>
<body>

	<div class="container">
		<ul class="nav justify-content-end">
			<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name}さん</a></li>
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
			<br> <br>

		</div>
	</div>
	<div class="container">
			<div class="row center">
				<h5 class=" col s12 light">ショッピングカート</h5>
			</div>
		<div class="section">
					<div class="cartactionmessage">
							<%=cartActionMessage%>
					</div>
			<form action="BuyServlet" method="post">
				<div class="choice">
					<button type="submit" class="btn btn-success">レジに進む</button>
				</div>
			</form>
			<div class="card-deck">
					<%
						int i = 0;
						for (ItemDataBeans item : cart) {
							i++;
					%>
				<div class="card">
					<a href="Item?item_id=<%=item.getId()%>"><img class="monster"
						src="<%="image/" + item.getFileName()%>" alt="モンスター"></a>
					<div class="card-body">
						<span class="card-title"><%=item.getName()%></span>
						<div class="card-footer">
							<a class="btn btn-primary"
								href="ItemDelete?delete_item_id_list=<%=item.getId()%>">削除</a>
						</div>
							</div>
						</div>
					<%
						if (i % 3 == 0) {
					%>
					</div>
					<div class="row">
					<%
						}
					%>
					<%
						}
					%>
					</div>
		</div>

	</div>
	</body>
</html>