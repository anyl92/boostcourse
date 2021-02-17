import AddCommaInNumber from "/js/utils/addCommaInNumber.js"; 

function Reservation() {
	
	const loadReservations = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/reservations?email="
			+ document.querySelector("#reservation_info_email_value").dataset.email);
		request.send();
	
		request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				const reservations = JSON.parse(this.responseText);
				setReservations(reservations.reservations);
			}
		}
	}
	
	// 예약 항목의 상태를 체크
	const setReservations = (reservations) => {
		reservations.forEach((reservation) => {
			let canceled = reservation.cancelYn;
			let $reservationInputTag;
			if (canceled == false) {
				$reservationInputTag = document.querySelector(".card.confirmed");
			} else {
				$reservationInputTag = document.querySelector(".card.used.cancel");
			}
			makeReservationCard($reservationInputTag, reservation.displayInfo, reservation, canceled);
		})
		setSummaryBoard();
		clickCancelButton();
	}

	// 체크한 예약 상태에 해당하는 위치에 템플릿을 그림
	const makeReservationCard = ($reservationInputTag, displayInfo, reservation, canceled) => {
		const totalPrice = addCommaInNumber.convertNumber(reservation.totalPrice);
		const reservationDate = reservation.reservationDate;
		
		let $reservationForm = document.querySelector("#confirm_cards").innerHTML;
		let bindTemplate = Handlebars.compile($reservationForm);
		let bindData = {
			"productDescription": displayInfo.productDescription,
			"reservationDate": reservationDate.year
		          + "." + reservationDate.monthValue
		          + "." + reservationDate.dayOfMonth,
			"productContent": "예약내역", 
			"placeName": displayInfo.placeName, 
			"homepage": displayInfo.homepage, 
			"totalPrice": totalPrice,
			"reservationInfoId": reservation.reservationInfoId
		}
		$reservationInputTag.innerHTML += bindTemplate(bindData);
		
		if (canceled == true) {
			$reservationInputTag.querySelector("#cancel_button").remove();
		}
	}
	
	// 태그의 child 개수로 summary board 카운트
	const setSummaryBoard = () => {
		let reserved = document.querySelector(".card.confirmed").childElementCount - 2;
		let used = document.querySelector(".card.used").childElementCount - 2;
		let canceled = document.querySelector(".card.used.cancel").childElementCount - 2;
		
		let $countBoardForm = document.querySelector("#reservation_count").innerHTML;
		let bindTemplate = Handlebars.compile($countBoardForm);
		let bindData = {
			"total": reserved + used + canceled,
			"confirmed": reserved,
			"used": used,
			"usedCancel": canceled
		}
		document.querySelector(".summary_board").innerHTML = bindTemplate(bindData);

		makeNoReservation();
	}
	
	// 예약 리스트가 비었을 경우 err div 보여주기
	const makeNoReservation = () => {
		let $confirmedTag = document.querySelector(".card.confirmed").lastElementChild;
		if ($confirmedTag.tagName == "DIV") {
			$confirmedTag.style.display = "block";
		}
		
		let $usedTag = document.querySelector(".card.used").lastElementChild;
		if ($usedTag.tagName == "DIV") {
			$usedTag.style.display = "block";
		}
		
		let $usedCancelTag = document.querySelector(".card.used.cancel").lastElementChild;
		if ($usedCancelTag.tagName == "DIV") {
			$usedCancelTag.style.display = "block";
		}
	}
	
	// 어떤 취소 버튼이 눌렸는지 확인 후 팝업 띄움
	const clickCancelButton = () => {
		document.querySelectorAll("#cancel_button").forEach((cancelButton) => {
	    	cancelButton.addEventListener("click", () => {
				openCancelPopup();
				setCancelPopup(cancelButton);
			})
		})
	}
	

	// 클릭된 예약 정보를 팝업에도 동일하게 세팅
	let currentCancelInfo;
	const setCancelPopup = (cancelInfo) => {
		document.querySelector("#popup_description").innerText = cancelInfo.dataset.description;
		document.querySelector("#popup_date").innerText = cancelInfo.dataset.reservationdate;
		
		currentCancelInfo = cancelInfo;
	}
	
	const closeCancelPopup = () => {
		document.querySelector(".popup_booking_wrapper").style.display = "none";
	}

	const openCancelPopup = () => {
		document.querySelector(".popup_booking_wrapper").style.display = "block";
	}

	// 취소하시겠습니까? 아니요 -> 팝업 닫음
	document.querySelector("#cancel_no").addEventListener("click", () => {
		closeCancelPopup();
	})

	document.querySelector("#cancel_ok").addEventListener("click", () => {
		cancelReservation();
	})

	// 취소 시 PUT 요청
	const cancelReservation = () => {
		const cancelInfo = currentCancelInfo
		let request = new XMLHttpRequest();
		request.open("PUT", "/api/reservations/"
			+ cancelInfo.dataset.id);
		request.send();
	
		request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				moveReservationCard(cancelInfo.dataset.id);
			} else if (request.readyState === 4 && request.status === 500) {
				alert("예약 취소에 실패하였습니다.")
			}
			closeCancelPopup();
		}
	}
	
	// 취소 시 예약 카드 옮기고 숫자 수정
	const moveReservationCard = (reservationInfoId) => {
		document.querySelectorAll(".card_item").forEach(card => {
			if (card.dataset.id == reservationInfoId) {
				document.querySelector(".card.used.cancel").children[1].style.display = "none";
				document.querySelector(".card.used.cancel").append(card);
				card.querySelector("#cancel_button").remove();
				document.querySelector(".card.confirmed").removeEventListener;
			}
		})
		setSummaryBoard();
	}

	const addCommaInNumber = new AddCommaInNumber();
	
	this.init = () => {
		loadReservations();
	}
}

const reservation = new Reservation;
window.addEventListener("DOMContentLoaded", reservation.init);
