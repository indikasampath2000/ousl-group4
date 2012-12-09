<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Login</title>
</head>


<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-login">

        <div class="register-area">
            <table width="616" height="317" border="0" cellpadding="3" cellspacing="0">
                <tr>
                    <td width="1" height="28">&nbsp;</td>
                    <td colspan="2" class="log-text-style1">Are you ready to explore..the endless benefits to be
                        discovered?
                    </td>
                </tr>
                <tr>
                    <td height="39">&nbsp;</td>
                    <td colspan="2" class="reg-text-style2">Here is a chance for you to join the thousands of people who
                        are already a part of the CeyBid family. Don't forget, it costs you nothing... but CeyBid will
                        always be rewarding its member community...
                    </td>
                </tr>
                <tr>
                    <td height="35">&nbsp;</td>
                    <td colspan="2"><span class="log-text-style3">Please register as a CeyBid Family Member and enjoy privileges including</span>:
                    </td>
                </tr>
                <tr>
                    <td height="181">&nbsp;</td>
                    <td colspan="2" valign="top">
                        <table width="578" border="0" cellpadding="6" cellspacing="0" class="reg-text-style4">
                            <tr>
                                <td width="25" valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td width="553">List anything you want to sell within minutes in one of the most secured
                                    sites from all over the world
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td>Sell anything you want within minutes</td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td>Bid, buy and find bargains from all over the world</td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td>Get rewarded in millions of Rupees through Credibility Rewards Scheme</td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td>The best of all... once you are registered as our family member... you don't always
                                    need access to a computer to benefit!!
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <div align="center"><img src="images/bul221.png" width="13" height="16"/></div>
                                </td>
                                <td>Connect with our CeyBid global community in seconds and more!</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="21">&nbsp;</td>
                    <td width="583">
                        <div align="right"><img src="images/signupbtn.png" width="90" height="28"/></div>
                    </td>
                    <td width="14">&nbsp;</td>
                </tr>
            </table>
        </div>

        <div class="login-area">
            <form action="j_spring_security_check" method="post">
                <table width="309" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td width="1" height="31">&nbsp;</td>
                        <td colspan="3" class="log-text-style1">Sign In to your world of fun and excitement</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td colspan="3" class="reg-text-style2">Back for more fun? Sign in now to buy, bid and sell, or
                            <br/>
                            to manage your account.
                        </td>
                    </tr>
                    <c:if test="${not empty param.error}">
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3" class="reg-text-style2">
                                <font color="red">
                                    Your login attempt was not successful, try again.<br/><br/>
                                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                </font>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td colspan="3" rowspan="2" valign="top">
                            <table width="267" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="81" height="31" class="log-text-style2">
                                        <label for="j_username">Username</label>
                                    </td>
                                    <td width="186">
                                        <input type="text" name="j_username" id="j_username"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="32" class="log-text-style2">
                                        <label for="j_password">Password</label>
                                    </td>
                                    <td>
                                        <input type="password" name="j_password" id="j_password"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td colspan="3">
                            <table width="296" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="28" valign="top">
                                        <input type='checkbox' name='_spring_security_remember_me'/>
                                    </td>
                                    <td width="268" class="reg-text-style2">
                                        <strong>Keep me signed in.</strong><br/>
                                        Don't check this box if you're at a public or shared computer
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td width="187"><a href="#" class="log-text-style3">Forgot my login details?</a></td>
                        <td width="95">
                            <div align="right">
                                <input type="image" src="images/signin.png" width="90" height="28"/>
                            </div>
                        </td>
                        <td width="2">&nbsp;</td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
    <!-- End content area 1 -->

    <div class="clear"></div>

</div>

<!-- End Container -->

<div class="clear"></div>