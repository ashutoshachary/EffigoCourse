import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import { useAuth } from "./security/AuthContext"

export default function LoginComponent() {
    const [username, setUsername] = useState('ashutosh')
    const [password, setPassword] = useState('')

    const [sucessMessage, setSucessMessage] = useState(false)
    const [errorMessage, setErrorMessage] = useState(false)

    const navigate = useNavigate();
    const authContext = useAuth()

    function handelUsernameChange(e) {
        setUsername(e.target.value)
    }
    function handelPasswordChange(e) {
        setPassword(e.target.value)
    }

    function handelSubmit() {

        if (authContext.login(username,password)){
            navigate(`/welcome/${username}`)
        }
        else{
            setErrorMessage(true)
        }
    }

    return (
        <div className="Login">
            <h1>Login</h1>
            {sucessMessage && <div className="sucessMessage">Authenticated Successfully</div>}
            {errorMessage && <div className="errorMessage">Invalid Credentials</div>}
            {/* <SucessMessageComponent sucessMessage={sucessMessage}/>
            <ErrorMessageComponent errorMessage={errorMessage}/> */}
            <div className="LoginForm">
                <div>
                    <label>Username:</label>
                    <input type="text" name="username" value={username} onChange={handelUsernameChange}/>
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password"  value={password} onChange={handelPasswordChange} />
                </div>
            </div>
            <button style={{color:"white"}} type="button" name="login"  onClick={handelSubmit}>Login</button>
           
        </div>
    )
}