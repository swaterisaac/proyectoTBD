<template>
  <v-container style="padding-top: 100px">
    <v-row>
      <v-col>
        <h2>Seleccione emergencia</h2>
        <p>{{ model }}</p>
        <v-card class="mx-auto scroll" max-width="400" height="400">
          <v-list>
            <v-list-item-group v-model="model"  color="red">
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-content>
                  <v-list-item-title v-text="item.text"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-col>

      <v-col>
        <h2>Tareas activas</h2>
        <p>{{ model2 }}</p>
        <v-card
          class="mx-auto scroll"
          height="400"
          max-width="500"
          v-if="model != null"
        >
          <v-list shaped>
            <v-list-item-group v-model="model2" multiple>
              <template v-for="(item, i) in items2">
                <v-list-item
                  :key="i"
                  :value="item"
                  active-class="red--text"
                >
                  <template v-slot:default="{ active }">
                    <v-list-item-content>
                      <v-list-item-title v-text="item"></v-list-item-title>
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

      <v-col>
        <h2>Seleccione voluntarios</h2>
        <p>{{ model3 }}</p>
        <v-card
          class="mx-auto scroll"
          height="400"
          max-width="500"
          v-if="model2 != 0"
        >
          <v-list shaped>
            <v-list-item-group v-model="model3" multiple>
              <template v-for="(item, i) in items2">
                <v-list-item
                  :key="i"
                  active-class="red--text"
                >
                  <template v-slot:default="{ active }">
                    <v-list-item-content>
                      <v-list-item-title v-text="item"></v-list-item-title>
                      <v-list-item-subtitle>Puntaje: </v-list-item-subtitle>
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

      <v-btn block color="black white--text"> Enviar correo </v-btn>
    </v-row>
  </v-container>
</template>


<script>
export default {
  data: () => ({
    model: null,
    items: [
      {
        text: "Wifi",
      },
      {
        text: "Bluetooth",
      },
      {
        text: "Data Usage",
      },
    ],
    model2: [],

    items2: ["Dog Photos", "Cat Photos", "Potatoes", "Carrots"],
    model3: [],

    mostrarTarea: null,
  }),
  mounted: function() {
      
  }
};

</script>

<style>
.scroll {
  overflow-y: scroll;
}
</style>