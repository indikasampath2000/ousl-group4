<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Sent Email Report</title>
</head>


<!-- Start Container -->
<div id="container">

    <div class="box">
        <div class="ui-state-hover-yel ui-corner-top box_title_new">
            <div><span></span>Sent Sms Report</div>
        </div>
        <div class="ui-corner-bottom box_data" style="padding:5px 2px 5px 10px;">
            <display:table name="sentSmses" id="sentMail"  pagesize="10" cellspacing="0"
                           cellpadding="0" requestURI="" class="table" export="true">
                <display:column property="smsSentTime" format="{0,date,yyyy-MM-dd}" title="Sent Date" sortable="true" />
                <display:column title="No. of recipients">
                    ${fn:length(sentMail.smsRecipients)}
                </display:column>
            </display:table>
        </div>
    </div>

</div>

<!-- End Container -->

<div class="clear"></div>