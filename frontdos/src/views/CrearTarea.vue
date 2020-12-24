<template>
  <v-container mt-12>
    <v-row>
      <v-col>
        <center><h1>Crear tarea</h1></center>
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
              :return-value.sync="dateinit"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateinit"
                  label="Fecha inicio"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">
                  Cancelar
                </v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(dateinit)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>

            <v-menu
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
              <v-date-picker no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">
                  Cancel
                </v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(datefinal)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>

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
                  v-model="date"
                  label="Birthday date"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                ref="picker"
                v-model="date"
                :max="new Date().toISOString().substr(0, 10)"
                min="1950-01-01"
                @change="save"
              ></v-date-picker>
            </v-menu>
          </v-row>

          <center>
            <v-btn
              class="mt-2"
              width="40%"
              color="primary"
              v-on:click="impVariables"
            >
              Crear
            </v-btn>
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
        maxZoom: 10,
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
    impVariables: function () {
      console.log(this.name);
      console.log(this.desc);
      console.log(this.volRequeridos);
      console.log(this.dateinit);
      console.log(this.datefinal);
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
  height: 800px;
}
</style>