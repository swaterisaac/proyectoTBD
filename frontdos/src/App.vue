<template>
  <v-app>
    <v-app-bar
      app
      absolute
      color="dark"
      elevate-on-scroll
      dark  
    >
    <v-toolbar-title>Title</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-account-multiple</v-icon>
      </v-btn>
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
