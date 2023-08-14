import React from 'react'

const VolunteerRegistration = () => {
    return (
        <div className='volunteer_registration template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Volunteer Registration</h3>
                    <br></br>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter Username' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="password" placeholder='Enter Password' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter First Name' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter Last Name' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <textarea className="form-control" placeholder='Enter Application Message' rows="3"></textarea>
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary'>Register Volunteer</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default VolunteerRegistration