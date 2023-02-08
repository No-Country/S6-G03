import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import { useDispatch, useSelector } from 'react-redux'
import { increment, decrement } from './Redux/counter'

function App() {
  const dispatch = useDispatch()
  const count = useSelector((state) => state.counter.testValue) 
  
  return (
    <div className="App">
      <h2>{count}</h2>
      <button onClick={() => dispatch(increment())}>Increment</button>
      <button onClick={() => dispatch(decrement())}>Decrement</button>
    </div>
  )
}

export default App
