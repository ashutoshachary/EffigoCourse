import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
function ErrorMessageComponent() {
    return (
        <div>
            <h1>Contact with our team</h1>

            <p>For any issues or concerns, please contact our team at 123-456-7890.</p>

        </div>
    )
}

export default ErrorMessageComponent;