<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ tarlib prefix = "c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>네이버 예약</title>
</head>

<body>
<h1>main page~~~!!</h1>

<div class="item_tab_section">
    <ul class="item_tab_list tab_lst_min">
        <li class="items" data-category="0">
            <a class="item active"> <span>전체리스트</span> </a>
        </li>
        <li class="items" data-category="1">
            <a class="item"> <span>전시</span> </a>
        </li>
        <li class="items" data-category="2">
            <a class="item"> <span>뮤지컬</span> </a>
        </li>
        <li class="items" data-category="3">
            <a class="item"> <span>콘서트</span> </a>
        </li>
        <li class="items" data-category="4">
            <a class="item"> <span>클래식</span> </a>
        </li>
        <li class="items" data-category="5">
            <a class="item"> <span>연극</span> </a>
        </li>
    </ul>
</div>


	${products}

</body>
</html>