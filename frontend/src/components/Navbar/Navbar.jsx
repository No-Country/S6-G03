import React from "react";

const Navbar = () => {
  return (
    <>
      <nav className=" flex justify-around bg-slate-300  py-3 items-center  ">
        <div className=" flex md:mx-auto">
          <img src="/brand-logo.svg" alt="Logo" />
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

          <div className="flex  gap-1  flex-col">
            <a href="/login-usuario"><button className="w-28 border border-black rounded-md">
              Iniciar Sesion
            </button></a>
            <a href="/registro-usuario"><button className="w-28 border border-black rounded-md">
              Registrarme
            </button></a>
            
          </div>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
