<template>
  <v-container>
      
      <v-row>
          <v-col>
              {{this.emergency.latlng}}
          </v-col>
          <v-col>
                <v-card>
                        <div id="map"></div>
                </v-card>
          </v-col>
      </v-row>
      
      
  </v-container>
</template>

<script>
import leaflet from 'leaflet';

export default {
    name: 'CrearTarea',
    data: function(){
        return {
            mymap: null,
            emergency: {
                name: null,
                latlng: null,
            },
            marker: null,
        }
    },
    mounted: function (){
        var mymap = leaflet.map('map').setView([-38.719, -72.478], 7);

        leaflet.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            maxZoom: 10
        }).addTo(mymap);


        var p = this;
        mymap.on('click', function(e) {
            p.emergency.latlng = e.latlng;
            if(p.marker){
                mymap.removeLayer(p.marker);
            }
            p.marker = leaflet.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap);
        }); 

        this.mymap = mymap;
    },
    methods: {  
    }
}
</script>

<style scoped>
@import 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.5.1/leaflet.css';
#map { 
    z-index: 0;
  height: 800px; 
}
</style>