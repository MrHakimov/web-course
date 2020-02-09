<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
            </thead>
            <tbody>
<#--            <template>-->
<#--                <tr>-->
<#--                    <td class="user_id"></td>-->
<#--                    <td class="user_login"></td>-->
<#--                </tr>-->
<#--            </template>-->
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login" colspan="2"></td>
                    <#if user?? && user.admin>
                        <td class="user_admin"><span class="isAdmin"></span><input type="button" name="${user.id}" class="admin" value=""/></td>
                    <#else>
                        <td class="user_admin" style="display: none"></td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

<#--    <#assign x=1>-->
<#--    <input id="freemarkervar" type="hidden" value="${c.user??.admin}"/>-->

    <script>
        // var isAdmin = $("#freemarkervar").val();
        // alert(isAdmin);
        $(function () {
            var $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            $.ajax({
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function(response) {
                    response["users"].forEach(function(user) {
                        $noData.hide();
                        var $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        $tr.find(".user_admin .isAdmin").text(user["admin"] ? "true " : "false ");
                        $tr.find(".user_admin .admin").val(user["admin"] ? "Disable" : "Enable");
                        $tr.find(".user_admin .admin").attr("name", user["id"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });

                    $(".user_admin .admin").click(function () {
                        var admin = $(this).val() === "Enable";
                        var userId = $(this).attr("name");
                        var $obj = $(this);

                        $.ajax({
                            type: "POST",
                            url: "",
                            dataType: "json",
                            data: {
                                action: "changeAdminStatus",
                                admin: admin,
                                userId: userId
                            },
                            success: function (response) {
                                if (response["actualAdminStatus"]) {
                                    $obj.val("Disable");
                                } else {
                                    $obj.val("Enable");
                                }
                            }
                        });

                    });

                    // $(".users tr").click(function() {
                    //     $.ajax({
                    //         dataType: "json",
                    //         data: {
                    //             action: "findUser",
                    //             userId: $(this).attr("data-userId")
                    //         },
                    //         success: function (response) {
                    //             notify(response["user"].creationTime);
                    //         },
                    //         type: "POST"
                    //     })
                    // });
                },
                type: "POST"
            });
        })
    </script>
</@c.page>
