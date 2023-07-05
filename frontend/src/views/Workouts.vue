<template>
  <ion-page>
    <ion-content>
      <ion-list>
        <ion-item v-for="session in trainingSessions" :key="session.id">
          <ion-card>
            <ion-card-header>
              <ion-card-title>{{ session.exercise }}</ion-card-title>
              <ion-card-subtitle
                >Datum: {{ session.trainingDate }}</ion-card-subtitle
              >
            </ion-card-header>

            <ion-card-content>
              <div>
                Dauer:
                <ion-badge color="primary"
                  >{{ session.trainingDurationMinutes }} min</ion-badge
                >
              </div>
              <div>
                Wiederholungen:
                <ion-badge color="secondary">{{ session.rep }}</ion-badge>
              </div>
              <div>
                Letzte Dauer-Differenz:
                <ion-badge color="success"
                  >{{ getLastDurationDifference(session) }} min</ion-badge
                >
              </div>
              <div>
                Letzte Wiederholungs-Differenz:
                <ion-badge color="danger">{{
                  getLastRepDifference(session)
                }}</ion-badge>
              </div>
            </ion-card-content>
          </ion-card>
        </ion-item>
      </ion-list>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonPage,
  IonContent,
  IonList,
  IonItem,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
  IonCardContent,
  IonBadge,
} from "@ionic/vue";
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
  components: {
    IonPage,
    IonContent,
    IonList,
    IonItem,
    IonCard,
    IonCardHeader,
    IonCardTitle,
    IonCardSubtitle,
    IonCardContent,
    IonBadge,
  },
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
