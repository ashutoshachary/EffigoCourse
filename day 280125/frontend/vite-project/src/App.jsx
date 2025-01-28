import React from 'react';
import { useState } from 'react'
import './App.css'
import LearningComponent from './components/learning-example/LearningComponent';
import LearningJavaScript from './components/learning-example/LearningJavaScript';
import Counter from './components/learning-example/counter/Counter';
import TodoApp from './components/learning-example/todo/TodoApp';
function App() {
 

  return (   
  <div className="">
      
      {/* <h1>My Todo App</h1> */}
      {/* <PlayingWithProps name="Ashutosh" age={29}/>
      <LearningComponent/>
      <LearningJavaScript/> */}
      {/* <Counter/> */}
      <TodoApp/>

      </div>
    
  )
}

function PlayingWithProps({ name, age}){
  console.log(name);
  console.log(age);
  return(
    <div className="">
      <h1>Playing with Props</h1>
    </div>
  )
}







export default App
