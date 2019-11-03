<#macro header>
<header>
    <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if logged_user_id?? && logged_user_id!=0>
            <#assign loggedUser=findBy(users, "id", logged_user_id)>
            <@userlink user=loggedUser nameOnly=false/>
            |
            <a href="#">Logout</a>
        <#else>
            <a href="#">Enter</a>
            |
            <a href="#">Register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <#assign IndexLink = getLinkClass("/index")/>
            <#assign UsersLink = getLinkClass("/users")/>
            <#assign HelpLink = getLinkClass("/misc/help")/>
            <li><a href="/index" class="${IndexLink}">Index</a></li>
            <li><a href="/users" class="${UsersLink}">Users</a></li>
            <li><a href="/misc/help" class="${HelpLink}">Help</a></li>
        </ul>
    </nav>
</header>
</#macro>

<#macro sidebar>
<aside>
    <section>
        <#assign post=posts?first>

        <div class="header">
            Post #${post.id}
        </div>
        <div class="body">
            <#assign minitext = post.text>
            <#if minitext?length &gt; 250>
                ${minitext?substring(0,249)}...
            <#else>
                ${minitext}
            </#if>
        </div>
        <div class="footer">
            <a href="/post?post_id=${post.id}">View all</a>
        </div>
    </section>
</aside>
</#macro>

<#macro footer>
<footer>
    <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
</footer>
</#macro>

<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
</head>
<body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
</body>
</html>
</#macro>

<#macro userlink user nameOnly=true>
    <#if nameOnly==true>
        <a href="/user?handle=${user.handle}">${user.handle}</a>
    <#else>
        <a href="/user?handle=${user.handle}" style="color: ${user.color}; text-decoration: none">${user.handle}</a>
    </#if>
</#macro>

<#function findBy items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro printPost post mode>
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By ${findBy(users, "id", post.user_id).handle}, 2 days ago, translation</div>

        <#if mode==true>
            <#assign minitext = post.text!"">
            <#if minitext?length &gt; 250>
                <div class="body">${minitext?substring(0,249)}...</div>
            <#else>
                <div class="body">${minitext}</div>
            </#if>
        <#else>
            <div class="body">${minitext}</div>
        </#if>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="/post?post_id=${post.id}">68</a>
            </div>
        </div>
    </article>
    <br>
</#macro>

<#function next items key id>
    <#list items as item>
        <#if item[key]==id>
            <#if (item_index < items?size - 1)>
                <#return items[item_index + 1]/>
            </#if>
        </#if>
    </#list>
</#function>

<#function prev items key id>
    <#list items as item>
        <#if item[key]==id>
            <#if (item_index > 0)>
                <#return items[item_index - 1]/>
            </#if>
        </#if>
    </#list>
</#function>

<#function getLinkClass link>
    <#if link == uri>
        <#return "current"/>
    <#else>
        <#return "simple"/>
    </#if>
</#function>
