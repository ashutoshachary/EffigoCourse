import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getTodoById, updateTodo } from "../../api/Api";
import { useAuth } from "./security/AuthContext";

export default function EditTodoComponent() {
    const { todoId } = useParams();
    const [todo, setTodo] = useState({ description: "", targetDate: "", done: false });
    const authContext = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        getTodoById(authContext.username, todoId)
            .then(response => {
                setTodo(response.data);
            })
            .catch(error => {
                console.error("Error fetching todo:", error);
            });
    }, [todoId, authContext.username]);

    function handleSubmit(e) {
        e.preventDefault();

        const updatedTodo = {
            ...todo,
            description: e.target.description.value,
            targetDate: e.target.targetDate.value,
        };

        updateTodo(authContext.username, todoId, updatedTodo)
            .then(() => {
                alert("Todo updated successfully!");
                navigate("/todos");
            })
            .catch(error => {
                console.error("Error updating todo:", error);
            });
    }

    return (
        <div className="container">
            <h1>Edit Todo</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Description</label>
                    <input type="text" className="form-control" name="description" defaultValue={todo.description} required />
                </div>
                <div className="form-group">
                    <label>Target Date</label>
                    <input type="date" className="form-control" name="targetDate" defaultValue={todo.targetDate.slice(0, 10)} required />
                </div>
                <div className="form-group mt-3">
                    <button type="submit" className="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    );
}
