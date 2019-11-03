<#import "common.ftl" as c/>

<@c.page>
    <#if post_id??>
        <#assign viewedPost=c.findBy(posts, "id", post_id)!/>
        <#if viewedPost.id??>
            <@c.printPost post=viewedPost mode=false/>
        <#else>
            <h1>No such post</h1>
        </#if>
    <#elseif user_id??>
        <#assign hasPosts=false>
        <#list posts as p>
            <#if p.user_id==user_id>
                <#assign hasPosts=true>
                <@c.printPost post=p mode=true></@c.printPost>
            </#if>
        </#list>

        <#if hasPosts==false>
            <h1>No posts</h1>
        </#if>
    <#else>
        <h1>No such post or user</h1>
    </#if>
</@c.page>
