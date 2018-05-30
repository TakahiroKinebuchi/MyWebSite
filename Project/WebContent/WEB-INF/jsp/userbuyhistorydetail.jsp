<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	page import="beans.ItemDataBeans"%>
<%@ page import="beans.DeliveryMethodDataBeans"%>
<%@ page import="beans.BuyDataBeans"%>
<%@	page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/userbuydata.css">

<meta charset="UTF-8">
<title>購入履歴詳細</title>
<%
	BuyDataBeans bdb = (BuyDataBeans)request.getAttribute("bdb");
	ArrayList<ItemDataBeans> buyIDBList = (ArrayList<ItemDataBeans>) request.getAttribute("buyIDBList");

%>
</head>
<body>


	<div class="container">
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name }さん</a></li>
				<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
				<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>
			</ul>

		<div id="form">
			<div class="heading">
				<h1>購入履歴詳細</h1>
			</div>

			<form action="post">

				<!--  購入履歴詳細 -->
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th class="center">購入日時</th>
								<th class="center">配送方法</th>
								<th class="center">合計金額</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td class="center"><%= bdb.getFormatDate() %></td>
								<td class="center"><%= bdb.getDeliveryMethodName()%></td>
								<td class="center"><%= bdb.getTotalPrice()%>円</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th class="center">商品名</th>
								<th class="center">単価</th>
							</tr>
						</thead>
						<tbody>
							<%
							for(ItemDataBeans buyIDB : buyIDBList) {
							%>
								<tr>
									<td class="center"><%= buyIDB.getName()%></td>
									<td class="center"><%= buyIDB.getPrice()%>円（税込）</td>
								</tr>
								<%
							}
								%>
						</tbody>
					</table>
				</div>

			</form>

			<div class="return">
				<p><a href="UserDataServlet">戻る</a></p>
			</div>
		</div>
	</div>
</body>
</html>