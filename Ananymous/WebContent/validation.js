function validateLoginFields()
{
	var userName = document.getElementById("loginusername").value;
	var userPass = document.forms["logonform"]["loginuserpass"].value;
	if(userName.trim() == '' || userName == null)
		{
			alert("please enter user name");
		}
	if(userPass.trim() == '' || userPass == null)
		{
			alert("please enter your password");
		}
	return true;
}