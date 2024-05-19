import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {currentPage, currentPageSize} from "../../store/pages-slice";
import DisplayByPageNumber from "../../components/page-filter/by-number/DisplayByPageNumber";
import PageRender from "../../components/page-filter/by-number/PageRender";
import {fetchAllCoupons} from "../../api/CustomerApi";
import CouponDisplay from "../../components/coupon-display/CouponDisplay";
import {getCoupons, insertCoupons} from "../../store/coupons-slice";
import styles from '../MainPages.module.css'

const Home = () => {
    const pageNumber = useSelector(currentPage)
    const pageSize = useSelector(currentPageSize)
    const coupons = useSelector(getCoupons)
    const dispatch = useDispatch()

    const [totalPages, setTotalPages] = useState(0)

    useEffect(() => {
        fetchAllCoupons({pageNumber, pageSize})
            .then(response => {
                dispatch(insertCoupons(response.content))
                setTotalPages(response.totalPages)
            })
    }, [pageNumber, pageSize]);

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
                />
            </div>
        ));
    }

    return (
        <div>
            <h1 className={styles.h1}>Coupons Store</h1>
            <h3 className={styles.h3}>For purchasing - Please Login</h3>
            <DisplayByPageNumber/>
            {displayCoupons()}
            <PageRender totalPages={totalPages}/>
        </div>
    )
}
export default Home