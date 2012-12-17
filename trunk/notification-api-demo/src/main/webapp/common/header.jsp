<%@ include file="/common/taglibs.jsp" %>
<!-- Start header -->

<div id="header">

    <a href="<c:url value="/welcome.html"/>">
        <div class="logo"></div>
    </a>

    <div class="top-banner"><img src="images/banner1.png"/></div>

    <div id="top-nav">
        <h2>Search what you looking for...</h2>

        <div class="top-menu">
            <ul>
                <li>
<security:authorize ifAllGranted="ROLE_ANONYMOUS">
    <a href="<c:url value="/welcome.html"/>">Home</a>
</security:authorize>
<security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN, ROLE_BUYER, ROLE_SELLER">
    <a href="<c:url value="/home.html"/>">Home</a>
</security:authorize>

                </li>
                <li><img src="images/sepa.png"/></li>
                <li><a href="#">About us</a></li>
                <li><img src="images/sepa.png"/></li>
                <li><a href="#">Help</a></li>
                <li><img src="images/sepa.png"/></li>
                <li><a href="#">Contact us</a></li>
            </ul>
        </div>

        <div class="clear"></div>

        <div class="search-box">
            <form action="" method="post">
                <input type="text" class="search-input"/>
                <select class="search-select-input">
                </select>
                <input name="imageField" type="image" class="search-btns" id="imageField" src="images/search-btn.png"/>
            </form>

            <img src="images/rss.png" class="social-icons"/>
            <img src="images/twt.png" class="social-icons"/>
            <img src="images/fb.png" class="social-icons"/>
        </div>
    </div>

    <security:authorize ifAllGranted="ROLE_ANONYMOUS">
        <div id="top-user-nav">
            <span><a href="<c:url value='/login.jsp'/>">Welcome Signin Here</a></span>
            <span><a href="<c:url value='/signup.html'/>">Register is Free and Easy</a></span>
        </div>
    </security:authorize>

    <security:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN, ROLE_BUYER, ROLE_SELLER">
        <div id="top-user-nav-log" style="z-index: 0;">
            <span>Hi, <c:out value="${userObj.username}"/> (<a href="<c:url value='/logout.jsp'/>">logout</a>)</span>
        </div>
    </security:authorize>

</div>

<!-- End header -->