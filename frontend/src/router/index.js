import BoardDetail from '@/pages/board/BoardDetail.vue';
import BoardList from '@/pages/board/BoardList.vue';
import BoardRegister from '@/pages/board/BoardRegister.vue';
import BoardUpdate from '@/pages/board/BoardUpdate.vue';
import Home from '@/pages/Home.vue';
import MemberDetail from '@/pages/member/MemberDetail.vue';
import MemberList from '@/pages/member/MemberList.vue';
import MemberLogin from '@/pages/member/MemberLogin.vue';
import MemberRegister from '@/pages/member/MemberRegister.vue';
import MemberUpdate from '@/pages/member/MemberUpdate.vue';
import NotFound from '@/pages/NotFound.vue';
import QuizRegister from '@/pages/quiz/QuizRegister.vue';
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

        // Member
        { path: '/member/login', component: MemberLogin },
        { path: '/member/register', component: MemberRegister },
        { path: '/member/detail/:userid', component: MemberDetail, props: true},
        { path: '/member/update/:userid', component: MemberUpdate, props: true},
        { path: '/member/list', component: MemberList },

        // Board
        { path: '/board/list', component: BoardList },
        { path: '/board/register', component: BoardRegister },
        { path: '/board/:bno', component: BoardDetail, props: true },
        { path: '/board/update/:bno', component: BoardUpdate, props: true },

        // Quiz
        { path: '/quiz/register', component: QuizRegister },

        // 404 Not Found
        { path: '/:paths(.*)*', component: NotFound },
    ]
})

export default router;