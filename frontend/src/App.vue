<template>
  <div class="app">
    <header class="header">
      AIREST
    </header>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style scoped>
.app {
  background-image: url('@/assets/images/background.jpg');
  background-size: cover;
  background-position: center;
  min-height: 100vh;
}

.header {
  background-color: #007aaf;
  color: white;
  padding: 15px 0;
  text-align: center;
  font-size: 24px;
  font-weight: bold;
}
</style>
