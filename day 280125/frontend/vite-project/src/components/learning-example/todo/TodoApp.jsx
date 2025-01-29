import React from "react";
import './TodoApp.css';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import LogOutComponent from "./LogoutComponent";
import LoginComponent from "./LoginComponent";
import WelcomeComponent from "./WelcomeComponent";
import HeaderComponent from "./HeaderComponent";
import ListTodosComponent from "./TodosComponent";
import EditTodoComponent from "./EditTodoComponent";// Import EditTodoComponent
import ErrorMessageComponent from "./ErrorComponent";
import FooterComponent from "./FooterComponent";
import AuthProvider, { useAuth } from "./security/AuthContext";

function AuthenticatedRoute({children}) {
    const authContext = useAuth();
    if (authContext.isAuthenticated) {
        return children;
    }
    return <Navigate to="/" />;
}

export default function TodoApp() {
    return (
        <>
            <div className="TodoApp">
                <AuthProvider>
                    <BrowserRouter>
                        <HeaderComponent />
                        <Routes>
                            <Route path="/" element={<LoginComponent />} />
                            <Route path="/login" element={<LoginComponent />} />

                            <Route path="/welcome/:username" element={
                                <AuthenticatedRoute>
                                    <WelcomeComponent />
                                </AuthenticatedRoute>
                            } />
                            <Route path="/todos" element={
                                <AuthenticatedRoute>
                                    <ListTodosComponent />
                                </AuthenticatedRoute>
                            } />
                            <Route path="/todos/edit/:todoId" element={
                                <AuthenticatedRoute>
                                    <EditTodoComponent />
                                </AuthenticatedRoute>
                            } />
                            <Route path="/logout" element={
                                <AuthenticatedRoute>
                                    <LogOutComponent />
                                </AuthenticatedRoute>
                            } />
                            <Route path="*" element={<ErrorMessageComponent />} />
                        </Routes>
                        <FooterComponent />
                    </BrowserRouter>
                </AuthProvider>
            </div>
        </>
    );
}
