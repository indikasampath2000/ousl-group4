<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
    <title><decorator:title/> | Notification API Demo</title>
    <!-- External CSS and JS files -->

    <link href="<c:url value="/styles/style.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/scripts/jquery-1.3.2.min.js" />" type="text/javascript"></script>

    <decorator:head/>
</head>

<body>
<div id="top"></div>
<!-- Start main wrapper -->

<div id="wrapper">
    <%@ include file="/common/header.jsp" %>

    <div id="page_left">

        <div class="ui-state-default box_title_new">
            <div>
                	<span> 
                        Buy
                	</span>
            </div>
        </div>
        <div class="box_data" style="padding-top:5px;">
            <ul class="side_bar">
                <li><a href="#">Watch List</a></li>
                <li><a href="#">Bids</a></li>
                <li><a href="#">Offers</a></li>
                <li><a href="#">Won</a></li>
                <li><a href="#">Didn't win</a></li>
            </ul>
        </div>

        <div class="ui-state-default box_title_new">
            <div>
                	<span>
                		Administrator
                	</span>
            </div>
        </div>
        <div class="box_data" style="padding-top:5px;">
            <ul class="side_bar">
                <li><a href="<c:url value="/email-promotion.html"/>">Email Promotion</a></li>
                <li><a href="<c:url value="/sms-promotion.html"/>">SMS Promotion</a></li>
            </ul>
        </div>

    </div>
    <div id="page_right">
        <decorator:body/>
    </div>


    <div class="clear"></div>
    <%@ include file="/common/footer.jsp" %>
</div>
<!--   End main wrapper -->
</body>

</html>