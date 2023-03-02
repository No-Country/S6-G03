import React from "react";
import SearchBar from "../SearchBar/Searchbar";
import LocationImg from "../../../src/assets/images/Location.png";
import SOSImg from "../../../src/assets/images/SOS.png";
import StarImg from "../../../src/assets/images/Star.png";
import PlomeriaImg from "../../../src/assets/images/Plomeria.png";
import CerrajeriaImg from "../../../src/assets/images/cerrajeria.jpg";
import ElectricistaImg from "../../../src/assets/images/Electricista.png";
import GasistaImg from "../../../src/assets/images/gasista.jpg";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="p-8">
      <div className="flex flex-col justify-center items-center text-center">
        <div className="p-8">
          <div>
            <span className="xs:text-[35px] md:font-[700] md:text-[45px] md:leading-64">
              ¿Necesitas un profesional urgente? <br />
              Conseguilo al instante con SolucionesYa!
            </span>
          </div>
        </div>

        <div className="p-4">
          <div>
            <span className="xs:text-[30px] md:font-[400] md:text-[45px] md:leading-56">
              ¿Qué tipo de profesional buscas?
            </span>
          </div>

          <SearchBar />
        </div>

        <div className="xs:hidden md:flex md:flex-col md:justify-around md:items-center md:bg-[#1E9E69] md:w-[1120px] md:h-[360px] md:p-3 md:rounded-[40px] ">
          <div>
            <span className="font-[700] text-[45px] leading-64 text-[#FFFFFF]">
              Ventajas de usar SolucionesYa!
            </span>
          </div>

          <div className="flex flex-row justify-around items-center w-full">
            <div className="flex flex-col justify-around items-center bg-[#1E9E69] w-[280px] h-[200px] border-4 border-[#9CD5BE] rounded-[40px]">
              <img src={LocationImg} alt="location" />
              <span className="font-[400] text-[23px] leading-28 text-[#FFFFFF] text-center">
                Podes contactar al profesional más cercano
              </span>
            </div>
            <div className="flex flex-col justify-around items-center bg-[#1E9E69] w-[280px] h-[200px] border-4 border-[#9CD5BE] rounded-[40px]">
              <img src={SOSImg} alt="location" />
              <span className="font-[400] text-[23px] leading-28 text-[#FFFFFF] text-center">
                Podes encontrar servicios las 24hs del dia
              </span>
            </div>
            <div className="flex flex-col justify-around items-center bg-[#1E9E69] w-[280px] h-[200px] border-4 border-[#9CD5BE] rounded-[40px]">
              <div className="flex flex-row">
                <img src={StarImg} alt="location" />
                <img src={StarImg} alt="location" />
                <img src={StarImg} alt="location" />
                <img src={StarImg} alt="location" />
                <img src={StarImg} alt="location" />
              </div>
              <span className="font-[400] text-[23px] leading-28 text-[#FFFFFF] text-center">
                Podes mirar las opiniones de clientes anteriores
              </span>
            </div>
          </div>
        </div>

        <div className="xs:w-[242px] xs:h-[285px] md:flex md:flex-col md:items-center md:justify-around md:p-8  md:w-full md:h-[500px]">
          <div>
            <span className="xs:text-[35px] md:font-[400] md:text-[43px] md:leading-56">
              <Link to="/categorias-profesionales">
              <button className="px-4 py-2  bg-green-secondary-80 rounded-lg text-white ">
                  Ver todos los servicios
              </button>
              </Link>
            </span>
          </div>
          <div className="xs:gap-3 xs:mt-4 flex flex-row items-center justify-around w-full">
            <Link to="/profesionales/plomeria">
              <div>
                <img
                  src={PlomeriaImg}
                  alt=""
                  className="xs:w-[75px] xs:h-[120px] xs:rounded-[10px] xs:mb-4 md:w-[261px] md:h-[250px] md:rounded-[40px]"
                />
                <span className=" xs:text-[19px] md:font-[400] md:text-[24px] md:leading-40">
                  Plomeria
                </span>
              </div>
            </Link>

            <Link to="/profesionales/cerrajeria">
              <div>
                <img
                  src={CerrajeriaImg}
                  alt=""
                  className="xs:w-[75px] xs:h-[120px] xs:rounded-[10px] xs:mb-4  md:w-[261px] md:h-[250px] md:rounded-[40px]"
                />
                <span className="xs:text-[19px] md:font-[400] md:text-[24px] md:leading-40">
                  Cerrajeria
                </span>
              </div>
            </Link>

            <Link to="/profesionales/electricista">
              <div>
                <img
                  src={ElectricistaImg}
                  alt=""
                  className="xs:w-[100px] xs:h-[120px] xs:rounded-[10px] xs:mb-4 md:w-[261px] md:h-[250px] md:rounded-[40px]"
                />
                <span className="xs:text-[19px] md:font-[400] md:text-[24px] md:leading-40">
                  Electricidad
                </span>
              </div>
            </Link>

            <Link to="/profesionales/gasista">
              <div>
                <img
                  src={GasistaImg}
                  alt=""
                  className="xs:w-[75px] xs:h-[120px] xs:rounded-[10px] xs:mb-4 md:w-[261px] md:h-[250px] md:rounded-[40px]"
                />
                <span className="xs:text-[19px] md:font-[400] md:text-[24px]  md:leading-40">
                  Gasista
                </span>
              </div>
            </Link>
          </div>


                    <div className="xs:hidden md:my-[15px]">
                        <Link to="/categorias-profesionales">
                        <button className=" xs:hidden md:block bg-[#1E9E69] w-[504px] h-[68px] rounded-[12px] content-center font-[700] text-[32px] leading-36 text-[#FFFFFF]">Ver todos los servicios</button></Link>
                    </div>
                </div>
            </div>

        </div>
      </div>
    </div>
  );
}

export default Home;
