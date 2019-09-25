<template>
  <div>
    <el-menu
      class="el-menu-demo align-start"
      mode="horizontal"
      active-text-color="red"
      @select="handleSelect"
    >
      <el-menu-item index="1">For You</el-menu-item>
      <el-menu-item index="2">Browse</el-menu-item>
      <el-menu-item index="3">Top Charts</el-menu-item>
      <el-submenu index="4" class="justify-end">
        <template slot="title">
          <span class="user-name">{{this.$store.getters['user/name']}}</span>
        </template>
        <el-menu-item index="4-1">
          <i class="el-icon-back" style="font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;">Log out</i>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    handleSelect(key, keyPath) {
      if (key === "1") {
        console.log("push to 1");
        this.$router.push({ path: "/home/foryou" });
      } else if (key === "2") {
        console.log("push to 2");
        this.$router.push({ path: "/home/browse" });
      } else if (key == "3"){
        console.log("push to 3");
        this.$router.push({ path: "/home/topcharts" });
      } else if (key == "4-1"){
        console.log("push to 4-1")
        this.$router.push({ path: "/login" });
      }
    }
  }
};
</script>

<style lang="css" scoped>
.nav-bar-icon {
  margin-left: 20px;
  margin-bottom: 10px;
}

.align-start {
  display: flex;
  justify-content: flex-start;
}

.justify-end {
  margin-left: auto;
}

.user-name {
  margin-left: 10px;
}
</style>

