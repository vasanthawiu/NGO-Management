function checkIsNull(value){
    return value === "" || value === undefined || value === null ? true : false;
}
function loginUser(){
    let data1  = localStorage.getItem("memberId");
    let requestBody = {
        "emailId" : $("#emailId").val(),
        "password" : $("#pwdId").val()
    }
    if(checkIsNull($("#emailId").val()) || checkIsNull($("#pwdId").val())){
        alert("Please fill Required Data");
    }else{
    $.ajax({
        type:'post',
       	contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/login",
        data:JSON.stringify(requestBody),
        success:function(response)
        {
            localStorage.setItem("memberId",response.memberId);
            window.location = "/home";
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
  }
}
function registerUser(){

    if(checkIsNull($("#memberNameId").val()) || checkIsNull($("#memberAddressId").val()) || checkIsNull($("#userEmailId").val()) 
        || checkIsNull($("#passwordId").val()) || checkIsNull($("#contactId").val())){
            alert("Please fill all the required data");
    }else{
        let requestBody = {
            "memberName"     : $("#memberNameId").val(),
            "memberAddress"  : $("#memberAddressId").val(),
            "emailId"  		 : $("#userEmailId").val(),
            "password"       : $("#passwordId").val(),
            "contactNum"     : $("#contactId").val()
        }
        $.ajax({
            type:'post',
            contentType: "application/json",
       		dataType: 'json',
        	cache: false,
            url:"http://localhost:8080/register",
            data:JSON.stringify(requestBody),
            success:function(response)
            {
                $('#regModelId').modal('hide');
                alert("Registerd sucessfully!!!");
            },error:function(error)
            {
                alert("Something went wrong");
            }
        });
    }
}
$(document).ready(function () {
$('#regModelId').on('hidden.bs.modal', function (e) {
    $("#memberNameId").val("");
    $("#memberAddressId").val("");
    $("#userEmailId").val("");
    $("#passwordId").val("");
    $("#contactId").val("");
  })
})