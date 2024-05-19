import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {fetchCoupon} from "../../api/CustomerApi";
import CouponDisplay from "../../components/coupon-display/CouponDisplay";

const Coupon = () => {
    const params = useParams()
    const uuid = params.couponUuid
    const [coupon, setCoupon] = useState('')

    useEffect(() => {
        fetchCoupon({uuid})
            .then(response => {
                setCoupon(response)
            })
    }, []);

    return(
        <div>
            <CouponDisplay
                title={coupon.title}
                category={coupon.category}
                imageURL={coupon.imageURL}
                startDate={coupon.startDate}
                endDate={coupon.endDate}
                description={coupon.description}
                price={coupon.price}
            />
        </div>


    )
}
export default Coupon