import {useRef} from "react";
import {useNavigate} from "react-router-dom";
import {login} from "../../store/auth-slice";
import {useDispatch} from "react-redux";
import {submitLogin} from "../../api/AuthApi";
import styles from './Auth.module.css'

const LoginForm = () => {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const emailRef = useRef(null)
    const passwordRef = useRef(null)

    const handleLogin = async (event) => {
        event.preventDefault()

        const email = emailRef.current.value
        const password = passwordRef.current.value

        submitLogin({email, password})
            .then(response => {
                const token = response
                localStorage.setItem(email, token)
                dispatch(login({email, token}));
                navigate('/Profile')
            })
        }

    return (
        <div className={styles.container}>
            <form onSubmit={handleLogin}>

                <label>Email:</label>
                <input type={"email"} ref={emailRef}/>
                <label>Password:</label>
                <input type={"password"} ref={passwordRef}/>

                <button className={styles.button} type={"submit"}>Login</button>

            </form>
        </div>
    )
}
export default LoginForm