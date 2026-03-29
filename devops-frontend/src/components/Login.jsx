import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/login.css";

function Login() {
  const [user, setUser] = useState("");
  const [contraseña, setContraseña] = useState("");
  const [mostrar, setMostrar] = useState(false); // controla mostrar/ocultar
  const navigate = useNavigate();
  const audioRef = useRef(null);

  // 🎵 Música mientras Login está montado
  useEffect(() => {
    const mes = new Date().getMonth();
    const audioSrc = {
      1: "/Music/TANK QUINCEAÑERA (PARODIA).mp3",
      2: "/Music/La mejor música de elevador o ascensor.mp3",
      7: "/Music/Marcha militar alemana _Erika_ - Subtitulado en Español(MP3_160K).mp3",
      11: "/Music/Sleigh Ride (Indian Christmas Remix) - Vindaloo Singh.mp3"
    };

    if (audioSrc[mes]) {
      audioRef.current = new Audio(audioSrc[mes]);
      audioRef.current.loop = true;
      audioRef.current.volume = 0.5;

      const iniciarMusica = () => {
        audioRef.current.play().catch(err =>
          console.log("Error al reproducir audio:", err)
        );
        document.removeEventListener("click", iniciarMusica);
        document.removeEventListener("keydown", iniciarMusica);
      };

      document.addEventListener("click", iniciarMusica);
      document.addEventListener("keydown", iniciarMusica);

      return () => {
        audioRef.current.pause();
        audioRef.current.currentTime = 0;
        document.removeEventListener("click", iniciarMusica);
        document.removeEventListener("keydown", iniciarMusica);
      };
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const esCorreo = user.includes("@");
  
    if (!esCorreo || user === '') {
      alert("Ingresa un correo válido");
      return;
    }
  
    try {
      const response = await fetch(`http://dev-server.local:8080/usuario/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          correo: user,
          contrasena: contraseña   // 👈 sin ñ (importante)
        })
      });
      const data = await response.json();
      if (data.login === true) {
        sessionStorage.setItem("Usuario", user);
        alert("Login correcto");
        fetch(`http://dev-server.local:8080/usuario/getRol?correo=${user}`)
        .then(res => res.json())
        .then(data => {
          console.log(data);
          if (data === 2) {
            navigate("/MainMenu/");
          } else {
            navigate("/MainMenuGestor");
          }
        })
        .catch(err => console.error(err));
      } else {
        alert("Credenciales incorrectas");
      }
  
    } catch (error) {
      console.error("Error:", error);
      alert("Error en el servidor");
    }
  };

  return (
    <div className="login-container">
      <form className="login-box" onSubmit={handleSubmit}>
        <img src="/Logo.svg" alt="Logo" className="logo" />
        <h2>Iniciar sesión</h2>

        <input
          type="text"
          placeholder="Ingrese su correo"
          value={user}
          onChange={(e) => setUser(e.target.value)}
          required
        />

        <input
          type={mostrar ? "text" : "password"}
          placeholder="Contraseña"
          value={contraseña}
          onChange={(e) => setContraseña(e.target.value)}
          required
        />

        <label className="custom-checkbox">
          <input
            className="checkbox"
            type="checkbox"
            checked={mostrar}
            onChange={() => setMostrar(!mostrar)}
          />
          <span className="checkmark"></span>
          <span className="label-text">Mostrar contraseña</span>
        </label>

        <button type="submit">Entrar</button>
      </form>
    </div>
  );
}

export default Login;