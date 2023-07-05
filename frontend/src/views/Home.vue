<template>
  <ion-page class="full-page">
    <ion-content class="full-content">
      <div class="form-container">
        <ion-card>
          <ion-card-header>
            <ion-card-title class="ion-text-center"
              >Training Session</ion-card-title
            >
          </ion-card-header>

          <ion-card-content>
            <img src="/logo.png" alt="ALLIN Logo" class="logo-image" />
            <ion-item>
              <ion-label position="floating">Training Date</ion-label>
              <br />
              <ion-button v-if="!showDatePicker" @click="showDatePicker = true">
                Wähle Datum
              </ion-button>
              <ion-datetime
                v-else
                v-model="trainingDate"
                display-format="DD-MMM-YYYY"
                placeholder="Select Date"
              ></ion-datetime>
            </ion-item>

            <ion-item>
              <ion-label>Exercise Name</ion-label>
              <ion-select
                v-model="exerciseName"
                interface="action-sheet"
                class="custom-select"
              >
                <ion-select-option
                  v-for="exercise in exercises"
                  :value="exercise"
                  :key="exercise"
                  >{{ exercise }}</ion-select-option
                >
              </ion-select>
            </ion-item>

            <ion-item>
              <ion-label position="floating">Weights</ion-label>
              <ion-input type="number" v-model="weights"></ion-input>
            </ion-item>

            <ion-item>
              <ion-label position="floating">Repetitions</ion-label>
              <ion-input type="number" v-model="reps"></ion-input>
            </ion-item>

            <ion-item>
              <ion-label position="floating">Sets</ion-label>
              <ion-input type="number" v-model="sets"></ion-input>
            </ion-item>

            <ion-button expand="full" @click="postTrainingSession"
              >Submit</ion-button
            >
          </ion-card-content>
        </ion-card>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonContent,
  IonDatetime,
  IonItem,
  IonLabel,
  IonInput,
  IonSelect,
  IonSelectOption,
  IonButton,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
} from "@ionic/vue";
import { defineComponent, ref } from "vue";
import axios from "axios";

export default defineComponent({
  components: {
    IonPage,
    IonContent,
    IonDatetime,
    IonItem,
    IonLabel,
    IonInput,
    IonSelect,
    IonSelectOption,
    IonButton,
    IonCard,
    IonCardHeader,
    IonCardTitle,
    IonCardContent,
  },
  setup() {
    const trainingDate = ref("");
    const showDatePicker = ref(false);
    const weights = ref(0);
    const reps = ref(0);
    const sets = ref(0);
    const exerciseName = ref("");
    const exercises = ref([
      "Bench Press",
      "Incline Press",
      "Dead Lift",
      "Squats",
      "Leg Curl",
      "Dips",
      "Leg Extension",
      "Push Up",
      "Chest Press",
      "Barbell Row",
      "Pull Up",
      "Shoulder Press",
      "Latral Row",
      "Wide Pull Down",
      "Hip Thrust",
      "Calf Raise",
      "Plank",
      "Crunches",
      "Russian Twist",
    ]);

    const postTrainingSession = async () => {
      const trainingSession = {
        trainingDate: trainingDate.value,
        weights: weights.value,
        reps: reps.value,
        sets: sets.value,
        exercise: exerciseName.value,
      };

      const config = {
        withCredentials: true,
      };
      try {
        const response = await axios.post(
          "http://localhost:8080/api/sessions",
          trainingSession,
          config
        );
        console.log(response);
      } catch (error) {
        console.error(error);
      }
    };

    return {
      trainingDate,
      showDatePicker,
      weights,
      reps,
      sets,
      exerciseName,
      exercises,
      postTrainingSession,
      //test
    };
  },
});
</script>

<style scoped>
ion-content.full-content {
  --ion-background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.full-page {
  background-color: black;
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

ion-select.custom-select {
  --ion-select-indicator-margin-end: 10px; /* Adjust the margin value as needed */
}
</style>