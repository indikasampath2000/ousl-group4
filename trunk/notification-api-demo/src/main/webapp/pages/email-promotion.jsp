<%@ include file="/common/taglibs.jsp" %>

<head>
    <title>Promotion</title>

    <script type="text/javascript" src="<c:url value="/scripts/tiny_mce/tiny_mce.js"/>"></script>
    <script type="text/javascript">

            tinyMCE.init({
                // General options
                mode : "textareas",
                theme : "advanced",
                plugins : "safari,spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

                // Theme options
                theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,formatselect,fontselect,fontsizeselect,|,outdent,indent,|,undo,redo,|,forecolor,backcolor",
                theme_advanced_buttons2 : "",
                theme_advanced_buttons3 : "",
                theme_advanced_toolbar_location : "top",
                theme_advanced_toolbar_align : "left",
                theme_advanced_resizing : false,
                tab_focus : ':prev,:next',
                height:'350px',
                width:'708px'
            });

    </script>
    <script>
        $(document).ready(function() {
            //add more file components if Add is clicked
            $('#addFile').click(function() {
                var fileIndex = $('#fileTable tr').children().length;
                $('#fileTable').append(
                        '<tr><td>'+
                        '<input type="file" name="files['+ fileIndex +']" size="40" />'+
                        '</td></tr>');
            });

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
            <form:form method="post" action="" modelAttribute="promotionCampaign">
                <table width="100%" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td class="log-text-style2" width="20%">Users</td>
                        <td class="log-text-style2">
                            <form:radiobutton path="user" value="R"/>Registered
                            <form:radiobutton path="user" value="U"/>Unregistered
                        </td>
                    </tr>
                    <tr>
                        <td width="20%"></td>
                        <td>
                            <div id="uploadUserList" style="display: none;">
                                <input type="file" path="spreadsheet" size="40" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <form:textarea path="message"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input id="addFile" type="button" value="Add Attachment(s)" />
                            <table id="fileTable">

                            </table>
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