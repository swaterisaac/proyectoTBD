<template>
  <v-container mt-12>
    <v-row style="padding: 50px">
      <v-col>
        <center><h1 style="padding: 50px">Crear tarea</h1></center>
        <form>
          <v-text-field v-model="name" label="Nombre tarea"></v-text-field>
          <v-text-field v-model="desc" label="Descripción"></v-text-field>

          <v-text-field
            v-model="volRequeridos"
            label="Cantidad de voluntarios requeridos"
            hide-details
            single-line
            type="number"
          />

          <v-row class="mt-4">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateinit"
                  label="Fecha de inicio"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                ref="picker"
                v-model="dateinit"
                :min="new Date().toISOString().substr(0, 10)"
                @change="save"
              ></v-date-picker>
            </v-menu>
            <v-menu
              ref="menu2"
              v-model="menu2"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="datefinal"
                  label="Fecha término"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                ref="picker"
                v-model="datefinal"
                :min= dateinit
                @change="save"
              ></v-date-picker>
            </v-menu>
          </v-row>

          <center>
            <v-btn
              class="mt-2"
              width="40%"
              color="primary"
              v-on:click="creartarea"
            >
              Crear  
               <v-icon dark>
                 mdi-pencil
              </v-icon>
            </v-btn>
          </center>
        </form>
      </v-col>
      <v-col>
        <v-card class="mx-auto">
          <div id="map"></div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import leaflet from "leaflet";
import axios from 'axios'

export default {
  name: "CrearTarea",
  data: function () {
    return {
      mymap: null,
      task: {
        latlng: null,
      },
      marker: null,
      name: "",
      desc: "",
      volRequeridos: null,
      dateinit: new Date().toISOString().substr(0, 10),
      datefinal: new Date().toISOString().substr(0, 10),
      menu: false,
      menu2: false,
      date: null,
      watch: {
        menu(val) {
          val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
        },
      },
    };
  },
  mounted: function () {
    var mymap = leaflet.map("map").setView([-38.719, -72.478], 7);
    
    leaflet
      .tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
        attribution:
          '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      })
      .addTo(mymap);
    var p = this;
    mymap.on("click", function (e) {
      p.task.latlng = e.latlng;
      if (p.marker) {
        mymap.removeLayer(p.marker);
      }
      p.marker = leaflet.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap);
    });

    this.mymap = mymap;
  },
  methods: {
    creartarea: function(){
      axios.post('http://localhost:1818/task/',{name: this.name, description: this.desc, volunteer_required: this.volRequeridos, volunteer_registered: 0, start_date: this.dateinit, final_date: this.datefinal, longitude : this.task.latlng.lng, latitude: this.task.latlng.lat, id_emergency : this.$route.params.id_emergencia, id_status: 1})
      console.log(this.name);
      console.log(this.desc);
      console.log(this.volRequeridos);
      console.log(this.dateinit);
      console.log(this.datefinal);
      console.log(this.task.latlng);
      console.log(this.$route.params.id_emergencia);
      },
    
    save(date) {
      this.$refs.menu.save(date);
    },
  },
};
</script>

<style scoped>
@import "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.5.1/leaflet.css";
#map {
  z-index: 0;
  height: 600px;
}
</style>