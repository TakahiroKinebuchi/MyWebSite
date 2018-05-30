<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/userupdate.css">

<meta charset="UTF-8">
<title>ユーザ情報更新</title>
</head>
<body>


	<div class="container">
	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name}さん</a></li>
				<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
				<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>
			</ul>

		<div id="form">
			<div class="heading">
				<h1>ユーザ情報更新</h1>
			</div>



			<form action="UserUpdateServlet" method="post">

			<div class="update-area">
			<div>
						<p><input type="hidden" value="${user.id}" name="id"></p>
			</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ログインID</label>
						<label class="col-sm-2 col-form-label">${user.loginId}</label>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ユーザ名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${user.name}" placeholder="" name="name">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">住所</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" value="${user.address}" placeholder="" name="address">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">パスワード</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">パスワード（確認）</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="passwordcheck">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">生年月日</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" value="${user.birthDate }" placeholder="" name="birthDate">
						</div>
					</div>

			</div>

			<div class="button_wrapper">
				<button type="submit" class="btn btn-success"  name="button" value="1">更新</button>
				</div>
			</form>

			<div class="return">
				<p><a href="UserAllServlet">戻る</a></p>
			</div>

		</div>



	</div>
</body>
</html>