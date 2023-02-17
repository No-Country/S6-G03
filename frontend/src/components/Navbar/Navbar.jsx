import React from "react";
import { useEffect, useState } from "react";
import { Navigate, useNavigate, Link } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState(localStorage.getItem("user"));
  const [auth, setAuth] = useState(false);

  useEffect(() => {

  setAuth(localStorage.getItem("auth"));
  setUser(localStorage.getItem("user"));
  

  }, [localStorage.getItem("user")]);
  
  //  const handleClick = () => {
      
  //   };
  
    const handleLogin = () => {
     if(auth){
      console.log("Cerrando sesion... "+auth);
      localStorage.removeItem("user");
      localStorage.removeItem("pwd");
      localStorage.setItem("auth", false);
      setUser("");
      setAuth(false);
     } 
    //  else{
    //   navigate("/login-usuario") ;
    //   setUser("");
    //   localStorage.setItem("auth", false);
    //  } 
      
    };

    

  return (
    <>
      <nav className="px-5 navbar justify-between flex   bg-blue-primary py-3 items-center text-white  ">
        <Link to="/"><div className=" flex justify-start w-4/5  md:mx-auto">
          <img  src="/brand-logo.svg" alt="Logo" className="brand-logo  " />
        </div></Link>
        
        {/* <a href="" className="flex md:mr-60">
          Inicio
        </a> */}
        <div className=" flex justify-end">
          <div className="flex items-center mt-5">
            <a href="" className="md:mr-30 sm:mr-10">
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
