import React from "react";
import { AiOutlineLeft, AiOutlineDown } from "react-icons/ai";
import cerrajeria from "../../assets/images/cerrajeria.jpg"
import albañileria from "../../assets/images/albañileria.jpg";
import electricidad from "../../assets/images/electricidad.jpg";
import gasista from "../../assets/images/gasista.jpg";
import herreria from "../../assets/images/herreria.jpg";
import jardineria from "../../assets/images/jardineria.jpg";
import mensajeria from "../../assets/images/mensajeria.jpg";
import mudanzas from "../../assets/images/mudanza.jpg";
import pintura from "../../assets/images/pintura.jpg";
import plomeria from "../../assets/images/plomeria.jpg";
import aire from "../../assets/images/aire.jpg";
import electro from "../../assets/images/electro.jpg";
import {useParams, Link}  from "react-router-dom";




const ServiceList = () => {
  return (
    <>
      <section className="mt-10 flex justify-center">
        <div className="grid grid-cols-6  w-[1032px] h-16  ">
          <div className="col-span-5 w-full flex place-items-center gap-2">
            <Link to="/">
            <div className="w-12 h-12 ">
              <Link to="/">
              <AiOutlineLeft className="w-12 h-12" />
              </Link>
              
            </div>
            </Link>
            <div className=" text-left font-bold text-[45px]">
              Todos los Servicios
            </div>
          </div>
          <button className="col-span-1 flex w-[174px] h-11  rounded-lg bg-[#1E9E69] px-2 py-4 place-items-center translate-y-2 -translate-x-1">
            <span className=" w-full text-white font-bold ">Filtrar por</span>
            <AiOutlineDown className="w-6 h-6 text-white" />
          </button>
        </div>
      </section>
      <section className=" mt-10  flex justify-center">
        <div className="grid grid-cols-4 w-[1032px] gap-6 place-items-center">
        <Link to="/profesionales/albañileria">
          <div>
            <img
              src={albañileria}
              alt="albañileria"
              className="rounded-xl mb-4"
            />
            <p className="text-3xl text-center ">Albañileria</p>
          </div>
        </Link>
        <Link to="/profesionales/cerrajeria">
          <div>
            <img
              src={cerrajeria}
              alt="cerrajeria"
              className="rounded-xl mb-4"
            />
            <p className="text-3xl text-center ">Cerrajeria</p>
          </div>
        </Link>
        <Link to="/profesionales/electricidad">
          <div>
              <img
                src={electricidad}
                alt="electricidad"
                className="rounded-xl mb-4"
              />
              <p className="text-3xl text-center ">Electricidad</p>
            </div>
         </Link>
         <Link to="/profesionales/gasista">
         <div>
            <img src={gasista} alt="gasista" className="rounded-xl mb-4" />
            <p className="text-3xl text-center ">Gasista</p>
          </div>
         </Link>
          
        </div>
      </section>
      <section className=" mt-6  flex justify-center">
        <div className="grid grid-cols-4 w-[1032px] gap-6 place-items-center">
          <Link to="/profesionales/albañileria">
          <div>
            <img src={herreria} alt="herreria" className="rounded-xl mb-4" />
            <p className="text-3xl text-center -translate-y-7">Herreria</p>
          </div>
          </Link>
          
          <Link to="/profesionales/jardineria">
          <div>
            <img
              src={jardineria}
              alt="jardineria"
              className="rounded-xl mb-4"
            />
            <p className="text-3xl text-center ">Jardineria</p>
          </div>
          </Link>
          <Link to="/profesionales/mensajeria">
          <div>
            <img
              src={mensajeria}
              alt="mensajeria"
              className="rounded-xl mb-4"
            />
            <p className="text-3xl text-center ">Mensajeria</p>
          </div>
          </Link>
          <Link to="/profesionales/mudanzas">
          <div>
            <img src={mudanzas} alt="mudanzas" className="rounded-xl mb-4" />
            <p className="text-3xl text-center ">Mudanzas</p>
          </div>
          </Link>
        </div>
      </section>
      <section className=" mt-6 mb-20  flex justify-center">
        
        <div className="grid grid-cols-4 w-[1032px] gap-6 place-items-center">
        <Link to="/profesionales/pintura">

          <div>
              <img src={pintura} alt="pintura" className="rounded-xl mb-4" />
              <p className="text-3xl text-center ">Pintura</p>
          </div>
        </Link>
          
          <Link to="/profesionales/plomeria">

          <div>
            <img src={plomeria} alt="plomeria" className="rounded-xl mb-4 " />
            <p className="text-3xl text-center ">Plomeria</p>
          </div>
          </Link>

          <Link to="/profesionales/servicio-ac">
          <div>
            <img src={aire} alt="Service Aire Ac" className="rounded-xl mb-4" />
            <p className="text-3xl text-center ">Servicio Aires AC</p>
          </div>
          </Link>
          <Link to="/profesionales/servicio-electro">
          <div>
            <img
              src={electro}
              alt="Service Electro"
              className="rounded-xl mb-4"
            />
            <p className="text-3xl text-center ">Servicio Electros</p>
          </div>
          </Link>
        </div>
      </section>
    </>
  );
};

export default ServiceList;
