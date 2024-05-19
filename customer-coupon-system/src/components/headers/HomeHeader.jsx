import {Link} from "react-router-dom";
import styles from './Header.module.css'

const HomeHeader = () => {
    return (
        <header className={styles}>
            <h1 className={styles.heading}>Welcome</h1>
            <nav>
                <ul>
                    <li>
                        <Link to="/Home" className={styles.link}>
                            Home
                        </Link>
                    </li>
                    <li>
                        <Link to="/Auth" className={styles.link}>
                            Login
                        </Link>
                    </li>
                </ul>
            </nav>
        </header>
    );
}
export default HomeHeader