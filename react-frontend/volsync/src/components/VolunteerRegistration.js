import React, { useState } from 'react'
import axios from 'axios';

// config for toast popup
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

const VolunteerRegistration = () => {
    // for api calls
    const USERS_API_BASE_URL = 'http://localhost:8080/api/v1/users'
    const VOLUNTEERS_API_BASE_URL = 'http://localhost:8080/api/v1/volunteers'

    // control state for our five fields
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [applicationMessage, setApplicationMessage] = useState("")

    // for navigation
    const navigate = useNavigate()

    const registerVolunteerAccount = async (e) => {
        e.preventDefault(); // don't refresh the page

        // only proceed if we have values for all fields
        if (username.trim() && password.trim() && firstName.trim() && lastName.trim() && applicationMessage.trim()) {
            // construct volunteer object
            const volunteer = { firstName, lastName, applicationMessage }

            // construct user object
            const user = { username, password, "role": "VOL" }

            // user creation
            let createdUserId = null

            try {
                createdUserId = await createUser(user)
            } catch (error) {
                toast(error.response.data.message)
                return
            }

            // volunteer creation
            let createdVolId = null

            try {
                createdVolId = await createVolunteer(volunteer)
            } catch (error) {
                console.log(error)
                return
            }

            // then we can sync the volunteer to the user
            try {
                await axios.put(VOLUNTEERS_API_BASE_URL + '/' + createdVolId + '/users/' + createdUserId)

                // then we can login the organization
                navigate('/')
            } catch (error) {
                console.log(error)
            }
        } else {
            toast("Fill in all fields!")
        }


    }

    // create a volunteer in the database
    const createVolunteer = async (volunteer) => {
        // create the volunteer and save the resultant data
        const res = await axios.post(VOLUNTEERS_API_BASE_URL, volunteer)
        const data = res.data

        // return the id of the created volunteer
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
        <div className='volunteer_registration template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <ToastContainer />
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Volunteer Registration</h3>
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
                            placeholder='Enter First Name'
                            className='form-control'
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)} />
                    </div>
                    <div className='mb-3'>
                        <input
                            type="text"
                            placeholder='Enter Last Name'
                            className='form-control'
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)} />
                    </div>
                    <div className='mb-3'>
                        <textarea
                            className="form-control"
                            placeholder='Enter Application Message (125 character limit)'
                            rows="3"
                            maxLength="150"
                            value={applicationMessage}
                            onChange={(e) => setApplicationMessage(e.target.value)}
                        ></textarea>
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary' onClick={(e) => registerVolunteerAccount(e)}>Register Volunteer</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default VolunteerRegistration