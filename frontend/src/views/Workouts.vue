<template>
  <ion-content>
    <ion-list>
      <ion-item v-for="session in trainingSessions" :key="session.id">
        <ion-label>
          Datum: {{ session.trainingDate }}
          <br />
          Dauer: {{ session.trainingDurationMinutes }} min
          <br />
          Wiederholungen: {{ session.rep }}
          <br />
          Übung: {{ session.exercise }}
        </ion-label>
      </ion-item>
    </ion-list>
  </ion-content>
</template>

<script lang="ts">
import { IonContent, IonList, IonItem, IonLabel } from "@ionic/vue";
import { defineComponent, ref, onMounted } from "vue";
import axios from "axios";

export default defineComponent({
  components: { IonContent, IonList, IonItem, IonLabel },
  setup() {
    // Erstellen Sie eine Inititalisierungsstruktur
    const sessionInit = {
      id: "",
      trainingDate: "",
      trainingDurationMinutes: 0,
      rep: 0,
      exercise: "",
    };

    // Fügen Sie Initialisierungsstruktur zur ref hinzu
    const trainingSessions = ref([sessionInit]);

    const fetchTrainingSessions = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/sessions", {
          withCredentials: true,
        });
        trainingSessions.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    onMounted(fetchTrainingSessions);

    return {
      trainingSessions,
    };
  },
});
</script>
