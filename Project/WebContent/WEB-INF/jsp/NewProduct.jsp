<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/NewProduct.css">

<meta charset="UTF-8">
<title>商品マスター新規作成</title>
<%
	ItemDataBeans item = (ItemDataBeans) request.getAttribute("item");
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
		<div class="row center">
			<h5 class=" col s12 light">商品マスター新規作成</h5>
		</div>
		<form action="NewProductServlet" method="post">
			<div class="row">
				<div class="col s6">
					<p>
						画像ファイル：<input type="file" name="file_name" accept="image/*"
							class="form-control">
					</p>
					<p>
						名前：<input type="text" name="name" class="form-control">
					</p>
					<p>
						商品詳細：<input type="text" name="detail" class="form-control">
					</p>
					<p>
						カフェイン含有量[mg/100ml]：<input type="number" name="caffeine"
							class="form-control">[mg/100ml]
					</p>
					<p>
						価格[円]：<input type="number" name="price" class="form-control">[円]（税込み）
					</p>
				</div>
			</div>
			<div class="button_wrapper">
				<button type="submit" class="btn btn-success" name="button"
					value="1">決定</button>
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