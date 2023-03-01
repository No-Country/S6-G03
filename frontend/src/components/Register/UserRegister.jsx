import "./userRegister.css";
import { BsFillEyeFill, BsGoogle, BsFacebook } from "react-icons/bs";
import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { redirect } from "react-router-dom";
import Swal from "sweetalert2";

const UserRegister = () => {
  const navigate = useNavigate();
  const [nav, setNav] = useState([]);
  const [nombre, setNombre] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [alerta, setAlerta] = useState();
  const [showPwd, setShowPwd] = useState(false);
  let errorMessage = "";
  let fakeRegister = true;

  useEffect(() => {
    // console.log("user es "+email);
    // console.log("pwd es "+password);
    // console.log("local user es "+localStorage.getItem("user"));
    // console.log("local pwd es "+localStorage.getItem("pwd"));
  }, []);

  const handleLogin = async (e) => {
    e.preventDefault();

    if ([nombre, email, password].includes("")) {
      console.log("tried to prevent");
      Swal.fire("Todos los campos son obligatorios", errorMessage, "error");
    }
    if ((fakeRegister = true)) {
      localStorage.setItem("user", email);
      localStorage.setItem("pwd", password);

      await Swal.fire(
        "¡Exito!",
        `¡Tu cuenta ha sido creada con exito !`,
        "success"
      ).then((result) => {
        console.log("awainting to redirect");
        if ((result.isDismissed = true)) {
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
            <h1 className=" text-left font-bold sesion pt-2">Registrarse</h1>

            <form id="login" className="mt-4" onSubmit={handleLogin}>
              <div className="mb-3">
                <label className="mb-2 flex text-left nombre font-bold">
                  Nombre
                </label>
                <input
                  value={nombre}
                  onChange={(e) => setNombre(e.target.value)}
                  autoComplete="on"
                  id="text"
                  type="text"
                  required
                  placeholder="John Doe"
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
                <label className="mb-2 mt-2 flex text-left correo font-bold">
                  Correo
                </label>
                <input
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  autoComplete="on"
                  id="email"
                  type="email"
                  placeholder="ejemplo@correo.com"
                  required
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
              </div>

              <div className="mb-3">
                <label className="mb-2 flex text-left correo font-bold">
                  Contraseña
                </label>
                <input
                  type={showPwd ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  autoComplete="off"
                  id="password"
                  required
                  placeholder="************"
                  className="block w-full rounded-md border border-gray-300 focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500 py-1 px-1.5 text-gray-500"
                />
                <div
                  onClick={() => setShowPwd(!showPwd)}
                  className="relative inset-y-0 flex items-center"
                >
                  {showPwd ? (
                    <BsFillEyeFill className=" absolute top-1/2 right-3 -translate-y-7" />
                  ) : (
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      fill="currentColor"
                      height={"1.1rem"}
                      className=" absolute top-1/2 right-3 -translate-y-7"
                    >
                      <path d="M3.53 2.47a.75.75 0 00-1.06 1.06l18 18a.75.75 0 101.06-1.06l-18-18zM22.676 12.553a11.249 11.249 0 01-2.631 4.31l-3.099-3.099a5.25 5.25 0 00-6.71-6.71L7.759 4.577a11.217 11.217 0 014.242-.827c4.97 0 9.185 3.223 10.675 7.69.12.362.12.752 0 1.113z" />
                      <path d="M15.75 12c0 .18-.013.357-.037.53l-4.244-4.243A3.75 3.75 0 0115.75 12zM12.53 15.713l-4.243-4.244a3.75 3.75 0 004.243 4.243z" />
                      <path d="M6.75 12c0-.619.107-1.213.304-1.764l-3.1-3.1a11.25 11.25 0 00-2.63 4.31c-.12.362-.12.752 0 1.114 1.489 4.467 5.704 7.69 10.675 7.69 1.5 0 2.933-.294 4.242-.827l-2.477-2.477A5.25 5.25 0 016.75 12z" />
                    </svg>
                  )}
                </div>
              </div>
              <div className="text-right pb-4">
                <span className=" text-gray-400 cuenta font-normal">
                  ¿Ya tienes cuenta?
                </span>
                <Link to="/login-usuario">
                  <a href="" className=" font-normal cuenta pl-2 text-blue-700">
                    Accede aquí
                  </a>
                </Link>
              </div>

              <div className="mb-3">
                <button
                  type="submit"
                  form="login"
                  className="mb-1.5  w-full  font-normal  text-center text-white bg-blue-600 hover:bg-blue-800 px-2 py-1.5 rounded-2xl"
                >
                  Registrarme
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
