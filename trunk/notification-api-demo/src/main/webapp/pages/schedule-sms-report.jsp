<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Schedule Sms Report</title>
</head>


<!-- Start Container -->
<div id="container">

    <div class="box">
        <div class="ui-state-hover-yel ui-corner-top box_title_new">
            <div><span></span>Schedule Sms Report</div>
        </div>
        <div class="ui-corner-bottom box_data" style="padding:5px 2px 5px 10px;">
            <display:table name="scheduleSmses" id="scheduleSms"  pagesize="10" cellspacing="0"
                           cellpadding="0" requestURI="" class="table" export="true">
                <display:column property="smsSchedule.jobName" title="Job Name" sortable="true"/>
                <display:column property="smsSchedule.scheduleDateTime" format="{0,date,yyyy-MM-dd HH:MM:SS}" title="Schedule Date" sortable="true"/>
                <display:column title="No. of recipients">
                    ${fn:length(scheduleSms.smsRecipients)}
                </display:column>
                <display:column title="Status">
                    <c:choose>
                        <c:when test="${scheduleSms.status}">
                            Sent
                        </c:when>
                        <c:otherwise>
                            Pending
                        </c:otherwise>
                    </c:choose>
                </display:column>
                <display:column property="smsSentTime" format="{0,date,yyyy-MM-dd HH:MM:SS}" title="Sent Date" sortable="true" />
            </display:table>
        </div>
    </div>

</div>

<!-- End Container -->

<div class="clear"></div>