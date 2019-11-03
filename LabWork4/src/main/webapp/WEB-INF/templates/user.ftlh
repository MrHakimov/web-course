<#import "common.ftl" as c/>

<@c.page>
    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
    <#else>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
    </#if>
    <#if viewedUser.id??>
        <#assign next=c.next(users, "handle", viewedUser.handle)!/>
        <#assign prev=c.prev(users, "handle", viewedUser.handle)!/>
        <div class="user-box">
            <div class="user-info">
                <span class="user-name"><@c.userlink user=viewedUser/></span>
                <ul>
                    <li>id: ${viewedUser.id}</li>
                    <li>handle: <@c.userlink user=viewedUser nameOnly=false></@c.userlink></li>
                    <li>name: ${viewedUser.name}</li>
                </ul>
            </div>
            <div class="user-image">
                <img src="../../img/user.jpg">
            </div>
        </div>
        <#if prev.id??>
            <span class="arrow"><a href="/user?handle=${prev.handle}">←</a></span>
        <#else>
            <span class="arrow">←</span>
        </#if>
        <#if next.id??>
            <span class="arrow"><a href="/user?handle=${next.handle}">→</a></span>
        <#else>
            <span class="arrow">→</span>
        </#if>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
