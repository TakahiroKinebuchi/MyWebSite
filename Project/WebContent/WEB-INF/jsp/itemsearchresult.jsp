<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import=" java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/origin/itemsearchresult.css">

<meta charset="UTF-8">
<title>検索結果</title>
<%
	ArrayList<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>) request.getAttribute("itemList");
	String searchWord = (String) session.getAttribute("searchWord");
	int pageMax = (int) request.getAttribute("pageMax");
	int pageNum = (int) request.getAttribute("pageNum");
	int itemCount = (int) request.getAttribute("itemCount");
%>
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
				<h1>検索結果</h1>
			</div>

			<div class="container">
				<div class="row center">
					<h5 class=" col s12 light">検索結果<%=itemCount %>件</h5>
					<form action="ItemSearchResult" method="post">
						<p>
							<input type="hidden" value="${search_word}" name="search_word">

							<select name="sort">
								<option value="priceDesc">価格が高い順</option>
								<option value="priceAsc">価格が安い順</option>
								<option value="caffeineDesc">カフェインが多い順</option>
								<option value="caffeineAsc">カフェインが少ない順</option>
							</select>
						</p>
						<p>
							<input type="submit" value="並び替え">
						</p>
					</form>
				</div>
				<div class="section">
					<!--   商品情報   -->
					<div class="row">
				<%
					int i = 0;
					for (ItemDataBeans item : itemList) {
						i++;
				%>
						<div class="card-deck">
							<div class="card">
								<a href="Item?item_id=<%=item.getId()%>&page_num=<%=pageNum%>"><img class="monster"
									src="<%="image/" + item.getFileName()%>"></a>
								<div class="card-body">
									<h5 class="card-title"><%=item.getName() %></h5>
									<p class="card-text"><%=item.getCaffeine() %>[mg/100ml]</p>
									<p class="card-text"><%=item.getPrice() %>[円]（税込）</p>
								</div>
							</div>
						</div>
				<%
					if (i % 3 == 0) {
				%>
					</div>
					<div class="row">
				<%
					}
				%>
				<%
					}
				%>
				</div>
				</div>
				<div class="row center">
					<ul class="pagination">
						<!-- １ページ戻るボタン  -->
				<%
					if (pageNum == 1) {
				%>
				<li class="disabled"><a><i class="material-icons">chevron_left</i></a></li>
				<%
					} else {
				%>
				<li class="waves-effect"><a href="<%="ItemSearchResult?search_word=" + searchWord + "&page_num=" + (pageNum - 1)%>"><i class="material-icons">chevron_left</i></a></li>
				<%
					}
				%>
						<!-- ページインデックス -->
				<%
					for (int j = pageNum - 5; j <= pageNum + 5; j++) {
				%>
						<!-- マイナスページが生成されないように -->
				<%
					if (j <= 0) {
							j = 1;
						}
				%>
				<li <%if (pageNum == j) {%> class="active" <%}%>><a href="<%="ItemSearchResult?search_word=" + searchWord + "&page_num=" + j%>"><%=j%></a></li>
						<!-- MAXを越さないように -->
				<%
					if (pageMax <= j) {
							break;
						}
				%>
				<%
					}
				%>
						<!-- 1ページ送るボタン -->
				<%
					if (pageNum == pageMax || pageMax == 0) {
				%>
				<li class="disabled"><a><i class="material-icons">chevron_right</i></a></li>
				<%
					} else {
				%>
				<li class="waves-effect"><a href="<%="ItemSearchResult?search_word=" + searchWord + "&page_num=" + (pageNum + 1)%>"><i class="material-icons">chevron_right</i></a></li>
				<%
					}
				%>
					</ul>
				</div>
			</div>
			<div class="return">
				<p>
					<a href="IndexServlet">戻る</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>