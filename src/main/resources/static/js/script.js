//로그인 체크 시작
var loginCheck= document.querySelector(".login-sessionCheck").value;
var loginCheckSuccess= document.querySelector(".login-sessionCheck-exist");
var loginCheckFail=document.querySelector(".login-sessionCheck-notExist");

console.log(loginCheck);
if(loginCheck===("")){
  loginCheckSuccess.style.display="none"
}else{
  loginCheckFail.style.display="none"
}

//로그인 체크 끝