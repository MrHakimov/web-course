<template>
    <div class="middle">
        <aside>
            <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
        </aside>
        <main>
            <Index v-if="page === 'Index'" :posts="allPosts"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import Users from "./middle/Users";
    import SidebarPost from './SidebarPost';

    export default {
        name: "Middle",
        props: ["posts", "users"],
        data: function () {
            return {
                page: "Index"
            }
        },
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            },
            allPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            }
        },
        components: {
            Index,
            Enter,
            Register,
            Users,
            SidebarPost
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
        }
    }
</script>

<style scoped>

</style>
