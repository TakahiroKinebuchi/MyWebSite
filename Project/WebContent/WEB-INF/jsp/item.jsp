<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/item.css">

<meta charset="UTF-8">
<title>商品詳細画面</title>
<%
	ItemDataBeans item = (ItemDataBeans) request.getAttribute("item");
	String searchWord = (String) session.getAttribute("searchWord");
	int pageNum = (int) request.getAttribute("pageNum");
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
						<button type="button" class="btn btn-primary">Search</button>
					</span>
				</div>
			</form>
			<br> <br>

		</div>
	</div>
	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">商品詳細</h5>
		</div>
		<div class="row">
			<div class="col s6">
				<div class="image">
					<img class="monster" src="<%="image/" + item.getFileName()%>" alt="モンスター">
				</div>
			</div>
			<div class="col s6">
				<h4 class="card-title"><%=item.getName()%></h4>
				<h5 class="card-text"><%=item.getDetail()%></h5>
				<h5 class="card-text"><%=item.getCaffeine()%>[mg/100ml]</h5>
				<h5 class="card-text"><%=item.getPrice()%>[円]（税込）</h5>
					<a class="btn btn-primary" href="ItemAdd?item_id=<%=item.getId() %>">買い物かごに追加</a>
			</div>
		</div>
		<div class="return">
			<p>
					<%
						if (searchWord != null) {
					%>
					<a href="ItemSearchResult?search_word=<%=searchWord%>&page_num=<%=pageNum%>">検索結果へ </a>
					<%
						}
					%>
			</p>
		</div>
	</div>
</body>
</html>