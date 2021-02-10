
	const deleteProducts = () => {
		let $productUlTags = document.querySelectorAll(".lst_event_box");
		$productUlTags.forEach((ul) => {
			while (ul.childElementCount !== 0) {
			    ul.firstElementChild.remove();
			}
		});
	}

	const loadProducts = (categoryId, start) => {
		let categoryIdRequestMessage = "";
		if (categoryId && categoryId > 0) {
			categoryIdRequestMessage = "categoryId=" + String(categoryId) + "&";
		}
		
		let request = new XMLHttpRequest();
		request.open("GET", "/api/products?"
		    + categoryIdRequestMessage
		    + "start=" + String(start));
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    const products = JSON.parse(this.responseText);
				setProducts(products);
				setProductsCountData(products.count);
    		}
		}
	}

	const setProducts = (products) => {
		let $productUlTags = document.querySelectorAll(".lst_event_box");
		let setDirection;
		let directionCount = 0;
		
	    products.items.forEach((product) => {		    
	    	if (directionCount % 2 === 0) {
	    		setDirection = 0;
    		} else {
    			setDirection = 1;
			}
	    	directionCount++;
	    	
	    	let $productListForm = document.querySelector("#itemList").innerHTML;
			let setProductHTML = $productListForm.replace("{id}", product.productId)
											.replace("{description}", product.productDescription)
											.replace("{ImageUrl}", product.productImageUrl)
											.replace("{description}", product.productDescription)
											.replace("{placeName}", product.placeName)
											.replace("{content}", product.productContent);
			$productUlTags[setDirection].innerHTML += setProductHTML;
	    });
	}

	const setProductsCountData = (productsCount) => {
	    let $productCountSpanTag = document.querySelector(".pink");
	    $productCountSpanTag.innerText = productsCount + "개";
	    $productCountSpanTag.dataset.count = productsCount;
	    
		let nowProductsCount = 0;
		let $productUlTags = document.querySelectorAll(".lst_event_box");
		$productUlTags.forEach((ul) => {
			nowProductsCount += ul.childElementCount;
		});
		document.querySelector(".wrap_event_box").dataset.count = nowProductsCount;
		
		if ($productCountSpanTag.dataset.count == nowProductsCount) {
			showMoreButtonInActive();
		}
	}
	
	const showMoreButtonInActive = () => {
		document.querySelector("#show_more_button").style.display = "none";
	}

    document.querySelector("#show_more_button").addEventListener("click", () => {
    	const categoryId = document.querySelector(".active").parentNode.dataset.category;
    	const start = document.querySelector(".wrap_event_box").dataset.count;
		loadProducts(categoryId, start);
    });
