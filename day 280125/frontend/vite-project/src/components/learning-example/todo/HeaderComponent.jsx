import React, { useContext } from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import { AuthContext, useAuth } from "./security/AuthContext"

export default  function HeaderComponent() {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated
    const logout = () => {
        authContext.logout()
    }
    return (
        <header className="border-bottom border-light border-5 mb-5 p-2">
        <div className="container">
            <div className="row">
                <nav className="navbar navbar-expand-lg">
                    <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="https://www.google.com">Todo App</a>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav">
                            <li className="nav-item fs-5">
                                {isAuthenticated? <Link className="nav-link" to="/welcome/ashutosh">Home</Link>: null}
                                
                                </li>
                            <li className="nav-item fs-5">
                            {isAuthenticated? <Link className="nav-link" to="/todos">Todos</Link>: null}
                                
                            </li>
                        </ul>
                    </div>
                    <ul className="navbar-nav">
                        <li className="nav-item fs-5">{!isAuthenticated? <Link className="nav-link" to="/login">Login</Link>: null}</li>
                       

                        <li className="nav-item fs-5"> {isAuthenticated? <Link className="nav-link" to="/logout" onClick={logout} >Logout</Link>: null}</li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    )
}