<template>
  <div>
    <b-container fluid>
      <Navbar></Navbar>
      <br>
      <b-container fluid>
        <b-card bg-variant="light">
          <b-container>
            <b-row>
              <b-col cols="6">
                <b-form-group horizontal
                              :label-cols="4"
                              label-size="sm"
                              label="产品&研发"
                              label-class="text-sm-right"
                              label-for="input_sm">
                  <b-form-select v-model="selected" :options="options" size="sm"/>
                </b-form-group>
              </b-col>
              <b-col cols="6">
                <b-button variant="primary" @click="doQuery">查询</b-button>
              </b-col>
            </b-row>
          </b-container>

        </b-card>
        <b-table hover striped bordered outlined small :busy.sync="isBusy" :items="myProvider"
                 :fields="fields" ref="myTable"></b-table>
      </b-container>

    </b-container>

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
        selected: null,
        options: [
          {value: null, text: "--- 请选择 ---"},
          {value: '0', text: '研发'},
          {value: '1', text: '产品'}
        ],
        fields: [
          {key: "name", label: "姓名"},
          {key: "count", label: "票数"}
        ],
        isBusy: false
      };

    },
    components: {Navbar},
    computed: mapState({user: state => state.user}), methods: {
      ...mapActions([USER_SIGNOUT]),
      gotoIndex: function () {
        this.$router.replace({path: '/'})
      }, doSignout: function () {
        this.USER_SIGNOUT()
        this.$router.replace({path: '/login'})
      },
      myProvider: function (ctx) {
        let promise = api.get('/queryPollCountList/' + this.selected);
        return promise.then((response) => {
          const items = response.data;
          console.log(items)
          return (items)
        }).catch(error => {
          return []
        })
      },
      doQuery: function () {
        this.$refs.myTable.refresh();
      }
    }
  }
</script>
<style scoped>

</style>
