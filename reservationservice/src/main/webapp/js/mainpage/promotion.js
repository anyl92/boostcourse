
	const loadPromotions = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/promotions");
		request.send();
	
		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    const promotions = JSON.parse(this.responseText);
			    setPromotions(promotions);
			}
		}
	}

	const setPromotions = (promotions) => {
		let $promotionUlTag = document.querySelector(".visual_img");
		
		promotions.items.forEach((promotion) => {
			let $promotionListForm = document.querySelector("#promotionItem").innerHTML;
			let setPromotionHTML = $promotionListForm
								.replace("{productImageUrl}", promotion.productImageUrl);
			$promotionUlTag.innerHTML += setPromotionHTML;
		})
		slidePromotions(promotions.items.length);
	}
	
	const slidePromotions = (imageCount) => {
		let count = 1;
		let $promotionUlTag = document.querySelector(".visual_img");
		
		for (var i = 0; i < imageCount; i++) {
		    $promotionUlTag.children[i].style.width = $promotionUlTag.offsetWidth + "px";
		}
		
		$promotionUlTag.style.width = ($promotionUlTag.offsetWidth * imageCount) + "px";
		$promotionUlTag.style.left = "0px";
		$promotionUlTag.style.transition = "1s ease-out";
		
		setInterval(() => {
			$promotionUlTag.style.left = (parseInt($promotionUlTag.style.left) 
										- ($promotionUlTag.offsetWidth / imageCount)) + "px";
			count++;
			
			if (count > imageCount) {
				count = 1;
				$promotionUlTag.style.left = "0px";
			}
		}, 1100);
	}
