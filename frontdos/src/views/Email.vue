<template>
  <v-container style="padding-top: 100px">
    <v-row>
      <v-col width="33%">
        <h2>Seleccione emergencia</h2>
        <v-card class="mx-auto scroll" max-width="400" height="300">
          <v-list>
            <v-list-item-group v-model="model"  color="red" >
              <v-list-item v-for="(item, i) in items" :key="i" :value="item.id" v-on:click="mostrarTareas(item.id)">
                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>

      <v-col width="33%">
        <h2>Seleccione tarea</h2>
        <v-card class="mx-auto scroll" max-width="400" height="300">
          <v-list>
            <v-list-item-group v-model="model2"  color="red" >
              <v-list-item v-for="(item, i) in items2" :key="i" :value="item.id" v-on:click="mostrarVoluntarios(item.id)">
                <v-list-item-content>
                  <v-list-item-title v-text="item.name"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>

      <v-col width="33%">
        <h2>Seleccione voluntarios</h2>
        <v-card
          class="mx-auto scroll"
          height="300"
          max-width="500"
          v-if="model2 != 0"
        >
          <v-list shaped>
            <v-list-item-group v-model="model3" multiple>
              <template v-for="(item, i) in voluntarios" >
                <v-list-item
                  :key="i"
                  :value="item.id"
                  active-class="red--text"
                >
                  <template v-slot:default="{ active }">
                    <v-list-item-content>
                      <v-list-item-title v-text="item.nombre"></v-list-item-title>
                      <v-list-item-subtitle>Puntaje: {{ item.score }}</v-list-item-subtitle>
                    </v-list-item-content>

                    <v-list-item-action>
                      <v-checkbox
                        :input-value="active"
                        color="red"
                      ></v-checkbox>
                    </v-list-item-action>
                  </template>
                </v-list-item>
              </template>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
      <v-btn block color="black white--text" v-on:click="enviarEmail"> Enviar correo </v-btn>
      <center><h3 v-if="enviado != 0"> ¡¡ Correo(s) enviado(s) exitosamente !! </h3></center>
  </v-container>
</template>


<script>

import axios from 'axios';

export default {
  data: () => ({
    model: null,
    model2: [],
    selected: null,
    items2: [],
    model3: [],
    items: [],
    voluntarios: [],
    enviado: 0,
  }),
  mounted: function() {
      
  },

  methods:{
    mostrarTareas: function(id){
      this.model2 = [];
      this.model3 = [];
      this.voluntarios = [];
      this.items2 = [];
      this.enviado = 0;
        axios.get('http://localhost:1818/task/emergencies/'+ id).then(response=>{
          this.items2 = response.data;
          console.log(this.items2)
        })
      },
    mostrarVoluntarios: function(id){
        this.model3 = [];
        this.voluntarios = [];
        this.enviado = 0;
        axios.get('http://localhost:1818/task/' + id + '/volunteers').then(response=>{
          this.voluntarios = response.data;
          console.log(this.voluntarios)
        })
      },
      enviarEmail: function(){

        axios.post('http://localhost:1818/volunteers/email', {
        task_id : this.model2,
        volunteers: this.model3
      }).then(()=>{ this.enviado = 1});
      console.log(this.model2);
      console.log(this.model3);
      },
   },

  created(){ 
      axios.get('http://localhost:1818/emergency/').then(response=>{
        this.items = response.data;
        console.log(this.items);
      })
    
    }
};

</script>

<style>
.scroll {
  overflow-y: scroll;
}
</style>