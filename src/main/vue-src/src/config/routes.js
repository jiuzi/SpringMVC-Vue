import App from '../app'

/**
 * auth true登录才能访问，false不需要登录，默认true
 */
export default [
  {
    path: '/',
    component: App,
    children: [
      {
        path: '/login', //登录
        meta: {auth: false},
        component: resolve => require(['../pages/login/'], resolve)
      },
      {
        path: '/signout', //退出
        component: resolve => require(['../pages/signout/'], resolve)
      },
      {
        path: '/report', //报表
        meta: {auth: false},
        component: resolve => require(['../pages/report/'], resolve)
      },
      {
        path: '/', //首页
        meta: {auth: false},
        component: resolve => require(['../pages/index/'], resolve)
      },
      {
        path: '*', //其他页面，强制跳转到登录页面
        redirect: '/login'
      }
    ]
  }
]
