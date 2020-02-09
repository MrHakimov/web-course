<#-- @ftlvariable name="viewedUser" type="ru.itmo.wp.domain.User" -->

<#import "commons.ftlh" as c>

<@c.page>
    <#if viewedUser??>
        <div class="user-box">
            <div class="user-info">
                <span class="user-name"><a href="/user/${viewedUser.id}">${viewedUser.login}</a></span>
                <ul>
                    <li>id: ${viewedUser.id}</li>
                    <li>handle: ${viewedUser.login}</li>
                    <li>registration date&time: ${viewedUser.creationTime}</li>
                </ul>
            </div>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
