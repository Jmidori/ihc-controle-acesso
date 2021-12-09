<template>
  <div id="app">
    <nav-bar-component />
    <sidebar-component />
    <router-view/>
  </div>
</template>

<script>
  import NavBarComponent from "./components/NavBarComponent"
  import SidebarComponent from "./components/SidebarComponent"
  
  export default{
    components: {
      NavBarComponent,
      SidebarComponent
    }
  }
</script>

<style>
</style>

