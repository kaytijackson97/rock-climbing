import Welcome from './components/Welcome';
import MyClimbs from './components/myClimbs/MyClimbs';
import ClimberApp from './components/climber/ClimberApp';
import ClimberProfile from './components/profile/ClimberProfile';
import { getAllClimbs } from './actions/climbs.action';
import { getAllClimbers } from './actions/climbers.action';
import Nav from './Nav';

import {
  BrowserRouter as Router,
  Switch,
  Route,
} from 'react-router-dom';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

function App() {
  const climbs = useSelector((state) => state.entries);
  const climbers = useSelector((state) => state.entries);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getAllClimbers());
    dispatch(getAllClimbs());
}, [dispatch]);

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
          <Route path='/climber/:climberId' exact>
            <ClimberProfile />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
