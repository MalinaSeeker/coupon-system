import {useDispatch, useSelector} from "react-redux";
import {choosePage, choosePageSize, currentPageSize, pageSizes} from "../../../store/pages-slice";
import styles from '../PageFilter.module.css'

const DisplayByPageNumber = () => {
    const dispatch = useDispatch()
    const pageOptions = useSelector(pageSizes)
    const currentSize = useSelector(currentPageSize)

    const displayPageNumbers = () => {
        return (
            pageOptions.map(number => {
                return (
                    <option key={number} value={number}>{number}</option>
                )
            }))
    }

    function handlePageSizeChange(event) {
        dispatch(choosePageSize(event.target.value))
        dispatch(choosePage(0))
    }

    return (
        <div className={styles.container}>
            <label className={styles.label} htmlFor="pageSizeId">Page Size:</label>
            <select className={styles.select}
                id="pageSizeId"
                value={currentSize}
                onChange={handlePageSizeChange}
            >
                {displayPageNumbers()}
            </select>
        </div>
    )
}
export default DisplayByPageNumber