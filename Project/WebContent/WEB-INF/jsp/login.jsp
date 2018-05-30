<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/login.css">

<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>

	<div class="container">
		<div id="form">
			<div class="heading">
				<h1>ログイン画面</h1>
			</div>

			<c:if test="${errMsg != null}">
				<div class="alert alert-danger" role="alert">${errMsg}</div>
			</c:if>

			<form action="LoginServlet" method="post">


				<div class="login-area">


					<div class="form-group row1">
							<input type="text" class="form-control" name="loginId" placeholder="ログインID" maxlength="20" size="20">
					</div>

					<div class="form-group row2">
							<input type="password" class="form-control" name="password" placeholder="パスワード" maxlength="20" size="20">
					</div>

				</div>

				<div class="button_wrapper">
					<button type="submit" class="btn btn-primary" name="button" value="1">ログイン</button>
				</div>

			</form>
		</div>

	</div>
</body>
</html>