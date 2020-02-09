<#-- @ftlvariable name="createNoticeForm" type="ru.itmo.wp.form.NoticeCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="createNotice form-box">
            <div class="header">Create notice</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="createNotice"/>
                    <div class="field">
                        <div class="name">
                            <label for="content">Content</label>
                        </div>
                        <div class="value">
                            <textarea id="content" name="content" value="${createNoticeForm.content!}"></textarea>
                        </div>
                        <@c.error "createNoticeForm.content"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Add">
                    </div>
                </form>
            </div>
        </div>
    <#else>
        Log-in to create notices!
    </#if>
</@c.page>
