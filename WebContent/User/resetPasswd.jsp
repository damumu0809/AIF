<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>重置密码 | 农互金平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    <!--Loading bootstrap css-->
    <link type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,800italic,400,700,800">
  <!--    <link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700,300">-->
    <link type="text/css" rel="stylesheet" href="vendors/jquery-ui-1.10.3.custom/css/ui-lightness/jquery-ui-1.10.3.custom.css">
    <link type="text/css" rel="stylesheet" href="vendors/font-awesome/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css">
    <!--Loading style vendors-->
    <link type="text/css" rel="stylesheet" href="vendors/animate.css/animate.css">
    <link type="text/css" rel="stylesheet" href="vendors/iCheck/skins/all.css">
    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="css/themes/style1/pink-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="css/themes/style1/pink-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="css/style-responsive.css">
    <link rel="shortcut icon" href="images/favicon.ico">
</head>

<body id="signup-page">
    <div class="page-form">
    	<form id="resetPasswd-form" action="user_resetPasswd" method="post" namespace="/" theme="simple">
            <div class="header-content">
                <h1>重置密码</h1>   
                <h3><s:actionerror/></h3>
            </div>
            <div class="body-content">
                
                <div class="form-group">
                    <div class="input-icon right"><i class="fa fa-envelope"></i>
                        <input type="email" id="email" placeholder="邮箱" id="email" name="email" class="form-control" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon right"><i class="fa fa-key"></i>
                        <input id="password" type="password" placeholder="密码" id="passwd" name="passwd" class="form-control" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon right"><i class="fa fa-key"></i>
                        <input type="password" placeholder="确认密码" id="passwdCon" name="passwordConfirm" class="form-control" >
                    </div>
                </div>
                <hr>
                <div style="margin-bottom: 15px" class="row">
                    <div class="col-lg-8">
                        <label>
                            <input type="text" placeholder="验证码" name="code" class="form-control">
                        </label>
                    </div>
                    <div class="col-lg-4">
                    	<button id="sendCode" type="button" class="btn pull-right">获取验证码</button>
                        
                    </div>
                </div>
                
                <hr>
                <div class="form-group mbn"><a href="login.jsp" class="btn btn-warning"><i class="fa fa-chevron-circle-left"></i>&nbsp;
登陆</a>
                    <button  id="register" type="submit" class="btn btn-info pull-right">重置&nbsp;
                        <i class="fa fa-chevron-circle-right"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <!--loading bootstrap js-->
    <script src="vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendors/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"></script>
    <script src="vendors/jquery-validate/jquery.validate.min.js"></script>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <script src="js/resetPasswd.js"></script>
<!--      <script src="vendors/iCheck/icheck.min.js"></script>
    <script src="vendors/iCheck/custom.min.js"></script>-->
    <script>
        //BEGIN CHECKBOX & RADIO
       /* $('input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_minimal-grey',
            increaseArea: '20%' // optional
        });
        $('input[type="radio"]').iCheck({
            radioClass: 'iradio_minimal-grey',
            increaseArea: '20%' // optional
        });*/
        //END CHECKBOX & RADIO
    </script>
    <script type="text/javascript">
    /*(function(i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function() {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
    ga('create', 'UA-145464-12', 'auto');
    ga('send', 'pageview');*/
        
       
        //发送验证码
  	    $("#sendCode").click(function(){
  	    	var passwd = $("#passwd").val();
  	    	//var passwdCon = $("#passwdCon").val();
        	var email = $("#email").val();
        	if( email == "") {
        		alert("请先输入邮箱");
        	} else{
        		var data = {email:  email,  passwd: passwd};
        		$.post("/ssh/user_sendCodeReset",data,function(res){
        			alert(res);
        		});
        	}
        });
        
    </script>
</body>

</html>
