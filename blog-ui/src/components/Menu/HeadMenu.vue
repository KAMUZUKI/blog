<template>
  <div style="float:right;">
    <a-space size="small" align="middle" direction="horizontal">
      <div class="link">
        <router-link class="nav-link" to="/">
          <span>首页</span>
        </router-link>
      </div>
      <div class="link">
        <router-link class="nav-link" to="/flink">
          <span>友链</span>
        </router-link>
      </div>
      <div class="link">
        <router-link class="nav-link" to="/editor">
          <span>撰写</span>
        </router-link>
      </div>
      <div class="link">
        <a-dropdown>
          <router-link class="nav-link" to="">
            <span>设置</span>
            <DownOutlined />
          </router-link>
          <template #overlay>
            <a-menu>
              <a-menu-item>
                <router-link class="nav-link" to="/setting/manager">
                  <span>管理用户</span>
                </router-link>
              </a-menu-item>
              <a-menu-item>
                <router-link class="nav-link" to="/setting/setflink">
                  <span>设置友链</span>
                </router-link>
              </a-menu-item>
              <a-menu-item>
                <router-link class="nav-link" to="/setting/article">
                  <span>管理文章</span>
                </router-link>
              </a-menu-item>
              <a-menu-item>
                <router-link class="nav-link" to="/setting/add">
                 <span>添加分类</span>
                </router-link>
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </div>
      <div class="link">
        <router-link class="nav-link" to="/about">
          <span>关于</span>
        </router-link>
      </div>
    </a-space>
  </div>
</template>

<style scoped>
.link {
  margin-right: 20px;
  display: inline-block;
  font-size: 20px;
  color: #08c;
  align-items: center;
}

.link :hover{
  background-color: rgb(246, 246, 246);
}
</style>

<script>
import { DownOutlined } from '@ant-design/icons-vue';
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const gotoEditor = () => {
  router.push({
    name: 'editor',
    params: {

    }
  })
};

export default defineComponent({
  name: 'BarDemo',
  components: {
    DownOutlined,
  },
  setup() {
    return {
      gotoEditor,
    };
  },
});
</script>