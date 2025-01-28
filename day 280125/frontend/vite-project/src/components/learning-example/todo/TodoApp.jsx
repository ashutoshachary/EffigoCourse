import React from "react"
import './TodoApp.css'
import { useState } from "react"
import { BrowserRouter, Routes, Route , useNavigate, useParams, Link} from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import LogOutComponent from "./LogoutComponent"
import LoginComponent from "./LoginComponent"
import WelcomeComponent from "./WelcomeComponent"
import HeaderComponent from "./HeaderComponent"
import ListTodosComponent from "./TodosComponent"
import ErrorMessageComponent from "./ErrorComponent"
import FooterComponent from "./FooterComponent"
import AuthProvider from "./security/AuthContext"
import { AuthContext } from "./security/AuthContext"
export default function TodoApp() {

    return(
        <>
        <div className="TodoApp">
           

            <AuthProvider>

            <BrowserRouter>
            <HeaderComponent/>
            <Routes>
                <Route path="/" element={<LoginComponent />} />
                <Route path="/login" element={<LoginComponent />} />

                <Route path="/welcome/:username" element={<WelcomeComponent />} />
                <Route path="/todos" element={<ListTodosComponent />} />
                <Route path="/logout" element={<LogOutComponent />} />
                <Route path="*" element={<ErrorMessageComponent />} />
            </Routes>
            <FooterComponent/>
            </BrowserRouter>
            </AuthProvider>
            
        


        </div>
        
        
        
        </>
    )

}




// function SucessMessageComponent({sucessMessage}) {
//     if (sucessMessage){
//         return <div className="sucessMessage">Authenticated Successfully</div>
//     }
//     return null
// }

// function ErrorMessageComponent({errorMessage}) {
//     if (errorMessage){
//         return <div className="errorMessage">Authemtication Failed, Please check your credentials.</div>
//     }
//     return null
// }












