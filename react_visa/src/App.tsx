import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <form action="localhost" method="post" className="p-10">
        <label htmlFor="name">Name</label>
        <input type="text" name="name" id="name" />
        <label htmlFor="processTimeInWeeks">Process time in weeks</label>
        <input type="text" name="processTimeInWeeks" id="processTimeInWeeks" />
        <label htmlFor="fees">fees</label>
        <input type="text" name="fees" id="fees" />
        <label htmlFor="country">country</label>
        <input type="text" name="country" id="country" />
        <label htmlFor="region">region</label>
        <input type="text" name="region" id="region" />
        <label htmlFor="gdpRank">GDP rank</label>
        <input type="text" name="gdpRank" id="gdpRank" />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default App;
