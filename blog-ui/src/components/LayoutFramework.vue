<template>
    <a-layout>
        <a-layout-header style="background-color:#d9d9d9;padding:0 0;height:50px">
            <a-row style="max-height:100%">
                <a-col style="max-height:50px" :span="15">
                    <span style="display: inline-block;width: 100px;font-size:xx-large;float: left;line-height:1">DIZCARD</span>
                    <!-- <BreadcrumbComponent style="float:left;margin-left:20px"></BreadcrumbComponent> -->
                </a-col>
                <a-col style="max-height:50px" :span="9">
                    <HeadMenu></HeadMenu>
                </a-col>
            </a-row>
        </a-layout-header>
        <router-view #default="{ route, Component }">
            <transition :enter-active-class="`animate__animated ${route.meta.transition_enter}`"
                :leave-active-class="`animate__animated ${route.meta.transition_leave}`" mode="out-in">
                <component :is="Component"></component>
            </transition>
        </router-view>
        <a-layout-footer>
            <a-col flex="1 1 200px"></a-col>
            <a-col flex="auto">copyright 2022 By MUZUKI</a-col>
            <a-col flex="0 1 300px"></a-col>
        </a-layout-footer>
    </a-layout>
</template>

<script>
import { defineComponent } from 'vue';
import HeadMenu from './Menu/HeadMenu.vue';
export default defineComponent({
    name: 'LayoutFramework',
    setup() {
        return {
        };
    },
    components: {
        HeadMenu,
    },
})

</script>

<style>
.animate__fadeInLeft {
    --animation-duration: 0.5s;
    --animate-delay: 2s;
}

.animate__fadeOutLeft {
    animation-duration: 0.5s;
    animation-fill-mode: both;
}

.a-row {
    background-color: #d9d9d9;
    margin-top: 20px;
}

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