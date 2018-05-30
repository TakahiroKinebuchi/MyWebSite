<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/usermaster.css">

<meta charset="UTF-8">
<title>商品マスター一覧</title>
<%
	String searchWord = (String) session.getAttribute("searchWord");
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
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
		<div id="form">
			<div class="heading">
				<h1>商品マスター一覧</h1>
			</div>

			<form action="UserMasterServlet" method="post">

			<div class="search-area">

			<div class="form-group row">
						<label class="col-sm-2 col-form-label">商品名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="商品名" name="search_word">
						</div>
					</div>
			</div>

			<div class="button_wrapper">
				<button type="submit" class="btn btn-primary"  name="button" value="1">検索</button>
			</div>
			</form>
			<div class="return">
				<p>
					<a href="IndexServlet">戻る</a>
				</p>
			</div>
			<hr/>
		</div>

		<div class="new">
			<a class="btn btn-secondary" href="NewProductServlet" role="button">新規登録</a>
		</div>

		<table class="table">
			<thead>
				<tr class="bg-primary">
					<th scope="col">商品名</th>
					<th scope="col">商品マスター一覧</th>
				</tr>
			</thead>
			<tbody>
			<%
					int i = 0;
					for (ItemDataBeans item : itemList) {
						i++;
				%>
				<tr>
				<td><%=item.getName() %></td>
					<td>
						<div class="button_info">
							<a class="btn btn-primary" href="ProductDetailServlet?item_id=<%=item.getId()%>">情報参照</a>
							<a class="btn btn-success" href="ProductUpdateServlet?item_id=<%=item.getId() %>">情報更新</a>
							<a class="btn btn-danger" href="ProductDeleteServlet?item_id=<%=item.getId()%>">情報削除</a>
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

</body>
</html>