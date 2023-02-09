import React from 'react'
import {Formik, Form, Field, ErrorMessage } from 'formik';
import {useState, useEffect} from 'react'

const userRegister = () => {
  // const [setOauth, oAuth]=useState(false)
  return (
    <>
    <div className="container mx-auto my-4 p-2 text-center">
    <h1>Mi perfil</h1>
      <Formik
      initialValues={{

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
           <label className="flex-initial">Contraseña</label>
           <Field type="password" name="password" className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500" id="inline-full-name"/>
           <ErrorMessage name="password" component="div" />
           <button className="mx-auto my-2 flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2" type="submit" disabled={isSubmitting}>
             Enviar
           </button>
         </Form>
       )}
     </Formik>

    </div>
    </>
  )
}



export default userRegister