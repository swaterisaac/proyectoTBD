<template>
  <v-app>
    <v-app-bar
      app
      color="dark"
      dark
    >
    </v-app-bar>
    <router-view/>
  </v-app>
</template>

<script>
export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
