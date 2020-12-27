<template>
  <v-parallax :src="require('@/assets/fondo.jpg')" height="810">
    <v-row align="center" justify="center">
      <v-col class="text-center" cols="12">
        <h1 class="display-1 font-weight">Bienvenidx a nuestra aplicación para emergencias!</h1>
        <br>
        <v-btn color="warning" href="/crearemergencia">Crear una emergencia.</v-btn>
        <br><br>
        <v-btn color="error" href="/creartarea/1">Crear tarea en emergencia 1.</v-btn>
        <br><br>
        <v-btn color="primary" href="/ubvoluntarios">Ver voluntarios por regiones.</v-btn>
        
        
      </v-col>
    </v-row>
  </v-parallax>
</template>

<script>
export default {
  name: "Home"
};
</script>

<style>
</style>

