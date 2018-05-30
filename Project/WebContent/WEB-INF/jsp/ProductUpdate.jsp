<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/ProductUpdate.css">

<meta charset="UTF-8">
<title>商品マスター情報更新</title>
<%
	ItemDataBeans item = (ItemDataBeans) request.getAttribute("item");
%>
</head>
<body>

	<div class="container">
		<ul class="nav justify-content-end">
			<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name }さん</a></li>
			<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
			<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>
		</ul>
	</div>

	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">商品マスター情報更新</h5>
		</div>
		<form action="ProductUpdateServlet" method="post">
			<div class="row">
				<div>
						<p><input type="hidden" value="<%=item.getId() %>" name="item_id"></p>
				</div>
				<div class="col s6">
					<div class="image">
						<img class="monster" src="<%="image/" + item.getFileName()%>" alt="モンスター">
					</div>
				</div>
				<div class="col s6">
					<h4 class="card-title"><%=item.getName()%></h4>
					<h5 class="card-text"><%=item.getDetail()%></h5>
					<h5 class="card-text"><%=item.getCaffeine()%>[mg/100ml]</h5>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">価格[円]（税込）</label>
						<div class="col-sm-10">
							<input type="number" class="form-control" name="price">
						</div>
					</div>
				</div>
			</div>
			<div class="button_wrapper">
				<button type="submit" class="btn btn-success" name="button"
					value="1">更新</button>
			</div>

		</form>
		<div class="return">
			<p>
				<a href="UserMasterServlet">戻る</a>
			</p>
		</div>
	</div>
</body>
</html>