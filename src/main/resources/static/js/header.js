var registerValid=[false,false,false,false];
var loginValid=false;
$(function(){
    emailValidSetting();
    passwordValidSetting();
    userNameValidSetting();
    $("#register").on("click",function(){
        createNewUser();
    })
    setSwitchBlock();
    setLoginBlock();
})
function emailValidSetting(){
    var email=$("input[name='mail']");
    email.on("blur",function(){
        var hint=$(".error-hint").eq(0);
        var pattern=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        var result=pattern.test(email.val());
        if(result){
            sendData2Server("/checkMail",{mail:email.val()},function(json){
                if(json.code=='SUCCESS'){
                    hint.css("display","none");
                    registerValid[0]=true;
                }else{
                    hint.css("display","block");
                    registerValid[0]=false;
                    hint.html(json.text);
                }
            });
        }else{
            hint.css("display","block");
            registerValid[0]=false;
            hint.html("请输入合法的邮箱");
        }
    });
}
function passwordValidSetting(){
    var password=$("input[name='password']");
    var rePassword=$("input[name='re-password']");
    password.on("blur",function(){
        var length=$.trim(password.val());
        var errorHint=$(".error-hint").eq(1);
        if(length<8||length>16){
            errorHint.css("display","block");
            registerValid[1]=false;
        }else{
            errorHint.css("display","none");
            registerValid[1]=true;
        }
    });
    rePassword.on("blur",function(){
        var errorHint=$(".error-hint").eq(2);
        if(registerValid[1]&&(password.val()==rePassword.val())){
            errorHint.css("display","none");
            registerValid[2]=true;
        }else{
            errorHint.css("display","block");
            registerValid[2]=false;
        }
    })
}
function userNameValidSetting(){
    var userName=$("input[name='username']");
    userName.on("blur",function(){
        var value=$.trim(userName.val());
        var errorHint=$(".error-hint").eq(3);
        if(value.length<4||value.length>16){
            registerValid[3]=false;
            errorHint.css("display","block");
        }else{
            registerValid[3]=true;
            errorHint.css("display","none");
        }
    })
}
function createNewUser(){
    var ok=true;
    $.each(registerValid,function(idx){
        if(!registerValid[idx]){
            ok=false;
            $(".error-hint").eq(idx).css("display","block");
        }else{
            $(".error-hint").eq(idx).css("display","none");
        }
    });
    if(ok){
        var data={userName:$("input[name='username']").val(),password:$("input[name='password']").val(),
                  mail:$("input[name='mail']").val()};
        sendData2Server("/createNewUser",data,function(json){
            if(json.code=='SUCCESS'){
                alert("注册成功,请进入邮箱接收");
                window.location.reload();
            }else{
                var hint=$(".error-hint").eq(0);
                hint.css("display","block");
                registerValid[0]=false;
                hint.html(json.text);
            }
        });
    }
}
function setSwitchBlock(){
    $("#switch-register").on("click",function(){
        $("#switch-register-block").css('display','none');
        $("#switch-login-block").css("display",'block');
        $(".signUp-block").css("display","block");
        $(".login-block").css("display","none");
    });
    $("#switch-login").on("click",function(){
        $("#switch-login-block").css("display",'none');
        $("#switch-register-block").css('display','block');
        $(".login-block").css("display","block");
        $(".signUp-block").css("display","none");
    });
}
function setLoginBlock(){
    var username=$("input[name='login-name']");
    username.on("blur",function(){
        var hint=$(".login-error-hint").eq(0);
        var pattern=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
        var result=pattern.test(username.val());
        if(result){
            sendData2Server("/checkMail",{mail:username.val()},function(json){
                if(json.code=='MAIL_EXIST'){
                    hint.css("display","none");
                    loginValid=true;
                }else{
                    hint.css("display","block");
                    loginValid=false;
                    hint.html("账户不存在");
                }
            });
        }else{
            hint.css("display","block");
            loginValid=false;
            hint.html("请输入合法的邮箱");
        }
    });
    $("#login-btn").on("click",function(){
        if(loginValid){
            var data={mail:username.val(),password:$("input[name='login-password']").val(),
                      remember:$("input[name='login-remember']")[0].checked};
            sendData2Server("/login",data,function(json){
                if(json.code=="PASSWORD_ERROR"){
                    var hint=$(".login-error-hint").eq(1);
                    hint.css("display","block");
                }else{
                    alert("登录成功");
                    window.location.reload();
                }
            })
        }
    });
}
function sendData2Server(url,data,done){
    $.ajax({
        url:url,
        data:JSON.stringify(data),
        type:"post",
        headers:{'Content-type':"application/json;charset=utf-8"},
        dataType:"JSON"
    }).done(done);
}