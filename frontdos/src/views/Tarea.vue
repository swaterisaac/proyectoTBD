
<template>
  <v-container fluid ma-0 pa-0 fill-height class="white">
    <v-card
      class="mx-auto"
      width="300">

      <v-card-title class= "justify-center mb-2">Nombre tarea {{$route.params.id}}</v-card-title>

      <!---<v-img
        height="200px"
        src="https://lh3.googleusercontent.com/proxy/QIZ1AyNmAbkyiVNpM38wPlUuj3xJfz32zpKdnIUHd1yPsarBBTtcn_FV-qoWNNMw8smIY898pVH9ZqmhXLSNrcT_IC4dRPeVC2EORHnCESl3hVHGGMKwrNEog4o_mZTzoqSrxXIB9dw_qfRfzgB_"
        >
      </v-img>--->

      

      <v-card-subtitle class="pb-2">
        id emergencia
      </v-card-subtitle>

      <v-card-text class="text--primary">
        <div>{{tarea.name}}</div>
      </v-card-text>
      <v-container id="dropdown-example-3" fluid style = "width: 80%">
        <v-overflow-btn
          class="my-2"
          :items="dropdown_font"
          :label="statusActual"
          target="#dropdown-example-1"
        ></v-overflow-btn>
      </v-container>

      <v-container>
        <span>Seleccionado: {{ selected }}</span>
        <select v-model="selected">
          <option v-for="stat in statuses" :key="stat.id" v-bind:value="stat.id">
            {{ stat.description }}
          </option>
        </select>
        
      </v-container>
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
  import axios from 'axios'

  export default {
    data: () => ({
      //idTarea = null,
      dropdown_font: [],
      tarea: [],
      status:[],
      statuses:[],
      selected:'',
      statusActual: '',
      variable: ''
    }),

    created(){ 
      axios.get('http://localhost:1818/task/' + this.$route.params.id).then(response=>{
      this.tarea = response.data;
      this.variable = JSON.stringify(this.tarea.id_status);
      console.log(this.variable);
      console.log(this.tarea.id_status);
      axios.get('http://localhost:1818/status/' + this.variable).then(response=>{
      this.status = response.data;
      this.statusActual = this.status.description;
      
    })
    }),
    axios.get('http://localhost:1818/status/').then(response=>{
      this.statuses = response.data;
      console.log(this.statuses);
    })
    
    }

  }

</script>


