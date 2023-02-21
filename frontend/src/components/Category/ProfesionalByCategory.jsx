import React from "react";
import { AiOutlineLeft, AiOutlineDown } from "react-icons/ai";
import ProfesionalCard from "./ProfesionalCard";
import {useParams, Link}  from "react-router-dom";

const ProfesionalByCategory = (props) => {
  const { category } = useParams();
  console.log(category);
  return (
    <>
      <section className="w-[80%] mx-auto mt-[156px] container">
        <div className="flex  items-center">
          <Link to="/"><AiOutlineLeft className="w-[48px] h-[48px] " /></Link>
          <div className="flex ">
            <span className="text-[45px] font-bold">Resultados de: </span>
            <p className="   text-[45px] capitalize">  {category} </p>
          </div>
          <div className="flex bg-green-secondary rounded-md md:w-[174px] md:h-[44px] text-center items-center gap-4 translate-x-[460px]">
            <span className="flex text-white font-bold text-center ml-4 mr-4">Filtrar por</span>
            <AiOutlineDown className=" w-6 h-6 text-white"/>
          </div>
        </div>
      </section>
      <section className="grid grid-cols-2 gap-6 mx-auto p-4 w-[80%] ">
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
       <Link to="/profesional/1"><ProfesionalCard /></Link>
      </section>
    </>
  );
};

export default ProfesionalByCategory;
