/*
// src/components/MapComponent.js
import React from 'react';
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';

// Define your coordinates data here
const coordinates = [
    { lat: 47.912654, lng: -122.29943 },
    { lat: 46.6046178, lng: -120.4688751 },
    { lat: 47.7981436, lng: -122.5178351 },
    { lat: 47.4739066, lng: -122.6530052 },
    { lat: 47.8956271, lng: -122.2032349 },
    { lat: 47.6702397, lng: -122.3788863 },
    { lat: 46.8882415, lng: -122.6771414 },
    { lat: 47.676241, lng: -122.1925969 },
    { lat: 47.676241, lng: -122.1925969 },
    { lat: 47.07503, lng: -122.86491 },
    { lat: 46.5654909, lng: -120.5807155 },
    { lat: 47.5930874, lng: -122.6231895 },
    { lat: 47.5759584, lng: -122.6961203 },
    { lat: 47.2884536, lng: -122.1550193 },
    { lat: 47.5098889, lng: -122.1819876 },
    { lat: 47.839957, lng: -122.206146 },
    { lat: 47.6305366, lng: -122.3441532 },
    { lat: 47.754528, lng: -122.201408 },
    { lat: 48.3015146, lng: -122.6447164 },
    { lat: 47.8976713, lng: -122.2507211 }
];

// Define map container style
const mapContainerStyle = {
    width: '100%',
    height: '500px',
};

// Define the center of the map
const center = {
    lat: 47.6062,
    lng: -122.3321,
};

const MapComponent = () => {
    return (
        <LoadScript googleMapsApiKey="AIzaSyC0gOamkyONwn_eT-tq_mDJDv2kqgr5Lr4">
            <GoogleMap mapContainerStyle={mapContainerStyle} center={center} zoom={8}>
                {coordinates.map((coord, index) => (
                    <Marker key={index} position={coord} />
                ))}
            </GoogleMap>
        </LoadScript>
    );
};

export default MapComponent;
*/
