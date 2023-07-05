<template>
  <ion-page>
    <ion-content>
      <ion-card>
        <ion-card-header>
          <ion-card-title class="ion-text-center"
            >Training Session</ion-card-title
          >
        </ion-card-header>

      <ion-card-content>
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
            <ion-label position="floating">Duration (minutes)</ion-label>
            <ion-input type="number" v-model="duration"></ion-input>
          </ion-item>

          <ion-item>
            <ion-label position="floating">Repetitions</ion-label>
            <ion-input type="number" v-model="repetitions"></ion-input>
          </ion-item>

          <ion-item>
            <ion-label>Exercise Name</ion-label>
            <ion-select v-model="exerciseName" interface="action-sheet">
              <ion-select-option
                v-for="exercise in exercises"
                :value="exercise"
                :key="exercise"
                >{{ exercise }}</ion-select-option
              >
            </ion-select>
          </ion-item>

          <ion-button expand="full" @click="postTrainingSession"
            >Submit</ion-button
          >
        </ion-card-content>
      </ion-card>
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
    const duration = ref(0);
    const repetitions = ref(0);
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
      "Biceps Curls",
      "Leg Press",
    ]);

    const postTrainingSession = async () => {
      const trainingSession = {
        trainingDate: trainingDate.value,
        trainingDurationMinutes: duration.value,
        rep: repetitions.value,
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
      duration,
      repetitions,
      exerciseName,
      exercises,
      postTrainingSession,
    };
  },
});
</script>

<style scoped>
ion-content {
  --ion-background-color: #f4f4f4;
}

ion-card {
  max-width: 400px;
  margin: 0 auto;
}

ion-button[expand="full"] {
  margin-top: 20px;
}
</style>
