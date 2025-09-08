import { Link, useLocation } from "react-router-dom";
import { Button } from "./ui/button";

export default function Navigation() {
  const location = useLocation();

  const isActive = (path: string) => location.pathname === path;

  return (
    <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
      <div className="container flex h-16 items-center justify-between">
        <div className="flex items-center space-x-4">
          <Link to="/" className="flex items-center space-x-2">
            <h1 className="text-xl font-bold">VisaAPI</h1>
          </Link>
        </div>
        
        <div className="flex items-center space-x-4">
          <Button
            variant={isActive("/") ? "default" : "ghost"}
            asChild
          >
            <Link to="/">Home</Link>
          </Button>
          
          <Button
            variant={isActive("/post_visa") ? "default" : "ghost"}
            asChild
          >
            <Link to="/post_visa">Post Visa</Link>
          </Button>
          
          <Button
            variant={isActive("/see_visas") ? "default" : "ghost"}
            asChild
          >
            <Link to="/see_visas">View Visas</Link>
          </Button>
        </div>
      </div>
    </nav>
  );
}
