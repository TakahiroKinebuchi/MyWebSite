<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/usermoredetail.css">

<meta charset="UTF-8">
<title>ユーザ情報詳細</title>
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
				<h1>ユーザ情報詳細</h1>
			</div>
			<div class="detail-area">

			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ログインID</label>
						<label class="col-sm-2 col-form-label">${user.loginId} </label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ユーザ名</label>
						<label class="col-sm-2 col-form-label">${user.name}</label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">住所</label>
						<label class="col-sm-4 col-form-label">${user.address}</label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">生年月日</label>
						<label class="col-sm-4 col-form-label">${user.birthDate}</label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">登録日時</label>
						<label class="col-sm-4 col-form-label">${user.createDate}</label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">更新日時</label>
						<label class="col-sm-4 col-form-label">${user.updateDate}</label>
					</div>
			</div>
			<div class="return">
				<p><a href="UserAllServlet">戻る</a></p>
			</div>

		</div>



	</div>
</body>
</html>