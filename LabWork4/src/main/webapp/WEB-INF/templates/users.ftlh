<#import "common.ftl" as c/>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
            <tr>
                <td><#if user?? && u.id == user.id>*</#if> ${u.id}</td>
                <td><#if user?? && u.id == user.id>*</#if> <@c.userlink user=u nameOnly=false/></td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
