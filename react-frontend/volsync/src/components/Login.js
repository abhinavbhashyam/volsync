import React, { useState } from 'react'
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import axios from "axios";

// config for toast popup
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Login = () => {
    // control state for our three fields
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [role, setRole] = useState('')

    const loginUser = async (e) => {
        e.preventDefault(); // don't reload page

        // only continue if we have values for all fields
        if (username && password && role) {
            try {
                // basic auth request with username and password
                const res = await axios.post(`http://localhost:8080/api/v1/login/${role.toLowerCase()}`, {}, {
                    auth: {
                        username: username,
                        password: password
                    }
                })

                // action upon successful login
                console.log(res)

            } catch (error) {
                // error handling for bad username/password or bad role
                if (error.response.status === 401) {
                    toast("Check that your username and password are correct!")
                } else if (error.response.status === 403) {
                    toast("Check that your account type is correct")
                }

            }

        } else {
            // don't have values for all fields, toast and finish execution
            toast("Fill in all fields!")
        }


    }


    return (
        <div className='login template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <ToastContainer />
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Login</h3>
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
                        <DropdownButton
                            id="dropdown-basic-button"
                            variant=''
                            title="Select your account type"
                            className='form-control'
                            onSelect={(e, eVal) => {
                                setRole(eVal.target.innerText)
                            }}>
                            <Dropdown.Item>Volunteer</Dropdown.Item>
                            <Dropdown.Item>Organization</Dropdown.Item>
                        </DropdownButton>
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary' onClick={(e) => loginUser(e)}>Login</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login