<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Schedule Email Report</title>
</head>


<!-- Start Container -->
<div id="container">

    <div class="box">
        <div class="ui-state-hover-yel ui-corner-top box_title_new">
            <div><span></span>Schedule Email Report</div>
        </div>
        <div class="ui-corner-bottom box_data" style="padding:5px 2px 5px 10px;">
            <display:table name="scheduleMails" id="scheduleMail"  pagesize="10" cellspacing="0"
                           cellpadding="0" requestURI="" class="table" export="true">
                <display:column property="mailSubject" title="Subject" sortable="true" />
                <display:column property="mailSchedule.jobName" title="Job Name" sortable="true"/>
                <display:column property="mailSchedule.scheduleDateTime" format="{0,date,yyyy-MM-dd HH:MM:SS}" title="Schedule Date" sortable="true"/>
                <display:column title="No. of recipients">
                    ${fn:length(scheduleMail.mailRecipients)}
                </display:column>
                <display:column title="Status">
                    <c:choose>
                        <c:when test="${scheduleMail.status}">
                            Sent
                        </c:when>
                        <c:otherwise>
                            Pending
                        </c:otherwise>
                    </c:choose>
                </display:column>
                <display:column property="mailSentTime" format="{0,date,yyyy-MM-dd HH:MM:SS}" title="Sent Date" sortable="true" />
            </display:table>
        </div>
    </div>

</div>

<!-- End Container -->

<div class="clear"></div>