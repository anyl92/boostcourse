	
	window.addEventListener("DOMContentLoaded", () => {
		loadComments();
	});


	const loadComments = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/products"
			    + "/" + document.querySelector("#display_info_id_value").dataset.id
			    + "/reviews");
		request.send();
		
		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    const comments = JSON.parse(this.responseText);
			    console.log(comments);
			    setComments(comments.comments);
			    setAverageScore(comments.averageScore, comments.comments.length);
			}
		}
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
		comments.forEach((comment) => {
			const score = setScoreToFloat(comment.score);
			const imageIsExist = setImageisExist(comment.commentImages);
			const setDate = setReservationDate(comment.reservationDate);
			
			let $commentForm = document.querySelector("#display_comment_list").innerHTML;
			let bindTemplate = Handlebars.compile($commentForm);
			let bindData = {
				"imageIsNotExist": imageIsExist ? "" : "display:none",
				"commentImage": imageIsExist ? comment.commentImages[0].saveFileName : "",
				"comment": comment.comment,
				"score": score,
				"starOfuserId": comment.starOfUserId,
				"reservationDate": setDate
			}
			$commentUlTag.innerHTML += bindTemplate(bindData);
		})
	}
	
