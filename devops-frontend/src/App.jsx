import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useRef, useEffect } from "react";
import Login from "./components/Login";
import MainMenuGestor from "./components/MainMenu-gestor";
import MainMenu from "./components/MainMenu-dueno";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/MainMenu/" element={<MainMenu />} />
        <Route path="/MainMenu" element={<MainMenuGestor />} />
      </Routes>
    </Router>
  );
}

export default App;