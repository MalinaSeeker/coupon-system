import {useEffect} from "react";
import {Link} from "react-router-dom";
import {purchaseCoupon} from "../../api/CustomerApi";
import {fetchNotPurchasedCoupons} from "../../api/CustomerApi";
import CouponDisplay from "../../components/coupon-display/CouponDisplay";
import {useDispatch, useSelector} from "react-redux";
import {getCoupons, insertCoupons} from "../../store/coupons-slice";
import styles from '../MainPages.module.css'

const Market = () => {
    const customerEmail = localStorage.key(0)
    const customerToken = localStorage.getItem(customerEmail)
    const coupons = useSelector(getCoupons)
    const dispatch = useDispatch()

    useEffect(() => {
        if (customerToken){
            fetchNotPurchasedCoupons({customerToken})
                .then(response => {
                    dispatch(insertCoupons(response))
                })
        }
    }, [])

    const handlePurchase = (couponUuid) => {
        purchaseCoupon({customerToken, couponUuid})
        window.location.reload()
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
                    imageURL={coupon.imageURL}
                    endDate={coupon.endDate}
                />
                <Link className={styles.detailsLink} to={`/Coupon/${coupon.uuid}`}>Show details</Link> <br/>
                <button className={styles.purchaseButton} onClick={() => handlePurchase(coupon.uuid)}>Purchase</button>
            </div>
        ))

    }

    return (
        <div>
            <h1 className={styles.h1}>Coupons Market</h1>
            {displayCoupons()}
        </div>
    )

}
export default Market