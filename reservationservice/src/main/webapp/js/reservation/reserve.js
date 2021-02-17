import AddCommaInNumber from "/js/utils/addCommaInNumber.js"; 

function Reserve() {
	
	const loadDisplayInfos = () => {
		let request = new XMLHttpRequest();
		request.open("GET", "/api/products"
		    + "/" + document.querySelector("#display_info_id_value").dataset.id
		    + "/reserve");
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    const displayInfos = JSON.parse(this.responseText);
				setDisplayInfos(displayInfos);
    		}
		}
	}
	
	const setDisplayInfos = (displayInfos) => {
		makeDisplayImage(displayInfos.productImages[0].saveFileName);
		makeDisplayInfo(displayInfos.displayInfo);
		makeTicketInfo(displayInfos.productPrices);
	}
	
	const makeDisplayImage = (saveFileName) => {
		let $displayImageForm = document.querySelector("#display_image").innerHTML;
		let bindTemplate = Handlebars.compile($displayImageForm);
		let bindData = {
			"saveFileName": saveFileName,
		}
		document.querySelector(".container_visual").innerHTML += bindTemplate(bindData);
	}
	
	const makeDisplayInfo = (displayInfo) => {
		let $displayInfoForm = document.querySelector("#display_info").innerHTML;
		let bindTemplate = Handlebars.compile($displayInfoForm);
		let bindData = {
			"productId": displayInfo.productId,
			"placeName": displayInfo.placeName,
			"openingHours": displayInfo.openingHours
		}
		document.querySelector(".store_details").innerHTML += bindTemplate(bindData);
	}
	
	const makeTicketInfo = (productPrices) => {
		let $ticketInfoForm = document.querySelector("#ticket_info").innerHTML;
		productPrices.forEach((productPrice) => {
			let bindTemplate = Handlebars.compile($ticketInfoForm);
			let bindData = {
				"productPriceId": productPrice.productPriceId,
				"priceId": productPrice.productPriceId,
				"priceTypeName": setTicketType(productPrice.priceTypeName),
				"price": productPrice.price,
				"discountRate": productPrice.discountRate 
			}
			document.querySelector(".ticket_body").innerHTML += bindTemplate(bindData);
		})
		setTicketCountAndPrice();
	}
	
	const setTicketType = (ticketType) => {
		switch (ticketType) {
			case "A":
				return ticketType = "성인";
			case "Y":
				return ticketType = "청소년";
			case "B":
				return ticketType = "유아";
			case "S":
				return ticketType = "셋트";
			case "D":
				return ticketType = "장애인";
			case "C":
				return ticketType = "지역주민";
			case "E":
				return ticketType = "얼리버드";
			case "V":
				return ticketType = "VIP";
			case "R":
				return ticketType = "R석";
			case "B":
				return ticketType = "B석";
			case "S":
				return ticketType = "S석";
			case "D":
				return ticketType = "평일";
		}
	}
	
    const setTicketCountAndPrice = () => {
        let $totalTicketCount = document.querySelector("#total_count");
        document.querySelectorAll(".qty").forEach((ticketType) => {
            const $clearfix = ticketType.querySelector(".clearfix");
            const $minusButton = $clearfix.querySelector(".ico_minus3");
            const $plusButton = $clearfix.querySelector(".ico_plus3");
            
            let $totalTicketPrice = ticketType.querySelector(".total_price");
            const $ticketPrice = ticketType.querySelector(".price").innerHTML;
            
            let $ticketCount = $clearfix.querySelector(".count_control_input");
            const $individualPrice = ticketType.querySelector(".individual_price");
			
            $minusButton.addEventListener("click", () => {
	            if ($ticketCount.value == "0") {
		        	return;
		        }

		        $ticketCount.value--;
                //$totalTicketCount.innerHTML--;

                let totalCount = Number($totalTicketCount.innerHTML);
                $totalTicketCount.innerHTML = totalCount - 1;
                
		        $totalTicketPrice.innerHTML = calcTotalPrice($ticketCount.value, $ticketPrice);
                
		        if ($ticketCount.value == "0" && !$minusButton.classList.contains("disabled")) {
                    toggleMinusButtonState($minusButton, $ticketCount, $individualPrice);
		        }
                setReserveButton();
            })
            
            $plusButton.addEventListener("click", () => {
	            $ticketCount.value++;
		        //$totalTicketCount.innerHTML++;

                let totalCount = Number($totalTicketCount.innerHTML);
                $totalTicketCount.innerHTML = totalCount + 1;

		        $totalTicketPrice.innerHTML = calcTotalPrice($ticketCount.value, $ticketPrice);
		
		        if ($minusButton.classList.contains("disabled")) {
		            toggleMinusButtonState($minusButton, $ticketCount, $individualPrice);
	        	}
                setReserveButton();
            })
        })
    }

    const toggleMinusButtonState = ($minusButton, $countControlInput, $individualPrice) => {
        $minusButton.classList.toggle("disabled");
        $countControlInput.classList.toggle("disabled");
        $individualPrice.classList.toggle("on_color");
    }
	
    const calcTotalPrice = (countControlInput, productPrice) => {
        let price = Number(countControlInput) * Number(productPrice.replace(',', ''));
        return addCommaInNumber.convertNumber(price);
    }
    
    const validCheckEvent = () => {
        validCheckByName();
        validCheckByTelNumber();
        validCheckByEmail();
        validCheckByAgreeTerms();
    }

    const validCheckByName = () => {
        const $nameInput = document.querySelector("[id='name']");
        const INVALID_NAME = "예매자명을 입력해주세요.";

        $nameInput.addEventListener("focusin", () => {
            $nameInput.style.color = "black";
            if ($nameInput.value == INVALID_NAME) {
                $nameInput.value = "";
            }
        })
        $nameInput.addEventListener("focusout", () => {
            if (isAllValidRight() == true) {
                return activeReserveButton();
            } 
            if (isValidName() == false) {
                $nameInput.value = INVALID_NAME;
                $nameInput.style.color = "deeppink";
            }
            inActiveReserveButton();
        })
    }

    const validCheckByTelNumber = () => {
        const $telNumberInput = document.querySelector("[id='tel']");
        const INVALID_TEL_NUMBER = "잘못된 번호 형식입니다.";

        $telNumberInput.addEventListener("focusin", () => {
            $telNumberInput.style.color = "black";
            if ($telNumberInput.value == INVALID_TEL_NUMBER) {
                $telNumberInput.value = "";
            }
        })
        $telNumberInput.addEventListener("focusout", () => {
            if (isAllValidRight() == true) {
                return activeReserveButton();
            }
            if (isValidTelNumber() == false) {
                $telNumberInput.value = INVALID_TEL_NUMBER;
                $telNumberInput.style.color = "deeppink";
            }
            inActiveReserveButton();
        })
    }

    const validCheckByEmail = () => {
        const $emailInput = document.querySelector("[id='email']");
        const INVALID_EMAIL = "잘못된 이메일 형식입니다.";

        $emailInput.addEventListener("focusin", () => {
            $emailInput.style.color = "black";
            if ($emailInput.value == INVALID_EMAIL) {
                $emailInput.value = "";
            }
        })
        $emailInput.addEventListener("focusout", () => {
            if (isAllValidRight() == true) {
                return activeReserveButton();
            }
            if (isValidEmail() == false) {
                $emailInput.value = INVALID_EMAIL;
                $emailInput.style.color = "deeppink";
            }
            inActiveReserveButton();
        })
    }

    const validCheckByAgreeTerms = () => {
        const $agreeCheckBox = document.querySelector("#chk3");
        $agreeCheckBox.addEventListener("click", () => {
            setReserveButton();
        })
    }

    const activeReserveButton = () => {
        const $reserveButton = document.querySelector(".bk_btn_wrap");
        $reserveButton.classList.remove("disable");
    }

    const inActiveReserveButton = () => {
        const $reserveButton = document.querySelector(".bk_btn_wrap");
        $reserveButton.classList.add("disable");
    }

    const isValidName = () => {
    	const nameLength = document.querySelector("[id='name']").value.length;
    	if (0 < nameLength && nameLength < 18) {
        	return true;
        }
        return false;
    }

    const isValidTelNumber = () => {
        let inputValue = document.querySelector("[id='tel']").value;
        if (inputValue.length <= 13) {
            return (/0[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}/).test(inputValue);
        }
        return false;
    }

    const isValidEmail = () => {
        const email = document.querySelector("[id='email']").value;
        const matchedList = email.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
        if (matchedList && email.length == matchedList[0].length) {
            return true;
        }
        return false;
    }

    const isCheckedAgreement = () => {
        return document.querySelector("#chk3").checked;
    }

    const isSelectedTicket = () => {
        return Number(document.querySelector("#total_count").innerText) > 0;
    }

    const isAllValidRight = () => {
        return isValidName() && isValidTelNumber() && isValidEmail() && isCheckedAgreement() && isSelectedTicket();
    }

    const setReserveButton = () => {
        if (isAllValidRight() == true) {
            return activeReserveButton();
        }
        inActiveReserveButton();
    }

    document.querySelector(".section_booking_agreement").querySelectorAll(".agreement").forEach(agreement => {
        if (agreement.classList.contains("all")) {
            return;
        }
        agreement.querySelector(".btn_agreement").addEventListener("click", () => {
            agreement.classList.toggle("open");
        });
    });
	
	const getReserveInfo = () => {
		let prices = [];
		document.querySelectorAll(".qty").forEach((qty) => {
			let price = {
				"count": qty.querySelector(".count_control_input").value,
				"productPriceId": qty.dataset.id
			}
			prices.push(price);
		})
		
		const $formTag = document.querySelector(".form_horizontal");
		let formData = {
			"displayInfoId": document.querySelector("#display_info_id_value").dataset.id,
			"prices": prices,
			"productId": document.querySelector("#hide_product_id").dataset.id,
			"reservationName": $formTag.querySelector("#name").value,
			"reservationEmail": $formTag.querySelector("#email").value,
			"reservationTelephone": $formTag.querySelector("#tel").value
		}
		sendReserveInfo(formData);
	}
	
	document.querySelector(".bk_btn").addEventListener("click", () => {
		if (isAllValidRight() == true) {
			getReserveInfo();
		}
	})

	
	const sendReserveInfo = (data) => {
		let request = new XMLHttpRequest();
	    
	    request.open("POST", "/api/reservations");
	    request.setRequestHeader("Content-Type", "application/json");
	    request.send(JSON.stringify(data));
	
	    request.onreadystatechange = function () {
			if (request.readyState === 4 && request.status === 200) {
				alert("예약되었습니다.");
				window.location.href = "/";
			}
	   	}
	}
	
    const addCommaInNumber = new AddCommaInNumber();
    
	this.init = () => {	
		loadDisplayInfos();
		validCheckEvent();
	} 
}

const reserve = new Reserve();
window.addEventListener("DOMContentLoaded", reserve.init);
