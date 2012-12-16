<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Sent Email Report</title>
</head>


<!-- Start Container -->
<div id="container">

    <div class="box">
        <div class="ui-state-hover-yel ui-corner-top box_title_new">
            <div><span></span>Sent Email Report</div>
        </div>
        <div class="ui-corner-bottom box_data" style="padding:5px 2px 5px 10px;">
            <display:table name="sentMails" id="sentMail" requestURI="sentEmailReport.html" >
                <display:column property="mailSubject" title="Subject" sortable="true" />
                <display:column property="mailSentTime" title="Sent Time" sortable="true" />
                <display:column title="No. of recipients">
                    ${fn:length(sentMail.mailRecipients)}
                </display:column>
                <display:column title="No. of attachments">
                    ${fn:length(sentMail.mailAttachments)}
                </display:column>
                <display:column title="No. of inline images">
                    ${fn:length(sentMail.mailInlineImages)}
                </display:column>
            </display:table>
        </div>
    </div>

</div>

<!-- End Container -->

<div class="clear"></div>