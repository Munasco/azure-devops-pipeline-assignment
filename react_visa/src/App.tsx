import React from 'react';
import './App.css';
import AddVisaForm from "./containers/AddVisaForm/AddVisaForm";
import SeeVisas from './containers/SeeVisas/SeeVisas';
import Navigation from './components/Navigation';
import Home from './components/Home';
import { Toaster } from './components/ui/toaster';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-background">
        <Navigation />
        <main>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/post_visa' element={<AddVisaForm/>} />
            <Route path='/see_visas' element={<SeeVisas/>}/>
          </Routes>
        </main>
        <Toaster />
      </div>
    </Router>
  );
}

export default App;
