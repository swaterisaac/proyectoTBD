
<template>
  <v-container fluid ma-0 pa-0 fill-height class="white">
    <v-card
      class="mx-auto"
      width="300">

      <v-card-title class= "justify-center mb-2"> {{tarea.name}}</v-card-title>

      <!---<v-img
        height="200px"
        src="https://lh3.googleusercontent.com/proxy/QIZ1AyNmAbkyiVNpM38wPlUuj3xJfz32zpKdnIUHd1yPsarBBTtcn_FV-qoWNNMw8smIY898pVH9ZqmhXLSNrcT_IC4dRPeVC2EORHnCESl3hVHGGMKwrNEog4o_mZTzoqSrxXIB9dw_qfRfzgB_"
        >
      </v-img>--->

      

      <v-card-subtitle class="pb-2">
        Id emergencia asociada: {{tarea.id_emergency}}
      </v-card-subtitle>

      <v-card-text class="text--primary">
        <div>Descripción tarea : {{tarea.description}}</div>
      </v-card-text>


      <v-container>
        <v-select :items="statuses" v-model="selected" :label="statusActual" item-text="description" item-value="id" solo>
        </v-select>
      </v-container>

      <v-card-actions>
        <v-btn color="orange" text v-on:click="editStatus">
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
      tarea: [],
      status:[],
      statuses:[],
      selected:'',
      statusActual: '',
      variable: '',
      idEmergencia:[]
    }),

    methods:{
      editStatus: function(){axios.post('http://localhost:1818/task/edit/'+ this.tarea.id, {id_status: 1})},
    },

    created(){ 
      axios.get('http://localhost:1818/task/' + this.$route.params.id).then(response=>{
      this.tarea = response.data;
      this.idEmergencia = response.data.id_emergencia;
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


