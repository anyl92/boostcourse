
function ReviewWrite () {

	let $ratingStarDivTags = document.querySelector(".rating");
	$ratingStarDivTags.addEventListener("click", (event) => {
		if (event.target.tagName !== "INPUT") {
			return;
		}
		const value = event.target.value;
		
		const $ratingTextTag = document.querySelector(".star_rank");
		$ratingTextTag.innerText = value;
		$ratingTextTag.classList.remove("gray_star");
		
		paintStars(event.target.value-1);
	})
	
	const paintStars = (value) => {
		let currentStar = document.querySelectorAll(".rating_rdo");
		for (let i = 0; i < 5; i++) {
			if (i <= value) {
				currentStar[i].checked = true;
			} else if (i > value) {
				currentStar[i].checked = false;
			}
		}
	}
	
	const $reviewWriteATag = document.querySelector(".review_write_info");
	$reviewWriteATag.addEventListener("click", () => {
		$reviewWriteATag.style.display = "none";
		document.querySelector(".review_textarea").focus();
	})
	
	document.querySelector(".review_textarea").addEventListener("keyup", (event) => {
		let textLength = event.target.value.length;
		document.querySelector(".guide_review").firstElementChild.innerText = textLength;
		
	})
	
	const $fileUploadTag = document.querySelector("#reviewImageFileOpenInput");
	$fileUploadTag.addEventListener("change", (event) => {
		const file = event.target.files[0];

		if (isValidFileType(file) == false) {
			alert("파일 확장자가 유효하지 않습니다. *.jpg/*.png 파일만 업로드 가능합니다.");
			return;
		}
		showThumbImage(file);
		sendImageFile();
	})
	
	const isValidFileType = (file) => {
		const result = (["image/jpg", "image/png"].indexOf(file.type) >- 1);
		return result;
	}
	
	const showThumbImage = (file) => {
		const $thumbImageTag = document.querySelector(".item_thumb");
		$thumbImageTag.src = window.URL.createObjectURL(file);
		$thumbImageTag.parentElement.style.display = "block";
	}
	
	const sendImageFile = () => {
		let formData = new FormData();
		
		formData.method = "post";
		formData.enctype = "multipart/form-data";
		formData.append("file", document.querySelector("#reviewImageFileOpenInput").files[0]);

		let request = new XMLHttpRequest();
		request.open("POST", "/api/reservations/" + 2 + "/upload");
		request.send(formData);
	
	    request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				console.log("전송성공");
			}
	   	}
	}
	
}

const reviewWrite = new ReviewWrite();
window.addEventListener("DOMContentLoaded", reviewWrite);
    
    