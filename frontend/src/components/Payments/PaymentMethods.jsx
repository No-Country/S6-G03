import React from 'react'
import { AiOutlineArrowLeft } from "react-icons/ai"

const PaymentMethods = () => {
    return (
        <section className="w-[80%] mx-auto mt-[156px]  mb-10 container grid place-items-center">
            <div className="flex -translate-x-[360px] items-center mb-6">
                <AiOutlineArrowLeft className="w-[48px] h-[48px] fill-blue-primary " />
                <div className="flex ">
                    <span className="text-[45px] font-bold">Volver</span>
                </div>
            </div>
            <div className='w-full h-[340px] lg:w-[880px] lg:h-[340px] items-center border-8  border-blue-400 rounded-[50px] p-4'>
                <div className=' flex justify-center mb-10 '>
                    <span className='text-[45px] font-bold'>Medios de Pago</span>
                </div>
                <div className='flex flex-col ml-10 '>
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio" className='w-6 h-6 ' />
                        <p className='text-[23px]'>Tarjeta de Débito</p>
                    </div>   
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio" className='w-6 h-6 ' />
                        <p className='text-[23px]'>Tarjeta de Crédito</p>
                    </div>  
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio" className='w-6 h-6 ' />
                        <p className='text-[23px]'>Mercado Pago</p>
                    </div>    
                   

                </div>
            </div>
            <button className='w-[880px] h-16 text-white mt-20 bg-green-secondary-80 rounded-xl text-[32px] font-bold mb-20'>
                Pagar
            </button>
        </section>
    )
}

export default PaymentMethods