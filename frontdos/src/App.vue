<template>
  <v-app>
    <v-app-bar app absolute color="black" elevate-on-scroll dark>
      <v-toolbar-title>
        <router-link to="/">
          <img class="mr-3" :src="require('@/assets/EMERGENCIA.gif')" height="100" />
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-account-multiple</v-icon>
      </v-btn>
    </v-app-bar>
    <router-view />

    <v-footer fixed padless>
      <v-col class="text-center" cols="12">
        {{ new Date().getFullYear() }} —
        <strong>Equipo 2 Taller BD.</strong>
      </v-col>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  })
};
</script>
