import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "../styles/MainMenu-dueno.css";

function MainMenu() {
  const navigate = useNavigate();
  const [dueno, setDueno] = useState(null);
  const [activeItem, setActiveItem] = useState(null); // controla item clickeado
  const [mascota, setMascota] = useState([]);
  const correo = sessionStorage.getItem("Usuario");

  const handleLogout = () => {
    navigate("/");
    sessionStorage.clear();
  };

  useEffect(() => {
    const correoEnSesion = sessionStorage.getItem("Usuario");
    if (!correoEnSesion) return;

    // Compatibilidad con sesiones antiguas que guardaron JSON.stringify(correo)
    const correo = correoEnSesion.replace(/^"|"$/g, "");

    fetch("http://dev-server.local:8080/usuario/getNombre", {
      method: "POST",
      headers: {
        "Content-Type": "text/plain"
      },
      body: correo
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("No se pudo obtener el nombre del usuario");
        }
        return res.json();
      })
      .then((data) => {
        setDueno(data);
      })
      .catch((error) => {
        console.error("Error al cargar datos del dueño:", error);
      });
  }, []);
  const menuItems = ["Mascotas", "Citas", "Tratamientos"];  

  useEffect(()=> {
    fetch(`http://dev-server.local:8080/mascota/usuario/${correo}`)
    .then(res => res.json())
    .then(data => setMascota(data))
    .catch(err => console.error(err));
  }, [correo])

  return (
    <div className="main-menu" style={{ display: "flex" }}>
      {/* Sidebar principal */}
      <div className={`sidebar ${dueno ? "active" : ""}`}>
        <h2>Menú Principal</h2>
        <h3>Bienvenido, {dueno ? dueno.nombre : "Dueño de Mascota"}</h3>
        <nav className="nav-links" aria-label="Menú principal">
          {menuItems.map((item) => (
            <div
              key={item}
              className={`menu-item ${activeItem === item ? "active" : ""}`}
              onClick={() => setActiveItem(item)}
              style={{ cursor: "pointer", margin: "5px 0" }}
            >
              {item}
            </div>
          ))}
        </nav>
        <button onClick={handleLogout} className="btn">Cerrar Sesión</button>
      </div>

      {/* Sidebar secundaria */}
    <div className={`sidebar secondary ${activeItem ? "active" : ""}`}>
      {activeItem && (
        <>
      <h2>{activeItem}</h2>

      {activeItem === "Mascotas" && (
        <form>
          <p>Gestion de {activeItem}</p>
          <p>
            <select className="menuSeleccionable">
              <option className="itemsX" value="">Seleccione su mascota</option>
              {mascota.map(mascota => (
                <option className="itemsX" key={mascota.id} value={mascota.id}>{mascota.nombre}</option>
              ))}
            </select>
          </p>
        </form>
      )}

      <button className="button-back" onClick={() => setActiveItem(null)}>Volver</button>
        </>
      )}
    </div>

  {/* Contenido principal, si quieres agregarlo */}
  <div className="main-content">
    {/* Aquí tu contenido principal */}
  </div>
</div>
  );
}

export default MainMenu;