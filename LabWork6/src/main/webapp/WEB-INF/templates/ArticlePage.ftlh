<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable error="text" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="creation form-box">
            <div class="header">Create article</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="create"/>
                    <div class="field">
                        <div class="name">
                            <label for="title">Title</label>
                        </div>
                        <div class="value">
                            <input id="title" name="title" value="${title!}"/>
                        </div>
                    </div>
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text"></textarea>
                        </div>
                    </div>
                    <div class="error">
                        ${error!}
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Create article">
                    </div>
                </form>
            </div>
        </div>
        <script>
            $(function () {
                $(".creation form").submit(function () {
                    var title = $(this).find("input[name='title']").val();
                    var text = $(this).find("textarea[name='text']").val();
                    var $error = $(this).find(".error");

                    var data = {"action": "create", "title": title, "text": text};
                    function success(response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        }
                    }
                    myAjax(data, success);

                    return false;
                });
            })
        </script>
    <#else>
        <script>
            $(function () {
                location.href = "/notFound";
            })
        </script>
    </#if>
</@c.page>
