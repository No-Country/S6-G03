import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import Login from "./components/Login/Login";
import ProfesionalByCategory from "./components/Category/ProfesionalByCategory";
import SingleProfessional from "./components/Category/SingleProfessional";
import ServiceList from "./components/ServiceList/ServiceList";
import UserRegister from "./components/Register/UserRegister";
import ProfesionalByZone from "./components/Category/ProfesionalByZone";
import Footer from "./components/Footer/Footer";
import Home from "./components/Home/Home";
import UserProfile from "./components/UserProfile/UserProfile";
import Addresses from "./components/Addresses/Addresses";
import 'flowbite';

const App = () => {

  return ( 
    <>
    <BrowserRouter>
    <Navbar />
    <Routes>
    <Route index element={<Home />} />
    {/* Rutas de Login */}
    <Route path="login-usuario" element={<Login />} />
    <Route path="login-profesional" element={<h1>Login de Profesional</h1>} />
    {/* Rutas de Registro */}
    <Route
            path="registro-usuario"
            element={<UserRegister/>}
          />
    <Route path="registro-profesional" element={<h1>Registro de  Profesional</h1>} />
    {/* Rutas de Busqueda de profesional */}
    <Route
            path="categorias-profesionales"
            element={<ServiceList/>}
          />
    <Route
            path="profesionales/:categoria"
            element={<ProfesionalByCategory />}
          />
            <Route
            path="profesionales-zone"
            element={<ProfesionalByZone />}
          />
    <Route path="profesional/:id" element={<SingleProfessional />} />
    {/* Rutas por defecto */}
    <Route path="restringido" element={<h1>No Autorizado</h1>} />
    
    
    {/* Rutas del Panel de Usuario */}
    <Route index element={<UserProfile />} />
          <Route path="perfil-usuario" element={/*user ? <UserPanel /> : <Navigate to="/login" replace />*/ <UserProfile />}>
  
            <Route path="editar" element={<h1>Editar perfil</h1>} />
            <Route path="contratos" element={<h1>Contratos</h1>} />
            <Route path="change-password" element={<h3>Cambiar pass</h3>} />
            <Route path="mensajes" element={<h3>Mis Mensajes</h3>} />
          </Route>
          <Route path="perfil-usuario/editar-direcciones" element={<Addresses />} />
          {/* Rutas del Panel de Profesional */}
          <Route
            path="perfil-profesional"
            element={
              /*user ? <UserPanel /> : <Navigate to="/login" replace />*/ <h1>
                Perfil de profesional
              </h1>
            }
          >
            <Route index element={<h1>Perfil profesional</h1>} />
            <Route path="editar" element={<h1>Editar perfil</h1>} />
            <Route path="contratos" element={<h1>Contratos</h1>} />
            <Route path="change-password" element={<h3>Cambiar pass</h3>} />
            <Route path="mensajes" element={<h3>Mis Mensajes</h3>} />
          </Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
};

export default App;
