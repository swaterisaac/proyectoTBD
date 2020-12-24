import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import CrearTarea from '../views/CrearTarea'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/tarea',
    name: 'tarea',
    component: () => import(/* webpackChunkName: "about" */ '../views/Tarea.vue')
  },
  
  {
    path: '/tarea/:id',
    name: 'tarea id',
    component: () => import(/* webpackChunkName: "about" */ '../views/Tarea.vue')
  },

  {
    path: '/creartarea/:id_emergencia',
    name: 'crear tarea',
    component: CrearTarea
  },

  {
    path: '/crearemergencia',
    name: 'crear emergencia',
    component: () => import(/* webpackChunkName: "about" */ '../views/CrearEmergencia.vue')
  },

  {
    path: '/ubvoluntarios',
    name: 'ubvoluntarios',
    component: () => import(/* webpackChunkName: "about" */ '../views/UbicacionVoluntarios.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
