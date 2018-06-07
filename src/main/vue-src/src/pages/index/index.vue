<template>
  <div>

    <b-container fluid>
      <Navbar></Navbar>
      <br>
      <b-container fluid>
        <b-row v-for="(value,key,index) in userInfoMap" :key="key">
          <b-col v-for="(voter,key,index) of value" :key="key" cols="3">
            <b-card-group>
              <b-card fluid no-body
                      v-bind:img-src="voter.photoUrl"
                      v-bind:img-alt="voter.userName">
                <div slot="footer">
                  <h5 v-if="voter.role == 0"> <b-badge  style="float: left" v-bind="voter"  v-on:click="showPraise(voter)"  variant="primary">{{voter.userName}} </b-badge> </h5>
                  <h5 v-if="voter.role == 1"> <b-badge  style="float: left" v-bind="voter"  v-on:click="showPraise(voter)"  variant="warning">{{voter.userName}} </b-badge> </h5>

                  <h5 > <b-badge  style="float: right" v-bind="voter"  v-on:click="showModal(voter)"  variant="danger">赞 {{voter.count}} </b-badge> </h5>

                </div>
              </b-card>
            </b-card-group>
          </b-col>
          <br>
        </b-row>
      </b-container>

    </b-container>

    <div>
      <!-- Modal Component -->
      <b-modal ref="myModalRef" cancel-title="再想想" ok-title="提交" v-bind:voterInfo="voterInfo" :title="voterInfo.title"
               @ok="handleOk">
        <div class="d-block text-center">
          <b-form-textarea id="textarea1"
                           v-model="voterInfo.msg"
                           placeholder="开始你的赞美吧！"
                           :rows="3"
                           :max-rows="6">
          </b-form-textarea>
        </div>
      </b-modal>

    </div>

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
  import {USER_SIGNOUT} from '../../store/user'
  import Navbar from "../../components/navbar"
  import api from "../../service/api"
  /* Register component with one of 2 methods */

  Vue.use(BootstrapVue);


  export default {
    data() {
      return {
        userInfoMap: {},
        voterInfo: {
          voter: {},
          msg: "",
          title: ""
        },
        praiseList: [{
          pollInfo: {
            userName: "",
            voterName: "",
            msg: ""
          }
        }]
      }
    },
    components: {Navbar},
    created() {
      var that = this;
      const userInfo = this.$store.state.user;
      api.get("/getAllUserInfoList/" + userInfo.erp).then((response) => {
        const userList = response.data;
        let userInfoMap = {};
        let colomns = 4;
        let row = 0;
        for (let i = 0, len = userList.length; i < len; i += colomns) {
          row += 1;
          userInfoMap[row] = userList.slice(i, i + colomns)

        }
        that.userInfoMap = userInfoMap;

      }, (response) => {
        //失败回调
      });
    },
    mounted() {
      //组件开始挂载时获取用户信息
      this.checkLogin();
    },
    computed: mapState({user: state => state.user}),
    methods: {
      ...mapActions([USER_SIGNOUT]),
      showPraise: function (voter) {
        console.log(voter)
        const that = this;
        api.get("/queryAllPollInfoList/" + voter.erp).then((response) => {
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


      },
      gotoReport: function () {
        this.$router.replace({path: '/report'})
      },
      doSignout: function () {
        this.USER_SIGNOUT()
        this.$router.replace({path: '/login'})
      },
      checkLogin: function () {
        const userInfo = this.$store.state.user;
        if (!userInfo || !userInfo.erp) {
          this.$router.replace({path: '/login'})
        }
      },
      showModal: function (voter) {
        this.voterInfo = {"voter": voter, "title": "你想对 " + voter.userName + " 说：", "msg": ""};
        this.$refs.myModalRef.show();
      },
      handleOk: function (evt) {
        evt.preventDefault()
        if (this.voterInfo.msg) {
          this.handleSubmit();
        } else {
          alert("请输入你的赞美!");
        }

      },
      handleSubmit: function () {
        const userInfo = this.$store.state.user;
        const voterInfo = this.voterInfo;

        api.request({
          url: "/savePollInfo",
          method: "post",
          params: {
            "userErp": userInfo.erp,
            "userName": userInfo.userName,
            "voterErp": voterInfo.voter.erp,
            "voterName": voterInfo.voter.userName,
            "msg": voterInfo.msg
          }
        }).then((response) => {
          const result = response.data;
          if (result && result.resultCode === "1") {
            alert(result.message);
            this.$refs.myModalRef.hide()
          } else {
            alert(result.message);
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
