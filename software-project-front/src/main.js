import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Vueaxios from 'vue-axios'
import './assets/css/reset.css'
import * as echarts from 'echarts'
import Vant from 'vant';
import 'vant/lib/index.css';

Vue.use(Vant);
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts;
Vue.use(ElementUI);
Vue.use(Vueaxios,axios);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
