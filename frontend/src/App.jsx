import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar"
import ProfesionalByCategory from "./components/Category/ProfesionalByCategory"
import Login from "./components/Login/Login";

const App = () => {

  return ( 
    <>

      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route index element={<h1>Inicio</h1>} />
          {/* Rutas de Login */}
          <Route path="login-usuario" element={<Login />} />
          <Route
            path="login-profesional"
            element={<h1>Login de Profesional</h1>}
          />
          {/* Rutas de Registro */}
          <Route
            path="registro-usuario"
            element={<h1>Registro de Profesional</h1>}
          />
          <Route
            path="registro-profesional"
            element={<h1>Registro de Profesional</h1>}
          />
          {/* Rutas de Busqueda de profesional */}
          <Route
            path="categorias-profesionales"
            element={<h1>Categorias de Profesionales</h1>}
          />
          <Route path="profesionales/:categoria" element={<ProfesionalByCategory/>} />
          <Route
            path="detalle-profesional/:id"
            element={<h1>Detalle de un solo Profesional</h1>}
          />
          {/* Rutas por defecto */}
          <Route path="restringido" element={<h1>No Autorizado</h1>} />

          {/* Rutas del Panel de Usuario */}
          <Route
            path="perfil-usuario"
            element={
              /*user ? <UserPanel /> : <Navigate to="/login" replace />*/ <h1>
                Perfil de usuario
              </h1>
            }
          >
            <Route index element={<h1>Perfil usuario</h1>} />
            <Route path="editar" element={<h1>Editar perfil</h1>} />
            <Route path="contratos" element={<h1>Contratos</h1>} />
            <Route path="change-password" element={<h3>Cambiar pass</h3>} />
            <Route path="mensajes" element={<h3>Mis Mensajes</h3>} />
          </Route>
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
        {/* Aqui puede ir el footer */}
      </BrowserRouter>
    </>
  );
};

export default App;
