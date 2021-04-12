<%-- 
    Document   : createAccount
    Created on : Mar 20, 2021, 11:32:59 PM
    Author     : visug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src='https://www.google.com/recaptcha/api.js'></script>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            .login-block{
                background: #DE6262;  /* fallback for old browsers */
                background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);  /* Chrome 10-25, Safari 5.1-6 */
                background: linear-gradient(to bottom, #FFB88C, #DE6262); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                float:left;
                width:100%;
                padding : 50px 0;
            }
            .banner-sec{background:url(img/camry.jpg)  no-repeat left bottom; background-size:cover; min-height:500px; border-radius: 0 10px 10px 0; padding:0;}
            .container{background:#fff; border-radius: 10px; box-shadow:15px 20px 0px rgba(0,0,0,0.1);}
            .carousel-inner{border-radius:0 10px 10px 0;}
            .carousel-caption{text-align:left; left:5%;}
            .login-sec{padding: 50px 30px; position:relative;}
            .login-sec .copy-text{position:absolute; width:80%; bottom:20px; font-size:13px; text-align:center;}
            .login-sec .copy-text i{color:#FEB58A;}
            .login-sec .copy-text a{color:#E36262;}
            .login-sec h2{margin-bottom:30px; font-weight:800; font-size:30px; color: #DE6262;}
            .login-sec h2:after{content:" "; width:100px; height:5px; background:#FEB58A; display:block; margin-top:20px; border-radius:3px; margin-left:auto;margin-right:auto}
            .btn-login{background: #DE6262; color:#fff; font-weight:600;}
            .banner-text{width:70%; position:absolute; bottom:40px; padding-left:20px;}
            .banner-text h2{color:#fff; font-weight:600;}
            .banner-text h2:after{content:" "; width:100px; height:5px; background:#FFF; display:block; margin-top:20px; border-radius:3px;}
            .banner-text p{color:#fff;}
        </style>
        <title>Create account</title>
    </head>
    <body>
        <section class="login-block">

            <div class="container">
                <div class="row">
                    <div class="col-md-8 banner-sec">   
                        <div class="banner-text">
                            <h2>Toyota Camry 2019</h2>
                            <p>Performance ranging from impressive to insane, curvaceous styling, high-tech cabin.</p>
                        </div>
                    </div>
                    <div class="col-md-4 login-sec">
                        <h2 class="text-center">Register Now</h2>
                        <c:set var="error" value="${requestScope.CREATEERROR}" />
                        <form class="login-form" action="createAccount">
                            <div class="form-group">
                                <label for="exampleInputEmail1" class="text-uppercase">Email 
                                    <c:if test="${not empty error.emailErr}" >
                                        <font color="red" >${error.emailErr}</font>
                                    </c:if> </label>
                                <input type="text" class="form-control" placeholder="" name="txtEmail" value="${param.txtEmail}">
                                <label for="exampleInputPassword1" class="text-uppercase">Password 
                                    <c:if test="${not empty error.passwordErr}" >
                                        <font color="red" >${error.passwordErr}</font>
                                    </c:if>
                                </label>
                                <input type="password" class="form-control" placeholder="" name="txtPassword" value="${param.txtPassword}">
                                <label for="exampleInputPassword1" class="text-uppercase">Confirm 
                                    <c:if test="${not empty error.confirmErr}" >
                                        <font color="red" >${error.confirmErr}</font>
                                    </c:if>
                                </label>
                                <input type="password" class="form-control" placeholder="" name="txtConfirm" value="${param.txtConfirm}">
                                <label for="exampleInputPassword1" class="text-uppercase">Phone 
                                    <c:if test="${not empty error.phoneErr}" >
                                        <font color="red" >${error.phoneErr}</font>
                                    </c:if>
                                </label>
                                <input type="text" class="form-control" placeholder="" name="txtPhone" value="${param.txtPhone}">
                                <label for="exampleInputPassword1" class="text-uppercase">Full name 
                                    <c:if test="${not empty error.nameErr}" >
                                        <font color="red" >${error.nameErr}</font>
                                    </c:if>
                                </label>
                                <input type="text" class="form-control" placeholder="" name="txtFullName" value="${param.txtFullName}">
                                <label for="exampleInputPassword1" class="text-uppercase">Address 
                                    <c:if test="${not empty error.addressErr}" >
                                        <font color="red" >${error.addressErr}</font>
                                    </c:if>
                                </label>
                                <input type="text" class="form-control" placeholder="" name="txtAddress" value="${param.txtAddress}">
                            </div>
                            <a class="card-link" href="login.jsp">Login</a>
                            <button type="submit" class="btn btn-login float-right" value="Register">Register</button>

                        </form>
                        <c:if test="${not empty error.emailDuplicateErr}" >
                            <font color="red" >${error.emailDuplicateErr}</font>
                        </c:if> 
                    </div>

                </div>
            </div>
        </section>

    </body>
</html>
