import React from "react";
import { Link } from "react-router-dom";

function Footer() {
  return (
    <div>
      <footer className="flex flex-row justify-around text-center text-white lg:text-lefttext-[#FFFFFF] bg-[#1D53BD] p-8">
        <div>
          <div>
            <span className="font-[700] text-[23px] leading-28">
              Sobre SolucionesYa
            </span>
          </div>

          <div className="xs:flex xs:flex-col xs:gap-2  md:flex md:flex-col md:gap-0  ">
            <Link className="font-[400] text-[16px]">Quienes Somos</Link>
            <Link className="font-[400] text-[16px]">
              Términos y Condiciones
            </Link>
            <Link className="font-[400] text-[16px]">Contáctanos</Link>
          </div>
        </div>

        <div className="xs:hidden md:flex md:flex-col md:items-center md:justify-between">
          <div>
            <span className="xs:hidden md:block md:font-[700] md:text-[23px] md:leading-28">
              ¿Querés ofrecer tus servicios?
            </span>
          </div>
          <button className=" xs:hidden md:block bg-[#1E9E69] w-[140px] h-[47px] rounded-[12px] content-center font-[700] text-[20px] leading-28">
            <Link to="/registro-usuario">Registrate</Link>
          </button>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
