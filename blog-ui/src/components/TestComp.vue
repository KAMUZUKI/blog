<template>
	<MenuComponent :showContentByCategory="showContentByCategory"></MenuComponent>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import MenuComponent from './Menu/MenuComponent.vue';
const transContent = ref()
const refresh = ref(true)
const showContentByCategory = (type) => {
    transContent.value.changeContenByCategory(type)
    refresh.value = !refresh.value
    refresh.value = !refresh.value
}   
</script>