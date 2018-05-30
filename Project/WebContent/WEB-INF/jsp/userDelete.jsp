<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/userdelete.css">

<meta charset="UTF-8">
<title>ユーザ削除確認</title>
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
				<h1>ユーザ削除確認</h1>
			</div>



			<form action="UserDeleteServlet" method="post">
				<p>
					<input type="hidden" value="${user.id}" name="id">
				</p>
				<div class="center">
					<label>ログインID：${user.loginId}を本当に削除しますか？</label>
				</div>
				<div class="mol-md-10 offset-md-5">
					<button type="submit" class="btn btn-danger" name="button">はい</button>
					<a class="btn btn-secondary" href="UserAllServlet" role="button">いいえ</a>
				</div>
			</form>

			<div class="return">
				<p><a href="UserAllServlet">戻る</a></p>
			</div>

		</div>



	</div>
</body>
</html>