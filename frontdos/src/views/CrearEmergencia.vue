<template>
  <v-container mt-12>
    <v-row>
      <v-col>
        <center>
          <h1>Crear emergencia</h1>
        </center>
        <form>
          <v-text-field v-model="name" label="Nombre de emergencia"></v-text-field>
          <v-text-field v-model="desc" label="Descripción"></v-text-field>
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
              <v-date-picker ref="picker" v-model="datefinal" :min="dateinit" @change="save"></v-date-picker>
            </v-menu>
          </v-row>
          <v-select
                  :items="institutions"
                  v-model="id_institutions"
                  label="seleccione institución"
                  item-text="name"
                  item-value="id"
                  solo
                ></v-select>
          <center>
            <v-btn class="mt-2" width="40%" color="primary" v-on:click="crearemergencia">Crear</v-btn>
          </center>
        </form>
        {{ this.task.latlng }}
      </v-col>
      <v-col>
        <v-card class="mx-auto" width="500" length="500">
          <div id="map"></div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import leaflet from "leaflet";
import axios from "axios";

export default {

  data: function() {
    return {
      mymap: null,
      task: {
        latlng: null
      },
      marker: null,
      name: "",
      desc: "",
      dateinit: new Date().toISOString().substr(0, 10),
      datefinal: new Date().toISOString().substr(0, 10),
      menu: false,
      menu2: false,
      date: null,
      institutions: [],
      id_institutions: '',
      watch: {
        menu(val) {
          val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
        }
      }
    };
  },

  created() {
      axios.get("http://localhost:1818/institution/").then(response => {
        console.log('Hola');
        this.institutions = response.data;
        console.log(this.institutions);
      });
    },
  mounted: function() {
    var mymap = leaflet.map("map").setView([-38.719, -72.478], 7);

    leaflet
      .tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
        attribution:
          '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        maxZoom: 10
      })
      .addTo(mymap);

    var p = this;
    mymap.on("click", function(e) {
      p.task.latlng = e.latlng;
      if (p.marker) {
        mymap.removeLayer(p.marker);
      }
      p.marker = leaflet.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap);
    });

    this.mymap = mymap;
  },
  methods: {
    crearemergencia: function() {
      //id_status por defecto 1.
      axios.post("http://localhost:1818/emergency/", {
        id_status : 1,
        name: this.name,
        description: this.desc,
        start_date: this.dateinit,
        final_date: this.datefinal,
        id_institution: this.id_institutions, 
        longitude: this.task.latlng.lng,
        latitude: this.task.latlng.lat, 
      });
      console.log(this.name);
      console.log(this.desc);
      console.log(this.dateinit);
      console.log(this.datefinal);
      console.log(this.id_institutions);
    },

    save(date) {
      this.$refs.menu.save(date);
    },
  }
};
</script>

<style scoped>
@import "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.5.1/leaflet.css";
#map {
  z-index: 0;
  height: 800px;
}
</style>