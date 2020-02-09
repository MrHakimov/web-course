<#-- @ftlvariable name="myArticles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="myArticles datatable">
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                <#list myArticles as article>
                    <tr>
                        <td>${article.id}</td>
                        <td>${article.title}</td>
                        <td>
                            <#assign x="Hide">
                            <#if article.hidden>
                                <#assign x="Show">
                            </#if>
                            <input type="button" name="${article.id}" class="hidden" value="${x}"/>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>

        <script>
            $(function () {
                $(".myArticles .hidden").click(function () {
                    var currentVisibility = $(this).val() === "Hide";
                    var articleId = $(this).attr("name");
                    var $obj = $(this);

                    var data = {
                        "action": "changeArticleVisibility",
                        "currentVisibility": currentVisibility,
                        "articleId": articleId
                    };

                    function success(response) {
                        if (response["actualArticleVisibility"]) {
                            $obj.val("Show");
                        } else {
                            $obj.val("Hide");
                        }
                    }

                    myAjax(data, success);

                });

                return false;
            });
        </script>
    <#else>
        <script>
            $(function () {
                location.href = "/notFound";
            })
        </script>
    </#if>
</@c.page>
