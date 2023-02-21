import React from "react";
import { AiFillStar } from "react-icons/ai";

const ProfesionalCard = () => {
  return (
    <>
      <div className=" bg-blue-primary border p-[25px] border-black w-[504px] h-[184.8px] rounded-lg flex gap-3  ">
        <div className="w-[92.2px] h-[135.63px]  ">
          <div className="w-[92.2px] h-[92.2px] bg-green-secondary rounded-lg"></div>
          <div className="flex px-1">
            <AiFillStar className="text-white"/>
            <AiFillStar className="text-white"/>
            <AiFillStar className="text-white"/>
            <AiFillStar className="text-white"/>
            <AiFillStar className="text-white"/>
          </div>
          <span className="text-[15px] text-white">45 opiniones</span>
        </div>
        <div className="w-[350.34px] h-[135.59px] grid grid-cols-1 grid-col ">
          <h1 className="text-left text-2xl font-bold text-white ">
            Roberto Perez
            <span className="ml-3  font-normal text-[15px]  ">
              Electricista matriculado
            </span>
          </h1>
          <p className="text-white">
            Se maneja por: <span className="font-bold">Villa Lugano</span>
          </p>
          <p className="text-white">Trabajos completados: 5</p>
        </div>
      </div>
    </>
  );
};

export default ProfesionalCard;
