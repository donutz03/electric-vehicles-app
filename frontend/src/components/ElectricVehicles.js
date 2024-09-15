import React, { useState } from 'react';

function ElectricVehicles() {
    const [make, setMake] = useState('');
    const [model, setModel] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [allVehicles, setAllVehicles] = useState([]);

    // Fetch search results
    const searchVehicles = () => {
        let url = `http://localhost:8080/search?`; // Update URL to match your API endpoint

        if (make) url += `make=${encodeURIComponent(make)}&`;
        if (model) url += `model=${encodeURIComponent(model)}`;

        fetch(url)
            .then(response => response.json())
            .then(data => setSearchResults(data))
            .catch(error => console.error('Error fetching search results:', error));
    };

    // Fetch all vehicles
    const fetchAllVehicles = () => {
        fetch('http://localhost:8080/api/vehicles') // Update URL to match your API endpoint
            .then(response => response.json())
            .then(data => setAllVehicles(data))
            .catch(error => console.error('Error fetching all vehicles:', error));
    };

    // Fetch all vehicles when component mounts
    React.useEffect(() => {
        fetchAllVehicles();
    }, []);

    return (
        <div>
            <h1>Electric Vehicles</h1>

            <form id="searchForm" onSubmit={(e) => e.preventDefault()}>
                <label htmlFor="make"></label>
                <input
                    type="text"
                    id="make"
                    placeholder="Make"
                    value={make}
                    onChange={(e) => setMake(e.target.value)}
                />
                <label htmlFor="model"></label>
                <input
                    type="text"
                    id="model"
                    placeholder="Model"
                    value={model}
                    onChange={(e) => setModel(e.target.value)}
                />
                <button type="button" onClick={searchVehicles}>Search</button>
            </form>

            <h2>Search Results</h2>
            <table id="resultsTable" border="1">
                <thead>
                <tr>
                    <th>VIN</th>
                    <th>County</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Code</th>
                    <th>Model Year</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Type</th>
                    <th>CAFV Eligibility</th>
                    <th>Electric Range</th>
                    <th>Base MSRP</th>
                    <th>Legislative District</th>
                    <th>Vehicle Location</th>
                    <th>Electric Utility</th>
                    <th>Census Tract</th>
                </tr>
                </thead>
                <tbody>
                {searchResults.map((vehicle, index) => (
                    <tr key={index}>
                        <td>{vehicle.vin}</td>
                        <td>{vehicle.county}</td>
                        <td>{vehicle.city}</td>
                        <td>{vehicle.state}</td>
                        <td>{vehicle.postalCode}</td>
                        <td>{vehicle.modelYear}</td>
                        <td>{vehicle.make}</td>
                        <td>{vehicle.model}</td>
                        <td>{vehicle.electricVehicleType}</td>
                        <td>{vehicle.cafvEligibility}</td>
                        <td>{vehicle.electricRange}</td>
                        <td>{vehicle.baseMsrp}</td>
                        <td>{vehicle.legislativeDistrict}</td>
                        <td>{vehicle.vehicleLocation}</td>
                        <td>{vehicle.electricUtility}</td>
                        <td>{vehicle.censusTract}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <h2>All Electric Vehicles</h2>
            <table border="1">
                <thead>
                <tr>
                    <th>VIN</th>
                    <th>County</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Code</th>
                    <th>Model Year</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Type</th>
                    <th>CAFV Eligibility</th>
                    <th>Electric Range</th>
                    <th>Base MSRP</th>
                    <th>Legislative District</th>
                    <th>Vehicle Location</th>
                    <th>Electric Utility</th>
                    <th>Census Tract</th>
                </tr>
                </thead>
                <tbody>
                {allVehicles.map((vehicle, index) => (
                    <tr key={index}>
                        <td>{vehicle.vin}</td>
                        <td>{vehicle.county}</td>
                        <td>{vehicle.city}</td>
                        <td>{vehicle.state}</td>
                        <td>{vehicle.postalCode}</td>
                        <td>{vehicle.modelYear}</td>
                        <td>{vehicle.make}</td>
                        <td>{vehicle.model}</td>
                        <td>{vehicle.electricVehicleType}</td>
                        <td>{vehicle.cafvEligibility}</td>
                        <td>{vehicle.electricRange}</td>
                        <td>{vehicle.baseMsrp}</td>
                        <td>{vehicle.legislativeDistrict}</td>
                        <td>{vehicle.vehicleLocation}</td>
                        <td>{vehicle.electricUtility}</td>
                        <td>{vehicle.censusTract}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default ElectricVehicles;
