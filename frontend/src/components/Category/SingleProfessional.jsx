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
  const getRating= ()=>{
    let prom= professional.reviews.reduce((partialSum, a) => partialSum + a.rating, 0);
    
    return (prom/professional.reviews.length);
    
  }

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
    

    <div className="flex-row container justify-center mx-auto max-w-sm: py-5">

    
    <div className="flex w-[1032px] mx-auto my-5  justify-between">
      <div className="flex align-middle items-center text-black text-3xl font-bold"><span className="text-3xl font-black mr-5 "><Link><BsChevronLeft stroke-width="1"/></Link></span>{professional.title}</div>
      <div className=""><span class="bg-[#89F0C5]  text-xl font-bold  inline-flex items-center px-5 py-3 rounded-3xl">
      {/* <svg aria-hidden="true" class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd"></path></svg> */}
      <BsFillGeoAltFill className=" mr-1"/>
      {professional.zone}
      </span></div>
    </div>



    <div className="flex flex-auto w-100  professional-card mx-auto my-5  text-white" >
    <div className=" text-center   gap-7 w-1/4">
        <div className="object-top rounded"><img className="pf-image mx-auto" src="/pf-image.jpg" alt="" /></div>
        <div className="py-24 flex"></div>
        <div className=" my-5">
        <h5 className="card-title">{professional.firstName} {professional.lastName}</h5>
        <p className="card-text">(rating)</p>
        {getRating((rating)=>{
          console.log("rating is..."+rating)
        })}
        
        <div class="flex space-x-2  justify-center">
          <button type="button" class="inline-block px-6 py-2.5 bg-green-secondary-70 text-white text-sm font-medium  leading-tight uppercase rounded shadow-md hover:bg-green-secondary-80 hover:shadow-lg focus:green-secondary-80 focus:shadow-lg focus:outline-none focus:ring-0  active:shadow-lg transition duration-150 ease-in-out">Ver Reseñas ({professional.reviews.length?professional.reviews.length:0})</button>
        </div>
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
        
          <div className="flex w-[1032px] mx-auto align-middle items-center mb-5 text-black text-3xl font-bold">Trabajos Realizados</div>
          <div className="flex-initial  w-[1032px] mx-auto justify-center  columns-2" id="pastJobs">
         {professional? (professional.jobs.map((job)=>{
          return(
           <div class="block h-100 w-100 rounded-[16px] shadow-lg bg-neutral-60  text-center mb-10" id="jobCard">
           <div class="flex  justify-between  py-6 px-6 ">
           <div className=""><span className="font-bold">{job.title+" "}</span><span className="text-xs">({job.date})</span></div>
           <div className="flex mt"><span class="bg-neutral-30  text-sm font-bold  inline-flex items-center px-3 py-1 rounded-3xl">
             {/* <svg aria-hidden="true" class="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd"></path></svg> */}
             <BsFillGeoAltFill className=" mr-1"/>
             {job.zone}
             </span></div>
           </div>
           <div class="p-3">
           <a href="#!" data-mdb-ripple="true" data-mdb-ripple-color="light">
             <img class="rounded" src={job.jobImg} alt=""/>
           </a>
           <div id="jobReview" class="py-5 px-5  mt-5 rounded-[16px] bg-neutral-80 text-black text-n">
            <div className="flex justify-start"><strong>{job.client}</strong>{" "}  - Cliente</div>
            <div className="flex text-justify ">{`"${job.review}"`}</div>
           </div>
           </div>
           
         </div>)
         })):"No hay trabajos para mostrar"
        }
          </div>
        </div>
    

  </>);
};

export default SingleProfessional;
