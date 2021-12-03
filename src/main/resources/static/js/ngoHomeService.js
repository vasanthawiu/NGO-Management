function checkIsNull(value){
    return value === "" || value === undefined || value === null ? true : false;
}
function onPageload(){
    $(".donationCls").show();
    $(".participateCls").hide();
    $(".referCls").hide();
    getDonationData();
}
function getDonationData(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/donation",
        success:function(response)
        {
            let donationData = response;
            
            $("#donationBodyId").empty();
            donationData.forEach((element,index) => {
                let srlno = index+1;
                $("#donationBodyId").append(
                    '<tr>'+
                    '<th>'+srlno+'</th>'+
                    '<td>'+element.donationId+'</td>'+
                    '<td>'+element.amount+'</td>'+
                    '<td>'+element.purpose+'</td>'+
                    '<td>'+element.donationDate+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function submitDonationdata(){
    if(checkIsNull($("#donationAmmountId").val()) || checkIsNull($("#donationDateId").val()) 
    || checkIsNull($("#donationPurposeId").val())){
        alert("Please fill all the required data");
}else{
    let requestBody = {
        "amount"    		: $("#donationAmmountId").val(),
        "donationDate"      : $("#donationDateId").val(),
        "purpose"   		: $("#donationPurposeId").val(),
        "memberId"          : localStorage.getItem("memberId"),
    }
    $.ajax({
        type:'post',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/donation",
        data:JSON.stringify(requestBody),
        success:function(response)
        {
            $('#donationModelId').modal('hide');
            alert("Data added sucessfully!!!");
            getDonationData();
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
}
function getParticipatedata(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/participate",
        success:function(response)
        {
            let participateData = response;
            
            $("#participateBodyId").empty();
            participateData.forEach((element,index) => {
                let srlno = index+1;
                $("#participateBodyId").append(
                    '<tr>'+
                    '<th>'+srlno+'</th>'+
                    '<td>'+element.participateId+'</td>'+
                    '<td>'+element.campaignName+'</td>'+
                    '<td>'+element.participateDate+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function submitParticipateData(){
    if(checkIsNull($("#campaignNameId").val()) || checkIsNull($("#campaignDateId").val())){
        alert("Please fill all the required data");
}else{
    let requestBody = {
        "campaignName"    : $("#campaignNameId").val(),
        "participateDate" : $("#campaignDateId").val(),
        "memberId"        : localStorage.getItem("memberId"),
    }
    $.ajax({
        type:'post',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/participate",
        data:JSON.stringify(requestBody),
        success:function(response)
        {
            $('#participatelId').modal('hide');
            alert("Data added sucessfully!!!");
            getParticipatedata();
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
} 
}
function getReferData(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/refer",
        success:function(response)
        {
            let referData = response;
            
            $("#referBodyId").empty();
            referData.forEach((element,index) => {
                let srlno = index+1;
                $("#referBodyId").append(
                    '<tr>'+
                    '<th>'+srlno+'</th>'+
                    '<td>'+element.referId+'</td>'+
                    '<td>'+element.memberName+'</td>'+
                    '<td>'+element.status+'</td>'+
                    '<td>'+element.memberEmailId+'</td>'+
                    '<td>'+element.contact+'</td>'+
                    '<td>'+element.comment+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function submitReferData(){
    if(checkIsNull($("#memberNameId").val()) || checkIsNull($("#statusId").val()) 
        || checkIsNull($("#userEmailId").val()) || checkIsNull($("#commentId").val()) || checkIsNull($("#contactId").val())){
            alert("Please fill all the required data");
    }else{
    let requestBody = {
        "memberName"      : $("#memberNameId").val(),
        "status"          : $("#statusId").val(),
        "memberEmailId"   : $("#userEmailId").val(),
        "comment"         : $("#commentId").val(),
        "contact"         : $("#contactId").val(),
        "memberId"        : localStorage.getItem("memberId"),
    }
    $.ajax({
        type:'post',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8080/refer",
        data:JSON.stringify(requestBody),
        success:function(response)
        {
            $('#referModallId').modal('hide');
            alert("Data added sucessfully!!!");
            getReferData();
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
}
function logout(){
    window.location = "/login";
}
$(document).ready(function () {
    $(".menu-active-cls").on("click", function(){
        $(".menu-active-cls").removeClass("active");
        $(this).addClass("active");
        let type = $(this).attr("menuType");
        $(".donationCls").hide();
        $(".participateCls").hide();
        $(".referCls").hide();
        if(type == "DONATION"){
            $(".donationCls").show();
            getDonationData();
        }else if(type == "PARTICIPATE"){
            $(".participateCls").show();
            getParticipatedata();
        }else if(type == "REFER"){
            $(".referCls").show();
            getReferData();
        }
    })
    $('#donationModelId').on('hidden.bs.modal', function (e) {
        $("#donationAmmountId").val("");
        $("#donationDateId").val("");
        $("#donationPurposeId").val("");
      })
      $("#participatelId").on('hidden.bs.modal',function(e){
        $("#campaignNameId").val("");
        $("#campaignDateId").val("");
      })
      $("#referModallId").on('hidden.bs.modal',function(e){
        $("#memberNameId").val("");
        $("#statusId").val("");
        $("#userEmailId").val("");
        $("#commentId").val("");
        $("#contactId").val("");
      })
})