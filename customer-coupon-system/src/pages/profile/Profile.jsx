import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {fetchPurchasedCoupons} from "../../api/CustomerApi";
import CouponDisplay from "../../components/coupon-display/CouponDisplay";
import DisplayByAttribute from "../../components/page-filter/by-attributes/DisplayByAttribute";
import {useDispatch, useSelector} from "react-redux";
import {getCoupons, insertCoupons} from "../../store/coupons-slice";
import {chooseAttribute} from "../../store/attributes-slice";
import styles from '../MainPages.module.css'

const Profile = () => {
    const customerEmail = localStorage.key(0)
    const customerToken = localStorage.getItem(customerEmail)
    const coupons = useSelector(getCoupons)
    const dispatch = useDispatch()
    const [refreshPage, setRefreshPage] = useState(false)

    useEffect(() => {
        if (customerToken){
            fetchPurchasedCoupons({customerToken})
                .then(response => {
                    dispatch(insertCoupons(response))
                })
        }
    }, [refreshPage]);

    const handleClearingFilter = () => {
      setRefreshPage(!refreshPage)
        dispatch(chooseAttribute("Category"))
    }

    const displayCoupons = () => {
        if (!coupons) {
            return <p>...</p>
        }
        if (coupons.length === 0) {
            return <p>No coupons in the list</p>
        }
        return coupons.map(coupon => (
            <div key={coupon.uuid}>
                <CouponDisplay
                    title={coupon.title}
                    category={coupon.category}
                    endDate={coupon.endDate}
                />
                <Link className={styles.detailsLink} to={`/Coupon/${coupon.uuid}`}>Show details</Link>
            </div>
        ));
    }

    return (
         <div>
            <h1 className={styles.h1}>Your coupons</h1>
            <DisplayByAttribute/>
            <button className={styles.button} onClick={() => {handleClearingFilter()}}>Clear filter</button>
            {displayCoupons()}
        </div>
    )
}
export default Profile