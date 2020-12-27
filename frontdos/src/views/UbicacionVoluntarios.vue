<template>
    <v-container mt-12>
        <div style="position: relative;">
            <div id="search">
                <v-card class="mx-auto" width="100%" height="30">
                    <form>
                        <v-select
                        :items="regions"
                        v-model="selectedRegion"
                        label="Seleccione una región"
                        name="selectedTegion"
                        item-text="nom_reg"
                        item-value="gid"
                        return-object
                        v-on:change="this.reloadMap"
                        solo
                        ></v-select>
                    </form>
                
                </v-card>
            </div>
            <div id="map-container">
                <v-card class="mx-auto" width="100%" length="500">
                    <div id="map"></div>
                </v-card>
            </div>
            
        </div>
    </v-container>

</template>

<script>
import leaflet from "leaflet";
import axios from 'axios'

export default {
    name: 'UbicacionVoluntarios',
    data: function(){
        return {
            mymap: null,
            regions: [],
            selectedRegion: null,
            volunteers: [],
            task: {},
            markers: [],
        }
    },
    created: function (){
        this.getRegions();
    },
    mounted: function () {
        var mymap = leaflet.map("map").setView([-33.45, -70.6777], 10);

        leaflet
        .tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
            attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            
        })
        .addTo(mymap);

        // var p = this;
        // mymap.on("click", function (e) {
        // p.task.latlng = e.latlng;
        // if (p.marker) {
        //     mymap.removeLayer(p.marker);
        // }
        // p.marker = leaflet.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap);
        // });

        this.mymap = mymap;

        
    },
    methods: {
        getRegions: function(){
            axios.get('http://localhost:1818/region/').then(response => {
                this.regions = response.data;
            });
        },
        getVolunteers: function(){
            axios.get('http://localhost:1818/region/user/'+this.selectedRegion.gid).then(response => {
                this.volunteers = response.data;
                this.volunteers.forEach(element => {
                    var marker = leaflet.marker([element.latitude, element.longitude]).addTo(this.mymap);
                    marker.bindTooltip(element.nombre+' '+element.apellido+' '+element.email);
                    this.markers.push(marker);
                });
                console.log(this.markers);
            });
        },
        reloadMap: function(){
            this.markers.forEach(element => {
                this.mymap.removeLayer(element);
            });
            this.markers = [];
            this.mymap.setView([this.selectedRegion.latitude,this.selectedRegion.longitude], 8);
            this.getVolunteers();
            
            
        }
    },
}
</script>

<style>
@import "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.5.1/leaflet.css";
#map {
  height: 800px;
  width: 100%;
  z-index: 0;
}
#map-container{
    position: absolute;
    height: 800px;
    width: 100%;
    top: 0;
    left: 0;
}
#search {
    z-index: 100000;
    width: 300px;
    position:absolute;
    right: 30px; 
    top: 40px
}
</style>