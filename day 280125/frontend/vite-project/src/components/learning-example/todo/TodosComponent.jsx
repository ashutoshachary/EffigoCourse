import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'

export default function ListTodosComponent() {

    const today = new Date();

    const targetDate = new Date(today.getFullYear()+12 , today.getMonth(), today.getDay())


    const todos = [{
        id:1,
        description: "Learn aws",
        done: false,
        targetDate: targetDate,
    },
    {
        id:2,
        description: "Learn React",
        done: false,
        targetDate: targetDate,
    },
    {
        id:3,
        description: "Learn Node.js",
        done: false,
        targetDate: targetDate,
    }

]
    return (
        <div className="cotainer">
            <h1>List Todos</h1>
            
            <div>
                <table className="table">

                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>is Done ?</th>
                            <th>Target Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                    
                                    <td>{todo.done? 'Yes' : 'No'}</td>
                                    
                                    <td>{todo.targetDate.toLocaleDateString()}</td>
                                </tr>
                            ))
                        }
                        
                    </tbody>
                </table>
            </div>
        </div>
    )
}
