import Welcome from './components/Welcome';
import MyClimbs from './components/MyClimbs';
import ClimberApp from './components/climber/ClimberApp';

import Nav from './Nav';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Router>
        <Nav/>
        <Switch>
          <Route path='/' exact>
            <Welcome />
          </Route>
          <Route path='/my-climbs' exact>
            <MyClimbs />
          </Route>
          <Route path='/climbers' exact>
            <ClimberApp />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
