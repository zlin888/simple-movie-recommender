<template>
  <el-container class="container">
    <el-header class="container">
      <nav-bar></nav-bar>
    </el-header>
    <el-main class="container">
      <router-view />
    </el-main>
  </el-container>
</template>


<script>
import NavBar from "@/components/NavBar";

export default {
  name: "Home",
  components: {
    "nav-bar": NavBar
  }
};
</script>

<style lang="css" scoped>
.container {
  padding: 0px;
  margin: 0px;
  min-height: 100%;
  min-width: 100%;
}
</style>