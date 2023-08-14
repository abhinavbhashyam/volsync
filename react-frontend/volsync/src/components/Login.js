import React from 'react'

const Login = () => {
    return (
        <div className='login template d-flex justify-content-center align-items-center vh-100 bg-primary'>
            <div className='form_container p-5 rounded bg-white'>
                <form>
                    <h3 className='text-center'>Login</h3>
                    <br></br>
                    <div className='mb-3'>
                        <input type="text" placeholder='Enter Username' className='form-control' />
                    </div>
                    <div className='mb-3'>
                        <input type="password" placeholder='Enter Password' className='form-control' />
                    </div>
                    <div className='d-grid'>
                        <button className='btn btn-primary'>Login</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login