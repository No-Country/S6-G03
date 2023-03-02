import React from 'react'
import { useState, useEffect } from 'react'
import { AiOutlineArrowLeft } from "react-icons/ai"
import { Link, useNavigate } from 'react-router-dom'

const ConfirmationRequest = () => {
    const [order, setOrder]= useState({
            client:{
              id:"",
            firstName:"",
            lastName:"",
            address:"",
            },
            professional:{
            id:"",
            firstName:"",
            lastName:"",
            },
            price:"",
    });
    const navigate = useNavigate();
    
    useEffect(() => {
        if(localStorage.getItem("previewOrder")){
            setOrder(JSON.parse(localStorage.getItem("previewOrder")));
            console.log(order);
        }
      
    }, [order])

    const createOrder = ()=>{
        let orderID=((Math.random()*10000)+1)
        setOrder({
            ...order, id:orderID
        })
        localStorage.setItem("previewOrder",JSON.stringify(order));
        navigate(`/profesional/${order.professional.id}/medios-de-pago`);
    }
    

   
    return (
        <section className="w-[80%] mx-auto mt-[156px] container mb-10 grid place-items-center ">
      <div className=" xs:-translate-x-40 md:flex md:-translate-x-[410px]   md:items-center mb-6">
               <Link onClick={()=> navigate(-1)}><AiOutlineArrowLeft className="xs:w-[30px] xs:h-[30px] lg:w-[48px] lg:h-[48px] fill-blue-primary " /></Link>
                <div className="flex ">
                    <span className="xs:text-[20px] xs:translate-x-10 xs:-translate-y-8 md:text-[45px] md:translate-x-1 md:translate-y-0 font-bold">Volver</span>  
                </div>
            </div>
            <div className="xs:w-[340px] xs:-translate-x-6 md:w-full md:h-[340px] lg:w-[880px] md:-translate-x-14 lg:h-[340px] items-center border-8  border-blue-400 md:rounded-[50px] grid grid-cols-1 lg:grid-cols-2 p-4">
                <div className=' flex justify-between col-span-2 '>
                    <span className="xs:text-[20px] md:text-[45px] font-bold">Confirmá tu solicitud</span>
                    <span className="xs:text[15px] md:text-[32px] md:translate-y-14">Costo</span>
                </div>
                <div className='flex justify-between col-span-2'>
                    <span className="xs:text-[17px] md:text-[32px]">Pedido de Presupuesto: <span className="xs:text-[17px] md:text-[32px] font-bold">{order.professional.firstName+" "+order.professional.lastName}</span></span>
                    <span className='text-[32px] font-bold'>${order.price}</span>
                </div>
                <span className=' h-[2px] w-full bg-gray-500 col-span-2 '></span>
                <div className='flex justify-between col-span-2'>
                    <span className="xs:text-[17px] md:text-[32px]">Total</span>
                    <span className="xs:text-[17px] xs:pl-3 md:text-[32px] font-bold">${order.price}</span>
                </div>
            </div>
            <div className="xs:mt-4 xs:ml-2 xs:-translate-x-[25px] md:mt-20 md:ml-4 md:-translate-x-36">
                <p className="xs:text-[15px] font-bold md:text-[32px]">La contraction incluye:</p>
                <p className="xs:text-[15px] md:text-[32px]">Presupuesto del servicio en el siguiente domicilio</p>
            </div>
            <div className=" xs:ml-2 xs:-translate-x-[25px] md:mt-2 md:ml-4 md:-translate-x-[105px] ">
                <p className="xs:text-[15px] md:text-[32px] ">
                <span className="xs:text-[14px] xs:-translate-y-4 md:text-[32px]  font-bold">Domicilio:</span>
                    {order.client.address.main}                  
                    </p>
                     <p className=" xs:text-[15px] md:text-[32px] ">
                    <span className="xs:text-[15px] md:text-[32px] font-bold ">Entre las calles:</span> {order.client.address.street}</p>
                
            </div>
            <div className="xs:mt-2 xs:mb-6 xs:-translate-x-[290px] md:mt-10 flex  md:mb-24 md:ml-4 md:-translate-x-[108px]">
              <p className=" xs:text-[15px] md:text-[32px] ">
                 <span className="xs:text-[15px] md:text-[32px]  font-bold">Contacto:</span> {order.client.phone}({order.client.firstName+" "+order.client.lastName})</p>
                <button className="xs:text-[14px] xs:w-[90px] xs:ml-6 xs:h-10 xs:-translate-y-10   xs:translate-x-[280px] xs:pr-1 border-4 text-blue-primary-80  md:text-[23px] font-bold border-blue-400 lg:translate-x-4 lg:-translate-y-2 rounded-full md:w-[150px] md:h-16 md:px-4 md:py-2">
                Editar / Elegir otra
            </button>
            </div>
             <span className="h-2 md:w-[100%] md:-translate-x-12 bg-black block mb-4"></span>
           
            <button onClick={createOrder} className="xs:text-[17px] xs:w-[326px] xs:rounded-none xs:h-10 xs:-translate-x-5 xs:translate-y-10 md:w-[880px] md:h-16 md:-translate-x-12 text-white bg-green-secondary-80 rounded-xl md:text-[32px] font-bold mb-20">
                Confirmar
            </button>


      
    </section>
  );
};

export default ConfirmationRequest;
