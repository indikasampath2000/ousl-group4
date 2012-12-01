<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
    <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
    <!-- External CSS and JS files -->

    <link href="<c:url value="styles/style.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="scripts/jquery-1.3.2.min.js" />" type="text/javascript"></script>

    <decorator:head/>
</head>

<body>
<div id="top"></div>
<!-- Start main wrapper -->
<div id="wrapper">
    <%@ include file="/common/header.jsp" %>
    <decorator:body/>
    <%@ include file="/common/footer.jsp" %>
</div>
<!--   End main wrapper -->
</body>

</html>