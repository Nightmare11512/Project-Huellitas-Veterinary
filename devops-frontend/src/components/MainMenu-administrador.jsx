import { useNavigate } from "react-router-dom";
//import { useEffect, useRef, useState } from "react";

function MainMenuAdministrador() {

const navigate = useNavigate;

    const HandleLogout = () => {
        navigate("/");
    }

    return (
        <><div></div><p><button className="botonLogout" onClick={HandleLogout}>Cerrar Sesión</button></p></>
    );
}

export default MainMenuAdministrador;