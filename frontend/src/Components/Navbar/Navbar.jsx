import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState(localStorage.getItem("user"));
  const [auth, setAuth] = useState(false);

  useEffect(() => {

  setAuth(localStorage.getItem("auth"));
  setUser(localStorage.getItem("user"));
  

  }, [localStorage.getItem("user")]);
  
  //  const handleClick = () => {
      
  //   };
  
    const handleLogin = () => {
     if(auth){
      console.log("Cerrando sesion... "+auth);
      localStorage.removeItem("user");
      localStorage.removeItem("pwd");
      localStorage.setItem("auth", false);
      setUser("");
      setAuth(false);
     } 
    //  else{
    //   navigate("/login-usuario") ;
    //   setUser("");
    //   localStorage.setItem("auth", false);
    //  } 
      
    };

    

  return (
    <>
      <nav className=" flex justify-around bg-slate-300  py-3 items-center bg-[#275ECD] ">
        <div className=" flex md:mx-auto">
          <img src="../../../src/Images/Logo_SolucionesYa.png" alt="Logo" />
        </div>
        <a href="/" className="flex md:mr-60 font-[400] text-[23px] leading-28 text-[#FFFFFF]">
          Inicio
        </a>
        <div className="w-1/2 flex">
          <div className="flex items-center">
            <a href="" className="md:mr-60 font-[400] text-[23px] leading-28 text-[#FFFFFF]">
              Quienes Somos
            </a>

          </div>

          <div className="flex  gap-1  flex-col">
            <button className="w-[140px]  h-[40px] border-2 border-[#1E9E69] rounded-md font-[400] text-[18px] leading-28 text-[#FFFFFF]">
              Iniciar Sesion
            </button>
            <button className="w-[140px] h-[40px] border border-none rounded-md bg-[#1E9E69] font-[400] text-[18px] leading-28 text-[#FFFFFF]">
            Registrarme
            </button>
          </div>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
