<template>
  <ion-list>
    <ion-item v-for="trainingSession in trainingSessions" :key="trainingSession.id">
      <ion-label>{{ trainingSession.exercise }}</ion-label>
    </ion-item>
  </ion-list>
</template>

<script lang="ts">
import { IonItem, IonLabel, IonList } from "@ionic/vue";
import { defineComponent, ref, onMounted } from "vue";
import axios from 'axios';

export default defineComponent({
  components: { IonItem, IonLabel, IonList },
  setup() {
    const trainingSessions = ref([]);

    const fetchTrainingSessions = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/sessions');
        trainingSessions.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    onMounted(fetchTrainingSessions);

    return {
      trainingSessions
    };
  },
});
</script>
