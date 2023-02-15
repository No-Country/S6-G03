import React from "react";
import { Link } from "react-router-dom";

function MiPerfil(){
    return(
        <div>
            {/* <nav className="flex flex-row justify-around items-center w-3/8 p-8">
                <div className="font-[700] text-[30px] leading-64"><Link><img src="../../../src/Images/BackArrow.png" alt="back" /></Link></div>
                <div className="font-[700] text-[30px] leading-64"><Link>Mis Datos</Link></div>
                <div className="font-[700] text-[30px] leading-64"><Link>Mis Pedidos</Link></div>
            </nav> */}

            <div class="flex flex-row justify-around items-center font-[700] text-[30px] text-center">
                <ul class="flex flex-row -mb-px">
                    <li class="mr-2">
                        <a href="#" class="inline-block p-4 border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300"><img src="../../../src/Images/BackArrow.png" alt="back" /></a>
                    </li>

                    <li class="mr-2">
                        <a href="#" class="inline-block p-4 border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">Mis Datos</a>
                    </li>

                    <li class="mr-2">
                        <a href="#" class="inline-block p-4 border-b-4 border-transparent rounded-t-lg hover:border-blue-700 dark:hover:text-gray-300">Mis Pedidos</a>
                    </li>
                </ ul>
            </div>

            <div className="flex flex-col justify-center items-center p-4">

            <div>
                <span className="font-[700] text-[45px] leading-64 flex flex-col justify-center items-center">Mi Perfil</span>
            </div>
            
                <div className="flex flex-col justify-around items-center bg-[#B6B8B8] w-[210px] h-[210px] rounded-full">
                    {/* Operador ternario, si no hay una imgaen, mostrar la form. Si la hay, mostrar un boton para editar */}
                    <form className="items-center justify-center mb-auto">
                    <label for="image" className="cursor-pointer">
                        {/* <img src="../../../src/Images/+.png" alt="" /> */}
                        <i className="font-[700] text-[70px] flex flex col justify-center items-center p-0 m-0">+</i>
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

            <div className="flex flex-col items-center justify-between">
                <form className="grid grid-cols-3 gap-2">
                    <div>
                    {/* <label for="edit" ><img src="../../../src/Images/edit.png" alt="edit" width="20px" height="20px" /></label> */}
                   
                   <div><label htmlFor="">Usuario</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2"
                        id="edit"
                        type="text"
                        name="name"
                        value=""
                    />
                    <button className="absolute">Edit</button>
                    </div>
                    <br />

                    <div>
                    <div><label htmlFor="">Contraseña</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2"
                        type="text"
                        name="name"
                        value=""
                    />
                    <button className="">Edit</button>
                    </div>
                    <br />

                    <div>
                    <div><label htmlFor="">Correo electronico</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2 w-full"
                        type="text"
                        name="name"
                        value=""
                    />
                    <button className="">Edit</button>
                    </div>
                    <br />

                    <div>
                    <div><label htmlFor="">DNI</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2"
                        type="text"
                        name="name"
                        value=""
                    />
                    <button className="">Edit</button>
                    </div>
                    <br />

                    <div>
                    <div><label htmlFor="">Telefono</label></div>
                    <input
                        className="border-solid border-[#B6B8B] border-2"
                        type="text"
                        name="name"
                        value=""
                    />
                    <button className="">Edit</button>
                    </div>
                    <br />
                    
                    <div>
                        <button type="submit" className="">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    )
};

export default MiPerfil;