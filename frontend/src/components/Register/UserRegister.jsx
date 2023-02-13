import React from 'react'
import {Formik, Form, Field, ErrorMessage } from 'formik';
import {useState, useEffect} from 'react'

const userRegister = () => {
  // const [setOauth, oAuth]=useState(false);
  return (
    <>
    <div className="container mx-auto my-4 p-2 text-center">
    <h1 className="text-2xl py-5">Mi perfil</h1>
      <Formik
      initialValues={{
        user:'',
        password:'',
        image:null,
        firstName: '',
        lastName: '',
        email:'',
        phone:'',
        address:{
          main:'',
          floor:'',
          department:'',
        },
        region:'',
        zipCode:'',
        
    }}
       validate={values => {
         const errors = {};
         if (!values.email) {
           errors.email = 'Required';
         } else if (
           !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
         ) {
           errors.email = 'Invalid email address';
         }
         return errors;
       }}
       onSubmit={(values, { setSubmitting }) => {
         setTimeout(() => {
           alert(JSON.stringify(values, null, 2));
           setSubmitting(false);
         }, 400);
       }}
     >
       {({ isSubmitting }) => (
         <Form className="mx-auto max-w-sm">
          
           
           <div>
            {/* <label className="block text-sm font-medium text-gray-700">Photo</label> */}
              <div className="mt-1 flex items-center"> 
                <button
                  type="button"
                  className="ml-5 rounded-md border border-gray-300 bg-white py-2 px-3 text-sm font-medium leading-4 text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                  >
                    <svg className="h-full w-full text-gray-300 " fill="currentColor" viewBox="0 0 24 24">
                        <path d="M24 20.993V24H0v-2.996A14.977 14.977 0 0112.004 15c4.904 0 9.26 2.354 11.996 5.993zM16.002 8.999a4 4 0 11-8 0 4 4 0 018 0z" />
                    </svg>
                     
                </button>
              </div>
              <label className="flex-initial">Cargar Imagen</label>
           <ErrorMessage name="user" component="div" />
                  </div>


          <label className="flex-initial">Usuario</label>
           <Field type="text" name="user"  className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name" />
           <ErrorMessage name="user" component="div" />
           <label className="flex-initial">Contraseña</label>
           <Field type="password" name="password" className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name"/>
           <ErrorMessage name="password" component="div" />
          <hr className="my-4"></hr>
          <label className="flex-initial">Nombre</label>
           <Field className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name" type="text" name="firstName" />
           <ErrorMessage name="name" component="div" />
           <label className="flex-initial">Apellido</label>
           <Field className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name" type="text" name="lastName" />
           <ErrorMessage name="name" component="div" />
          <label className="flex-initial">Correo Electrónico</label>
           <Field className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name" type="email" name="email" />
           <ErrorMessage name="email" component="div" />
           <label className="flex-initial">Telefono</label>
           <Field type="text" name="phone" className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name"/>
           <ErrorMessage name="phone" component="div" />
           <label className="flex-initial">Address</label>
           <Field type="text" name="phone" className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name"/>
           <ErrorMessage name="phone" component="div" />
          
           <button className="mx-auto my-5 flex justify-center  border border-transparent bg-gray-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2" type="submit" disabled={isSubmitting}>
             Guardar
           </button>
         </Form>
       )}
     </Formik>

    </div>
    </>
  )
}



export default userRegister