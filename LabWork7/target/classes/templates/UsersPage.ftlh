<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#-- @ftlvariable name="changeStatusForm" type="ru.itmo.wp.form.StatusCredentials" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Status</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as currentUser>
                <tr>
                    <td class="id">${currentUser.id}</td>
                    <td class="login"><a href="/user/${currentUser.id}">${currentUser.login}</a></td>
                    <td class="creationTime">${currentUser.creationTime}</td>
                    <#if user??>
                        <td class="disabled">
                            <form method="post" action="">
                                <input type="hidden" name="action" value="changeStatus"/>
                                <div class="button-field">
                                    <input type="submit" value="${currentUser.disabled?string("Enable", "Disable")}">
                                    <input type="hidden" id="disabled" name="disabled" value="${currentUser.disabled?string("false", "true")}"/>
                                    <input type="hidden" id="strId" name="strId" value="${currentUser.id}"/>
                                </div>
                            </form>
                        </td>
                    </#if>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
