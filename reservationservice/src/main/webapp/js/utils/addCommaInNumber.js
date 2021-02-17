
function AddCommaInNumber() {
	
}
AddCommaInNumber.prototype = {
	convertNumber: function (price) {
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
        commaPrice = commaPrice.split("").reverse().join("");
        return commaPrice;
	}
}

export default AddCommaInNumber;