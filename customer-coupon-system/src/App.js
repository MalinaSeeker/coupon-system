import {Route, Routes, useNavigate} from "react-router-dom";
import Home from "./pages/home/Home";
import Auth from "./pages/auth/Auth";
import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {login} from "./store/auth-slice";
import Profile from "./pages/profile/Profile";
import ProfileHeader from "./components/headers/ProfileHeader";
import HomeHeader from "./components/headers/HomeHeader";
import Market from "./pages/market/Market";
import Coupon from "./pages/coupon/Coupon";
import './App.css'

function App() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const customerEmail = localStorage.key(0)
    const storedToken = localStorage.getItem(customerEmail)

    useEffect(() => {
        if (storedToken) {
            dispatch(login({customerEmail, storedToken}));
            navigate('/Profile')
        } else {
            navigate("/Home")
        }
    }, [])

    return (
        <div>
            {storedToken ? (<ProfileHeader/>) : (<HomeHeader/>)}
            <Routes>
                <Route path={"/Home"} element={<Home/>}/>
                <Route path={"/Auth"} element={<Auth/>}/>
                <Route path={"/Profile"} element={<Profile/>}/>
                <Route path={"/Market"} element={<Market/>}/>
                <Route path={"/Coupon/:couponUuid"} element={<Coupon/>}/>
            </Routes>
        </div>
    );
}

export default App;
