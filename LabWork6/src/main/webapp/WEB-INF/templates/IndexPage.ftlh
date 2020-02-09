<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <p class="noData">
            <span>No data</span>
        </p>

        <template style="display: none">
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
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
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
            <br>
        </template>
    </div>

<script>
    $(function () {
        var $noData = $(".articles .noData");
        $noData.find("span").text("Loading...");

        // $.ajax({
        //     dataType: "json",
        //     data: {
        //         action: "findAll"
        //     },
        //     success: function(response) {
        //         function getUserLogin(id) {
        //             response["users"].forEach(function (user) {
        //                 if (user["id"] === id) {
        //                     response["currentUserLogin"] = user["login"];
        //                 }
        //             })
        //         }
        //
        //         response["articles"].forEach(function(article) {
        //             $noData.hide();
        //             var $p = $($(".articles template").prop("content")).clone();
        //             $p.find(".title").text(article["title"]);
        //             getUserLogin(article["userId"]);
        //             $p.find(".information").text("By " + response["currentUserLogin"] + ", 2 days ago, translation");
        //             $p.find(".body").text(article["text"]);
        //             $p.find("article").attr("article-id", article["id"]);
        //             $(".articles").append($p);
        //         });
        //     },
        //     type: "POST"
        // });

        var data = {"action": "findAll"};

        function success(response) {
            function getUserLogin(id) {
                response["users"].forEach(function (user) {
                    if (user["id"] === id) {
                        response["currentUserLogin"] = user["login"];
                    }
                })
            }

            response["articles"].forEach(function(article) {
                $noData.hide();
                var $p = $($(".articles template").prop("content")).clone();
                $p.find(".title").text(article["title"]);
                getUserLogin(article["userId"]);
                $p.find(".information").text("By " + response["currentUserLogin"] + ", 2 days ago, translation");
                $p.find(".body").text(article["text"]);
                $p.find("article").attr("article-id", article["id"]);
                $(".articles").append($p);
            });
        }

        myAjax(data, success);

        $("aside section").click(function () {
            $(this).css("background-color", "red");
        });
    })
</script>
</@c.page>
