import axios from "axios";

export const apiClient = {
  API_URL: "http://localhost:8083",
};

const authHeader = {
  Authorization: "Basic " + btoa("username:password"),
};

export const retrievePathVariable = (username) =>
  axios.get(`${apiClient.API_URL}/hello-world-bean/path-variable/${username}`, {
    headers: authHeader,
  });

export const getAllTodos = (username) =>
  axios.get(`${apiClient.API_URL}/users/${username}/todos`, {
    headers: authHeader,
  });

export const getTodoById = (username, todoId) => 
    axios.get(`${apiClient.API_URL}/users/${username}/todos/${todoId}`, {
        headers: authHeader,
    });

export const createTodo = (username, todo) =>
  axios.post(`${apiClient.API_URL}/users/${username}/todos`, todo, {
    headers: {
      ...authHeader,
      "Content-Type": "application/json",
    },
  });

export const updateTodo = (username, todoId, todo) => 
    axios.put(`${apiClient.API_URL}/users/${username}/todos/${todoId}`, todo, {
        headers: authHeader,
    });

export const deleteTodo = (username, id) =>
  axios.delete(`${apiClient.API_URL}/users/${username}/todos/${id}`, {
    headers: authHeader,
  });
