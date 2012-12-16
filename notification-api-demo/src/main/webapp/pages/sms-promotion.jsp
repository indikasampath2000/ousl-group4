<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>Promotion</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#datepicker" ).datepicker();
        });
    </script>
    <script>
        $(document).ready(function() {

            $('#user1').click(function(){
               $('#uploadUserList').css({
                   'display':'none'
               });
            });

            $('#user2').click(function(){
                $('#uploadUserList').css({
                    'display':''
                });
            });

            $("#scheduleMail").click(function(){
                var value = $("#scheduleMail").is(":checked");
                if(value){
                    $('#scheduleTimer').css({
                        'display':''
                    });
                } else {
                    $('#scheduleTimer').css({
                        'display':'none'
                    });
                }
            });

        });
    </script>

</head>

<!-- Start Container -->
<div id="container">

    <div class="box">
        <div class="ui-state-hover-yel ui-corner-top box_title_new">
            <div><span>Notification API Promotion Campaign</span></div>
        </div>
        <div class="ui-corner-bottom box_data" style="padding:5px 2px 5px 10px;">
            <form:form method="post" action="createSmsPromotion.html" modelAttribute="promotionCampaign" enctype="multipart/form-data">
                <form:hidden path="type" />
                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td class="log-text-style2" width="20%">Users</td>
                        <td class="log-text-style2">
                            <form:radiobutton path="user" value="R" label="Registered"/>
                            <form:radiobutton path="user" value="U" label="Unregistered"/><br/>
                            <span><form:errors path="user" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2" width="20%"></td>
                        <td class="log-text-style2">
                            <div id="uploadUserList" style="display: none;">
                                Excel file (.xls)
                                <input type="file" name="spreadsheet" size="30"/><br/>
                                <span><form:errors path="spreadsheet" cssClass="error"/></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2" width="20%">Schedule Mail</td>
                        <td class="log-text-style2">
                            <form:checkbox path="schedule" id="scheduleMail"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="log-text-style2" width="20%"></td>
                        <td class="log-text-style2">
                            <div id="scheduleTimer" style="display: none;">
                                job name <form:input path="jobName"/><br/>
                                date <form:input path="scheduleDate" id="datepicker"/>
                                hour <form:select path="hour" items="${hours}"/>
                                minute <form:select path="minute" items="${minutes}"/>
                                second <form:select path="second" items="${seconds}"/><br/>
                                <span><form:errors path="scheduleDate" cssClass="error"/></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:textarea path="message" cssClass="sms-message-box"/>
                            <span><form:errors path="message" cssClass="error" /></span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" colspan="2">
                            <input type="submit" value="create"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>

</div>

<!-- End Container -->