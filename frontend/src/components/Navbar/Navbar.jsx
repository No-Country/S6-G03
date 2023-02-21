import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <>
      <nav className=" flex justify-around bg-slate-300  py-3 items-center bg-[#275ECD] ">
        <div className=" flex md:mx-auto">
          <img src="../../../src/assets/images/Logo_SolucionesYa.png" alt="Logo" />
        </div>
        <a href="/" className="flex md:mr-60 font-[400] text-[23px] leading-28 text-[#FFFFFF]">
          Inicio
        </a>
        <div className="w-1/2 flex">
          <div className="flex items-center">
            <a href="" className="md:mr-60 font-[400] text-[23px] leading-28 text-[#FFFFFF]">
              Quienes Somos
            </a>
          </div>

          <div className="flex  gap-1  flex-col">
            <button className="w-[140px]  h-[40px] border-2 border-[#1E9E69] rounded-md font-[400] text-[18px] leading-28 text-[#FFFFFF]">
              Iniciar Sesion
            </button>
            <button className="w-[140px] h-[40px] border border-none rounded-md bg-[#1E9E69] font-[400] text-[18px] leading-28 text-[#FFFFFF]">
            Registrarme
            </button>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
