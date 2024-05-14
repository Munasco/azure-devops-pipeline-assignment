import React from 'react';
import './App.css';
import AddVisaForm from "./containers/AddVisaForm/AddVisaForm";
import SeeVisas from './containers/SeeVisas/SeeVisas';
import { BrowserRouter as Router,
        Route,
        Routes,
        Link,
} from "react-router-dom";

function App() {
  return (
      <Router>
        <div>
          <nav>
            <ul>
              <li><Link to='/'>Home</Link></li>
              <li><Link to='/post_visa'>Post Visa</Link></li>
              <li><Link to='/see_visas'>See Visas</Link></li>
            </ul>
          </nav>

        <Routes>
          <Route path='/post_visa' element={<AddVisaForm/>} />
          <Route path='/see_visas' element={<SeeVisas/>}/>
        </Routes>

        </div>
      </Router>
  );
}

export default App;
