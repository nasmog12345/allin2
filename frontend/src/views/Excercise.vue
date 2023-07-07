<template>
    <ion-page>
      <ion-header>
        <ion-toolbar>
          <ion-title>Exercise List</ion-title>
          <ion-buttons slot="end">
            <ion-button @click="searchExercises">Search</ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>
  
      <ion-content>
        <ion-searchbar
          placeholder="Search exercises"
          v-model="searchQuery"
          @ionChange="searchExercises"
        ></ion-searchbar>
  
        <ion-list>
          <ion-item
            v-for="(exercise, index) in result"
            :key="index"
            @click="openModal(exercise)"
          >
            <ion-label>{{ exercise.name }}</ion-label>
          </ion-item>
        </ion-list>
  
        <ion-modal :is-open="showModal" @ionModalDidDismiss="showModal = false">
          <ion-header>
            <ion-toolbar>
              <ion-title>{{ modalContent.name }}</ion-title>
            </ion-toolbar>
          </ion-header>
          <ion-content>
            <ion-list>
              <ion-item>
                <ion-label>Type:</ion-label>
                <ion-label>{{ modalContent.type }}</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Muscle:</ion-label>
                <ion-label>{{ modalContent.muscle }}</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Equipment:</ion-label>
                <ion-label>{{ modalContent.equipment }}</ion-label>
              </ion-item>
              <ion-item>
                <ion-label>Difficulty:</ion-label>
                <ion-label>{{ modalContent.difficulty }}</ion-label>
              </ion-item>
            </ion-list>
            <ion-text color="dark">
              <p class="instruction-text">{{ modalContent.instructions }}</p>
            </ion-text>
          </ion-content>
          <ion-footer>
            <ion-toolbar>
              <ion-button expand="full" @click="showModal = false">Close</ion-button>
            </ion-toolbar>
          </ion-footer>
        </ion-modal>
      </ion-content>
    </ion-page>
  </template>
  
  <script>
  import axios from "axios";
  import {
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonList,
    IonItem,
    IonLabel,
    IonModal,
    IonText,
    IonButton,
    IonSearchbar,
  } from "@ionic/vue";
  
  export default {
    components: {
      IonPage,
      IonHeader,
      IonToolbar,
      IonTitle,
      IonContent,
      IonList,
      IonItem,
      IonLabel,
      IonModal,
      IonText,
      IonButton,
      IonSearchbar,
    },
    data() {
      return {
        muscle: "biceps",
        result: [],
        showModal: false,
        modalContent: {
          name: "",
          type: "",
          muscle: "",
          equipment: "",
          difficulty: "",
          instructions: "",
        },
        searchQuery: "",
        timer: null, // Variable to store the timer ID
      };
    },
    methods: {
      async fetchExercises() {
        try {
          const response = await axios.get(
            "https://api.api-ninjas.com/v1/exercises",
            {
              params: {
                muscle: this.muscle,
                search: this.searchQuery,
              },
              headers: {
                "X-Api-Key": "U0OnKPjeah92LGfFhPrvfg==nDCaAM6HSiWKYjkx",
              },
            }
          );
          this.result = response.data;
        } catch (e) {
          console.error("Error:", e.response.data);
        }
      },
      openModal(exercise) {
        this.modalContent = exercise;
        this.showModal = true;
      },
      searchExercises() {
        clearTimeout(this.timer); // Clear any previous timer
  
        // Set a new timer to delay the API request
        this.timer = setTimeout(() => {
          this.fetchExercises();
        }, 500); // Adjust the delay time (in milliseconds) as needed
      },
    },
    mounted() {
      this.fetchExercises();
    },
  };
  </script>
  
  <style scoped>
  .instruction-text {
    font-size: 1.2em;
    line-height: 1.6;
    text-align: justify;
    padding: 0 30px;
  }
  </style>
  