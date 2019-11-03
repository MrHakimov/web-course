<#import "common.ftl" as c/>

<@c.page>
    <#list posts as post>
<#--            <article>-->
<#--                    <div class="title">${post.title}</div>-->
<#--                    <div class="information">By ${c.findBy(users, "id", post.user_id).handle}, 2 days ago, translation</div>-->
<#--                    <div class="body">${post.text}</div>-->
<#--                    <div class="footer">-->
<#--                            <div class="left">-->
<#--                                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>-->
<#--                                    <span class="positive-score">+173</span>-->
<#--                                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>-->
<#--                            </div>-->
<#--                            <div class="right">-->
<#--                                    <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>-->
<#--                                    2 days ago-->
<#--                                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>-->
<#--                                    <a href="#">68</a>-->
<#--                            </div>-->
<#--                    </div>-->
<#--            </article>-->
<#--            <br>-->
            <@c.printPost post=post mode=true/>
    </#list>
</@c.page>
