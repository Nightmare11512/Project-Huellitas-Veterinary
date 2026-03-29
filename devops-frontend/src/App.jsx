import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import MainMenuGestor from "./components/MainMenu-gestor";
import MainMenu from "./components/MainMenu-dueno";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/MainMenu/" element={<MainMenu />} />
        <Route path="/MainMenuGestor" element={<MainMenuGestor />} />
      </Routes>
    </Router>
  );
}

export default App;