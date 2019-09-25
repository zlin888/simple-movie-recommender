<template>
  <div class="container">
    <!-- 标题 -->
    <div class="main-title">
      <!-- icon -->
      <div>
        <img src="@/assets/top_charts_48.png" />
      </div>
      <!-- word -->
      <div style="margin-left:20px">Top Charts</div>
      <!-- refresh button -->
      <div style="margin-left:auto; margin-right: 300px">
        <img src="@/assets/refresh_36.png" />
      </div>
    </div>
    <el-divider></el-divider>
    <!-- 影片展示区 -->
    <div class="card-container">
      <movie-card v-for="movie in this.movies" :key="movie.idFilm" :movie="movie"></movie-card>
    </div>
  </div>
</template>

<script>
import { listTopNMovies } from "@/api/film";
import { formatRating } from "@/utils/parser.js";
import MovieCard from "@/components/MovieCard";

export default {
  name: "TopChartsPage",
  components: {
    "movie-card": MovieCard
  },
  data() {
    return {
      movies: []
    };
  },
  mounted: function() {
    listTopNMovies(this.$store.getters["user/id"], 8).then(
      response => {
        let recommendedMovies = response.data.data.films;
        formatRating(recommendedMovies);
        this.movies = recommendedMovies;
        console.log(this.movies);
      },
      error => {
        console.log(error);
      }
    );
  }
};
</script>

<style lang="css" scoped>
.container {
  padding: 30px;
}
.main-title {
  font-size: 2em;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
}

.main-title div {
  margin: auto 2px;
}
.card-container {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  flex-wrap: wrap;
}
.card-container > div {
  margin: 20px;
}
</style>