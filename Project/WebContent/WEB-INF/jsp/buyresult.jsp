<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	page import="beans.ItemDataBeans"%>
<%@ page import="beans.DeliveryMethodDataBeans"%>
<%@ page import="beans.BuyDataBeans"%>
<%@	page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/buyresult.css">

<meta charset="UTF-8">
<title>購入完了</title>
<%
	ArrayList<ItemDataBeans> buyIDBList = (ArrayList<ItemDataBeans>) request.getAttribute("buyIDBList");
	ArrayList<BuyDataBeans> resultBDB = (ArrayList<BuyDataBeans>) request.getAttribute("resultBDB");
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

	<div class="container">
		<div class="done">
			<h5 class=" col s12 light">購入が完了しました。</h5>
		</div>
		<div class="next">
			<div class="col s12">
				<div class="col s6 center-align">
					<a href="IndexServlet" class="btn btn-primary" role="button">引き続き買い物をする</a>
					<a href="UserDataServlet" class="btn  waves-effect waves-light">購入履歴一覧へ</a>
				</div>
			</div>
		</div>
		<hr>
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
					<%for(BuyDataBeans result : resultBDB) {
								%>
					<tr>
						<td class="center"><%=result.getFormatDate() %></td>
						<td class="center"><%=result.getDeliveryMethodName() %></td>
						<td class="center"><%=result.getTotalPrice() %>円</td>
					</tr>
					<%
						}
					%>
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
									for (ItemDataBeans buyIDB : buyIDBList) {
								%>
					<tr>
						<td class="center"><%=buyIDB.getName() %></td>
						<td class="center"><%=buyIDB.getPrice() %>円（税込）</td>
					</tr>
					<%
						}
					%>
					<%
						for (BuyDataBeans result : resultBDB) {
					%>
					<tr>
						<td class="center"><%=result.getDeliveryMethodName()%></td>
						<td class="center"><%=result.getDeliveryMethodPrice()%>円</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>