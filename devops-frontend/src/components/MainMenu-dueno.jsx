import { useNavigate } from "react-router-dom";
import { Select, MenuItem, FormControl, InputLabel, Button } from "@mui/material";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import "../styles/MainMenu-dueno.css";

const TablaCitas = ({ citas }) => {
  if (!citas || !Array.isArray(citas)) {
    return <p style={{color: "black", opacity: 1}}>No hay citas registradas.</p>;
  } else if (citas.length === 0) {
    return <p style={{color: "black", opacity: 1}}>No hay citas registradas.</p>;
  } else {
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
                    cita.estadoCita === 2 ? "completado" 
                    : cita.estadoCita === 0 ? "Asignacion Pendiente"
                    : cita.estadoCita === 1 ? "pendiente" 
                    : "cancelado"
                  }`}>
                    {cita.estadoCita === 2 ? "Completado" 
                    : cita.estadoCita === 1 ? "Pendiente" 
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
  }
};

const TablaMascotas = ({mascotas}) => {
  if (!mascotas || !Array.isArray(mascotas)){
    return <p color="black">No hay mascotas registradas</p>
  } else if (mascotas.length === 0) {
    <p color="black">No hay citas registradas</p>
  } else {
    return (
      <div className="tabla-container">
        <table className="tabla-mascotas">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Fecha de Nacimiento</th>
              <th>Peso</th>
              <th>Especie</th>
              <th>Raza</th>
            </tr>
          </thead>
          <tbody>
            {mascotas.map((mascota) => (
              <tr key={mascota.idMascota}>
                <td>{mascota.nombre}</td>
                <td>{mascota.fechaNacimiento}</td>
                <td>{mascota.peso} Kg</td>
                <td>{mascota.raza.especie.nombre}</td>
                <td>{mascota.raza.nombre}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
};

function MainMenu() {
  const navigate = useNavigate();
  const [dueno, setDueno] = useState(null);
  const [activeItem, setActiveItem] = useState("");
  const [mascotas, setMascotas] = useState([]);
  const [selectedMascota, setSelectedMascota] = useState("");
  const [ListaCitas, setCitas] = useState([]);
  const correo = sessionStorage.getItem("Usuario")?.replace(/^"|"$/g, "");
  const menuItems = ["Mascotas", "Citas", "Tratamientos"];
  const [noMascotas, setNoMascota] = useState(0);
  const [noCitas, setNoCita] = useState(0);
  const [isLeaving, setIsLeaving] = useState(false);
  const mascotaSeleccionadaObj = mascotas.find((m) => m.idMascota === Number(selectedMascota));

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
    if (!correo) {
      Swal.fire({
        title: "Sesión Expirada",
        text: "Por favor de iniciar sesion nuevamente",
        icon: "warning", 
        timer: 3000,
        timerProgressBar: true,
        showConfirmButton: false  
      }).then(() => {
        navigate("/");
      });
    }
  }, [correo]);

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
  }, [correo]);

  useEffect(() => {
    if (!correo) return;
      fetch(`http://dev-server.local:8080/mascota/usuario/${correo}`)
      .then(res => res.json())
      .then(data => setNoMascota(data))
      .catch(err => console.error(err));
  }, [correo]);

  useEffect(() => {
    if (!correo) return;
      fetch(`http://dev-server.local:8080/cita/usuario/${correo}`)
      .then(res => res.json())
      .then(data => setNoCita(data))
      .catch(err => console.error(err));
  }, [correo]);

  const changeMenu = (item) => {
    if (item === activeItem) return;

    setIsLeaving(true);

    setTimeout(() => {
      setActiveItem(item);
      setIsLeaving(false);
    }, 250);
  };

  useEffect(() => {
    if (!correo) return;
    if (mascotaSeleccionadaObj) {
      fetch(`http://dev-server.local:8080/cita/${correo}/${mascotaSeleccionadaObj.idMascota}`)
      .then(res => res.json())
      .then(data => setCitas(data))
      .catch(err => console.error(err));
    } else {
      fetch(`http://dev-server.local:8080/cita/${correo}`)
      .then(res => res.json())
      .then(data => setCitas(data))
      .catch(err => console.error(err));
    }
  }, [correo, mascotaSeleccionadaObj]);

  useEffect(() => {
    if (!correo) return;
    fetch(`http://dev-server.local:8080/mascota/${correo}`)
    .then(res => res.json())
    .then(data => setMascotas(data))
    .catch((err => console.error(err)));
  }, [correo])

  return (
    <div className="main-menu" style={{ display: "flex" }}>

      <div className={`sidebar primary ${dueno ? "active" : ""}`}>
        <h2 style={{color: "white"}}>Menú Principal</h2>
        <h3 style={{color: "white"}}>Bienvenido, {dueno ? dueno.nombre : "Dueño de Mascota"}</h3>
        <nav className="nav-links">
          {menuItems.map((item) => (
            <div
              key={item}
              className={`menu-item ${activeItem === item ? "active" : ""}`}
              onClick={() => {changeMenu(item); setSelectedMascota("")}}
              style={{ cursor: "pointer", margin: "5px 0" }}>
              {item}
            </div>
          ))}
        </nav>
        <button type="button" onClick={handleLogout} className="btn">Cerrar Sesión</button>
      </div>
      <div className={`sidebar secondary ${activeItem}`}>

      {activeItem === "" && (
        <>
          <h2 style={{fontWeight: "bold", color: "black"}}>Datos Generales</h2>

          <div className="slydes-wrapper">
            <div className={`slydes ${isLeaving ? "slide-out" : ""}`}>
              <span className="icon">🐕</span>

              <div className="card-text">
                <span style={{fontWeight: "bold", fontSize: "30px"}}>&nbsp;Mascotas</span>
                <span className="subtitle">
                &nbsp;Usted tiene {noMascotas} {noMascotas === 1 ? "Mascota" : "Mascotas"}
                </span>
              </div>
            </div>

            <div className={`slydes ${isLeaving ? "slide-out" : ""}`}>
              <span className="icon">🗓️</span>

              <div className="card-text">
                <span style={{fontWeight: "bold", fontSize: "30px"}}>&nbsp;Citas</span>
                <span className="subtitle">
                &nbsp;Usted tiene {noCitas} {noCitas === 1 ? "Cita" : "Citas"} pendientes
                </span>
              </div>
            </div>

            <div className={`slydes ${isLeaving ? "slide-out" : ""}`}>
              <span className="icon">💉</span>

              <div className="card-text">
                <span style={{fontWeight: "bold", fontSize: "30px"}}>&nbsp;Tratamientos</span>
                <span className="subtitle">
                &nbsp;Usted tiene {noCitas} {noCitas === 1 ? "Tratamiento" : "Tratamientos"}
                </span>
              </div>
            </div>
          </div>
        </>
      )}
      
          
      {activeItem === "Mascotas" && (
        <div className={isLeaving ? "slide-out" : "slide-in"}>
          <h2 style={{fontWeight: "bold", color: "black"}}>Datos de Mascotas</h2>
          <TablaMascotas mascotas={mascotas}></TablaMascotas>
        </div>
      )}

      {activeItem === "Citas" && (
        <div className={isLeaving ? "slide-out" : "slide-in"}>
          <h2 style={{fontWeight: "bold", color: "black"}}>Datos de Citas</h2>
          <FormControl fullWidth sx={{ m: 1, minWidth: 200 }}>
            <InputLabel id="select-mascota-label">Filtrar por Mascota</InputLabel>
            <Select
              labelId="select-mascota-label"
              value={selectedMascota}
              label="Filtrar por Mascota" // Esto es vital para que el borde no corte el texto
              onChange={(e) => setSelectedMascota(e.target.value)}
            >
              <MenuItem value=""><em>Todas las mascotas</em></MenuItem>
              {mascotas.map((m) => (
                <MenuItem key={m.idMascota} value={m.idMascota}>{m.nombre}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <TablaCitas citas={ListaCitas}></TablaCitas>
        </div>
      )}

      {activeItem === "Tratamientos" && (
        <div className={isLeaving ? "slide-out" : "slide-in"}>
          <h2 style={{fontWeight: "bold", color: "black"}}>Datos de Tratamientos</h2>
        </div>
      )}

      {activeItem != "" && (
        <>
        <p></p>
        <button onClick={() => {setSelectedMascota(""); setActiveItem("")}} className="btn">Volver</button>
        </>
      )} 
      </div>
    </div>
  );
}

export default MainMenu;