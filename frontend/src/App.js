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
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Button from '@mui/material/Button';
import Navbar from 'react-bootstrap/Navbar';
import ElectricVehicles from "./components/ElectricVehicles"; // Using Bootstrap Navbar (if needed)

function App() {
    return (
        <div className="App">
            {/* Use the Material-UI Navbar component */}
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        MyApp
                    </Typography>
                    <Button color="inherit">Home</Button>
                    <Button color="inherit">About</Button>
                    <Button color="inherit">Contact</Button>
                </Toolbar>
            </AppBar>

            {/* Material-UI Button example */}
            <Button variant="contained" color="primary" style={{ margin: '20px' }}>
                Material-UI Button
            </Button>

            {/* Bootstrap Navbar example (if needed) */}
            <Navbar bg="light" expand="lg">
                <Navbar.Brand href="#home">Bootstrap Navbar</Navbar.Brand>
            </Navbar>

            {/* Your other components can go here */}
            <ElectricVehicles/>
        </div>
    );
}

export default App;
