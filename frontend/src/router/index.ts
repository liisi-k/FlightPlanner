import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import PlaneSeats from '@/views/PlaneSeats.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/seats/:flightId',
      name: 'plane-seats',
      component: PlaneSeats,
      props: true
    }
      ]
    })
export default router
