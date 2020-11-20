<template>
  <v-container fluid ma-0 pa-0 fill-height class="white">
      <v-card
    class="mx-auto"
    max-width="400">
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://lh3.googleusercontent.com/proxy/QIZ1AyNmAbkyiVNpM38wPlUuj3xJfz32zpKdnIUHd1yPsarBBTtcn_FV-qoWNNMw8smIY898pVH9ZqmhXLSNrcT_IC4dRPeVC2EORHnCESl3hVHGGMKwrNEog4o_mZTzoqSrxXIB9dw_qfRfzgB_"
    >
      <v-card-title>-Nombre de la tarea-</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">
      -Emergencia asociada-
    </v-card-subtitle>

    <v-card-text class="text--primary">
      <div>Descripción tarea</div>
    </v-card-text>
    <v-overflow-btn
      class="my-2"
      :items="dropdown_font"
      label="Actualizar el estado de la tarea"
      target="#dropdown-example-1"
    ></v-overflow-btn>
    <v-card-actions>
      <v-btn
        color="orange"
        text
      >
        Guardar estado
      </v-btn>
    </v-card-actions>
  </v-card>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      dropdown_font: ['Creada', 'Iniciada', 'Cancelada', 'Finalizada'],
    }),
  }
</script>