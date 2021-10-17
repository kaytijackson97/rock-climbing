import Welcome from './Components/Welcome';
import MyClimbs from './Components/My-Climbs';
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
        </Switch>
      </Router>
    </div>
  );
}

export default App;
