
	window.addEventListener("DOMContentLoaded", () => {
		loadDisplayInfos();
	});


	const loadDisplayInfos = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/products"
			+ "/" + document.querySelector("#display_info_id_value").dataset.id);
		request.send();
	
		request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				const displayInfos = JSON.parse(this.responseText);
				console.log(displayInfos);
				setDisplayInfos(displayInfos);
			}
		}
	}
	
	const setDisplayInfos = (displayInfos) => {
		setAverageScore(displayInfos.averageScore, displayInfos.comments.length);
		setComments(displayInfos.comments);
		setProductImages(displayInfos.productImages, displayInfos.displayInfo.productDescription);
		setProductImagesCount(displayInfos.productImages.length);
		setProductContent(displayInfos.displayInfo.productContent);
		setDisplayInfo(displayInfos.displayInfo, displayInfos.displayInfoImage.saveFileName);
	}

	const setAverageScore = (averageScore, commentsLength) => {
		averageScore = setScoreToFloat(averageScore);
		const width = `${Number(averageScore / 5.0) * 100}%`;

		let $displayScoreDivTag = document.querySelector(".grade_area");
		let $displayScoreForm = document.querySelector("#display_score").innerHTML;		
		bindTemplate = Handlebars.compile($displayScoreForm);
		bindData = {
			"score": averageScore,
			"count": commentsLength,
			"width": width
		}
		$displayScoreDivTag.innerHTML = bindTemplate(bindData);
	}
	
	const setScoreToFloat = (score) => {
		if (isNaN(score)) {
			score = 0;
		}
		return score.toFixed(1);
	}

	const setImageisExist = (commentImages) => {
		if (commentImages.length === 0) {
			return false;
		}
		return true;
	}

	const setReservationDate = (dateObject) => {
		return dateObject.year + "." + dateObject.monthValue + "." + dateObject.dayOfYear + ".";
	}

	const setComments = (comments) => {
		let $commentUlTag = document.querySelector(".list_short_review");
		for (let i = 0; i < 3; i++) {
			if (i == comments.length) {
				break;
			}

			const score = setScoreToFloat(comments[i].score);
			const imageIsExist = setImageisExist(comments[i].commentImages);
			const setDate = setReservationDate(comments[i].reservationDate);
			
			let $commentForm = document.querySelector("#display_comment_list").innerHTML;
			let bindTemplate = Handlebars.compile($commentForm);
			let bindData = {
				"imageIsNotExist": imageIsExist ? "" : "display:none",
				"commentImage": imageIsExist ? comments[i].commentImages[0].saveFileName : "",
				"comment": comments[i].comment,
				"score": score,
				"starOfuserId": comments[i].starOfUserId,
				"reservationDate": setDate
			}
			$commentUlTag.innerHTML += bindTemplate(bindData);
		}
	}

	const setProductImages = (productImages, description) => {
		let $displayImageUlTag = document.querySelector(".visual_img");
		productImages.forEach((productImage) => {
			let $displayImageForm = document.querySelector("#display_image_List").innerHTML;
			let bindTemplate = Handlebars.compile($displayImageForm);
			let bindData = {
				"description": description,
				"saveFileName": productImage.saveFileName
			}
			$displayImageUlTag.innerHTML += bindTemplate(bindData);
		});
		setProductImageStyle();
	}
	
	const setProductImagesCount = (imagesLength) => {
		let $displayImageCountDivTag = document.querySelector(".figure_pagination");
		
		let $displayImageCountForm = document.querySelector("#display_image_count").innerHTML;
		let bindTemplate = Handlebars.compile($displayImageCountForm);
		let bindData = {
			"current": 1,
			"total": imagesLength
		}
		$displayImageCountDivTag.innerHTML += bindTemplate(bindData);
		
		if (imagesLength > 1) {
			document.querySelector(".btn_nxt").addEventListener("click", () => nextImageViewButton());
			document.querySelector(".btn_prev").addEventListener("click", () => prevImagViewButton());
		}
	}

	const setProductContent = (content) => {
		let $displayContentDivTag = document.querySelector(".store_details");
		let $displayContentForm = document.querySelector("#display_content").innerHTML;
		let bindTemplate = Handlebars.compile($displayContentForm);
		let bindData = {
			"content": content
		}
		$displayContentDivTag.innerHTML = bindTemplate(bindData);
		
		let $detailContent = document.querySelector("#detail-content");
		$detailContent.innerText = content;
	}

	const setDisplayInfo = (display, saveFileName) => {
		let $displayInfoMapDivTag = document.querySelector(".detail_location");
		let $displayInfoMapForm = document.querySelector("#display_map").innerHTML;
		bindTemplate = Handlebars.compile($displayInfoMapForm);
		bindData = {
			"homePage": display.homepage,
			"saveFileName": saveFileName,
			"productDescription": display.productDescription,
			"placeStreet": display.placeStreet,
			"placeLot": display.placeLot,
			"placeName": display.placeName,
			"telephone": display.telephone ? display.telephone : "-"
		}
		$displayInfoMapDivTag.innerHTML += bindTemplate(bindData);
	}


	document.querySelector(".info_tab_lst").addEventListener("click", (event) => {
		let $displayInfoTabUlTag = document.querySelector(".info_tab_lst");
		let clickedTab;

		if (event.target.classList.contains("_detail") == true) {
			clickedTab = 0;
		} else if (event.target.classList.contains("_path") == true) {
			clickedTab = 1;
		}
		
		if (event.target.tagName != "SPAN") {
			return;
		}
		if ($displayInfoTabUlTag.children[clickedTab].firstElementChild.classList.contains("active")) {
			return;
		}
		
		$displayInfoTabUlTag.children[0].firstElementChild.classList.toggle("active");
		$displayInfoTabUlTag.children[1].firstElementChild.classList.toggle("active");
		
		document.querySelector("#info_tab_detail").classList.toggle("hide");
		document.querySelector("#info_tab_map").classList.toggle("hide");
	})
	
	
	document.querySelector(".bk_more._open").addEventListener("click", () => {
		document.querySelector(".store_details").classList.toggle("close3");
		document.querySelector(".bk_more._open").style.display = "none";
		document.querySelector(".bk_more._close").style.display = "block";
	})
	
	document.querySelector(".bk_more._close").addEventListener("click", () => {
		document.querySelector(".store_details").classList.toggle("close3");
		document.querySelector(".bk_more._open").style.display = "block";
		document.querySelector(".bk_more._close").style.display = "none";
	})
	

	const setProductImageStyle = () => {
		let $displayImageUlTag = document.querySelector(".visual_img");
		$displayImageUlTag.style.width = ($displayImageUlTag.offsetWidth * 2 ) + "px";
		$displayImageUlTag.style.transition = "1s ease-out";
		$displayImageUlTag.style.left = "0px";
	}
	
	const nextImageViewButton = () => {
		let $displayImageUlTag = document.querySelector(".visual_img");
		let calc = (parseInt($displayImageUlTag.style.left) 
										- ($displayImageUlTag.offsetWidth / 2));
		if (calc < -414) {
			$displayImageUlTag.style.left = "0px";
		} else {
			$displayImageUlTag.style.left = calc + "px";
		}
		document.querySelector("#display_image_number").innerText 
			= $displayImageUlTag.style.left == "0px" ? 1 : 2;
	}
	
	const prevImagViewButton = () => {
		let $displayImageUlTag = document.querySelector(".visual_img");
		let calc = (parseInt($displayImageUlTag.style.left) 
										+ ($displayImageUlTag.offsetWidth / 2));
		if (calc > 0) {
			$displayImageUlTag.style.left = "-414px";
		} else {
			$displayImageUlTag.style.left = calc + "px";
		}
		document.querySelector("#display_image_number").innerText 
			= $displayImageUlTag.style.left == "0px" ? 1 : 2;
	}

