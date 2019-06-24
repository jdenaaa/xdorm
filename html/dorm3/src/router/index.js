import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/icon',
                    component: resolve => require(['../components/page/Icon.vue'], resolve),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/studentmanager',
                    component: resolve => require(['../components/page/StudentManager.vue'], resolve),
                    meta: { title: '学生管理' }
                },
     
                {
                    path: '/addstudent',
                    component: resolve => require(['../components/page/AddStudent.vue'], resolve),
                    meta: { title: '添加学生' }
                },
                {
                    path: '/addcollege',
                    component: resolve => require(['../components/page/AddCollege.vue'], resolve),
                    meta: { title: '管理学院' }
                },
                {
                    path: '/addmajor',
                    component: resolve => require(['../components/page/AddMajor.vue'], resolve),
                    meta: { title: '管理专业' }
                },
                {
                    path: '/addclass',
                    component: resolve => require(['../components/page/AddClass.vue'], resolve),
                    meta: { title: '管理班级' }
                },
                {
                    path: '/addapartment',
                    component: resolve => require(['../components/page/AddApartment.vue'], resolve),
                    meta: { title: '管理公寓' }
                },
                {
                    path: '/addroom',
                    component: resolve => require(['../components/page/AddRoom.vue'], resolve),
                    meta: { title: '管理房间' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        },
        {
            path: '/editstudent',
            component: resolve => require(['../components/page/EditStudent.vue'], resolve),
           
            meta: { title: '修改学生信息' }
        },
    ]
})
