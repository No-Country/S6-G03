import React from "react";
import { useEffect, useState } from "react";
import { Navigate, useNavigate, Link } from "react-router-dom";
import "./Navbar.css";
import muñeco from "../../assets/images/pf-image.jpg";
import { FaChevronDown } from "react-icons/fa";

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
    if (auth) {
      console.log("Cerrando sesion... " + auth);
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
        <Link to="/">
          <div className=" flex justify-start w-4/5  md:mx-auto">
            <img src="/brand-logo.svg" alt="Logo" className="brand-logo  " />
          </div>
        </Link>

        {/* <a href="" className="flex md:mr-60">
          Inicio
        </a> */}

        <div className=" flex justify-end">
          <div className="md:flex md:items-center md:mt-4 md:font-[400] md:text-[18px] md:leading-28">
            {user ? (
              ""
            ) : (
              <a href="" className="xs:hidden md:mr-30 md:block sm:mr-10">
                Quienes Somos
              </a>
            )}
          </div>

          {user ? (
            //  Elementos si esta logueado

            <div className="flex flex-col items-center  ">
              <div className="flex-row flex justify-between  gap-4  ">
                <div className="flex">
                  <Link to="/perfil-usuario">
                    <img
                      src={muñeco}
                      alt="muñeco"
                      className="w-8 h-8 border rounded-full translate-y-3 "
                    />
                  </Link>
                </div>
                <div className="text-center mt-4 text-[20px]"> {user}</div>
              </div>
              <button
                onClick={handleLogin}
                className="w-[140px]  h-[40px] font-[400] text-[18px] leading-28  mt-3 border border-red-800 rounded-md bg-red-800"
              >
                Cerrar Sesión
              </button>
            </div>
          ) : (
            //Elementos si no hay user logueado

            <div className="flex  gap-1  flex-col">
              <Link to="/login-usuario">
                <button className="w-[140px]  h-[40px] font-[400] text-[18px] leading-28  border border-green-secondary rounded-md">
                  Iniciar Sesion
                </button>
              </Link>

              <Link to="/registro-usuario">
                <button className="w-[140px]  h-[40px] font-[400] text-[18px] leading-28 border border-green-secondary rounded-md bg-green-secondary">
                  Registrarme
                </button>
              </Link>
            </div>
          )}
        </div>
      </nav>
    </>
  );
};

export default Navbar;
