<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Congratulation !</title>
</head>


<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-signup">
        <div style="text-align: center; margin-top: 100px;">
            <div class="reg-text-style3" >
                Congratulation !
            </div>
            <br/><br/>
            <div class="log-text-style2">
                Your the max bidder. <a href="<c:url value="biditem.html?lt="/>${listingId}">Back to Item</a>
            </div>
        </div>
    </div>
    <!-- End content area 1 -->

    <div class="clear"></div>

</div>

<!-- End Container -->

<div class="clear"></div>