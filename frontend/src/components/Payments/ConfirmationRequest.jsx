import React from 'react'
import { AiOutlineArrowLeft } from "react-icons/ai"

const ConfirmationRequest = () => {
    return (
        <section className="w-[80%] mx-auto mt-[156px] container mb-10 ">
            <div className="flex  items-center mb-6">
                <AiOutlineArrowLeft className="w-[48px] h-[48px] fill-blue-primary " />
                <div className="flex ">
                    <span className="text-[45px] font-bold">Volver</span>
                </div>
            </div>
            <div className='w-full h-[340px] lg:w-[880px] lg:h-[340px] items-center border-8  border-blue-400 rounded-[50px] grid grid-cols-1 lg:grid-cols-2 p-4'>
                <div className=' flex justify-between col-span-2 '>
                    <span className='text-[45px] font-bold'>Confirmá tu solicitud</span>
                    <span className='text-[32px] translate-y-14'>Costo</span>
                </div>
                <div className='flex justify-between col-span-2'>
                    <span className='text-[32px]'>Pedido de Presupuesto: <span className='font-bold'>Alberto Paez</span></span>
                    <span className='text-[32px] font-bold'>$1000</span>
                </div>
                <span className=' h-[2px] w-full bg-gray-500 col-span-2 '></span>
                <div className='flex justify-between col-span-2'>
                    <span className='text-[32px]'>Total</span>
                    <span className='text-[32px] font-bold'>$1000</span>
                </div>
            </div>
            <div className='mt-20 ml-4'>
                <p className='font-bold text-[32px]'>La contraction incluye:</p>
                <p className='text-[32px]'>Presupuesto del servicio en el siguiente domicilio</p>
            </div>
            <div className='mt-16 ml-4'>
                <p className='text-[32px] '>
                    <span className='font-bold'>Domicilio:</span>
                    Merlo 4061 - Remedios de Escalada CP 1826                   
                    </p>
                    <p className='text-[32px] '><span className='font-bold '>Entre las calles:</span> Sáenz Peña - De la Cruz</p>
                <p className='text-[32px]'>Presupuesto del servicio en el siguiente domicilio</p>
            </div>
            <div className='mt-10 flex gap-4 mb-24 ml-4'>               
                <p className='text-[32px] '><span className='font-bold'>Contacto:</span> 11 6228-3220(Esteban Rivellota)</p>
                <button className='border-4 text-blue-primary-80 text-[23px] font-bold border-gray-400 lg:translate-x-4 lg:translate-y-16 rounded-full w-[219] h-16 px-4 py-2'>
                Editar / Elegir otra
            </button>
            </div>
            <span className=' h-2 w-[57%] bg-black block mb-4'></span>
           
            <button className='w-[880px] h-16 text-white bg-green-secondary-80 rounded-xl text-[32px] font-bold mb-20'>
                Confirmar
            </button>

        </section>
    )
}

export default ConfirmationRequest