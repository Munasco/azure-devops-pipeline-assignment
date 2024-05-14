import React, { useEffect, useState } from 'react'
import { VisaInterface } from '../../interface/VisaInterface';

function SeeVisas() {
    const [allVisas, setAllVisas] = useState<Array<VisaInterface>|null>(null);
    const [buttonClicked, setButtonClicked] = useState(false);
    

    useEffect(() => {
        const getVisas = async () => {
        fetch('http://localhost:8080/api/visas', {method: "GET"})
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Request failed with status: ${response.status}`);
                }
                return response.json();
            })
            .then((data) => {
                setAllVisas(data);
                console.log(data);
            })
            .catch((error) => {
                console.error("Error fetching data: ", error)
                alert("Visa failed to be retrieved");
            })
        };

        if (buttonClicked) {
            getVisas();
            setButtonClicked(false);
        }


    }, [buttonClicked])

    return (
    <div>
        <button onClick={() => setButtonClicked(buttonClicked => !buttonClicked)}>Get Visas</button>
        {allVisas ? (
            <>
                {allVisas.map(function (visa) 
                {
                    return (
                        <React.Fragment key={visa.visaId}>
                                <p>id: {visa.visaId}</p>
                                <p>name: {visa.name}</p>
                                <p>processTimeInDays: {visa.processTimeInDays}</p>
                                <p>country: {visa.country_code}</p>
                                <p>region: {visa.region}</p>
                                <p>gdpRank: {visa.gdpRank}</p>
                        </React.Fragment>
                    )
                }
                )}
            </>
            ) : (<h1>Load this</h1>)}
    </div>
    )
}

export default SeeVisas