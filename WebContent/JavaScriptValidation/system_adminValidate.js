/**
 * getrole
 */
function get(a) {
	document.getElementById('role').value = a.value;
	document.getElementById('des').value = a.options[a.selectedIndex].text;
}
/**
 * edit employee
 */
function editEmpValidate() {
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

/**
 * empdataentry
 */
function empvalidate() {

	var mobile = document.getElementById('mobile').value;
	var pin = document.getElementById('pin1').value;
	var city = document.getElementById('city').value;
	if ((isNaN(mobile)) || mobile > 9999999999 || mobile < 1000000000) {
		alert("Mobile no. is incorrect");
		return false;
	} else if (isNaN(pin) || pin > 999999 || pin < 100000) {
		alert("Pin no. is not valid");
		return false;
	} else if (!(isNaN(city))) {
		alert("city is not valid");
		return false;
	}
	return true;
}
function cancel() {
	document.getElementById('name').value = "";
	document.getElementById('dob').value = "";
	document.getElementById('pin1').value = "";
	document.getElementById('mobile').value = "";
	document.getElementById('city').value = "";
	document.getElementById('mail').value = "";
	document.getElementById('plot').value = "";
	document.getElementById('doj').value = "";
}
/**
 * password
 */
function emppwdValidate() {

	var password = document.getElementById('password').value;
	var repassword = document.getElementById('repassword').value;

	if (password != repassword) {
		alert("Password does not match");
		return false;
	}

	return true;

}
