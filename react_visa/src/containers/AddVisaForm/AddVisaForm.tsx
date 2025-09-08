import React, { ChangeEvent, FormEvent, useState } from 'react'
import axios from 'axios';
import { Button } from '../../components/ui/button';
import { Input } from '../../components/ui/input';
import { Label } from '../../components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../../components/ui/card';

function AddVisaForm() {
    const [visaFormData, setVisaFormData] = useState({
        name: '',
        processTimeInWeeks: '',
        fees: '',
        country: '',
        region: '',
        gdpRank: ''
    })

    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setVisaFormData(previousState => ({
            ...previousState,
            [name]: value
        }))
    }

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setIsSubmitting(true);

        try {
            const response = await axios.post('http://localhost:8080/api/visas', visaFormData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.status === 201) {
                console.log('Form submitted successfully');
                console.log(response.data)
                alert("Visa was posted successfully!");
                // Reset form
                setVisaFormData({
                    name: '',
                    processTimeInWeeks: '',
                    fees: '',
                    country: '',
                    region: '',
                    gdpRank: ''
                });
            } else {
                console.error('Form submission failed');
                alert("Failed to post visa. Please try again.");
            }
        } catch (error) {
            console.error('error submitting form:', error);
            alert("An error occurred. Please try again.");
        } finally {
            setIsSubmitting(false);
        }
    }

    return (
        <div className="container mx-auto py-8 px-4">
            <Card className="max-w-2xl mx-auto">
                <CardHeader>
                    <CardTitle className="text-2xl">Add New Visa</CardTitle>
                    <CardDescription>
                        Fill in the details below to add a new visa type to the database.
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="name">Visa Name *</Label>
                                <Input 
                                    id="name"
                                    name="name"
                                    type="text"
                                    placeholder="e.g., Tourist Visa"
                                    value={visaFormData.name}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            
                            <div className="space-y-2">
                                <Label htmlFor="processTimeInWeeks">Processing Time (Weeks) *</Label>
                                <Input 
                                    id="processTimeInWeeks"
                                    name="processTimeInWeeks"
                                    type="number"
                                    placeholder="e.g., 2"
                                    value={visaFormData.processTimeInWeeks}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="fees">Fees (USD) *</Label>
                                <Input 
                                    id="fees"
                                    name="fees"
                                    type="number"
                                    step="0.01"
                                    placeholder="e.g., 150.00"
                                    value={visaFormData.fees}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            
                            <div className="space-y-2">
                                <Label htmlFor="country">Country *</Label>
                                <Input 
                                    id="country"
                                    name="country"
                                    type="text"
                                    placeholder="e.g., United States"
                                    value={visaFormData.country}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <Label htmlFor="region">Region *</Label>
                                <Input 
                                    id="region"
                                    name="region"
                                    type="text"
                                    placeholder="e.g., North America"
                                    value={visaFormData.region}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            
                            <div className="space-y-2">
                                <Label htmlFor="gdpRank">GDP Rank</Label>
                                <Input 
                                    id="gdpRank"
                                    name="gdpRank"
                                    type="number"
                                    placeholder="e.g., 1"
                                    value={visaFormData.gdpRank}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <Button 
                            type="submit" 
                            className="w-full" 
                            disabled={isSubmitting}
                        >
                            {isSubmitting ? "Submitting..." : "Add Visa"}
                        </Button>
                    </form>
                </CardContent>
            </Card>
        </div>
    )
}

export default AddVisaForm