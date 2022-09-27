import axios from "axios";

const API_URL = '/api'

const executeBasicAuthenticationService = (username, password) => {
    return axios.get(API_URL + '/auth',
        { headers: { authorization: createBasicAuthToken(username, password) } })
}

const createBasicAuthToken = (username, password) => {
    return 'Basic ' + window.btoa(username + ":" + password)
}

const addAuthorizationHeaderToRequests = () => {
    axios.interceptors.request.use(
        (config) => {
            if (isUserLoggedIn()) {
                config.headers.authorization = getToken();
            }
            return config
        }
    )
}

const logout = () => {
    sessionStorage.removeItem('login');
    sessionStorage.removeItem('token');
    window.location.reload();
}

const registerSuccessfulLogin = (username, password) => {
    const token = createBasicAuthToken(username,password);
    sessionStorage.setItem('login', username)
    sessionStorage.setItem('token', token)
    addAuthorizationHeaderToRequests()
}

const isUserLoggedIn = () => {
    return sessionStorage.getItem('login') && sessionStorage.getItem('token')
}

const getLoggedInUserName = () => {
    let user = sessionStorage.getItem('login')
    if (user === null) return ''
    return user
}

const getToken = () => sessionStorage.getItem('token');

const AuthenticationService = {
    addAuthorizationHeaderToRequests,
        executeBasicAuthenticationService,
    registerSuccessfulLogin,
    getLoggedInUserName,
    isUserLoggedIn,
    logout
}

export default AuthenticationService;