<!--suppress HtmlUnknownAnchorTarget -->
<template>
    <article>
        <div class="post">
<!--            <div class="title"><a href="#page=PostPage" @click="viewPost(post)">{{post.title}}</a></div>-->
            <div class="title">{{post.title}}</div>
            <div class="information">By {{post.user.login}}</div>
            <div class="body">
                {{post.text}}
            </div>
            <div class="footer">
                <div class="left">
                    <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    2 days ago
                    <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    68
<!--                    <a href="#page=PostPage" @click="viewPost(post)">68</a>-->
                </div>
            </div>
        </div>
    </article>
</template>

<script>
    export default {
        props: ['post'],
        name: "Post"
    }
</script>

<style scoped>
    article .title {
        color: var(--caption-color);
        font-weight: bold;
        font-size: 1.3rem;
        padding-top: 4px;
        padding-bottom: 4px;
    }

    article .title a {
        text-decoration: none;
        color: var(--caption-color);
    }

    article .information {
        color: var(--information-color);
        font-size: 0.9rem;
    }

    article .body {
        border-left: 4px solid var(--border-color);
        padding-left: 10px;
        font-size: 0.88rem;
    }

    article .footer {
        padding: 0;
        margin: 1rem 0 0;
        text-align: center;
    }

    /* https://stackoverflow.com/questions/5803023/how-to-place-two-divs-next-to-each-other */
    article .footer {
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
        padding: var(--border-radius);
        overflow: hidden;
    }

    article .footer .left {
        float: left;
    }

    article .footer .right {
        float: right;
    }

    article .footer .left img {
        vertical-align: middle;
    }

    article .footer .left .positive-score {
        color: #008000;
        font-weight: bold;
    }
</style>
