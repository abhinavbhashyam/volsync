import React, { useState, useEffect } from 'react'
import { useLocation, useParams } from 'react-router-dom'
import axios from 'axios'

const VolunteerDashboard = () => {
    // capture the username of the user we signed in
    const { username } = useParams()

    // maybe save this username here

    // manage state for our signed in volunteer
    const [volunteer, setVolunteer] = useState()

    // on page load
    useEffect(() => {
        // fetch data
        const getVolunteer = async () => {
            const volunteerFromServer = await fetchVolunteer()
            setVolunteer(volunteerFromServer)
            console.log("fetch")
        }

        getVolunteer()

    }, [])

    const fetchVolunteer = async () => {
        const res = await axios.get(`http://localhost:8080/api/v1/users/${username}`)
        return res.data.volunteer
    }

    return (
        <h3>
            This is Volunteer Dashboard
        </h3>
    )
}

export default VolunteerDashboard