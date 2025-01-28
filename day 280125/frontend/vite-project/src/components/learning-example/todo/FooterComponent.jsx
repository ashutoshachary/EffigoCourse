
import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
function FooterComponent() {
    return (
        <footer className="footer">
            <div className="container">
            Footer 
            </div>
            
            
        </footer>
    )
}

export default FooterComponent;