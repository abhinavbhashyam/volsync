import React, { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import axios from 'axios'


// config for toast popup
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const VolunteerDashboard = () => {
    // navigation
    const navigate = useNavigate()

    // capture the username of the user we signed in
    const { username } = useParams()

    // rename this variable
    const [discoverOpportunities, setDiscoverOpportunities] = useState(false)

    // manage state for our three sign up arrays
    const [signedUpPosts, setSignedUpPosts] = useState([])
    const [acceptedToPosts, setAcceptedToPosts] = useState([])
    const [rejectedFromPosts, setRejectedFromPosts] = useState([])

    // manage state for discover post array
    const [discoverPosts, setDiscoverPosts] = useState([])

    // on page load
    useEffect(() => {
        initializePostArrays()
    }, [])

    // fetch data
    const initializePostArrays = async () => {
        const volunteerFromServer = await fetchVolunteer()
        setSignedUpPosts(volunteerFromServer.signedUpPosts)
        setAcceptedToPosts(volunteerFromServer.acceptedToPosts)
        setRejectedFromPosts(volunteerFromServer.rejectedFromPosts)

        const discoverPosts = await fetchDiscoverPosts(volunteerFromServer.id)
        setDiscoverPosts(discoverPosts)
    }

    const fetchVolunteer = async () => {
        // get volunteer from api
        const res = await axios.get(`http://localhost:8080/api/v1/users/${username}`)
        return res.data.volunteer
    }

    const fetchDiscoverPosts = async (id) => {
        const res = await axios.get(`http://localhost:8080/api/v1/volunteers/${id}/posts`)
        return res.data
    }


    // use fetch volunteer
    const signUpVolunteerForPost = async (e, discoverPostId) => {
        e.preventDefault(); // stop reload

        const currVolunteer = await fetchVolunteer()
        const currVolunteerId = currVolunteer.id

        await axios.post(`http://localhost:8080/api/v1/volunteer-posts/volunteers/${currVolunteerId}/posts/${discoverPostId}`)

        toast("Sign up successful! Toggle back to see your updated opportunities!")
    }

    const toggleDiscoverOpportunities = (e) => {
        e.preventDefault()  // stop reload

        discoverOpportunities ? setDiscoverOpportunities(false) : setDiscoverOpportunities(true)

        // since we're toggling pages, update data
        initializePostArrays()

    }

    const buttonTitle = () => {
        if (discoverOpportunities) {
            return 'Go back to see my opportunities'
        } else {
            return 'Go back to discover more opportunities'
        }
    }

    const table = () => {
        if (!discoverOpportunities) {
            return <table className="table table-bordered table-striped">
                <thead>
                    <th>Title</th>
                    <th>Body</th>
                    <th>Date</th>
                    <th>Posted By</th>
                    <th>Status</th>
                </thead>

                <tbody>
                    {
                        (signedUpPosts).map(
                            signedUpPost =>
                                <tr key={signedUpPost.id}>
                                    <td>{signedUpPost.postTitle}</td>
                                    <td>{signedUpPost.postBody}</td>
                                    <td>{signedUpPost.postDate}</td>
                                    <td>{signedUpPost.postedByOrganization}</td>
                                    <td>Pending</td>

                                </tr>
                        )
                    }

                    {

                        (acceptedToPosts).map(
                            signedUpPost =>
                                <tr key={signedUpPost.id}>
                                    <td>{signedUpPost.postTitle}</td>
                                    <td>{signedUpPost.postBody}</td>
                                    <td>{signedUpPost.postDate}</td>
                                    <td>{signedUpPost.postedByOrganization}</td>
                                    <td>Accepted</td>

                                </tr>
                        )
                    }

                    {

                        (rejectedFromPosts).map(
                            signedUpPost =>
                                <tr key={signedUpPost.id}>
                                    <td>{signedUpPost.postTitle}</td>
                                    <td>{signedUpPost.postBody}</td>
                                    <td>{signedUpPost.postDate}</td>
                                    <td>{signedUpPost.postedByOrganization}</td>
                                    <td>Rejected</td>

                                </tr>
                        )
                    }
                </tbody>
            </table>
        } else {
            return <table className="table table-bordered table-striped">
                <thead>
                    <th>Title</th>
                    <th>Body</th>
                    <th>Date</th>
                    <th>Posted By</th>
                    <th># Signed Up</th>
                    <th># Accepted</th>
                    <th># Rejected</th>
                    <th>Action</th>
                </thead>

                <tbody>
                    {
                        (discoverPosts).map(
                            discoverPost =>
                                <tr key={discoverPost.id}>
                                    <td>{discoverPost.postTitle}</td>
                                    <td>{discoverPost.postBody}</td>
                                    <td>{discoverPost.postDate}</td>
                                    <td>{discoverPost.postedByOrganization}</td>
                                    <td>{discoverPost.signedUpVolunteers.length}</td>
                                    <td>{discoverPost.acceptedVolunteers.length}</td>
                                    <td>{discoverPost.rejectedVolunteers.length}</td>
                                    <td>
                                        <button className="btn btn-success" onClick={(e) => signUpVolunteerForPost(e, discoverPost.id)}>Sign Up</button>
                                    </td>
                                </tr>
                        )
                    }

                </tbody>
            </table>
        }
    }

    return (
        <div className="container">
            <ToastContainer />
            <br></br>
            <h2 className="text-center">My Dashboard</h2>
            <br></br>
            <button className='btn btn-primary mb-5' onClick={(e) => toggleDiscoverOpportunities(e)}>{buttonTitle()}</button>
            {table()}
        </div>
    )
}

export default VolunteerDashboard