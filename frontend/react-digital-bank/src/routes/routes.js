export const routes = {
    login: '/',
    register:'/register',
    enterpriseRegister:'/enterprise',
    home:'/home',
    admin:"/admin",
    error: '*',
}

export const urlPOST = {
    userRegister: 'http://localhost:8080/users/register',
    enterpriseRegister:'http://localhost:8080/users/register/company',
    login: 'http://localhost:8080/login'
}
  