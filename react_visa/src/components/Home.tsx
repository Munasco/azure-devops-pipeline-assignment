import { Button } from "./ui/button";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="container mx-auto py-8 px-4">
      <div className="text-center mb-12">
        <h1 className="text-4xl font-bold tracking-tight mb-4">
          Welcome to VisaAPI
        </h1>
        <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
          Your comprehensive platform for managing visa applications and exploring visa requirements across different countries.
        </p>
      </div>

      <div className="grid md:grid-cols-2 gap-6 max-w-4xl mx-auto">
        <Card className="hover:shadow-lg transition-shadow">
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              📝 Post New Visa
            </CardTitle>
            <CardDescription>
              Add a new visa type with all the required details including processing time, fees, and requirements.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Button asChild className="w-full">
              <Link to="/post_visa">Create Visa Entry</Link>
            </Button>
          </CardContent>
        </Card>

        <Card className="hover:shadow-lg transition-shadow">
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              🔍 View All Visas
            </CardTitle>
            <CardDescription>
              Browse through all available visa types, compare processing times, fees, and requirements.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Button asChild variant="outline" className="w-full">
              <Link to="/see_visas">Browse Visas</Link>
            </Button>
          </CardContent>
        </Card>
      </div>

      <div className="mt-16 text-center">
        <h2 className="text-2xl font-semibold mb-4">Features</h2>
        <div className="grid md:grid-cols-3 gap-4 max-w-3xl mx-auto">
          <div className="p-4">
            <div className="text-3xl mb-2">🌍</div>
            <h3 className="font-medium">Global Coverage</h3>
            <p className="text-sm text-muted-foreground">Visa information for countries worldwide</p>
          </div>
          <div className="p-4">
            <div className="text-3xl mb-2">⚡</div>
            <h3 className="font-medium">Real-time Updates</h3>
            <p className="text-sm text-muted-foreground">Always up-to-date visa requirements</p>
          </div>
          <div className="p-4">
            <div className="text-3xl mb-2">📊</div>
            <h3 className="font-medium">Detailed Analytics</h3>
            <p className="text-sm text-muted-foreground">Processing times and success rates</p>
          </div>
        </div>
      </div>
    </div>
  );
}
