
import React, { useContext } from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import { AuthContext } from "./security/AuthContext"
function FooterComponent() {
    const authContext = useContext(AuthContext)
    return (
        <footer className="footer">
            <div className="container">
            Footer 
            </div>
            
            
        </footer>
    )
}

export default FooterComponent;