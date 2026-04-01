import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import "../styles/MainMenu-dueno.css";

function MainMenu() {
  const navigate = useNavigate();
  const [dueno, setDueno] = useState(null);
  const [activeItem, setActiveItem] = useState(null);
  const [mascota, setMascota] = useState([]);
  const correo = sessionStorage.getItem("Usuario");

  const handleLogout = () => {
    Swal.fire({
      title: "Cerrar Sesión",
      text: "¿Estás seguro de querer cerrar sesión?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
    }).then((result) => {
      if (result.isConfirmed) {
        navigate("/");
        sessionStorage.clear();
        Swal.fire({
          title: "Sesión cerrada con Exito",
          text: "Hasta pronto",
          icon: "success",
        });
      }
    });
  };

  useEffect(() => {
    const correoEnSesion = sessionStorage.getItem("Usuario");
    if (!correoEnSesion) return;

    const correo = correoEnSesion.replace(/^"|"$/g, "");

    fetch("http://dev-server.local:8080/usuario/getNombre", {
      method: "POST",
      headers: {
        "Content-Type": "text/plain",
      },
      body: correo,
    })
      .then((res) => {
        if (!res.ok) throw new Error("No se pudo obtener el nombre del usuario");
        return res.json();
      })
      .then((data) => setDueno(data))
      .catch((error) => console.error("Error al cargar datos del dueño:", error));
  }, []);

  const menuItems = ["Mascotas", "Citas", "Tratamientos"];

  useEffect(() => {
    fetch(`http://dev-server.local:8080/mascota/usuario/${correo}`)
      .then((res) => res.json())
      .then((data) => setMascota(data))
      .catch((err) => console.error(err));
  }, [correo]);

  const [selectedMascota, setSelectedMascota] = useState(null);
  const mascotaSeleccionadaObj = mascota.find((m) => m.idMascota == selectedMascota);

  return (
    <div className="main-menu" style={{ display: "flex" }}>

      {/* Sidebar principal */}
      <div className={`sidebar primary ${dueno ? "active" : ""}`}>
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
              <>
                <p>Gestion de {activeItem}</p>
                <form className="formulario">
                  <div>
                    <select
                      className="menuSeleccionable"
                      onChange={(e) => setSelectedMascota(e.target.value)}
                    >
                      <option className="itemsX" value="">Seleccione su mascota</option>
                      {mascota.map((m) => (
                        <option className="itemsX" key={m.idMascota} value={m.idMascota}>
                          {m.nombre}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="font-size-1">Nombre: {mascotaSeleccionadaObj?.nombre}</div>
                  <div className="font-size-1">
                    Peso: {mascotaSeleccionadaObj?.peso ? `${mascotaSeleccionadaObj.peso} Kg` : ""}
                  </div>
                  <div className="font-size-1">Fecha de nacimiento:</div>
                  <div className="font-size-2">{mascotaSeleccionadaObj?.fechaNacimiento}</div>
                  <div className="font-size-1">
                    Especie: {mascotaSeleccionadaObj?.raza.especie.nombre}
                  </div>
                  <div className="identado">Raza: {mascotaSeleccionadaObj?.raza.nombre}</div>
                </form>
              </>
            )}

            {activeItem === "Citas" && (
              <>
                <p>Gestion de {activeItem}</p>
                <form className="formulario">
                  <div>
                    <select
                      className="menuSeleccionable"
                      onChange={(e) => setSelectedMascota(e.target.value)}
                    >
                      <option className="itemsX" value="">Seleccione su mascota</option>
                      {mascota.map((m) => (
                        <option className="itemsX" key={m.idMascota} value={m.idMascota}>
                          {m.nombre}
                        </option>
                      ))}
                    </select>
                  </div>
                </form>
              </>
            )}

            <p>
              <button
                className="button-back"
                onClick={() => {
                  setActiveItem(null);
                  setSelectedMascota(null);
                }}
              >
                Volver
              </button>
            </p>
          </>
        )}
      </div>

      {/* Sidebar terciaria — siempre en el DOM para que CSS pueda transicionarla */}
      <div
        className={`sidebar tertiary ${
          activeItem === "Citas" || activeItem === "Tratamientos" ? "active" : ""
        }`}
      >
        <h2>Historial de {activeItem}</h2>
      </div>

      {/* Contenido principal */}
      <div className="main-content">
        {/* Aquí tu contenido principal */}
      </div>

    </div>
  );
}

export default MainMenu;