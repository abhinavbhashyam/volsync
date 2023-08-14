import React from 'react'

const OrganizationRegistration = () => {
    return (
        <div className='organization_registration template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Organization Registration</h3>
                    <br></br>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter Username' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="password" placeholder='Enter Password' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter Organization Name' className='form-control' />
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary'>Register Organization</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default OrganizationRegistration
