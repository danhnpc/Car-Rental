<%-- 
    Document   : searchCar
    Created on : Mar 16, 2021, 8:25:08 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/search.css" rel="stylesheet" >

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style.css">

        <title>Search Page</title>

    </head>
    <body>
        <c:set var="fullName" value="${sessionScope.FULLNAME}" />
        <c:if test="${not empty fullName}" >
            <h4 class="card-title">Welcome, <font color="red">
                ${fullName}
                </font></h4> <a class="card-link" href="logOut">Log out</a>
            </c:if>
            <c:if test="${empty fullName}" >
            <a class="card-link" href="login.jsp">Login</a>
        </c:if>

        <div class="search-text"> 
            <div class="container">
                <div class="row text-center">
                    <div class="form">
                        <form id="search-form" class="form-search form-horizontal" action="searchCar">
                            <input type="text" class="input-search" placeholder="Search" name="txtSearchValue" value="${param.txtSearchValue}">
                            <select class="input-category" name="txtCategory">
                                <option value="0">All</option>
                                <c:forEach var="category" items="${sessionScope.CATEGORY}" >
                                    <option value="${category.categoryID}" 
                                            <c:if test="${(category.categoryID) == (param.txtCategory)}" >
                                                selected=""
                                            </c:if>
                                            >${category.categoryName}</option>
                                </c:forEach>
                            </select><br/>
                            Date rental:
                            <input type="date" name="txtDateRental" value="${param.txtDateRental}">
                            Date return:
                            <input type="date" name="txtDateReturn" value="${param.txtDateReturn}">
                            Quantity:
                            <input type="text" name="txtQuantity" value="${param.txtQuantity}" />
                            <br/>

                            <c:if test="${not empty requestScope.RENTALDATEERROR}" >
                                <font color="black" >${requestScope.RENTALDATEERROR}</font><br/>
                            </c:if>
                            <c:if test="${not empty requestScope.RETURNDATEERROR}" >
                                <font color="black" >${requestScope.RETURNDATEERROR}</font><br/>
                            </c:if>    
                            <c:if test="${not empty requestScope.TOTALDATEERROR}" >
                                <font color="black" >${requestScope.TOTALDATEERROR}</font><br/>
                            </c:if> 
                            <button type="submit" class="btn-search" name="btAction">Search</button>
                        </form>
                    </div>
                </div>         
            </div>     
        </div>

        <c:set var="result" value="${requestScope.LISTCARPAGING}" />
        <c:set var="page" value="${sessionScope.PAGE}" />
        <div class="content">
            <c:if test="${not empty result}" >
                <form action="paging">
                    <div class="item">

                        <c:forEach var="dto" items="${result}" >
                            <div class="card">
                                <img class="card-img-top" src="img/${dto.image}" />
                                <div class="card-body">

                                    <h5 class="card-title">${dto.name}</h5>
                                    <p>Color: ${dto.color}</p>
                                    <p>${dto.price}$/day</p>
                                    <c:set var="total" value="${requestScope.RENTALDATE}" />
                                    <c:if test="${not empty total}" >
                                        <h3>-Total: ${total * (dto.price)}$</h3>
                                    </c:if>
                                    <input type="hidden" name="totalDay" value="${total}" />
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <div style="text-align: center;padding-top: 50px;">
                        <input type="hidden" name="txtCategory" value="${param.txtCategory}" />
                        <input type="hidden" name="txtDateRental" value="${param.txtDateRental}" />
                        <input type="hidden" name="txtDateReturn" value="${param.txtDateReturn}" />
                        <input type="hidden" name="txtQuantity" value="${param.txtQuantity}" />
                            <input class="btn btn-warning"
                                   <c:if test="${page == 0}" >
                                       disabled="true"
                                   </c:if>
                                       type="submit" value="Previous" name="btAction" style="margin-right: 250px; width: 10%"/>
                        
                        <input class="btn btn-warning" type="submit" value="Next" name="btAction" style="margin-left: 250px; width: 10%"/>
                    </div>
                </form>

            </c:if>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    </body>
</html>
