<%@page import="beans.UserDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/userall.css">

<meta charset="UTF-8">
<title>ユーザ一覧</title>
</head>
<body>


	<div class="container">
			<ul class="nav justify-content-end">
				<li class="nav-item"><a class="nav-link username" href="UserDetailServlet?id=${userInfo.id}">${userInfo.name}さん</a></li>
				<li class="nav-item"><a href="NewUserServlet"><img class="newuser" src="image/ユーザー新規.png" alt="ユーザー新規"></a></li>
				<li class="nav-item"><a href="LogoutServlet"><img class="logout" src="image/ログアウトアイコン.png" alt="ログアウトアイコン"></a></li>
			</ul>

		<div id="form">
			<div class="heading">
				<h4 class="header" align="left" onclick="location.href='IndexServlet'">EnergyXpressService</h4>
				<h1>ユーザ一覧</h1>
			</div>

			<form action="UserAllServlet" method="post">

			<div class="search-area">

			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ログインID</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="ログインID" name="login-id" id="login-id">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">ユーザ名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="ユーザ名" name="user-name" id="user-name">
						</div>
					</div>
			<div class="form-group row">
						<label class="col-sm-2 col-form-label">生年月日</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" name="date-start" id="date-start">～<input type="date" class="form-control" name="date-end" id="date-end">
						</div>
					</div>

			</div>

			<div class="button_wrapper">
				<button type="submit" class="btn btn-primary"  name="button" value="1">検索</button>
				</div>
			</form>
			<hr/>
		</div>

		<table class="table">
		  <thead>
		    <tr class="bg-primary">
		      <th scope="col">ログインID</th>
		      <th scope="col">ユーザ名</th>
		      <th scope="col">生年月日</th>
		      <th scope="col">アカウント編集リンク集</th>
		      <th scope="col">購入履歴</th>
		    </tr>
		  </thead>
		  <tbody>
                 <c:forEach var="user" items="${userList}" >
					<tr>
						<td>${user.loginId}</td>
						<td>${user.name}</td>
						<td>${user.birthDate}</td>
						<td><c:choose>
								<c:when test="${userInfo.loginId == 'admin'}">
									<a class="btn btn-primary"
										href="UserDetailServlet?id=${user.id}">詳細</a>
									<a class="btn btn-success"
										href="UserUpdateServlet?id=${user.id}">更新</a>
									<a class="btn btn-danger"
										href="UserDeleteServlet?id=${user.id}">削除</a>
								</c:when>
								<c:otherwise>
									<c:if test="${userInfo.loginId == user.loginId }">
										<a class="btn btn-primary"
											href="UserDetailServlet?id=${user.id}">詳細</a>
										<a class="btn btn-success"
											href="UserUpdateServlet?id=${user.id}">更新</a>
									</c:if>
								</c:otherwise>
							</c:choose></td>
						<td><c:if test="${userInfo.loginId == user.loginId }">
								<div class="buy_info">
									<a class="btn btn-secondary" href="UserDataServlet?id=${user.id}">一覧</a>
								</div>
							</c:if></td>
					</tr>
				</c:forEach>
		  </tbody>
		</table>

	</div>
</body>
</html>