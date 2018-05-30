<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="beans.BuyDataBeans"%>
<%@ page import="beans.UserDataBeans"%>
<%@ page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/userbuydata.css">

<meta charset="UTF-8">
<title>購入履歴一覧</title>
<%
	String validationMessage = (String) request.getAttribute("validationMessage");
	ArrayList<BuyDataBeans> buyList = (ArrayList<BuyDataBeans>)request.getAttribute("buyList");
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
				<h1>購入履歴一覧</h1>
			</div>
		<!--  購入履歴 -->
		<div class="row">
			<div class="col s12">
				<div class="card grey lighten-5">
					<div class="card-content">
						<table class="table">
							<thead>
								<tr>
									<th class="center" width="10%"></th>
									<th class="center">購入日時</th>
									<th class="center">配送方法</th>
									<th class="center">購入金額</th>
								</tr>
							</thead>
							<tbody>
										<%for(BuyDataBeans buy : buyList) { %>
										<tr>
										<td class="center"><a href="UserBuyHistoryDetailServlet?buy_id=<%= buy.getId()%>"><img class="userbuyhistorydetail" src="image/購入詳細.png" alt="購入詳細"></a></td>
										<td class="center"><%=buy.getFormatDate() %></td>
										<td class="center"><%=buy.getDeliveryMethodName() %></td>
										<td class="center"><%=buy.getTotalPrice() %>円</td>
										</tr>
										<%
										}
										%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
			<div class="return">
				<p><a href="UserAllServlet">ユーザ一覧へ戻る</a></p>
			</div>
		</div>
	</div>
</body>
</html>