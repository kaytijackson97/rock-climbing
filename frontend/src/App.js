import {
  BrowserRouter as Router,
  Switch,
  Route,
} from 'react-router-dom';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';

import Welcome from './components/Welcome';
import MyClimbs from './components/myClimbs/MyClimbs';
import ClimberList from './components/climber/ClimberList';
import AddClimb from './components/myClimbs/AddClimb';
import ClimberProfile from './components/profile/ClimberProfile';
import Nav from './Nav';

import { CLIENT_ENDPOINTS } from './constants/Routes';

// Actions
import { getAllClimbs } from './store/actions/climbs.action';
import { getAllClimbers } from './store/actions/climbers.action';
import { getAllGyms } from './store/actions/gyms.action';
import { getAllRouteGrades } from './store/actions/routeGrades.action';

import climb from './sass/climb.scss';

function App() {
  const dispatch = useDispatch();
  const { 
    MY_CLIMBS,
    ADD_CLIMB,
    CLIMBERS,
    CLIMBER_PROFILE,
  } = CLIENT_ENDPOINTS

  useEffect(() => {
    dispatch(getAllClimbers());
    dispatch(getAllClimbs());
    dispatch(getAllGyms());
    dispatch(getAllRouteGrades());
  }, [dispatch]);

  return (
    <div className="App">
      <Router>
        <Nav/>
        <Switch>
          <Route exact path='/' component={Welcome} />
          <Route exact path={`${MY_CLIMBS}`} component={MyClimbs} />
          <Route exact path={`${ADD_CLIMB}`} component={AddClimb} />
          <Route exact path={`${CLIMBERS}`}  component={ClimberList} />
          <Route exact path={`${CLIMBER_PROFILE}`} component={ClimberProfile} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
