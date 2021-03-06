<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Sign-up</title>
</head>


<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-signup">
        <form:form method="post" modelAttribute="user" action="signupComplete.html">
            <div class="account-info-area">
                <table width="460" border="0" cellpadding="3" cellspacing="0">
                    <tr>
                        <td colspan="2" class="reg-text-style1"><fmt:message key="signup.accountinfo"/></td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.username"/></td>
                        <td>
                            <form:input path="username" size="25"/><br>
                            <span><form:errors path="username" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.password"/></td>
                        <td>
                            <form:password path="password" size="25"/><br>
                            <span><form:errors path="password" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.confirmedpassword"/></td>
                        <td>
                            <form:password path="confirmedPassword" size="25"/><br>
                            <span><form:errors path="confirmedPassword" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.email"/></td>
                        <td>
                            <form:input path="email" size="25"/><br>
                            <span><form:errors path="email" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="reg-text-style1"><fmt:message key="signup.recover"/></td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.secretquest"/></td>
                        <td>
                            <form:select path="secretQuestion">
                                <form:option value="" label="Select secret question"/>
                                <form:options items="${secretQuestions}" />
                            </form:select><br>
                            <span><form:errors path="secretQuestion" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.secretansw"/></td>
                        <td>
                            <form:input path="secretAnswer" size="25"/><br>
                            <span><form:errors path="secretAnswer" cssClass="error" /></span>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="personal-info-area">
                <table width="460" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td height="28" colspan="2" class="reg-text-style1"><fmt:message
                                key="signup.personalinfo"/></td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.firstname"/></td>
                        <td>
                            <form:input path="firstName" size="25"/><br>
                            <span><form:errors path="firstName" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.lastname"/></td>
                        <td>
                            <form:input path="lastName" size="25"/><br>
                            <span><form:errors path="lastName" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.gender"/></td>
                        <td class="log-text-style2">
                            <form:radiobutton path="gender" value="M" label="Male"/>
                            <form:radiobutton path="gender" value="F" label="Female"/><br>
                            <span><form:errors path="gender" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.dob"/></td>
                        <td class="log-text-style2">
                            <form:input path="dob" size="12"/> <c:out value="Ex: (yyyy-MM-dd)"/><br>
                            <span><form:errors path="dob" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.addressline1"/></td>
                        <td>
                            <form:input path="addressLine1" size="25"/><br>
                            <span><form:errors path="addressLine1" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.addressline2"/></td>
                        <td>
                            <form:input path="addressLine2" size="25"/><br>
                            <span><form:errors path="addressLine2" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.addressline3"/></td>
                        <td>
                            <form:select path="addressLine3" items="${countries}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.mobileno"/></td>
                        <td>
                            <form:input path="mobileNumber" size="25"/><br>
                            <span><form:errors path="mobileNumber" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2"><fmt:message key="signup.landno"/></td>
                        <td>
                            <form:input path="landNumber" size="25"/><br>
                            <span><form:errors path="landNumber" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right"><input style="margin-right: 50px;" type="image"
                                                 src="<c:url value='images/signupbtn.png'/>" alt="Signup"/></td>
                    </tr>
                </table>
            </div>
        </form:form>
    </div>
    <!-- End content area 1 -->

    <div class="clear"></div>

</div>

<!-- End Container -->

<div class="clear"></div>