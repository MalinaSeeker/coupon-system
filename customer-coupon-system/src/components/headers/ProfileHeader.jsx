import {useDispatch} from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import {logout} from "../../store/auth-slice";
import styles from './Header.module.css'


const ProfileHeader = () => {
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const customerEmail = localStorage.key(0)

    const handleLogout = () => {
        localStorage.clear()
        dispatch(logout())
        navigate('/Home')
    }

    return (
        <header className={styles}>
            <h1 className={styles.heading}>Welcome {customerEmail}</h1>
            <nav>
                <ul>
                    <li>
                        <button onClick={handleLogout}>Logout</button>
                    </li>
                    <li>
                        <Link to="/Market">Market</Link>
                    </li>
                    <li>
                        <Link to="/Profile">My Profile</Link>
                    </li>
                </ul>
            </nav>
        </header>
    );
}
export default ProfileHeader