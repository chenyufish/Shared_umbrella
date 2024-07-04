// import { StoreOptions } from "vuex";
// import axios from "axios";
// import ACCESS_ENUM from "../access/accessEnum";
//
// export default {
//     namespaced: true,
//     state: () => ({
//         loginUser: {
//             userName: "未登录",
//         },
//     }),
//     actions: {
//         async getLoginUser({ commit, state }, payload) {
//             try {
//                 const res = await axios.get("http://localhost:8123/api/user/getloginuser/");
//                 if (res.data.code === 0) {
//                     commit("updateUser", res.data.data);
//                 } else {
//                     commit("updateUser", {
//                         ...state.loginUser,
//                         userRole: ACCESS_ENUM.NOT_LOGIN,
//                     });
//                 }
//             } catch (error) {
//                 console.error("Error fetching login user:", error);
//                 commit("updateUser", {
//                     ...state.loginUser,
//                     userRole: ACCESS_ENUM.NOT_LOGIN,
//                 });
//             }
//         },
//     },
//     mutations: {
//         updateUser(state, payload) {
//             state.loginUser = payload;
//         },
//     },
// } as StoreOptions<any>;
