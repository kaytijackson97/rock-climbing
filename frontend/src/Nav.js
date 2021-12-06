import { Link } from 'react-router-dom';

function Nav() {
    return (
        <div className="ml-3">
            <nav className="navbar navbar-expand-lg navbar-light  bg-light">
                <Link className="navbar-brand" to="/">Home</Link>
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" to="/my-climbs">My Climbs</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/climber">My Profile</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

export default Nav;