<template>
  <ion-page>
    <ion-content>
      <ion-toolbar>
        <ion-searchbar v-model="filterExercise" placeholder="Search Exercise"></ion-searchbar>
      </ion-toolbar>
      <ion-list>
        <ion-item v-for="session in filteredTrainingSessions" :key="session.id">
          <ion-card>
            <ion-card-header>
              <ion-card-title>{{ session.exercise }}</ion-card-title>
              <ion-card-subtitle>Date: {{ session.trainingDate }}</ion-card-subtitle>
            </ion-card-header>

            <ion-card-content>
              <div>
                Weight:
                <ion-badge color="primary">{{ session.weights }} kg</ion-badge>
              </div>
              <div>
                Repetitions:
                <ion-badge color="secondary">{{ session.reps }}</ion-badge>
              </div>
              <div>
                Sets:
                <ion-badge color="tertiary">{{ session.sets }}</ion-badge>
              </div>
              <div>
                Volume Progress:
                <ion-badge color="success">{{ getLastWeightsDifference(session) }} kg</ion-badge>
              </div>
              <div>
                Repetitions Progress:
                <ion-badge color="danger">{{ getLastRepsDifference(session) }}</ion-badge>
              </div>
              <div>
                Sets Progress:
                <ion-badge color="warning">{{ getLastSetsDifference(session) }}</ion-badge>
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
  IonToolbar,
  IonSearchbar,
  IonList,
  IonItem,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
  IonCardContent,
  IonBadge,
} from "@ionic/vue";
import { defineComponent, ref, computed, onMounted } from "vue";
import axios from "axios";

interface TrainingSession {
  id: string;
  trainingDate: string;
  weights: number;
  reps: number;
  sets: number;
  exercise: string;
}

export default defineComponent({
  components: {
    IonPage,
    IonContent,
    IonToolbar,
    IonSearchbar,
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
      weights: 0,
      reps: 0,
      sets: 0,
      exercise: "",
    };

    const trainingSessions = ref<TrainingSession[]>([sessionInit]);

    const fetchTrainingSessions = async () => {
      try {
        const response = await axios.get<TrainingSession[]>("http://localhost:8080/api/sessions", {
          withCredentials: true,
        });
        trainingSessions.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const getLastWeightsDifference = (session: TrainingSession) => {
      const sessions = trainingSessions.value;
      const exerciseSessions = sessions.filter((s) => s.exercise === session.exercise);

      if (exerciseSessions.length >= 2) {
        const currentSessionIndex = exerciseSessions.findIndex((s) => s.id === session.id);
        if (currentSessionIndex > 0) {
          const previousSession = exerciseSessions[currentSessionIndex - 1];
          if (previousSession.exercise === session.exercise) {
            return session.weights - previousSession.weights;
          }
        }
      }

      return null;
    };

    const getLastRepsDifference = (session: TrainingSession) => {
      const sessions = trainingSessions.value;
      const exerciseSessions = sessions.filter((s) => s.exercise === session.exercise);

      if (exerciseSessions.length >= 2) {
        const currentSessionIndex = exerciseSessions.findIndex((s) => s.id === session.id);
        if (currentSessionIndex > 0) {
          const previousSession = exerciseSessions[currentSessionIndex - 1];
          if (previousSession.exercise === session.exercise) {
            return session.reps - previousSession.reps;
          }
        }
      }

      return null;
    };

    const getLastSetsDifference = (session: TrainingSession) => {
      const sessions = trainingSessions.value;
      const exerciseSessions = sessions.filter((s) => s.exercise === session.exercise);

      if (exerciseSessions.length >= 2) {
        const currentSessionIndex = exerciseSessions.findIndex((s) => s.id === session.id);
        if (currentSessionIndex > 0) {
          const previousSession = exerciseSessions[currentSessionIndex - 1];
          if (previousSession.exercise === session.exercise) {
            return session.sets - previousSession.sets;
          }
        }
      }

      return null;
    };

    const filterExercise = ref("");

    const filteredTrainingSessions = computed(() => {
      const filter = filterExercise.value.toLowerCase();
      return trainingSessions.value.filter((session) =>
        session.exercise.toLowerCase().includes(filter)
      );
    });

    onMounted(fetchTrainingSessions);

    return {
      trainingSessions,
      getLastWeightsDifference,
      getLastRepsDifference,
      getLastSetsDifference,
      filterExercise,
      filteredTrainingSessions,
    };
  },
});
</script>