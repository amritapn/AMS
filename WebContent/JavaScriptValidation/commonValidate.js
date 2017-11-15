/**
 * for change password
 */
function compare() {
	var a = document.getElementById("newPass").value;
	var b = document.getElementById("rePass").value;
	if (a != b)
		document.getElementById("rePass").value = null;
}
function back() {
	document.getElementById("rePass").value = null;
	document.getElementById("newPass").value = null;
	document.getElementById("oldPass").value = null;
}
/**
 * for forgot and change password
 */
function forgotChange() {
	var a = document.getElementById("newPass").value;
	var b = document.getElementById("rePass").value;
	if (a != b)
		document.getElementById("rePass").value = null;
}
/**
 * forgot password
 */
function phonevalid() {
	var a = document.getElementById("phone").value;
	var b = parseInt(a);
	var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	if (!a.match(phoneno)) {
		document.getElementById("phone").value = null;
	}

	if (b > 10000000000)
		document.getElementById("phone").value = null;

}
/**
 * getdays
 */
function getDays(a) {
	document.getElementById('number').value = a.value;
	document.getElementById('leave').value = a.options[a.selectedIndex].text;
}
