/**
 * for course amount validation
 */
function courseAmountValidate() {

	var fees = document.getElementById('fees').value;

	if (isNaN(fees)) {
		alert("Fees entered is incorrect");
		return false;
	}
	return true;
}
/**
 * for expense amount validation
 */
function expenseAmountValidate() {

	var amount = document.getElementById('amount').value;

	if (isNaN(amount)) {
		alert("Expense Amount is incorrect");
		return false;
	}
	return true;
}

/**
 * for student data entry validation
 */
$(function() {
	$("#dob").datepicker();
});
function dobChange() {
	var dateTime = new Date($("#dob").datepicker("getDate"));
	var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
			+ "/" + dateTime.getFullYear();
	document.getElementById("dob").value = strDateTime;
}
$(function() {
	$("#doj").datepicker();
});
function dojChange() {
	var dateTime = new Date($("#doj").datepicker("getDate"));
	var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
			+ "/" + dateTime.getFullYear();
	document.getElementById("doj").value = strDateTime;
}

function studentDataValidate() {
	var element = document.getElementById("course");
	var selectedValue = element.options[element.selectedIndex].text;
	document.getElementById('finalcourse').value = selectedValue;

	var element1 = document.getElementById("state");
	var selectedValue1 = element1.options[element1.selectedIndex].text;
	document.getElementById('finalstate').value = selectedValue1;

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

function studentCancel() {
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
 * for student installment table
 */

function showTable(which) {

	if (which == "1") {
		document.getElementById('installtable').style.display = "none";
	}
	if (which == "2") {
		document.getElementById('installtable').style.display = "table";
	}
}