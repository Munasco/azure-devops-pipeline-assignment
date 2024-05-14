import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios';

function AddVisaForm() {

    const [visaFormData, setVisaFormData] = useState({
        name: '',
        processTimeInWeeks: '',
        fees: '',
        country: '',
        region: '',
        gdpRank: ''
    })

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setVisaFormData(previousState => ({
            ...previousState,
            [name]: value
        }))
        console.log(e.target)
    }

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/visas', visaFormData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.status === 201) {
                console.log('Form submitted successfully');
                console.log(response.data)
                alert("Visa was posted!");
            } else {
                console.error('Form submission failed');
                alert("Visa failed to post :(");
            }
        } catch (error) {
            console.error('error submitting form:', error);
        }
    }

  return (
    <form className='grid p-20' onSubmit={handleSubmit}>
        <label htmlFor="name">Name</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Name" name="name" id="name" />
        <label htmlFor="processTimeInWeeks">Process time in weeks</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Process time in weeks" name="processTimeInWeeks" id="processTimeInWeeks" />
        <label htmlFor="fees">fees</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Fees" name="fees" id="fees" />
        <label htmlFor="country">country</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Country" name="country" id="country" />
        <label htmlFor="region">region</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Region" name="region" id="region" />
        <label htmlFor="gdpRank">GDP rank</label>
        <input onChange={handleChange} className="border-2 border-black border-solid" type="text" placeholder="Gdp rank" name="gdpRank" id="gdpRank" />
        <button className="mt-5 py-2 px-4 border-2 border-b-blue-900 border-solid bg-blue-500" type="submit">Submit</button>
    </form>
  )
}

export default AddVisaForm