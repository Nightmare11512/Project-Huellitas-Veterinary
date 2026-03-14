import { useNavigate } from "react-router-dom";

function MainMenuGestor() {
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="main-menu-container">
      <h1>Menú Principal</h1>
      <button onClick={handleLogout}>Cerrar Sesión</button>
    </div>
  );
}

export default MainMenuGestor;