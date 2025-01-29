import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'
export default function WelcomeComponent() {

    const { username } = useParams();
    const [message, setMessage] = useState(null)
    function callHelloWorldRestApi() {
        axios
        .get("http://localhost:8083/hello-world-bean", {
            headers: {
                Authorization: "Basic " + btoa("username:password"),
            },
        })
        .then((response) => {
            console.log("Data:", response.data);
            setMessage(response.data.message);
        })
        .catch((error) => {
            console.error("Error fetching data:", error);
        })
        .finally(() => {
            console.log("Request completed.");
        });
    }

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <p>This is a welcome component go to your todos <Link to="/todos">click here</Link></p>

            <button onClick={callHelloWorldRestApi} className="btn btn-success m-5">Call Hello World API</button>
            <div className="text-info">{message}</div>


        </div>
    )
}