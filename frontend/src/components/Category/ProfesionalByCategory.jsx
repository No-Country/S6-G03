import React from "react";
import { AiOutlineLeft, AiOutlineDown,  } from "react-icons/ai";
import { RxMagnifyingGlass } from "react-icons/rx";
import ProfesionalCard from "./ProfesionalCard";

const ProfesionalByCategory = () => {
  return (
    <>
      <section className="w-[80%] mx-auto mt-[156px]">
        <div className="flex flex-col  gap-4 lg:w-[1022px] lg:h-[144px]  ">
          <div className="flex flex-row  gap-2 items-center">
            <AiOutlineLeft className="h-10 w-10  " />
            <h1 className="flex justify-start text-[45px]">Buscador</h1>
          </div>
          <div className="flex relative items-center">
            <RxMagnifyingGlass className="absolute top-[0.25] left-4 w-10 h-10 text-slate-400 " />
            <input
              type="text"
              className="w-[824px] h-[40px] flex border border-slate-500 rounded-lg bg-slate-200 mr-6 text-center"
            />
            <span className="font-bold flex gap-4">
              Filtrar por <AiOutlineDown className="w-6 h-6" />
            </span>
          </div>
        </div>
      </section>
      <section className="grid grid-cols-2 gap-6 mx-auto  w-[80%] ">
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
        <ProfesionalCard />
      </section>
    </>
  );
};

export default ProfesionalByCategory;
