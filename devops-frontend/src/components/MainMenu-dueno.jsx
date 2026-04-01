import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import "../styles/MainMenu-dueno.css";

const TablaCitas = ({ citas }) => {
  if (!citas || !Array.isArray(citas) || citas.length === 0) 
    return <p>No hay citas registradas.</p>;
  return (
    <div className="tabla-container">
      <table className="tabla-citas">
        <thead>
          <tr>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Entrada Agendada</th>
            <th>Hora de Salida</th>
          </tr>
        </thead>
        <tbody>
          {citas.map((cita) => (
            <tr key={cita.idCita}>
              <td>{cita.fecha}</td>
              <td>
                <span className={`estado ${cita.estado_cita === 1 ? "completado" : "pendiente"}`}>
                  {cita.estado_cita === 1 ? "Completado" : "Pendiente"}
                </span>
              </td>
              <td>{cita.entradaAgendada}</td>
              <td>{cita.estado_cita === 1 ? cita.horaSalida : "—"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

function MainMenu() {
  const navigate = useNavigate();
  const [dueno, setDueno] = useState(null);
  const [activeItem, setActiveItem] = useState(null);
  const [mascota, setMascota] = useState([]);
  const [selectedMascota, setSelectedMascota] = useState(null);
  const [citas, setCitas] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const correo = sessionStorage.getItem("Usuario")?.replace(/^"|"$/g, "");

  const mascotaSeleccionadaObj = mascota.find((m) => m.idMascota == selectedMascota);

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
        Swal.fire({ title: "Sesión cerrada con Exito", text: "Hasta pronto", icon: "success" });
      }
    });
  };

  useEffect(() => {
    if (!correo) return;
    fetch("http://dev-server.local:8080/usuario/getNombre", {
      method: "POST",
      headers: { "Content-Type": "text/plain" },
      body: correo,
    })
      .then((res) => res.json())
      .then((data) => setDueno(data))
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    if (!correo) return;
    fetch(`http://dev-server.local:8080/mascota/usuario/${correo}`)
      .then((res) => res.json())
      .then((data) => setMascota(data))
      .catch((err) => console.error(err));
  }, [correo]);

  useEffect(() => {
    if (!correo || !mascotaSeleccionadaObj) return;
    fetch(`http://dev-server.local:8080/cita/${correo}/${mascotaSeleccionadaObj.idMascota}`)
      .then((res) => {
        if (!res.ok) throw new Error("Error al obtener citas");
        return res.json();
      })
      .then((data) => setCitas(Array.isArray(data) ? data : []))
      .catch((err) => {
        console.error(err);
        setCitas([]);
      });
  }, [selectedMascota, refresh]);

  const menuItems = ["Mascotas", "Citas", "Tratamientos"];

  return (
    <div className="main-menu" style={{ display: "flex" }}>

      <div className={`sidebar primary ${dueno ? "active" : ""}`}>
        <h2>Menú Principal</h2>
        <h3>Bienvenido, {dueno ? dueno.nombre : "Dueño de Mascota"}</h3>
        <nav className="nav-links">
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

      <div className={`sidebar secondary ${activeItem ? "active" : ""}`}>
        {activeItem && (
          <>
            <h2>{activeItem}</h2>
            {(activeItem === "Mascotas" || activeItem === "Citas") && (
              <>
                <p>Gestión de {activeItem}</p>
                <form className="formulario">
                  <div>
                    <select
                      className="menuSeleccionable"
                      onChange={(e) => setSelectedMascota(e.target.value)}
                    >
                      <option value="">Seleccione su mascota</option>
                      {mascota.map((m) => (
                        <option key={m.idMascota} value={m.idMascota}>{m.nombre}</option>
                      ))}
                    </select>
                  </div>

                  {activeItem === "Mascotas" && (
                    <>
                      <div className="font-size-1">Nombre: {mascotaSeleccionadaObj?.nombre}</div>
                      <div className="font-size-1">
                        Peso: {mascotaSeleccionadaObj?.peso ? `${mascotaSeleccionadaObj.peso} Kg` : ""}
                      </div>
                      <div className="font-size-1">Fecha de nacimiento:</div>
                      <div className="font-size-2">{mascotaSeleccionadaObj?.fechaNacimiento}</div>
                      <div className="font-size-1">Especie: {mascotaSeleccionadaObj?.raza.especie.nombre}</div>
                      <div className="identado">Raza: {mascotaSeleccionadaObj?.raza.nombre}</div>
                    </>
                  )}
                </form>
              </>
            )}
            <p>
              <button className="button-back" onClick={() => { setActiveItem(null); setSelectedMascota(null); }}>
                Volver
              </button>
            </p>
          </>
        )}
      </div>

      <div className={`sidebar tertiary ${activeItem === "Citas" || activeItem === "Tratamientos" ? "active" : ""}`}>
        <h2>Historial de {activeItem}</h2>
        {activeItem === "Citas" && (
          <TablaCitas citas={citas} />
        )}
      </div>

      <div className="main-content"></div>
    </div>
  );
}

export default MainMenu;