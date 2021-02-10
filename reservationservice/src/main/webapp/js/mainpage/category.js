

	const loadCategories = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/categories");
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    const categories = JSON.parse(this.responseText);
			    setCategories(categories);
    		}
		}
	}
	
	const setCategories = (categories) => {
		let count = 1;
		let $categoryUlTag = document.querySelector(".event_tab_lst");

		categories.items.forEach((category) => {
			let $categoryListForm = document.querySelector("#categoryItem").innerHTML;
			let setCategoryHTML = $categoryListForm.replace("{categoryName}", category.name)
												.replace("{categoryId}", category.id);
			$categoryUlTag.innerHTML += setCategoryHTML;
			count++;
		});
		clickCategory($categoryUlTag);
	}
	
	const clickCategory = ($categoryUlTag) => {
		let categoryCount = $categoryUlTag.childElementCount;
		for (var i = 0; i < categoryCount; i++) {
			$categoryUlTag.children[i].addEventListener("click", (event) => {
				let categoryTarget = event.target;
				let $categoryATag = categoryTarget.parentNode;
				
				if (categoryTarget.tagName !== "SPAN") {
					return;
				}
				document.querySelector(".active").setAttribute("class", "anchor");
				$categoryATag.setAttribute("class", "anchor active");
				deleteProducts();
				showMoreButtonActive();

				const categoryId = $categoryATag.parentNode.dataset.category;
				loadProducts(categoryId, 0);
			});
		}
	}


	const showMoreButtonActive = () => {
		document.querySelector("#show_more_button").style.display = "block";
	}
	