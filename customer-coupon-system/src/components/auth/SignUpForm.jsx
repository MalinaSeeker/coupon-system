import {useRef} from "react";
import {useDispatch} from "react-redux";
import {toggleRegistration} from "../../store/auth-slice";
import {submitSignUp} from "../../api/AuthApi";
import styles from './Auth.module.css'


const SignUpForm = () => {
    const dispatch = useDispatch()

    const emailRef = useRef(null);
    const passwordRef = useRef(null);
    const firstNameRef = useRef(null)
    const lastNameRef = useRef(null)

    const handleSignup = async (event) => {
        event.preventDefault()

        const email = emailRef.current.value
        const password = passwordRef.current.value.toString()
        const firstName = firstNameRef.current.value
        const lastName = lastNameRef.current.value

        submitSignUp({firstName, lastName, email, password})
            .then(dispatch(toggleRegistration())
            )
    }

    return (
        <div className={styles.container}>
            <form onSubmit={handleSignup}>

                <label>First name:</label>
                <input type='text' ref={firstNameRef}/>
                <label>Last name:</label>
                <input type='text' ref={lastNameRef}/>
                <label>Email:</label>
                <input type={'email'} ref={emailRef}/>
                <label>Password:</label>
                <input type={"password"} ref={passwordRef}/>

                <button className={styles.button} type={'submit'}>Sign up</button>

            </form>
        </div>
    )
}
export default SignUpForm