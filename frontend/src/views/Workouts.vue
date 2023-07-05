<template>
  <ion-page>
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
            <br />
            Letzte Dauer-Differenz: {{ getLastDurationDifference(session) }} min
            <br />
            Letzte Wiederholungs-Differenz: {{ getLastRepDifference(session) }}
          </ion-label>
        </ion-item>
      </ion-list>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonContent, IonList, IonItem, IonLabel, IonPage } from "@ionic/vue";
import { defineComponent, ref, onMounted } from "vue";
import axios from "axios";

interface TrainingSession {
  id: string;
  trainingDate: string;
  trainingDurationMinutes: number;
  rep: number;
  exercise: string;
}

export default defineComponent({
  components: { IonContent, IonList, IonItem, IonLabel, IonPage },
  setup() {
    const sessionInit: TrainingSession = {
      id: "",
      trainingDate: "",
      trainingDurationMinutes: 0,
      rep: 0,
      exercise: "",
    };

    const trainingSessions = ref<TrainingSession[]>([sessionInit]);

    const fetchTrainingSessions = async () => {
      try {
        const response = await axios.get<TrainingSession[]>(
          "http://localhost:8080/api/sessions",
          {
            withCredentials: true,
          }
        );
        trainingSessions.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const getLastDurationDifference = (session: TrainingSession) => {
      const sessions = trainingSessions.value;
      const exerciseSessions = sessions.filter(
        (s) => s.exercise === session.exercise
      );

      if (exerciseSessions.length >= 2) {
        const currentSessionIndex = exerciseSessions.findIndex(
          (s) => s.id === session.id
        );
        if (currentSessionIndex > 0) {
          const previousSession = exerciseSessions[currentSessionIndex - 1];
          if (previousSession.exercise === session.exercise) {
            return (
              session.trainingDurationMinutes -
              previousSession.trainingDurationMinutes
            );
          }
        }
      }

      return null;
    };

    const getLastRepDifference = (session: TrainingSession) => {
      const sessions = trainingSessions.value;
      const exerciseSessions = sessions.filter(
        (s) => s.exercise === session.exercise
      );

      if (exerciseSessions.length >= 2) {
        const currentSessionIndex = exerciseSessions.findIndex(
          (s) => s.id === session.id
        );
        if (currentSessionIndex > 0) {
          const previousSession = exerciseSessions[currentSessionIndex - 1];
          if (previousSession.exercise === session.exercise) {
            return session.rep - previousSession.rep;
          }
        }
      }

      return null;
    };

    onMounted(fetchTrainingSessions);

    return {
      trainingSessions,
      getLastDurationDifference,
      getLastRepDifference,
    };
  },
});
</script>