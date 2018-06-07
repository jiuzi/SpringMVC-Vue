<template>
  <div>
    <b-navbar toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand href="#">同事眼中的你</b-navbar-brand>
      <b-collapse is-nav id="nav_collapse">

        <b-navbar-nav>
          <b-nav-item href="#" @click="gotoIndex">主页</b-nav-item>
          <b-nav-item href="#" @click="gotoReport">报表</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <!-- Using button-content slot -->
            <template slot="button-content">
              {{user.userName}}
            </template>
            <b-dropdown-item href="#" @click="myPraise(user)">荣誉</b-dropdown-item>
            <b-dropdown-item href="#" v-on:click="doSignout">退出</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>

      </b-collapse>
    </b-navbar>


    <div>
      <!-- Modal Component -->
      <b-modal ref="praiseDetailRef" hide-header ok-only centered ok-title="返回">
        <b-list-group v-for="poll of praiseList" :key="poll.userName">

          <b-list-group-item href="#" disabled class="flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1">{{poll.userName}} 说：</h5>
            </div>
            <p class="mb-1">
              {{poll.msg}}
            </p>
          </b-list-group-item>
        </b-list-group>
      </b-modal>

    </div>
  </div>
</template>

<script>
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap-vue/dist/bootstrap-vue.css'
  import Vue from 'vue'
  import BootstrapVue from 'bootstrap-vue'
  import {mapActions, mapState} from 'vuex'
  import {USER_SIGNOUT} from '../store/user'
  import api from "../service/api"

  Vue.use(BootstrapVue);
  export default {
    name: "p-navbar",
    data() {
      return {praiseList: []}
    },
    computed: mapState({user: state => state.user}),
    methods: {
      ...mapActions([USER_SIGNOUT]),
      doSignout: function () {
        this.USER_SIGNOUT()
        this.$router.replace({path: '/login'})
      }, gotoReport: function () {
        this.$router.replace({path: '/report'})
      },
      gotoIndex: function () {
        this.$router.replace({path: '/'})
      },
      myPraise: function (user) {
        const that = this;
        api.get("/queryAllPollInfoList/" + user.erp).then((response) => {
          let praiseList = response.data;
          if (!praiseList || praiseList.length <= 0) {
            alert("还没有人赞美~");
          } else {
            that.praiseList = praiseList;
            that.$refs.praiseDetailRef.show();
          }
        }, (response) => {
          //失败回调
        });

      }
    }
  }
</script>

<style scoped>

</style>
