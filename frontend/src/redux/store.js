import { configureStore } from '@reduxjs/toolkit'
import { testCounterSlice } from './counter.js'

export const store = configureStore({
  reducer: {
    counter: testCounterSlice.reducer
  },
})

//// SE TIENE QUE IMPORTAR TODO "SLICE" AL OBJETO REDUCER(STORE GLOBAL). DARLE NOMBRE CUALQUIERA AL KEY Y NOMBRE DE REDUCER AL VALUE ////