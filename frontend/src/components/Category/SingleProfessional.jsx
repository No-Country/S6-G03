import React from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import "./SingleProfessional.css";
import {professionals} from "./dummyDB.js";
import { BsFillGeoAltFill, BsChevronLeft} from "react-icons/bs";



const SingleProfessional = () => {
  const { id } = useParams();
  const [professional, setProfessional] = useState(professionals[0]);
  let reviewsCount = 0;

  const getSkills = (p)=>{
    console.log(p.skills);
    p.skills.map((i)=>{
      console.log(i);})
  } 
  useEffect(() => {
//     const url = `${import.meta.env.VITE_BACKEND_URL}/api/profesional/${id}`;
getSkills(professional);
//     axios
//       .get(url)
//       .then((res) => setOneProduct(res.data))
//       .catch((err) => console.log(err));
 //setProfessional(professionals[0]);
 


  }, []);
  console.log(professional.reviews.length);

  return (
  <>
    



    
    <div className="flex w-[1032px] mx-auto my-5  justify-between">
      <div className="flex align-middle items-center text-black text-3xl font-bold"><span className="text-3xl font-black mr-5 "><Link><BsChevronLeft stroke-width="1"/></Link></span>{professional.title}</div>
      <div className=""><span class="bg-[#89F0C5]  text-xl font-bold  inline-flex items-center px-5 py-3 rounded-3xl">
      {/* <svg aria-hidden="true" class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd"></path></svg> */}
      <BsFillGeoAltFill className=" mr-1"/>
      {professional.zone}
      </span></div>
    </div>



    <div className="flex w-100  professional-card mx-auto my-5  text-white" >
    <div className=" text-center   gap-7 w-1/4">
        <div className="object-top rounded"><img className="pf-image mx-auto" src="/pf-image.jpg" alt="" /></div>
        <div className="py-32 flex"></div>
        <div className=" my-5">
        <h5 className="card-title">{professional.firstName} {professional.lastName}</h5>
        <p className="card-text">(rating)({professional.reviews.length?professional.reviews.length:0})</p>
        <a href="#" className="btn btn-primary">Ver Reseñas</a>
        </div>
        
    </div>
    <div className="col-2 w-3/4 gap-7">
        <div className="text-white">{professional.description}</div>
        <div className="pt-4"><span className="mt-1">Otros servicios que presta:</span>  {professional.skills.map((item)=>{ return(
          <span class="bg-blue-100 text-black text-sm  font-semibold mr-2 px-3 py-1  rounded ">{item}</span>
        )
          
        })}</div>
        <h3 className="my-5 text-5xl">Valor Presupuesto: $ {professional.budget}</h3>
        <div class="flex space-x-2 justify-center">
        <button
            type="button"
            data-mdb-ripple="true"
            data-mdb-ripple-color="light"
            class="inline-block w-full px-6 py-4 bg-green-secondary text-white font-bold text-lg leading-tight uppercase rounded shadow-md hover:bg-green-secondary/60 hover:shadow-lg focus:bg-green-secondary/60 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out"
        >Contratar</button>
        </div>
        <p className="pt-4">La Contratacion Incluye: Visita al domicilio y presupuesto del servicio</p>
    </div>
    </div>
        <div className="container mx-auto max-w-sm: py-5">
          <div className="flex align-middle items-center text-black text-3xl font-bold">Trabajos Realizados</div>
          <div className="flex">
          <div class="flex justify-center">
  <div class="block rounded-lg shadow-lg bg-white max-w-sm text-center">
    <div class="flex w-100 justify-between py-3 px-6 border-b border-gray-300">
     <div className=" "><span className="font-bold">{professional.jobs[0].title+" "}</span><span className="text-xs">({professional.jobs[0].date})</span></div><div className="flex"><span class="bg-[#B6B8B8]  text-sm font-bold  inline-flex items-center px-3 py-1 rounded-3xl">
      {/* <svg aria-hidden="true" class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd"></path></svg> */}
      <BsFillGeoAltFill className=" mr-1"/>
      {professional.jobs[0].zone}
      </span></div>
    </div>
    <div class="p-6">
    <a href="#!" data-mdb-ripple="true" data-mdb-ripple-color="light">
      <img class="rounded-t-lg" src="https://mdbootstrap.com/img/new/standard/nature/182.jpg" alt=""/>
    </a>
    </div>
    <div class="py-3 px-6 border-t border-gray-300 text-gray-600 text-n">
      2 days ago
    </div>
  </div>
</div>
          </div>
        </div>
    

  </>);
};

export default SingleProfessional;