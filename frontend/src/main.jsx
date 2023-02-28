import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import { store } from './redux/store'
import { Provider } from 'react-redux'
// import { createClient } from '@supabase/supabase-js'
// const supabase = createClient('https://<project>.supabase.co', '<your-anon-key>')



ReactDOM.createRoot(document.getElementById('root')).render(
  <Provider store={store}>
  <React.StrictMode>
    <App />
  </React.StrictMode>
   </Provider>
);
