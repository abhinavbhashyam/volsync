import axios from "axios";

const ORG_REG_BASE_REST_API_URL = 'http://localhost:8080/api/v1/'

class RegistrationService {
    createUser(user) {
        return axios.post(ORG_REG_BASE_REST_API_URL + 'users', user);
    }

    createOrganization(organization) {
        return axios.post(ORG_REG_BASE_REST_API_URL + 'organizations', organization);
    }

    registerUserToOrganization() {

    }
}