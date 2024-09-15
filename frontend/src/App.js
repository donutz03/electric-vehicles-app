// import logo from './logo.svg';
// import './App.css';
// import ElectricVehicles from "./components/ElectricVehicles";
// import BasicExample from "./components/BasicExample";
// import {AppBar, Button} from "@mui/material";
// import Navbar from "react-bootstrap/Navbar";
//
//
// function App() {
//     return (
//          <div className="App">
//             {/*<BasicExample/>*/}
// <AppBar></AppBar>
//              <Button    />
//              <Navbar expand="lg"></Navbar>
//              <AppBar    />
//     {/*<ElectricVehicles/>*/}
//
//          </div>
//
//
//     );
//
// }
//
// export default App;
import React from 'react';
import './App.css';

import ElectricVehicles from "./components/ElectricVehicles";
import MaterialNavbar from "./components/MaterialNavbar"; // Using Bootstrap Navbar (if needed)

function App() {
    return (
        <div className="App">
            <MaterialNavbar />
            <ElectricVehicles/>
        </div>
    );
}

export default App;
