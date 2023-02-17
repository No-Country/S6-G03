import React from "react";
import { useEffect, useState } from "react";
import { Navigate, useNavigate, Link } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  let user = '';
  if(localStorage.getItem("user")==null){
    user=''
  }else{
   user= localStorage.getItem("user");
  }
  //Object.keys(auth.user).length > 0 ? (user = auth.user) : (user = null);
   const handleClick = () => {
      
    };
  
    const handleLogin = () => {
      user? localStorage.removeItem("user"): navigate("/login-usuario") ;
      
    };

    useEffect(() => {
      
      

  
    }, [user]);

  return (
    <>
      <nav className=" flex justify-around bg-blue-primary py-3 items-center text-white  ">
        <div className=" flex md:mx-auto">
          <img  src="/brand-logo.svg" alt="Logo" />
        </div>
        <a href="" className="flex md:mr-60">
          Inicio
        </a>
        <div className="w-1/2 flex">
          <div className="flex items-center">
            <a href="" className="md:mr-60">
              Quienes Somos
            </a>
          </div>

         {user? 
        //  Elementos si esta logueado
         <div className="flex  gap-1  flex-col">
            <button onClick={handleLogin} className="w-28 border border-red-800 rounded-md bg-red-800">
              Cerrar Sesión
            </button>
            
          </div>:
          //Elementos si no hay user logueado 
         <div className="flex  gap-1  flex-col">
          <Link to="/login-usuario">
          <button className="w-28 border border-green-secondary rounded-md">
              Iniciar Sesion
            </button>
          </Link>
            
            <Link to="/registro-usuario"><button className="w-28 border border-green-secondary rounded-md bg-green-secondary">
              Registrarme
            </button></Link>
            
          </div>}
        </div>
      </nav>
    </>
  );
};

export default Navbar;
