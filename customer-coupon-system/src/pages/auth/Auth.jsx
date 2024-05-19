import SignUpForm from "../../components/auth/SignUpForm";
import LoginForm from "../../components/auth/LoginForm";
import {useDispatch, useSelector} from "react-redux";
import {isRegistered, toggleRegistration} from "../../store/auth-slice";
import styles from './AuthPage.module.css'


const Auth = () => {
    const dispatch = useDispatch()
    const isUserRegistered  = useSelector(isRegistered)

    const toggle = () => {
        dispatch(toggleRegistration())
    }

    return (
        <div className={styles.container}>
            {isUserRegistered  ? (
                <div>
                    <LoginForm/>
                    <button className={styles.button} onClick={(toggle)}>New here? Sign Up</button>
                </div>

            ) : (
                <div>
                    <SignUpForm/>
                    <button className={styles.button} onClick={toggle}>Already have an account? Login</button>
                </div>
                )
            }
        </div>
    )
}
export default Auth
