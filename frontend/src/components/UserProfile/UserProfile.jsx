import React, { useState } from "react";
import { Link, Outlet } from "react-router-dom";
import EditIcon from "../../../src/assets/images/edit.png";
import DeleteIcon from "../../../src/assets/images/delete.png";
import BackArrowIcon from "../../../src/assets/images/BackArrow.png";
import PlusIcon from "../../../src/assets/images/PlusCircle.png";

function UserProfile(){

    const [input, setInput] = useState({
        username: "",
        email: "",
        password: "",
        dni: "",
        phone: ""
    })

    function handleInput(e) {
        setInput({
            ...input,
            [e.target.name]: e.target.value
        })
    }

    return(
        <div>
            <div>
                <ul className="flex flex-row -mb-px justify-evenly">
                    <li className="mr-2 flex flex-col justify-center">
                        <a href="#" className="border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">
                            <img src={BackArrowIcon} alt="back" />
                        </a>
                    </li>

                    <li className="mr-2 font-[700] text-[30px]">
                        <a href="#" className="inline-block p-4 border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">Mis Datos</a>
                    </li>

                    <li className="mr-2 font-[700] text-[30px]">
                        <a href="#" className="inline-block p-4 border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">Mis Pedidos</a>
                    </li>
          
                </ ul>
            </div>

{/* Here are the form details wrapped in a container */}
      
        <div className="flex flex-col justify-center items-center p-4">
            <div>
                <span className="font-[700] text-[45px] leading-64 flex flex-col justify-center items-center">Mi Perfil</span>
            </div>
            
                <div className="flex flex-col justify-around items-center bg-[#B6B8B8] w-[210px] h-[210px] rounded-full">
                    {/* Operador ternario, si no hay una imgaen, mostrar la form. Si la hay, mostrar un boton para editar */}
                    <form className="items-center justify-center mb-auto">
                    <label htmlFor="image" className="cursor-pointer">
                        {/* <img src="../../../src/assets/images/+.png" alt="" /> */}
                        <i className="font-[700] text-[70px]  flex col justify-center items-center p-0 m-0">+</i>
                    </label>
                        <input 
                        className="hidden"
                        type="file"
                        id="image" name="image"
                        accept="image/png, image/jpeg" />
                        <br />
                        <button className="font-[700] text-[18px]">Cargar imágen</button>
                    </form>
                </div>
            </div>



            <div className="flex flex-col items-center justify-center w-full p-6">
                <form>
                    <div className="flex flex-row justify-between p-2 w-[700px]">
                        <div>
                        <div><label htmlFor="">Usuario</label></div>
                        <input
                            className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"
                            type="text"
                            name="username"
                            value={input.username}
                            onChange={handleInput}
                        />
                        {/* <button className="absolute">Edit</button> */}
                        </div>
                        

                        <div>
                        <div><label htmlFor="">Contraseña</label></div>
                        <input
                            className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"
                            type="password"
                            name="password"
                            value={input.password}
                            onChange={handleInput}
                        />
                        {/* <button className="">Edit</button> */}
                        </div>
                        
                    </div>

                    <div className="p-2">
                    <div><label htmlFor="">Correo electronico</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2 w-full h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"
                        type="text"
                        name="email"
                        value={input.email}
                        onChange={handleInput}
                    />
                    {/* <button className="">Edit</button> */}
                    </div>
                    

                    <div className="flex flex-row justify-between p-2"> 
                        <div>
                        <div><label htmlFor="">DNI</label></div>
                        <input
                            className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"
                            type="text"
                            name="dni"
                            value={input.dni}
                            onChange={handleInput}
                        />
                        {/* <button className="">Edit</button> */}
                        </div>
                       

                        <div>
                        <div><label htmlFor="">Telefono</label></div>
                        <input
                            className="border-solid border-[#B6B8B] border-2 h-[35px] rounded-[10px] font-[400] text-[25px] text-[#3F3F3F]"
                            type="text"
                            name="phone"
                            value={input.phone}
                            onChange={handleInput}
                        />
                        {/* <button className="">Edit</button> */}
                        </div>
                        
                        </div>
                    <div>
                        <button 
                            type="submit" 
                            className="px-4 float-right font-[700] text-[25px] text-[#3F3F3F] border-transparent border-b-2 hover:border-blue-700 dark:hover:text-gray-300">
                            Modificar mis datos
                            </button>
                    </div>
                </form>

                <div>
                    <div className="p-2 flex flex-row justify-start pr-[367px] pt-8">
                        <span className="font-[700] text-[45px] leading-64">Mis direcciones</span>
                    </div>

                    <div className="flex flex-col justify-evenly h-[150px]">
                            <button 
                            id="dropdownDefaultButton"
                            data-dropdown-toggle="dropdown" 
                            className="flex flex-row justify-between items-center w-full bg-[#B6B8B8] hover:bg-gray-400 font-[700] text-[20px] rounded-[10px] px-4 py-1.5 text-center" 
                            type="button">
                                Domicilio personal 
                            <svg 
                            className="w-6 h-6 ml-2" 
                            aria-hidden="true" 
                            fill="none" 
                            stroke="currentColor" 
                            viewBox="0 0 24 24" 
                            xmlns="http://www.w3.org/2000/svg">
                                <path 
                                strokeLinecap="round" 
                                strokeLinejoin="round" 
                                strokeWidth="3" 
                                d="M19 9l-7 7-7-7">
                                </path>
                            </svg>
                        </button>
                            <div id="dropdown" className="grow z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-[700px] dark:bg-gray-700">
                                <ul className="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
                                    <li className="flex flex-col justify-center">
                                        <div className="flex flex-row">
                                            <span className="font-[700] text-md">Calle:&nbsp;</span>
                                            <p>Andres Osuna #125</p>&nbsp;
                                            <span className="font-[700] text-md">Ciudad:&nbsp;</span>
                                            <p>Ciudad de Mexico</p>
                                        </div>
                                       <div className="flex flex-row justify-end gap-10">
                                            <button><img src={EditIcon} alt="add" width="20" height="20"/></button>
                                            <button><img src={DeleteIcon} alt="add" width="20" height="20"/></button>
                                       </div>
                                    </li>
                                    <li className="flex flex-col justify-center">
                                        <span>Calle: Andres Osuna #125</span>
                                       <div className="flex flex-row justify-end gap-10">
                                            <button><img src={EditIcon} alt="add" width="20" height="20"/></button>
                                            <button><img src={DeleteIcon} alt="add" width="20" height="20"/></button>
                                       </div>
                                    </li>
                                    <li className="flex flex-col justify-center">
                                        <span>Calle: Andres Osuna #125</span>
                                       <div className="flex flex-row justify-end gap-10">
                                            <button><img src={EditIcon} alt="add" width="20" height="20"/></button>
                                            <button><img src={DeleteIcon} alt="add" width="20" height="20"/></button>
                                       </div>
                                    </li>
                                </ul>
                            </div>

                        <Link to="editar-direcciones">
                            <button  
                            className="flex flex-row justify-between items-center w-full bg-[#B6B8B8] hover:bg-gray-400 font-[700] text-[20px] rounded-[10px] px-4 py-1.5 text-center"
                            type="button">
                            Agregar Direccion 
                            <img src={PlusIcon} alt="add" width="20" height="20"/>
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    )
};

export default UserProfile;