import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
export default function WelcomeComponent() {

    const { username } = useParams();

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <p>This is a welcome component go to your todos <Link to="/todos">click here</Link></p>
            

        </div>
    )
}