import axios from "axios";

const API_URL = '/api'

const getLoggedUser = () => {
    return axios.get(API_URL + '/user')
}

const UserService = {
    getLoggedUser,
}

export default UserService;