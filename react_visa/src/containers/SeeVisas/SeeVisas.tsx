import React, { useEffect, useState, useMemo } from 'react'
import { VisaInterface } from '../../interface/VisaInterface';
import { Button } from '../../components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '../../components/ui/card';
import { Badge } from '../../components/ui/badge';
import { Input } from '../../components/ui/input';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '../../components/ui/select';
import { Checkbox } from '../../components/ui/checkbox';
import { Label } from '../../components/ui/label';
import { Loader2, RefreshCw, Globe, Clock, DollarSign, MapPin, Search, Filter, X } from 'lucide-react';

function SeeVisas() {
    const [allVisas, setAllVisas] = useState<Array<VisaInterface>|null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    
    // Filter states
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedRegion, setSelectedRegion] = useState<string>('all');
    const [selectedCountry, setSelectedCountry] = useState<string>('all');
    const [maxProcessingTime, setMaxProcessingTime] = useState<string>('');
    const [maxFees, setMaxFees] = useState<string>('');
    const [hasPerks, setHasPerks] = useState(false);
    const [hasCitizenship, setHasCitizenship] = useState(false);
    const [sortBy, setSortBy] = useState<string>('name');
    const [showFilters, setShowFilters] = useState(false);
    
    const getVisas = async () => {
        setLoading(true);
        setError(null);
        try {
            // Using test endpoint for now since the main endpoint has database issues
            const response = await fetch('http://localhost:8080/api/visas/test', {method: "GET"});
            if (!response.ok) {
                throw new Error(`Request failed with status: ${response.status}`);
            }
            const data = await response.json();
            setAllVisas(data);
            console.log(data);
        } catch (error) {
            console.error("Error fetching data: ", error);
            setError("Failed to fetch visas. Please try again.");
        } finally {
            setLoading(false);
        }
    };

    // Load visas automatically on component mount
    useEffect(() => {
        getVisas();
    }, [])

    // Get unique regions and countries for filter dropdowns
    const regions = useMemo(() => {
        if (!allVisas) return [];
        return Array.from(new Set(allVisas.map(visa => visa.region)));
    }, [allVisas]);

    const countries = useMemo(() => {
        if (!allVisas) return [];
        return Array.from(new Set(allVisas.map(visa => visa.countryCode)));
    }, [allVisas]);

    // Filter and sort visas
    const filteredAndSortedVisas = useMemo(() => {
        if (!allVisas) return [];
        
        let filtered = allVisas.filter(visa => {
            // Search term filter
            const matchesSearch = visa.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                                visa.countryCode.toLowerCase().includes(searchTerm.toLowerCase());
            
            // Region filter
            const matchesRegion = selectedRegion === 'all' || visa.region === selectedRegion;
            
            // Country filter
            const matchesCountry = selectedCountry === 'all' || visa.countryCode === selectedCountry;
            
            // Processing time filter
            const matchesProcessingTime = !maxProcessingTime || visa.processTimeInDays <= parseInt(maxProcessingTime);
            
            // Fees filter
            const matchesFees = !maxFees || visa.feesHigh <= parseFloat(maxFees);
            
            // Perks filter
            const matchesPerks = !hasPerks || visa.hasPerks;
            
            // Citizenship filter
            const matchesCitizenship = !hasCitizenship || visa.hasRoadToCitizenship;
            
            return matchesSearch && matchesRegion && matchesCountry && 
                   matchesProcessingTime && matchesFees && matchesPerks && matchesCitizenship;
        });
        
        // Sort results
        filtered.sort((a, b) => {
            switch (sortBy) {
                case 'name':
                    return a.name.localeCompare(b.name);
                case 'processingTime':
                    return a.processTimeInDays - b.processTimeInDays;
                case 'feesLow':
                    return a.feesLow - b.feesLow;
                case 'country':
                    return a.countryCode.localeCompare(b.countryCode);
                case 'region':
                    return a.region.localeCompare(b.region);
                default:
                    return 0;
            }
        });
        
        return filtered;
    }, [allVisas, searchTerm, selectedRegion, selectedCountry, maxProcessingTime, maxFees, hasPerks, hasCitizenship, sortBy]);

    // Clear all filters
    const clearFilters = () => {
        setSearchTerm('');
        setSelectedRegion('all');
        setSelectedCountry('all');
        setMaxProcessingTime('');
        setMaxFees('');
        setHasPerks(false);
        setHasCitizenship(false);
    };

    return (
        <div className="container mx-auto py-8 px-4">
            <div className="flex justify-between items-center mb-8">
                <div>
                    <h1 className="text-3xl font-bold tracking-tight">All Visas</h1>
                    <p className="text-muted-foreground">Browse all available visa types and their requirements</p>
                    <p className="text-sm text-muted-foreground mt-1">
                        Showing {filteredAndSortedVisas.length} of {allVisas?.length || 0} visas
                    </p>
                </div>
                <div className="flex gap-2">
                    <Button 
                        onClick={() => setShowFilters(!showFilters)}
                        variant="outline"
                        className="mr-2"
                    >
                        <Filter className="mr-2 h-4 w-4" />
                        {showFilters ? "Hide Filters" : "Show Filters"}
                    </Button>
                    <Button 
                        onClick={getVisas} 
                        disabled={loading} 
                        className="bg-primary text-primary-foreground hover:bg-primary/90 border-primary"
                    >
                        {loading ? (
                            <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                        ) : (
                            <RefreshCw className="mr-2 h-4 w-4" />
                        )}
                        {loading ? "Loading..." : "Refresh"}
                    </Button>
                </div>
            </div>

            {/* Search and Filters */}
            <div className="mb-6 space-y-4">
                {/* Search Bar */}
                <div className="relative">
                    <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground h-4 w-4" />
                    <Input
                        placeholder="Search visas by name or country..."
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        className="pl-10"
                    />
                </div>

                {/* Advanced Filters */}
                {showFilters && (
                    <Card className="p-4">
                        <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4 mb-4">
                            {/* Sort By */}
                            <div className="space-y-2">
                                <Label>Sort By</Label>
                                <Select value={sortBy} onValueChange={setSortBy}>
                                    <SelectTrigger>
                                        <SelectValue placeholder="Sort by..." />
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem value="name">Name</SelectItem>
                                        <SelectItem value="processingTime">Processing Time</SelectItem>
                                        <SelectItem value="feesLow">Fees (Low to High)</SelectItem>
                                        <SelectItem value="country">Country</SelectItem>
                                        <SelectItem value="region">Region</SelectItem>
                                    </SelectContent>
                                </Select>
                            </div>

                            {/* Region Filter */}
                            <div className="space-y-2">
                                <Label>Region</Label>
                                <Select value={selectedRegion} onValueChange={setSelectedRegion}>
                                    <SelectTrigger>
                                        <SelectValue placeholder="All regions" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem value="all">All Regions</SelectItem>
                                        {regions.map(region => (
                                            <SelectItem key={region} value={region}>{region}</SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>

                            {/* Country Filter */}
                            <div className="space-y-2">
                                <Label>Country</Label>
                                <Select value={selectedCountry} onValueChange={setSelectedCountry}>
                                    <SelectTrigger>
                                        <SelectValue placeholder="All countries" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem value="all">All Countries</SelectItem>
                                        {countries.map(country => (
                                            <SelectItem key={country} value={country}>{country}</SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>

                            {/* Max Processing Time */}
                            <div className="space-y-2">
                                <Label>Max Processing Time (days)</Label>
                                <Input
                                    type="number"
                                    placeholder="e.g., 30"
                                    value={maxProcessingTime}
                                    onChange={(e) => setMaxProcessingTime(e.target.value)}
                                />
                            </div>
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                            {/* Max Fees */}
                            <div className="space-y-2">
                                <Label>Max Fees (USD)</Label>
                                <Input
                                    type="number"
                                    placeholder="e.g., 500"
                                    value={maxFees}
                                    onChange={(e) => setMaxFees(e.target.value)}
                                />
                            </div>

                            {/* Perks Checkbox */}
                            <div className="flex items-center space-x-2 pt-7">
                                <Checkbox
                                    id="hasPerks"
                                    checked={hasPerks}
                                    onCheckedChange={(checked) => setHasPerks(checked as boolean)}
                                />
                                <Label htmlFor="hasPerks">Has Perks</Label>
                            </div>

                            {/* Citizenship Checkbox */}
                            <div className="flex items-center space-x-2 pt-7">
                                <Checkbox
                                    id="hasCitizenship"
                                    checked={hasCitizenship}
                                    onCheckedChange={(checked) => setHasCitizenship(checked as boolean)}
                                />
                                <Label htmlFor="hasCitizenship">Path to Citizenship</Label>
                            </div>
                        </div>

                        <div className="flex justify-end">
                            <Button onClick={clearFilters} variant="outline" size="sm">
                                <X className="mr-2 h-4 w-4" />
                                Clear All Filters
                            </Button>
                        </div>
                    </Card>
                )}
            </div>

            {error && (
                <Card className="mb-6 border-red-200 bg-red-50">
                    <CardContent className="pt-6">
                        <p className="text-red-600">{error}</p>
                    </CardContent>
                </Card>
            )}

            {loading && !allVisas && (
                <div className="text-center py-12">
                    <Loader2 className="mx-auto h-8 w-8 animate-spin mb-4" />
                    <p className="text-muted-foreground">Loading visas...</p>
                </div>
            )}

            {allVisas && allVisas.length === 0 && (
                <Card className="text-center py-12">
                    <CardContent>
                        <Globe className="mx-auto h-12 w-12 text-muted-foreground mb-4" />
                        <h3 className="text-lg font-semibold mb-2">No visas found</h3>
                        <p className="text-muted-foreground">There are currently no visas in the database.</p>
                    </CardContent>
                </Card>
            )}

            {allVisas && allVisas.length > 0 && filteredAndSortedVisas.length === 0 && (
                <Card className="text-center py-12">
                    <CardContent>
                        <Search className="mx-auto h-12 w-12 text-muted-foreground mb-4" />
                        <h3 className="text-lg font-semibold mb-2">No visas match your filters</h3>
                        <p className="text-muted-foreground mb-4">Try adjusting your search criteria to see more results.</p>
                        <Button onClick={clearFilters} variant="outline">
                            <X className="mr-2 h-4 w-4" />
                            Clear All Filters
                        </Button>
                    </CardContent>
                </Card>
            )}

            {filteredAndSortedVisas && filteredAndSortedVisas.length > 0 && (
                <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                    {filteredAndSortedVisas.map(function (visa) {
                        return (
                            <Card key={visa.visaId} className="hover:shadow-lg transition-shadow">
                                <CardHeader>
                                    <div className="flex justify-between items-start">
                                        <CardTitle className="text-xl">{visa.name}</CardTitle>
                                        <Badge variant="secondary">#{visa.visaId}</Badge>
                                    </div>
                                    <CardDescription className="flex items-center gap-1">
                                        <MapPin className="h-4 w-4" />
                                        {visa.countryCode} • {visa.region}
                                    </CardDescription>
                                </CardHeader>
                                <CardContent className="space-y-4">
                                    <div className="grid grid-cols-2 gap-4">
                                        <div className="flex items-center gap-2">
                                            <Clock className="h-4 w-4 text-muted-foreground" />
                                            <div>
                                                <p className="text-sm font-medium">{visa.processTimeInDays} days</p>
                                                <p className="text-xs text-muted-foreground">Processing time</p>
                                            </div>
                                        </div>
                                        
                                        <div className="flex items-center gap-2">
                                            <DollarSign className="h-4 w-4 text-muted-foreground" />
                                            <div>
                                                <p className="text-sm font-medium">${visa.feesLow} - ${visa.feesHigh}</p>
                                                <p className="text-xs text-muted-foreground">Fee range (USD)</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="flex flex-wrap gap-2 mt-4">
                                        <Badge variant={visa.hasPerks ? "default" : "secondary"}>
                                            {visa.hasPerks ? "Has Perks" : "No Perks"}
                                        </Badge>
                                        <Badge variant={visa.hasRoadToCitizenship ? "default" : "secondary"}>
                                            {visa.hasRoadToCitizenship ? "Path to Citizenship" : "No Citizenship Path"}
                                        </Badge>
                                        <Badge variant="outline">
                                            GDP Rank: #{visa.gdpRank}
                                        </Badge>
                                    </div>
                                </CardContent>
                            </Card>
                        )
                    })}
                </div>
            )}
        </div>
    )
}

export default SeeVisas