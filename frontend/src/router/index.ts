import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";
import Tabs from "@/views/Tabs.vue";
import Login from "@/views/Login.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/tabs/",
    component: Tabs,
    children: [
      {
        path: "",
        redirect: "/tabs/tab1",
      },
      {
        path: "home",
        component: () => import("@/views/Home.vue"),
      },
      {
        path: "workouts",
        component: () => import("@/views/Workouts.vue"),
      },
      {
        path: "excercise",
        component: () => import("@/views/Excercise.vue"),
      },
      {
        path: "progress",
        component: () => import("@/views/Progress.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
