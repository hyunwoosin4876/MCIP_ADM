	function validateEncryptedForm() {
	    var username = document.getElementById("i_UserID_O").value;
	    var password = document.getElementById("i_UserPW_O").value;
	    if (!username || !password) {
	        alert("계정정보가 일치하지 않습니다.");
	        return false;
	    }
	    try {
	        var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
	        var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
	        submitEncryptedForm(username,password, rsaPublicKeyModulus, rsaPublicKeyExponent);
	   	} catch(err) {
	        //alert(err);
	   		console.log(err);
	   	}
	    return false;
	}

	function submitEncryptedForm(username, password, rsaPublicKeyModulus, rsaPpublicKeyExponent) {
	    var rsa = new RSAKey();
	    rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);
	    // 사용자ID와 비밀번호를 RSA로 암호화한다.
	    var securedUsername = rsa.encrypt(username);
	    var securedPassword = rsa.encrypt(password);
	    // POST 로그인 폼에 값을 설정하고 발행(submit) 한다.
	    var securedLoginForm = document.securedLoginForm;
	    securedLoginForm.i_UserID.value = securedUsername;
	    securedLoginForm.i_UserPW.value = securedPassword;
	    /*alert(securedUsername + " // " + securedPassword);*/
	    
	    if(document.getElementById("enterKey").value=="YES"){
	    	securedLoginForm.submit();
		}
	}