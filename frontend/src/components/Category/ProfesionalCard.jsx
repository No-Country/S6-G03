import React from "react";
import { AiFillStar } from "react-icons/ai";

const ProfesionalCard = () => {
  return (
    <>
      <div className=" bg-slate-300 border p-[25px] border-black lg:w-[504px] lg:h-[184.8px] rounded-lg flex gap-3">
        <div className="lg:w-[92.2px] lg:h-[135.63px]  ">
          <div className="lg:w-[92.2px] lg:h-[92.2px] bg-slate-400"></div>
          <div className="flex px-1">
            <AiFillStar />
            <AiFillStar />
            <AiFillStar />
            <AiFillStar />
            <AiFillStar />
          </div>
          <span className="text-[15px]">45 opiniones</span>
        </div>
        <div className="lg:w-[350.34px] lg:h-[135.59px] grid grid-cols-1 grid-col ">
          <h1 className="text-left text-2xl font-bold ">
            Roberto Perez{" "}
            <span className="ml-3  font-normal text-[15px] ">
              Electricista matriculado
            </span>
          </h1>
          <p>
            Se maneja por: <span className="font-bold">Villa Lugano</span>
          </p>
          <p>Trabajos completados: 5</p>
        </div>
      </div>
    </>
  );
};

export default ProfesionalCard;
