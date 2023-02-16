import React from "react";
import { Outlet } from "react-router-dom";

function Direcciones() {
    return (
        <div className="flex flex-col items-center justify-center">

            <div className="flex flex-row p-8 w-[856px] items-center justify-start">
               <div>
                    <ul className="flex flex-row -mb-px">
                        <li className="mr-2 flex flex-col justify-center">
                            <a href="#" className="border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">
                                <img src="../../../src/Images/BackArrow.png" alt="back" />
                            </a>
                        </li>
                    </ul>
               </div>

                <div className="pl-[170px]">
                    <span className="font-[700] text-[45px] leading-64 flex flex-col justify-center items-center">Mis Direcciones</span>
                </div>
            </div>

           

            <div className="flex flex-col w-[700px]">
                <div>
                    <form action="">
                        <div className="grid grid-cols-2 gap-y-6 gap-x-4 justify-items-stretch p-8">
                            <div>
                                <div><label htmlFor="">Nombre Completo</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            <div>
                                <div><label htmlFor="">Calle / Avenida</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            <div>
                                <div><label htmlFor="">Ciudad</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            <div>
                                <div><label htmlFor="">Provincia</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            <div>
                                <div><label htmlFor="">Codigo Postal</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            <div>
                                <div><label htmlFor="">Telefono</label></div>
                                <input type="text" className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"/>
                            </div>

                            
                        </div>
                        <div className="flex flex-col w-full justify-evenly p-8">
                            <div className="inline-flex flex-start">
                                <span className="font-[700] text-[20px] leading-64 flex flex-col justify-center items-center">¿Que tipo de direccion es?</span>
                            </div>
                            <div className="flex flex-row w-full justify-between">
                                <div className="flex flex-row items-center justify-evenly">
                                    <button type="radio" className="mx-2 border-4 border-[#9C9D9D] h-[25px] w-[25px]"></button>  
                                    <img src="../../../src/Images/House.png" alt="back" width="25" height="25" className="mx-1"/>
                                    <label className="font-[400] text-[20px]">Domicilio Personal</label>
                                </div>
                                <div className="flex flex-row items-center">
                                    <button type="radio" className="mx-2 border-4 border-[#9C9D9D] h-[25px] w-[25px]"></button>  
                                    <img src="../../../src/Images/Suitcase.png" alt="back" width="25" height="25" className="mx-1"/>
                                    <label className="font-[400] text-[20px]">Domicilio Trabajo</label>
                                </div>
                            </div>
                        </div>

                        <div className="p-8">
                            <span className="font-[700] text-[25px] leading-64 flex flex-col justify-center items-center">Agregar indicaciones de este domicilio</span>
                            <div className="flex flex-col justify-center items-center">
                                <textarea type="text" maxLength="300" className="w-full h-[100px] bg-[#EBECEC] rounded-[10px] border-4 border-[#B6B8B8]" />
                            </div>
                            <span className="inline-block relative float-right">100/300</span>
                        </div>

                        <div className="flex flex-row justify-center items center p-8">
                            <button className="bg-[#1E9E69] w-[856px] h-[47px] rounded-[12px] content-center font-[700] text-[20px] leading-28 text-white">Guardar direccion</button>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    )
}

export default Direcciones;