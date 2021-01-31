<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ tarlib prefix = "c" uri="http://java.sun.com/jstl/core_rt" %> --%>
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

<div class="product-list">
	<div class="product-list-left">
		<div class="product-item">

		</div>
	</div>
	<div class="product-list-right">
	
	</div>
</div>
	

</body>
<script>

	const loadCategories = () => {
		let request = new XMLHttpRequest();
		request.open('GET', '/api/categories');
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState == 4 && request.status == 200) {
		    	console.log(this.response, '리스폰스');
//			    const categories = JSON.parse(this.responseText);
			    console.log(categories, '카테고리');
    		}
		};
	}
	
	const loadProducts = (categoryId, start) => {
		let request = new XMLHttpRequest();
		request.open('GET', '/api/products'
		    + '?categoryId=' + String(categoryId)
		    + '&start=' + String(start));
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState == 4 && request.status == 200) {
			    const products = JSON.parse(this.responseText);
			    setProducts(products);
			    console.log(products, '상품');
    		}
		};
	}
	
	const setProducts = ((products) => {
	    let productItems = document.getElementsByClassName('product-item')[0];
	    products.items.forEach((product) => {
		  	const productImage = document.createElement('img');
		  	const productDescription = document.createElement('h4');
		  	const productPlace = document.createElement('h6')
		  	const productContent = document.createElement('div');
		  	
		  	productImage.setAttribute("src",
			          "/img/" + product.productImageUrl);
		  	productDescription.innerHTML = product.productDescription;
		  	productPlace.innerHTML = product.placeName;
		  	productContent.innerHTML = product.productContent;
		  	
		  	productItems.appendChild(productImage);
		  	productItems.appendChild(productDescription);
		  	productItems.appendChild(productPlace);
		  	productItems.appendChild(productContent);	    	
	    });
	})

	window.addEventListener('DOMContentLoaded', (loadCategories(), loadProducts(0, 0)));


</script>
</html>