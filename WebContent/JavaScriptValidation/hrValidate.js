/**
 * Edid Employee validation
 */
function editEmployeeValidate() {
	var mobile = document.getElementById('mobile').value;
	var city = document.getElementById('city').value;
	var pin = document.getElementById('pin1').value;
	var salary = document.getElementById('salary').value;
	var ta = document.getElementById('ta').value;
	var da = document.getElementById('da').value;
	var hra = document.getElementById('hra').value;
	var epf = document.getElementById('epf').value;
	if (isNaN(mobile) || (mobile > 9999999999) || (mobile < 1000000000)) {
		alert("Mobile is incorrect");
		return false;
	} else if (!(isNaN(city))) {
		alert("City is invalid");
		return false;
	} else if (isNaN(pin) || (pin > 999999) || (pin < 100000)) {
		alert("Pin is not valid");
		return false;
	} else if (isNaN(salary)) {
		alert("Salary is not valid");
		return false;
	} else if (isNaN(ta)) {
		alert("TA is not valid");
		return false;
	} else if (isNaN(da)) {
		alert("DA is not valid");
		return false;
	} else if (isNaN(hra)) {
		alert("HRA is not valid");
		return false;
	} else if (isNaN(epf)) {
		alert("EPF is incorrect");
		return false;
	}
}
