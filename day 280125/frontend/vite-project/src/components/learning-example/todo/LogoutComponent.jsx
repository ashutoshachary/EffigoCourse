import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
export default  function LogOutComponent() {
    return (
        <div className="logout">
            <h1>Log out Sucessful</h1>

<p>You are logout Sucessful</p>

        </div>
    )
}