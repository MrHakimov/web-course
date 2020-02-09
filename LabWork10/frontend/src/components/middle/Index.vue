<template>
    <div class="posts">
        <div v-for="post in posts" :key="post.id">
            <Post :post="post"/>
        </div>
    </div>
</template>

<script>
    import Post from "./Post";

    export default {
        name: "Index",
        props: ["posts"],
        components: {
            Post
        }
    }
</script>

<style scoped>

</style>
