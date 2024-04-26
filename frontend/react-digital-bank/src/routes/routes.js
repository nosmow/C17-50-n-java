export const routes = {
    login: '/',
    register:'/register',
    enterpriseRegister:'/enterprise',
    home:'/home',
    admin:"/admin",
    error: '*',
    send:"/send",
    receive:"/receive",
    transactions:"/transactions",
    dashboard:"/dashboard",
    maintain:"/maintain"
}
const def= 'https://c17-50-n-java.onrender.com/'
export const urlPOST = {
    userRegister: def + 'users/register',
    enterpriseRegister:def + 'users/register/company',
    login: def + 'login',
    home: def + 'transactions/home',
    send: def+ 'transactions/send',
    transactions: def + "transactions/all",
    listCategory: def + "categories/list",
}
  