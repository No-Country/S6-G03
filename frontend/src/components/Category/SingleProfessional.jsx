import React from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import "./SingleProfessional.css";
import {professionals} from "./dummyDB.js";



const SingleProfessional = () => {
  const { id } = useParams();
  const [professional, setProfessional] = useState(professionals[0]);
  let reviewsCount = 0;
  useEffect(() => {
//     const url = `${import.meta.env.VITE_BACKEND_URL}/api/profesional/${id}`;

//     axios
//       .get(url)
//       .then((res) => setOneProduct(res.data))
//       .catch((err) => console.log(err));
 //setProfessional(professionals[0]);



  }, []);
  console.log(professional.reviews.length);

  return (
  <>
    <div></div>
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
        <div>Otros servicios que presta:</div>
        <h3 className="my-5 text-5xl">Valor Presupuesto: $ {professional.budget}</h3>
        <div class="flex space-x-2 justify-center">
        <button
            type="button"
            data-mdb-ripple="true"
            data-mdb-ripple-color="light"
            class="inline-block w-full px-6 py-4 bg-green-secondary text-white font-bold text-lg leading-tight uppercase rounded shadow-md hover:bg-green-secondary/60 hover:shadow-lg focus:bg-green-secondary/60 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out"
        >Contratar</button>
        </div>
        <p>La Contratacion Incluye: Visita al domicilio y presupuesto del servicio</p>
    </div>
    </div>

    <div className="flex justify-center">
  <div className="flex flex-col md:flex-row md:max-w-xl rounded-lg bg-white shadow-lg">
    <img className=" w-full h-96 md:h-auto object-cover md:w-48 rounded-t-lg md:rounded-none md:rounded-l-lg" src="https://mdbootstrap.com/wp-content/uploads/2020/06/vertical.jpg" alt />
    <div className="p-6 flex flex-col justify-start">
      <h5 className="text-gray-900 text-xl font-medium mb-2">Card title</h5>
      <p className="text-gray-700 text-base mb-4">
        This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
      </p>
      <p className="text-gray-600 text-xs">Last updated 3 mins ago</p>
    </div>
  </div>
</div>

  </>);
};

export default SingleProfessional;
