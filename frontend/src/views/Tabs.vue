<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="home" href="/tabs/home">
          <ion-icon :icon="flash" />
          <ion-label>Home</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="workouts" href="/tabs/workouts">
          <ion-icon :icon="apps" />
          <ion-label>Workouts</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="excercise" href="/tabs/excercise">
          <ion-icon :icon="barbellOutline" />
          <ion-label>Excercise</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="progress" href="/tabs/progress">
          <ion-icon :icon="send" />
          <ion-label>Progress</ion-label>
        </ion-tab-button>
      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonTabBar,
  IonTabButton,
  IonTabs,
  IonLabel,
  IonIcon,
  IonPage,
  IonRouterOutlet,
} from "@ionic/vue";
import { flash, apps, barbellOutline, send } from "ionicons/icons";
</script>