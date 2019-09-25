<template>
  <div class="container">
    <!-- 标题 -->
    <div class="main-title">
      <!-- icon -->
      <div>
        <img src="@/assets/browse_48.png" />
      </div>
      <!-- word -->
      <div style="margin-left:20px">Browse</div>
      <!-- refresh button -->
      <div style="margin-left:auto; margin-right: 300px">
        <img src="@/assets/refresh_36.png" />
      </div>
    </div>
    <el-divider></el-divider>
    <!-- 搜索框 -->
    <div style="width: 50%">
      <el-input
        placeholder="Please enter the name of movie"
        prefix-icon="el-icon-search"
        v-model="searchText"
      ></el-input>
    </div>

    <!-- 影片展示区 -->
    <div class="card-container">
      <movie-card
        v-for="movie in this.movies.filter(movie => !searchText || movie.title.toLowerCase().includes(searchText.toLowerCase()))"
        :key="movie.idFilm"
        :movie="movie"
      ></movie-card>
    </div>
  </div>
</template>

<script>
import MovieCard from "@/components/MovieCard";
import { formatRating } from "@/utils/parser.js";
import { listAllMovies } from "@/api/film";

export default {
  name: "BrowsePage",
  components: {
    "movie-card": MovieCard
  },
  data() {
    return {
      movies: [],
      searchText: ""
    };
  },
  mounted: function() {
    listAllMovies().then(
      response => {
        let allMovies = response.data.data.films;
        formatRating(allMovies);
        this.movies = allMovies;
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