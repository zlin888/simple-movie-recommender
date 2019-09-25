<template>
  <div>
    <el-card class="login-form-layout">
      <el-form autocomplete="on" :model="loginForm" ref="loginForm" label-position="left">
        <div style="text-align: center">
          <!-- <img src="" /> -->
        </div>
        <h2 class="login-title color-main">登录</h2>
        <el-form-item>
          <el-input
            name="userId"
            type="text"
            v-model="loginForm.userId"
            autocomplete="on"
            placeholder="请输入用户ID"
            clearable
          >
            <span slot="prefix">
              <i class="el-icon-user-solid"></i>
            </span>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            name="password"
            :type="pwdType"
            @keyup.enter.native="handleLogin"
            v-model="loginForm.password"
            autocomplete="off"
            placeholder="请输入密码"
            clearable
          >
            <span slot="prefix">
              <i class="el-icon-key"></i>
            </span>
            <span slot="suffix" @click="showPwd">
              <i class="el-icon-view"></i>
            </span>
          </el-input>
        </el-form-item>
        <p class="alertInfo">{{alertInfo}}</p>
        <el-form-item style="margin-bottom: 60px">
          <el-button
            style="width: 100%"
            type="danger"
            :loading="loading"
            @click.native.prevent="handleLogin"
          >登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- <img :src="login_center_bg" class="login-center-layout" /> -->
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error("密码不能小于3位"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        userId: "",
        passWord: "123456"
      },
      loading: false,
      pwdType: "password",
      alertInfo: ""
    };
  },
  methods: {
    showPwd() {
      if (this.pwdType === "password") {
        this.pwdType = "";
      } else {
        this.pwdType = "password";
      }
    },
    handleLogin() {
      this.loading = true;
      this.loginForm.userId = Number(this.loginForm.userId);
      this.$store
        .dispatch("user/login", this.loginForm)
        .finally(() => {
          this.loading = false;
        })
        .then(
          result => {
            this.loading = false;
            this.$router.push({ path: "/home/foryou" });
          },
          error => {
            console.log(error);
          }
        );
    }
  }
};
</script>

<style scoped>
.login-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 360px;
  margin: 140px auto;
  border-top: 10px solid #ff7875;
}

.login-title {
  text-align: center;
}

.login-center-layout {
  background: #ff7875;
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  margin-top: 200px;
}
</style>