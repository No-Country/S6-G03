import React from 'react'
import { AiOutlineArrowLeft, AiOutlineDown } from "react-icons/ai"
import { MdLocationOn, MdRadar } from "react-icons/md";
import mapa from "../../assets/images/mapa.jpg"
import ProfesionalCard from "./ProfesionalCard";

const ProfesionalByZone = () => {
    return (
        <section className="w-[80%] mx-auto mt-[156px] container">
            <div className="flex  items-center">
                <AiOutlineArrowLeft className="w-[48px] h-[48px] fill-blue-primary " />
                <div className="flex ">
                    <span className="text-[45px] font-bold">Resultados de:</span>
                    <p className="   text-[45px] ">  Plomero </p>
                </div>
            </div>
            <div className="flex  items-center">
                <MdLocationOn className="w-[48px] h-[48px]  " />
                <div className="flex ">
                    <span className="text-[45px] font-bold">Seleccione su zona en el mapa</span>
                </div>
            </div>
            <div className='flex gap-6'>
                <input type="text" className='w-[834px] h-16 md-rounded border-4 border-blue-600' />
                <div className="flex bg-green-secondary rounded-md md:w-[174px] md:h-[44px] text-center items-center  translate-y-4 ">
                    <span className="flex text-white font-bold text-center ml-4 mr-4 text-[23px]">Filtrar por</span>
                    <AiOutlineDown className=" w-6 h-6 text-white" />
                </div>
            </div>
            <div className='mt-6 flex flex-col  '>
                <div className='flex-row '>
                    <div className='flex '>
                        <img src={mapa} alt="mapa" />
                    </div>
                    <div className='flex mt-16'>
                        <MdRadar className='w-[48px] h-[48px]' />
                        <h1 className='font-bold text-[32px]'>Profesionales cerca de tu zona</h1>
                    </div>
                </div>
            </div>
            <section className='grid grid-cols-2 gap-4 mt-2'>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>
                <ProfesionalCard/>


            </section>



        </section>
    )
}

export default ProfesionalByZone