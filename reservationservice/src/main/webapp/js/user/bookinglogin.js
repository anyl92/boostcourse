
function BookingLogin () {

	sendConfirmedEmail = () => {
		const email = document.querySelector("[id='resrv_id']").value;
		let request = new XMLHttpRequest();
		request.open("GET", `/myreservation?email=${email}`);
		request.send();

		request.onreadystatechange = function () {
		    if (request.readyState === 4 && request.status === 200) {
			    window.location.href = request.responseURL; 
    		} else if (request.readyState === 4 && request.status === 500) {
    			alert("예약 정보가 없습니다");
    		}
		}
	}
    
    const validCheckByEmail = () => {
        const $emailInput = document.querySelector("[id='resrv_id']");
        const INVALID_EMAIL = "잘못된 이메일 형식입니다.";

        $emailInput.addEventListener("focusin", () => {
            $emailInput.style.color = "black";
            if ($emailInput.value == INVALID_EMAIL) {
                $emailInput.value = "";
            }
        })
        $emailInput.addEventListener("focusout", () => {
            if (isValidEmail() == false) {
                $emailInput.value = INVALID_EMAIL;
                $emailInput.style.color = "deeppink";
            }
        })
    }
    	
	const isValidEmail = () => {
        const email = document.querySelector("[id='resrv_id']").value;
        const matchedList = email.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
        if (matchedList && email.length == matchedList[0].length) {
            return true;
        }
        return false;
    }
    
    document.querySelector(".login_btn").addEventListener("click", () => {
    	if (isValidEmail() == true) {
			sendConfirmedEmail();
		}
	})
	
	this.init = () => {	
		validCheckByEmail();
	} 
}

const bookingLogin = new BookingLogin();
window.addEventListener("DOMContentLoaded", bookingLogin.init);
    
    