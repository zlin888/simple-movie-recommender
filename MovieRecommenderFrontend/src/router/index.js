import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)


const HomePage = r => require.ensure([], () => r(require('@/pages/HomePage')), 'HomePage')
const ForYouPage = r => require.ensure([], () => r(require('@/pages/ForYouPage')), 'ForYouPage')
const BrowsePage = r => require.ensure([], () => r(require('@/pages/BrowsePage')), 'BrowsePage')
const TopChartsPage = r => require.ensure([], () => r(require('@/pages/TopChartsPage')), 'TopChartsPage')
const Login = r => require.ensure([], () => r(require('@/pages/Login')), 'Login')

const routes = [{
    path: '/home',
    name: 'Home',
    component: HomePage,
    redirect: '/home/foryou',
    children: [{
        path: 'foryou',
        name: 'Main',
        component: ForYouPage
      },
      {
        path: 'browse',
        name: 'Browse',
        component: BrowsePage
      },
      {
        path: 'topcharts',
        name: 'TopCharts',
        component: TopChartsPage
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]


export default new Router({
  routes: routes
})
