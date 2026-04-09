import { useState } from "react";
import { useNavigate } from "react-router-dom";

function MainMenuGestor() {
  const navigate = useNavigate();
  const [correo, setCorreo] = useState("");
  const [contraseña, setContrasena] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellidoP, setPaterno] = useState("");
  const [apellidoM, setMaterno] = useState("");
  const [rol, setRol] = useState("");

  const handleLogout = () => {
    navigate("/");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Crear el objeto usuario según tu backend
    const usuario = {
      nombre,
      paterno: apellidoP,
      materno: apellidoM,
      correo,
      contraseña,
      rol: {
        idRol: parseInt(rol)  // convertir a número porque tu backend espera Integer
      }
    };

    try {
      const response = await fetch("http://dev-server.local:8080/usuario/guardar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
      });

      if (!response.ok) {
        const errorData = await response.json();
        console.error("Error del backend:", errorData);
        alert("No se pudo crear el usuario");
        return;
      }

      const data = await response.json();
      console.log("Usuario creado:", data);
      alert("Usuario creado con éxito");

    } catch (err) {
      console.error("Error al enviar el usuario:", err);
      alert("Error de conexión con el backend");
    }
  };

  return (
    <div className="main-menu-container">
      <h1>Menú Principal</h1>
      <p>
      <input
          type="text"
          placeholder="Ingrese su correo"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          required
      />
      </p>
      <p>
      <input
          type="password"
          placeholder="Ingrese su contraseña"
          value={contraseña}
          onChange={(e) => setContrasena(e.target.value)}
          required
      />
      </p>
      <p>
      <input
          type="text"
          placeholder="Ingrese su nombre"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          required
      />
      </p>
      <p>
      <input
          type="text"
          placeholder="Ingrese su apellido paterno"
          value={apellidoP}
          onChange={(e) => setPaterno(e.target.value)}
          required
      />
      </p>
      <p>
      <input
          type="text"
          placeholder="Ingrese su apellido materno"
          value={apellidoM}
          onChange={(e) => setMaterno(e.target.value)}
          required
      />
      </p>
      <p><select id="opciones" 
      name="opciones" 
      value={rol} 
      onChange={(e) => setRol(e.target.value)} 
      required>
        <option value="1">Administrador</option>
        <option value="2">Cliente</option>
        <option value="3">Gestor</option>
        <option value="4">Veterinario</option>
      </select></p>
      <p><button onClick={handleSubmit}>Crear usuario</button></p>
      <button onClick={handleLogout}>Cerrar Sesión</button>
    </div>
  );
}

export default MainMenuGestor;