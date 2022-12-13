<template>
  <LoginForm v-if="!avatarVisible" :showDrawer="showDrawer" :showAvatar="showAvatar" style="width:100%">
  </LoginForm>
  <a-drawer placement="left" :closable="false" :visible="visible" :get-container="true"
    :style="{ position: 'absolute' }" @close="onClose">
    <RegisterComponent :onClose="onClose"></RegisterComponent>
  </a-drawer>
</template>
<script>
import { defineComponent, ref } from 'vue';
// import { notification } from 'ant-design-vue';
import LoginForm from './Login/LoginForm.vue';
import RegisterComponent from './Register/RegisterComponent.vue';
// import AvatarComponent from './tools/AvatarComponent.vue';
export default defineComponent({
  name: 'DrawerComponent',
  setup() {
    const visible = ref(false);
    // const avatarVisible = ref(false);

    const afterVisibleChange = bool => {
      console.log('visible', bool);
    };

    const showDrawer = () => {
      visible.value = true;
    };

    const onClose = () => {
      visible.value = false;
    };

    // const showAvatar = () => {
    //   avatarVisible.value = !avatarVisible.value;
    // };

    // const openNotificationWithIcon = (type,status,message) => {
    //   notification[type]({
    //     message: status,
    //     description: message,
    //   });
    // };

    return {
      visible,
      afterVisibleChange,
      showDrawer,
      onClose,
      // showAvatar,
      // avatarVisible,
      // openNotificationWithIcon,
    };
  },
  components: {
    LoginForm,
    RegisterComponent,
    // AvatarComponent
  },
  props: {
    avatarVisible: {
      type: Boolean,
      default: false
    },
    showAvatar: {
      type: Function,
      required: true,
    },
  },
});
</script>