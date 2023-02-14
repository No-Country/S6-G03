import "./login.css";
import { BsFillEyeFill, BsGoogle, BsFacebook } from "react-icons/bs";
const Login = () => {
  return (
    <>
      <div className="flex flex-wrap min-h-screen w-full content-center justify-center  py-10">
        <div className="flex flex-wrap content-center justify-center rounded-l-md bg-white">
          <div className="w-auto">
            <h1 className=" text-left font-bold sesion pt-2">Iniciar sesión</h1>

            <form className="mt-4">
              <div className="mb-3">
                <label className="mb-2 flex text-left correo font-bold">
                  Correo
                </label>
                <input
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
                  placeholder="************"
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
                <div className="relative inset-y-0 flex items-center">
                  <BsFillEyeFill className=" absolute top-1/2 right-3 -translate-y-7" />
                </div>
              </div>

              <div className="mb-3  text-right flex-wrap content-end">
                <a
                  href="#"
                  className="text-right  olvidar font-normal underline text-zinc-900"
                >
                  Olvidé mi usuario o contraseña
                </a>
              </div>
              <div className="text-right pb-4">
                <span className=" text-gray-400 cuenta font-normal">
                  No tienes cuenta?
                </span>
                <a href="#" className=" font-normal cuenta pl-2 text-blue-700">
                  Registrate aquí
                </a>
              </div>

              <div className="mb-3">
                <button className="mb-1.5  w-full  font-normal  text-center text-white bg-blue-600 hover:bg-blue-800 px-2 py-1.5 rounded-2xl">
                  Ingresar
                </button>
                <button className="flex bg-white font-normal ml-6 flex-wrap justify-center w-72  border border-gray-300 hover:border-gray-500 px-2 py-1.5 rounded-md">
                  Iniciar sesión con Google
                  <div className="relative inset-y-0 flex items-center">
                    <BsGoogle className="absolute mt-6 ml-3" />
                  </div>
                </button>
                <button className="flex bg-white  mt-3 flex-wrap ml-6  justify-center w-72  border border-gray-300 hover:border-gray-500 px-2 py-1.5 rounded-md">
                  Iniciar sesión con Facebook
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

export default Login;
