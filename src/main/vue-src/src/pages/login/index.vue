<style scoped>

</style>
<template>


  <div class="mx-auto" style="width:300px;margin-top: 320px;">

    <form class="login" v-on:submit.prevent="submit">
      <b-input-group>
        <b-form-input id="exampleInput1"
                      type="text"
                      v-model="userInfo.erp"
                      required
                      placeholder="ERP">
        </b-form-input>
        <b-input-group-append>
          <b-btn type="submit" variant="primary">Submit</b-btn>
        </b-input-group-append>
      </b-input-group>
    </form>

  </div>
</template>
<script>
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap-vue/dist/bootstrap-vue.css'
  import Vue from 'vue'
  import BootstrapVue from 'bootstrap-vue'
  import {mapActions} from 'vuex'
  import {USER_SIGNIN} from '../../store/user'
  import api from "../../service/api"

  Vue.use(BootstrapVue);
  export default {
    data() {
      return {
        btn: false, //true 已经提交过， false没有提交过
        userInfo: {
          "erp": "",
          "userName": "",
          "password": ""
        }
      }
    },
    methods: {
      ...mapActions([USER_SIGNIN]),
      submit() {
        this.btn = true
        if (!this.userInfo.erp) return;

        var that = this;
        api.get("/getUserInfoByErp/" + this.userInfo.erp).then((response) => {
          console.log(response.data);//成功回调
          if (response.data) {
            that.userInfo = response.data;
            that.USER_SIGNIN(this.userInfo);
            that.$router.replace({path: '/'})
          }
        }, (response) => {
          //失败回调
        });

      }

    }
  }
</script>
