<template>
  <ion-content>
    <ion-item>
      <ion-label position="floating">Training Date</ion-label>
      <br>
      <ion-button v-if="!showDatePicker" @click="showDatePicker = true">Wähle Datum</ion-button>
      <ion-datetime v-else v-model="trainingDate" display-format="D MMM YYYY" placeholder="Select Date"></ion-datetime>
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
      <ion-select v-model="exerciseName">
        <ion-select-option v-for="exercise in exercises" :value="exercise" :key="exercise">{{ exercise }}</ion-select-option>
      </ion-select>
    </ion-item>

    <ion-button @click="postTrainingSession">Submit</ion-button>
  </ion-content>
</template>


<script lang="ts">
import { IonContent, IonDatetime, IonItem, IonLabel, IonInput, IonSelect, IonSelectOption, IonButton } from "@ionic/vue";
import { defineComponent, ref } from "vue";
import axios from 'axios';  // Importieren von Axios

export default defineComponent({
  components: {
    IonContent,
    IonDatetime,
    IonItem,
    IonLabel,
    IonInput,
    IonSelect,
    IonSelectOption,
    IonButton
  },
  setup() {
    const trainingDate = ref(null);
    const showDatePicker = ref(false);
    const duration = ref(0);
    const repetitions = ref(0);
    const exerciseName = ref('');
    const exercises = ref([
      'Übung 1',
      'Übung 2',
      'Übung 3',
      // ...fügen Sie hier mehr Übungen hinzu
    ]);

    const postTrainingSession = async () => {
      const trainingSession = {
        trainingDate: trainingDate.value,
        trainingDurationMinutes: duration.value,
        rep: repetitions.value,
        excercises: [exerciseName.value] // oder wie auch immer Sie es formatieren möchten
      };

      try {
        const response = await axios.post('http://localhost:8080/api/sessions', trainingSession);
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
      postTrainingSession // Sie können diese Funktion dann verwenden, um die aktuelle Trainingssession zu posten
    };
  },
});
</script>