
function Reservation(reservationTypes) {

	this.types = reservationTypes;
	this.currentCancelInfo;

	this.loadReservations();
}

Reservation.prototype = {
	loadReservations: function () {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/reservations?email="
			+ document.querySelector("#reservation_info_email_value").dataset.email);
		request.send();
	
		request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				const reservations = JSON.parse(request.responseText);
				console.log(reservations);
				this.setReservations(reservations.reservations);
			}
		}.bind(this);
	},
	setReservations: function (reservations) {
		reservations.forEach((reservation) => {
			let reserved = reservation.cancelYn;
			let currentType;
			if (reserved == true) {
				currentType = this.types[0];
			} else {
				currentType = this.types[2];
			}
			this.makeReservationCard(currentType, reservation.displayInfo, reservation, reserved);
		})
		this.setSummaryBoard();
		this.clickCancelButton();
	},
	makeReservationCard: function (currentType, displayInfo, reservation, reserved) {
		const totalPrice = this.convertToCommaPrice(reservation.totalPrice);
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
		currentType.innerHTML += bindTemplate(bindData);
		
		if (reserved == false) {
			currentType.querySelector("#cancel_button").remove();
		}
	},
	convertToCommaPrice: function(price) {
        let commaPrice = '';
        let count = 0;

        if (price == 0) {
			return '0';
		}

        while (price) {
            if (count != 0 && (count % 3) == 0) {
                commaPrice += ",";
			}

            commaPrice += String(price % 10);
            price = Math.floor(price / 10);
            count++;
        }

        return commaPrice.split("").reverse().join("");
	},
	setSummaryBoard: function() {
		let reserved = this.types[0].childElementCount - 2;
		let used = this.types[1].childElementCount - 2;
		let canceled = this.types[2].childElementCount - 2;
		
		let $countBoardForm = document.querySelector("#reservation_count").innerHTML;
		let bindTemplate = Handlebars.compile($countBoardForm);
		let bindData = {
			"total": reserved + used + canceled,
			"confirmed": reserved,
			"used": used,
			"usedCancel": canceled
		}
		document.querySelector(".summary_board").innerHTML = bindTemplate(bindData);
		this.makeNoReservation();
	},
	clickCancelButton: function() {
		document.querySelectorAll("#cancel_button").forEach((cancelButton) => {
	    	cancelButton.addEventListener("click", () => {
				this.openCancelPopup();
				this.setCancelPopup(cancelButton);
			})
		})
	},
	makeNoReservation: function() {
		this.types.forEach((type) => {
			let $currentTag = type.lastElementChild;
			if ($currentTag.tagName == "DIV") {
				$currentTag.style.display = "block";
			}
		})
	},
	setCancelPopup: function(cancelInfo) {
		document.querySelector("#popup_description").innerText = cancelInfo.dataset.description;
		document.querySelector("#popup_date").innerText = cancelInfo.dataset.reservationdate;
		
		this.currentCancelInfo = cancelInfo;
	},
	cancelReservation: function() {
		const cancelInfo = this.currentCancelInfo;
		let request = new XMLHttpRequest();
		request.open("PUT", "/api/reservations/"
			+ cancelInfo.dataset.id);
		request.send();
	
		request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				if (request.responseText == "success") {
					moveReservationCard(cancelInfo.dataset.id);
				} else {
					alert("예약 취소에 실패하였습니다.")
				}
				this.closeCancelPopup();
			}
		}.bind(this);
	},
	moveReservationCard: function(reservationInfoId) {
		document.querySelectorAll(".card_item").forEach(card => {
			if (card.dataset.id == reservationInfoId) {
				document.querySelector(".card.used.cancel").children[1].style.display = "none";
				document.querySelector(".card.used.cancel").append(card);
				card.querySelector("#cancel_button").remove();
				document.querySelector(".card.confirmed").removeEventListener;
			}
		})
		this.setSummaryBoard();
	},
	openCancelPopup: function() {
		document.querySelector(".popup_booking_wrapper").style.display = "block";
	},
	closeCancelPopup: function() {
		document.querySelector(".popup_booking_wrapper").style.display = "none";
	}
	// clickCancelPopup: function() {

	// },
	// clickConfirmPopup: function() {
		
	// }
}

$reserved = document.querySelector(".card.confirmed");
$used = document.querySelector(".card.used");
$canceled = document.querySelector(".card.used.cancel");

const types = [$reserved, $used, $canceled];
const reservation = new Reservation(types);

document.querySelector("#cancel_ok").addEventListener("click", () => {
	reservation.cancelReservation();
})
document.querySelector("#cancel_no").addEventListener("click", () => {
	reservation.closeCancelPopup();
})
//window.addEventListener("DOMContentLoaded", reservation.init);
