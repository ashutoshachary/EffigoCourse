import React, { useState, useEffect } from "react";
import './TodoApp.css';
import { getAllTodos, createTodo , deleteTodo} from "../../api/Api"; // Make sure to import createTodo function
import { useAuth } from "./security/AuthContext";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function ListTodosComponent() {
    const today = new Date();
    const [todos, setTodos] = useState([]);
    const [newTodo, setNewTodo] = useState({
        description: '',
        targetDate: today.toISOString().slice(0, 10), // Default to today's date
        done: false
    });
    const [isCreating, setIsCreating] = useState(false); // Toggle create form visibility
    const authContext = useAuth();

    useEffect(() => {
        refreshTodos();
    }, []);

    function refreshTodos() {
        getAllTodos(authContext.username)
            .then(response => {
                setTodos(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }

    function handleCreateTodoChange(e) {
        const { name, value } = e.target;
        setNewTodo(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    function handleCreateTodoSubmit(e) {
        e.preventDefault();

        const todoData = {
            description: newTodo.description,
            targetDate: newTodo.targetDate,
            done: newTodo.done
        };

        createTodo(authContext.username, todoData)
            .then(response => {
                alert('Todo Created Successfully');
                setNewTodo({
                    description: '',
                    targetDate: today.toISOString().slice(0, 10),
                    done: false
                });
                setIsCreating(false); 
                refreshTodos(); 
            })
            .catch(error => {
                console.error(error);
            });
    }
    function handleDelete(todoId) {
        if (window.confirm("Are you sure you want to delete this todo?")) {
            deleteTodo(authContext.username, todoId)
            .then(() => {
                refreshTodos(); // Refresh the list after deleting
            })
            .catch(error => {
                console.error("Error deleting todo:", error);
            });
        }
    }

    return (
        <div className="container">
            <h1>List Todos</h1>

            {/* Create Todo Button */}
            <button
                className="btn btn-primary mb-3"
                onClick={() => setIsCreating(!isCreating)}
            >
                {isCreating ? 'Cancel' : 'Create Todo'}
            </button>

            {isCreating && (
                <form onSubmit={handleCreateTodoSubmit}>
                    <div className="form-group">
                        <label>Description</label>
                        <input
                            type="text"
                            className="form-control"
                            name="description"
                            value={newTodo.description}
                            onChange={handleCreateTodoChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Target Date</label>
                        <input
                            type="date"
                            className="form-control"
                            name="targetDate"
                            value={newTodo.targetDate}
                            onChange={handleCreateTodoChange}
                            required
                        />
                    </div>
                    <div className="form-group mt-3">
                        <button type="submit" className="btn btn-success">Create</button>
                    </div>
                </form>
            )}

            <div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Is Done?</th>
                            <th>Target Date</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {todos.map(todo => (
                            <tr key={todo.id}>
                                <td>{todo.id}</td>
                                <td>{todo.description}</td>
                                <td>{todo.done ? 'Yes' : 'No'}</td>
                                <td>{todo.targetDate}</td>
                                <td><Link className="btn btn-success" to={`/todos/edit/${todo.id}`}>Edit</Link></td>
                                <td> <button onClick={() => handleDelete(todo.id)} className="btn btn-danger">
                                            Delete
                                        </button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
