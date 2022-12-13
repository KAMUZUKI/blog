<template>
    <a-layout>
        <a-layout-content v-if="!refresh">
            <a-space :size="small" direction="horizontal" align="start">
                <a-col style="float:left;height:800px" flex="fix">
                    <DrawerComponent :avatarVisible="avatarVisible" :showAvatar="showAvatar"></DrawerComponent>
                    <MenuComponent :showContentByCategory="showContentByCategory"></MenuComponent>
                </a-col>
                <a-col style="background-color:#fff;min-width: 600px;max-width: 1300px;" flex="fix">
                    <router-view #default="{ route, Component }">
                        <transition :enter-active-class="`animate__animated ${route.meta.transition_enter}`"
                            :leave-active-class="`animate__animated ${route.meta.transition_leave}`" mode="out-in">
                            <component :is="Component" ref="transContent"></component>
                        </transition>
                    </router-view>
                </a-col>
                <a-col style="float:left;" flex="fix">
                    <a-space :size="40" direction="vertical" align="start">
                        <AvatarComponent v-if="avatarVisible" :showAvatar="showAvatar" style="width:100%;">
                        </AvatarComponent>
                        <AWordComponent></AWordComponent>
                        <CarouselElement></CarouselElement>
                        <SiderTag :showContentByKeyword="showContentByKeyword" :showContent="showContent"></SiderTag>
                    </a-space>
                </a-col>
            </a-space>
        </a-layout-content>
    </a-layout>
</template>

<script>
import { defineComponent, ref } from 'vue';
// import { notification } from 'ant-design-vue';
import DrawerComponent from './DrawerComponent.vue';
// import BreadcrumbComponent from './tools/BreadcrumbComponent.vue';
import AvatarComponent from './Avatar/AvatarComponent.vue';
import MenuComponent from './Menu/MenuComponent.vue';
import SiderTag from './SiderTag/SiderTag.vue'
import AWordComponent from './AWord/AWordComponent.vue'
import ContentComponent from './Content/ContentComponent.vue'
import CarouselElement from './Carousel/CarouselElement.vue'
export default defineComponent({
    setup() {

        const transContent = ref()
        const refresh = ref(true)

        const showContentByCategory = (type) => {
            transContent.value.changeContenByCategory(type)
            refresh.value = !refresh.value
            refresh.value = !refresh.value
        }

        const showContentByKeyword = (type) => {
            transContent.value.changeContenByKeyword(type)
            refresh.value = !refresh.value
            refresh.value = !refresh.value
        }

        const avatarVisible = ref(false);

        const showAvatar = () => {
            avatarVisible.value = !avatarVisible.value;
        };

        return {
            transContent,
            avatarVisible,
            showAvatar,
            showContentByCategory,
            showContentByKeyword,
        };
    },
    components: {
        DrawerComponent,
        AvatarComponent,
        // BreadcrumbComponent,
        MenuComponent,
        SiderTag,
        AWordComponent,
        ContentComponent,
        CarouselElement
    },
    // props: {
    //   avatarVisible:{
    //       type:Boolean,
    //       default:false
    //   },
    //   showAvatar: {
    //     type: Function,
    //     required: true,
    //   },
    //   openNotificationWithIcon: {
    //     type: Function,
    //     required: true,
    //   },
    // },
})

</script>

<style>
#components-layout-demo-basic .code-box-demo {
    text-align: center;
}

#components-layout-demo-basic .ant-layout-header,
#components-layout-demo-basic .ant-layout-footer {
    color: #fff;
    background: #7dbcea;
}

[data-theme='light'] #components-layout-demo-basic .ant-layout-header {
    background: #6aa0c7;
}

[data-theme='light'] #components-layout-demo-basic .ant-layout-footer {
    background: #6aa0c7;
}

#components-layout-demo-basic .ant-layout-footer {
    line-height: 1.5;
}

#components-layout-demo-basic .ant-layout-sider {
    color: #fff;
    line-height: 120px;
    background: #3ba0e9;
}

[data-theme='light'] #components-layout-demo-basic .ant-layout-sider {
    background: #3499ec;
}

#components-layout-demo-basic .ant-layout-content {
    min-height: 120px;
    color: #fff;
    line-height: 120px;
    background: rgba(16, 142, 233, 1);
}

[data-theme='light'] #components-layout-demo-basic .ant-layout-content {
    background: #107bcb;
}

#components-layout-demo-basic>.code-box-demo>.ant-layout+.ant-layout {
    margin-top: 48px;
}
</style>