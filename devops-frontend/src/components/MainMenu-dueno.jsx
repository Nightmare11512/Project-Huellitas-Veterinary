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
                <span className={`estado ${
                  cita.estado_cita === 1 ? "completado" 
                  : cita.estado_cita === 2 ? "pendiente" 
                  : "cancelado"
                }`}>
                  {cita.estado_cita === 1 ? "Completado" 
                  : cita.estado_cita === 2 ? "Pendiente" 
                  : "Cancelado"}
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
  const [selectedMascota, setSelectedMascota] = useState("");
  const [citas, setCitas] = useState([]);
  // eslint-disable-next-line no-unused-vars
  const [refresh, setRefresh] = useState(false);
  const correo = sessionStorage.getItem("Usuario")?.replace(/^"|"$/g, "");
  const [loading, setLoading] = useState(false);
  const menuItems = ["Mascotas", "Citas", "Tratamientos"];
  const [fecha, setFecha] = useState("");
  const [hora, sethora] = useState("");

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
  
    const fetchCitas = fetch(`http://dev-server.local:8080/cita/${correo}/${mascotaSeleccionadaObj.idMascota}`)
      .then((res) => {
        if (!res.ok) throw new Error("Error al obtener citas");
        return res.json();
      });
  
    const swalTimer = new Promise((resolve) => setTimeout(resolve, 2000));
  
    Promise.all([fetchCitas, swalTimer])
      .then(([data]) => {
        setCitas(Array.isArray(data) ? data : []);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setCitas([]);
        setLoading(false);
      });
  }, [selectedMascota, refresh]);

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
              onClick={() => {setActiveItem(item); 
                              setSelectedMascota("");
                              setFecha("");
                              sethora("");}}
              style={{ cursor: "pointer", margin: "5px 0" }}>
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
                      value={selectedMascota}
                      onChange={(e) => {
                        const valor = e.target.value;
                        setSelectedMascota(valor);
                        if (valor !== "") {
                          setLoading(true);
                          Swal.fire({
                            title: "Buscando datos espere",
                            text: "Buscando espere...",
                            icon: "info",
                            timerProgressBar: true,
                            timer: 2000,
                            showConfirmButton: false
                          }).then(() => {
                            setLoading(false); // <-- se desactiva cuando el Swal cierra
                          });
                        } else {
                          setLoading(false);
                        }
                      }}
                    >
                      <option value="">Seleccione su mascota</option>
                      {mascota.map((m) => (
                        <option key={m.idMascota} value={m.idMascota}>{m.nombre}</option>
                      ))}
                    </select>
                  </div>

                  {activeItem === "Mascotas" && (
                    loading ? <p>Cargando...</p> : (
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
                    )
                  )}

                  {activeItem === "Citas" && (
                    <>
                    <p></p>
                    <div className="EntradaTxt">Indique una fecha para agendar</div>
                    <input type="date" value={fecha} onChange={(e) => {setFecha(e.target.value)}} placeholder="Indique una fecha" className="Entrada"/>
                    <p></p>
                    <div className="EntradaTxt">Indique una hora para agendar</div>
                    <input type="time" value={hora} onChange={(e) => {sethora(e.target.value)}} placeholder="Indique su hora de llegada" className="Entrada"/>
                    <p></p>

                    <button className="buton" onClick={() => {}}>Crear cita</button>
                    </>
                  )}
                </form>
              </>
            )}
            <p>
              <button className="button-back" onClick={() => { setActiveItem(null); 
                                                               setSelectedMascota("");
                                                               setFecha("");
                                                               sethora("");}}>
                Volver
              </button>
            </p>
          </>
        )}
      </div>

      <div className={`sidebar tertiary ${activeItem === "Citas" || activeItem === "Tratamientos" ? "active" : ""}`}>
        <h2>Historial de {activeItem}</h2>
        {activeItem === "Citas" && (
          loading ? <p>Cargando...</p> : (
          <TablaCitas citas={citas} />
          )
        )}
      </div>

      <div className="main-content"></div>
    </div>
  );
}

export default MainMenu;