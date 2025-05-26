import Home from '@/pages/Home.vue';
import MemberDetail from '@/pages/member/MemberDetail.vue';
import MemberLogin from '@/pages/member/MemberLogin.vue';
import MemberRegister from '@/pages/member/MemberRegister.vue';
import { createRouter, createWebHistory } from 'vue-router';

const mockMember = {
    userid: 'hong123',
    name: '홍길동',
    age: 30,
    user_lock: true,
};

const currentUser = {
    userid: 'hong123', // 본인
    role: 'admin',
};


const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: Home },

        // // Member
        { path: '/member/login', component: MemberLogin },
        { path: '/member/register', component: MemberRegister },
        {
            path: '/member/detail/:userid', component: MemberDetail, props: {
                member: mockMember,
                sessionUser: currentUser,
                isAdmin: true,
            },
        },
        // { path: '/member/update', component: MemberUpdate },
        // { path: '/member/list', component: MemberList },
        // { path: '/member/logout', component: MemberLogout },

        // // Board
        // { path: '/board/list', component: BoardList },
        // { path: '/board/register', component: BoardRegister },
        // { path: '/board/detail/:bno', component: BoardDetail, props: true },
        // { path: '/board/update/:bno', component: BoardUpdate, props: true },

        // // Quiz
        // { path: '/quiz/register', component: QuizRegister },
        // { path: '/quiz/random', component: QuizRandom },

        // // 404 Not Found
        // { path: '/:paths(.*)*', component: NotFound },
    ]
})

export default router;