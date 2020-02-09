<template>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody v-for="user in users" :key="user.id">
            <tr>
                <td>{{user.id}}</td>
                <td>{{user.login}}</td>
                <td>{{user.name}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "Users",
        props: ["users"]
    }
</script>

<style scoped>
    .datatable table {
        border: 1px solid var(--table-border-color);
        border-collapse: collapse;
        width: 100%;
        text-align: center;
        font-size: 0.8rem;
    }

    .datatable table td, th {
        border: 1px solid var(--table-border-color);
        padding: 3px;
    }

    .datatable table tbody:nth-child(even) {
        background-color: var(--search-background-color);
    }

    .datatable .caption {
        background-color: var(--table-border-color);
        padding: 0.25rem 0.5rem;
        border-radius: var(--border-radius) var(--border-radius) 0 0;
        font-size: 0.9rem;
    }
</style>
