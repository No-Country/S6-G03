import React from 'react'
import { AiOutlineArrowLeft } from "react-icons/ai";
import { Link, useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import { BrowserRouter } from "react-router-dom";
import './PaymentMethods.css';

const PaymentMethods = () => {
    const [option, setOption]= useState(null)
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
    const notify = () => toast(<h1 className='text-red-50'>Wow so easy!</h1>);

    useEffect(() => {
            if(localStorage.getItem("previewOrder")){
                setOrder(JSON.parse(localStorage.getItem("previewOrder")));
                
            }
        
    }, [])

    const MySwal = withReactContent(Swal)
    // const ResultMessage = withReactContent(Swal)

  const showResult= ()=>{
    Swal.fire({
        
        title: '¡Tu solicitud fue realizada con exito!',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Ver mis solicitudes',
        denyButtonText: 'Ir al inicio',
        customClass: {
          actions: 'my-actions',
          cancelButton: 'order-1 right-gap',
          confirmButton: 'order-2',
          denyButton: 'order-3',
        }
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire('Saved!', '', 'success')
        } else if (result.isDenied) {
          Swal.fire('Changes are not saved', '', 'info')
        }
      })
  }

    const showSwalWithLink = () => {
        MySwal.fire({
            customClass: {
                // container: 'checkoutCard',
                popup: "checkoutCard",
                
              },
            width: 856,
          html: (
            // <BrowserRouter>
            //   <Link to="/" onClick={() => Swal.close()}>
            //     Navigate to /about
            //   </Link>
            // </BrowserRouter>
            <BrowserRouter>
            <div className='block text-center align-middle pt-10  text-black gap-14 rounded-[40px]'>
            <h3 className="font-bold leading-10 text-[45px] mt-3 mb-6">¡Tu solicitud fue realizada con éxito!</h3>
            <p  className="text-3xl my-6">A la brevedad, el profesional {} se pondra en contacto para coordinar la visita a tu domicilio</p>
            <h4 className="text-3xl my-6 font-bold">Gracias por elegir Soluciones YA!</h4>
            <div className='my-10 flex text-right justify-end'>
                <Link onClick={() =>{
                    Swal.close();
                    navigate("/");
                }}><span className='text-blue-primary-80 text-[32px] mr-5'>Ver Mis Solicitudes</span></Link><span>{"  "}</span><Link onClick={() =>{
                    Swal.close();
                    navigate("/");
                } }><span className="text-blue-primary-80 text-[32px]">Ir a inicio</span></Link>
            </div>
            </div>
            </BrowserRouter>
            
          ),
          showConfirmButton:false,
          

        });
      };

    const makePayment = (e)=>{
        e.preventDefault();
        if(option){
            setOrder({
                ...order,
                paymentOption:option
            });
            localStorage.removeItem("previewOrder");
            console.log(order);
            localStorage.setItem("order",JSON.stringify(order));
            //showResult();
            showSwalWithLink();
        }else{
            Swal.fire({
                width:800,
                title: '¡Debes seleccionar una opción de pago!',
                confirmButtonColor: '#1E9E69'
                
              })
        }
       
       // showSwalWithLink();
        //notify();
        //navigate(`/?payment=success`);

    }

    const onOptionChange = e => {
        setOption(e.target.value)
      }

    return (
        <section className="w-[80%] mx-auto mt-[156px]  mb-10 container grid place-items-center">
            <div className="flex -translate-x-[360px] items-center mb-6">
                <Link onClick={()=> navigate(-1)}><AiOutlineArrowLeft className="w-[48px] h-[48px] fill-blue-primary " /></Link>
                <div className="flex ">
                    <span className="text-[45px] font-bold">Volver</span>
                </div>
            </div>
            <form id="payment" className="mt-4" onSubmit={makePayment}>
            <div className='w-full h-[340px] lg:w-[880px] lg:h-[340px] items-center border-8  border-blue-400 rounded-[50px] p-4'>
                <div className=' flex justify-center mb-10 '>
                    <span className='text-[45px] font-bold'>Medios de Pago</span>
                </div>
                <div className='flex flex-col ml-10' >
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio" 
                            value="debit" 
                            id='debit' 
                            checked={option==='debit'} 
                            onChange={onOptionChange}
                            className='w-6 h-6 ' />
                        <p className='text-[23px]'>Tarjeta de Débito</p>
                    </div>   
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio"
                         value="credit" 
                         id='credit' 
                         checked={option==='credit'} 
                         onChange={onOptionChange}
                         className='w-6 h-6 ' />
                        <p className='text-[23px]'>Tarjeta de Crédito</p>
                    </div>  
                    <div className='flex gap-2 flex-row p-2'>
                        <input type="radio" 
                        value="mercadopago" 
                        id='mercadopago' 
                        checked={option==='mercadopago'} 
                        onChange={onOptionChange}
                        className='w-6 h-6 ' />
                        <p className='text-[23px]'>Mercado Pago</p>
                    </div>    
                   

                </div>
            </div>
            {/* onClick={makePayment} */}
            <button  className='w-[880px] h-16 text-white mt-20 bg-green-secondary-80 rounded-xl text-[32px] font-bold mb-20'>
                Pagar
            </button>
            </form>
            
        </section>
        
    )
}

export default PaymentMethods