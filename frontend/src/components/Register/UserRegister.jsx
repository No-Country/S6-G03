import "./userRegister.css";
import { BsFillEyeFill, BsGoogle, BsFacebook } from "react-icons/bs";
import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { redirect } from "react-router-dom";
import Swal from "sweetalert2";

const UserRegister = () => {
  const navigate = useNavigate();
  const [nav, setNav] = useState([]);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [alerta, setAlerta] = useState();
  let errorMessage="";
  let fakeRegister=true;


  useEffect(() => {
    // console.log("user es "+email);
    // console.log("pwd es "+password);
    // console.log("local user es "+localStorage.getItem("user"));
    // console.log("local pwd es "+localStorage.getItem("pwd"));

  }, [])

  const handleLogin = async (e) => {
    e.preventDefault();

    if ([email, password].includes("")) {
      console.log("tried to prevent");
      Swal.fire('Todos los campos son obligatorios', errorMessage, 'error');}
      if(fakeRegister=true){
        localStorage.setItem("user", email);
        localStorage.setItem("pwd", password);
        
        await Swal.fire(
          "¡Exito!",
          `¡Tu cuenta ha sido creada con exito !`,
          "success"
        ).then((result)=>{
          console.log("awainting to redirect");
          if(result.isDismissed=true){
            localStorage.setItem("auth", true);
            console.log("redirecting...");
            navigate("/");
            //history.replace("/");
          }
          
        });
        
       
      }
    
    
  };
  
  return (
    <>
      <div className="flex flex-wrap min-h-screen w-full content-center justify-center  py-10">
        <div className="flex flex-wrap content-center justify-center rounded-l-md bg-white">
          <div className="w-auto">
            <h1 className=" text-left font-bold sesion pt-2">Registrate</h1>

            <form id="login" className="mt-4" onSubmit={handleLogin}>
              <div className="mb-3">
                <label className="mb-2 flex text-left correo font-bold">
                  Correo
                </label>
                <input
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  autoComplete="on"
                  id="email"
                  type="email"
                  placeholder="ejemplo@correo.com"
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
              </div>

              <div className="mb-3">
                <label className="mb-2 flex text-left correo font-bold">
                  Contraseña
                </label>
                <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  autoComplete="off"
                  id="password"
                  placeholder="************"
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
                <div className="relative inset-y-0 flex items-center">
                  <BsFillEyeFill className=" absolute top-1/2 right-3 -translate-y-7" />
                </div>
              </div>

              {/* <div className="mb-3  text-right flex-wrap content-end">
                <a
                  href="#"
                  className="text-right  olvidar font-normal underline text-zinc-900"
                >
                  Olvidé mi usuario o contraseña
                </a>
              </div> */}
              <div className="text-right pb-4">
                <span className=" text-gray-400 cuenta font-normal">
                  ¿Ya eres usuario?
                </span>
                <Link to="/login-usuario"><a href="" className=" font-normal cuenta pl-2 text-blue-700">
                  Accede aquí
                </a>
                </Link>
              </div>

              <div className="mb-3">
                <button type="submit" form="login"  className="mb-1.5  w-full  font-normal  text-center text-white bg-blue-600 hover:bg-blue-800 px-2 py-1.5 rounded-2xl">
                  Registrarse
                </button>
                <button className="flex bg-white font-normal ml-6 flex-wrap justify-center w-72  border border-gray-300 hover:border-gray-500 px-2 py-1.5 rounded-md">
                  Registrarse con Google
                  <div className="relative inset-y-0 flex items-center">
                    <BsGoogle className="absolute mt-6 ml-3" />
                  </div>
                </button>
                <button className="flex bg-white  mt-3 flex-wrap ml-6  justify-center w-72  border border-gray-300 hover:border-gray-500 px-2 py-1.5 rounded-md">
                  Registrarse  con Facebook
                  <div className="relative inset-y-0 flex items-center">
                    <BsFacebook className="absolute mt-6 ml-3" />
                  </div>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserRegister;