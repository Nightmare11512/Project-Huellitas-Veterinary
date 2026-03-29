import { useState } from "react";
import { useNavigate } from "react-router-dom";

function MainMenuGestor() {
  const navigate = useNavigate();
  const [correo, setCorreo] = useState("");

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="main-menu-container">
      <h1>Menú Principal</h1>
      <input
          type="text"
          placeholder="Ingrese su correo"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          required
        />
        <p></p>
      <button onClick={handleLogout}>Cerrar Sesión</button>
    </div>
  );
}

export default MainMenuGestor;