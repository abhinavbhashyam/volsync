import React, { useState } from 'react'
import axios from "axios";

// config for toast popup
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const OrganizationRegistration = () => {
    // for api calls
    const USERS_API_BASE_URL = 'http://localhost:8080/api/v1/users'
    const ORGANIZATIONS_API_BASE_URL = 'http://localhost:8080/api/v1/organizations'

    // control state for our three fields
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [orgName, setOrgName] = useState("")

    const registerOrganizationAccount = async (e) => {
        e.preventDefault(); // don't refresh the page

        // construct organization object
        const organization = { orgName }

        // construct user object
        const user = { username, password, "role": "ORG" }

        // organization creation
        let createdOrgId = null

        try {
            createdOrgId = await createOrganization(organization)
        } catch (error) {
            console.log(error)
            return
        }

        // user creation
        let createdUserId = null

        try {
            createdUserId = await createUser(user)
        } catch (error) {
            toast(error.response.data.message)
            return
        }

        // then we can sync the organization to the user
        axios.put(ORGANIZATIONS_API_BASE_URL + '/' + createdOrgId + '/users/' + createdUserId)


    }

    // create an organization in the database
    const createOrganization = async (organization) => {
        // create the organization and save the resultant data
        const res = await axios.post(ORGANIZATIONS_API_BASE_URL, organization)
        const data = res.data

        // return the id of the created organization
        return data.id

    }

    // create a user in the database
    const createUser = async (user) => {
        // create the user and save the resultant data
        const res = await axios.post(USERS_API_BASE_URL, user)
        const data = res.data

        // return the id of the created user
        return data.id
    }

    return (
        <div className='organization_registration template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Organization Registration</h3>
                    <br></br>
                    <div className='mb-3'>
                        <input
                            type="text"
                            placeholder='Enter Username'
                            className='form-control'
                            value={username}
                            onChange={(e) => setUsername(e.target.value)} />
                    </div>
                    <div className='mb-3'>
                        <input
                            type="password"
                            placeholder='Enter Password'
                            className='form-control'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className='mb-3'>
                        <input
                            type="text"
                            placeholder='Enter Organization Name'
                            className='form-control'
                            value={orgName}
                            onChange={(e) => setOrgName(e.target.value)} />
                    </div>
                    <div className='d-grid'>
                        <ToastContainer />
                        <button className='btn btn-primary' onClick={(e) => registerOrganizationAccount(e)}>Register Organization</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default OrganizationRegistration
