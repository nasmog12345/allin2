<template>
    <ion-page class="full-page">
      <ion-content class="full-content">
        <div class="form-container">
          <ion-card>
            <ion-card-header>
              <ion-card-title class="ion-text-center">Weight Tracker</ion-card-title>
            </ion-card-header>
  
            <ion-card-content>
              <img src="/logo.png" alt="ALLIN Logo" class="logo-image" />
              <ion-item>
                <ion-label position="floating">Weight Date</ion-label>
                <br />
                <ion-button v-if="!showDatePicker" @click="showDatePicker = true">
                  Choose Date
                </ion-button>
                <ion-datetime
                  v-else
                  v-model="weightDate"
                  display-format="YYYY-MM-DD, HH:mm:ss"
                  picker-format="YYYY-MM-DDTHH:mm:ss"
                  placeholder="Select Date"
                ></ion-datetime>
              </ion-item>
  
              <ion-item>
                <ion-label position="floating">Weight</ion-label>
                <ion-input type="number" v-model="weight"></ion-input>
              </ion-item>
  
              <ion-button expand="full" @click="postWeight">Submit</ion-button>
              <ion-toast
                :is-open="showToast"
                :message="toastMessage"
                position="middle"
                :duration="3000"
              ></ion-toast>
            </ion-card-content>
          </ion-card>
  
          <table v-if="weights.length > 0" class="weight-table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Weight</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="weight in weights" :key="weight.id">
                <td>{{ formatDate(weight.weightDate) }}</td>
                <td>{{ weight.weight }}</td>
              </tr>
            </tbody>
          </table>
          <div v-else class="no-data-message">No weight data available.</div>
        </div>
      </ion-content>
    </ion-page>
  </template>
  
  <script>
  import {
    IonPage,
    IonContent,
    IonDatetime,
    IonItem,
    IonLabel,
    IonInput,
    IonButton,
    IonCard,
    IonCardHeader,
    IonCardTitle,
    IonCardContent,
    IonToast,
  } from "@ionic/vue";
  import { defineComponent, ref, onMounted } from "vue";
  import axios from "axios";
  
  export default defineComponent({
    components: {
      IonPage,
      IonContent,
      IonDatetime,
      IonItem,
      IonLabel,
      IonInput,
      IonButton,
      IonCard,
      IonCardHeader,
      IonCardTitle,
      IonCardContent,
      IonToast,
    },
    setup() {
      const weightDate = ref("");
      const showDatePicker = ref(false);
      const weight = ref(0);
  
      const showToast = ref(false);
      const toastMessage = ref("");
  
      const weights = ref([]);
  
      const postWeight = async () => {
        const weightEntry = {
          weightDate: weightDate.value,
          weight: weight.value,
        };
  
        const config = {
          withCredentials: true,
        };
  
        try {
          const response = await axios.post(
            "http://localhost:8080/api/weight",
            weightEntry,
            config
          );
          console.log(response);
  
          showToast.value = true;
          toastMessage.value = "Weight created successfully";
          fetchWeights();
        } catch (error) {
          console.error(error);
        }
      };
  
      const fetchWeights = async () => {
        try {
          const response = await axios.get("http://localhost:8080/api/weight", {
            withCredentials: true,
          });
          weights.value = response.data;
        } catch (error) {
          console.error(error);
        }
      };
  
      const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleString("en-US", {
          year: "numeric",
          month: "2-digit",
          day: "2-digit",
          hour: "2-digit",
          minute: "2-digit",
          second: "2-digit",
        });
      };
  
      onMounted(fetchWeights);
  
      return {
        weightDate,
        showDatePicker,
        weight,
        showToast,
        toastMessage,
        weights,
        postWeight,
        formatDate,
      };
    },
  });
  </script>
  
  <style scoped>
  .full-page {
    background-color: black;
  }
  
  .full-content {
    --ion-background-color: black;
  }
  
  .form-container {
    max-width: 400px;
    margin: auto;
  }
  
  .logo-image {
    display: block;
    margin: 0 auto;
    max-width: 150px;
    max-height: 150px;
    object-fit: contain;
  }
  
  .weight-table {
    width: 100%;
    color: white;
    border-collapse: collapse;
  }
  
  .weight-table th,
  .weight-table td {
    padding: 10px;
    border: 1px solid white;
  }
  
  .no-data-message {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
    color: white;
    font-size: 16px;
  }
  </style>