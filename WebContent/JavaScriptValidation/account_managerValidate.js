/**
 * validating tax
 */
function taxValidate() {
	var tax = document.getElementById('tax').value;
	var res = (isNaN(tax));
	if (res == true) {
		alert("tax is incorrect");
		return false;
	} else {
		document.forms["f1"].action = "TurnOverServlet";
	}
}