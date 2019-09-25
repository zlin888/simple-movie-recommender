<template>
  <div>
    <el-card class="movie-card" :body-style="{ padding: '0px'}">
      <img :src="movie.poster" class="image" />
      <div style="padding: 5px 14px;">
        <span>{{movie.title}}</span>
        <span style="color:#8c8c8c">({{movie.yr}})</span>
      </div>
      <div style="padding: 7px 14px; height: 20px">
        <span style="color:#8c8c8c;font-size: 12px">Score:</span>
        <el-rate
          v-model="movie.avgRating"
          disabled
          show-score
          text-color="#ff9900"
          style="display:inline"
          score-template="{value}"
        ></el-rate>
      </div>
      <div style="padding: 7px 14px;">
        <el-button type="text" @click="handleOpenRateDialog">Rate it</el-button>
      </div>
    </el-card>
    <el-dialog :visible.sync="rateDialogVisible" width="30%" :before-close="handleClose">
      <div style="padding: 7px 14px;">
        <span>Your Rate of {{movie.title}}</span>
      </div>
      <div style="padding: 7px 14px;">
        <el-rate v-model="personalRating" show-score></el-rate>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="rateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import { rateFilm, getUserRating } from "@/api/rating";
import { successDialog, fialDialog } from "@/utils/notification";

export default {
  name: "MovieCard",
  props: {
    movie: Object
  },
  data() {
    return {
      rateDialogVisible: false,
      personalRating: 0
    };
  },
  methods: {
    handleOpenRateDialog() {
      this.rateDialogVisible = true;
      getUserRating(this.$store.getters["user/id"], this.movie.idFilm).then(
        response => {
          this.personalRating = response.data.data.rating;
        },
        error => {
          this.personalRating = 0;
        }
      );
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    handleConfirm() {
      rateFilm(
        this.$store.getters["user/id"],
        this.movie.idFilm,
        this.personalRating.toFixed(1)
      ).then(
        response => {
          this.personalRating = 0;
          console.log(response.data.data);
          successDialog("Thank you! Your rate has been recorded!");
        },
        error => {
          console.log(error);
        }
      );
      this.rateDialogVisible = false;
    }
  }
};
</script>


<style lang="css" scoped>
.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.movie-card {
  width: 250px;
}
</style>