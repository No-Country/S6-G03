import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
<<<<<<< HEAD
import { store } from './Redux/store.js'
import { Provider } from 'react-redux'
import Navbar from './components/Navbar/Navbar'
=======
import { store } from './redux/store'
import { Provider } from 'react-redux'

>>>>>>> c5a0b982d402a7f2b43b41c35170059c7b68bafc

ReactDOM.createRoot(document.getElementById('root')).render(
  <Provider store={store}>
  <React.StrictMode>
    <App />
  </React.StrictMode>
   </Provider>
);
