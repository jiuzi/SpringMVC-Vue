<style scoped>

</style>
<template>
  <router-view></router-view>
</template>
<script>
  export default {
    "$route": function () {
      console.log("chanage")
    }
  }
</script>
