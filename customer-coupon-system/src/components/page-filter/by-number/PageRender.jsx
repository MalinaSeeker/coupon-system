import {useDispatch} from "react-redux";
import {choosePage} from "../../../store/pages-slice";
import styles from '../PageFilter.module.css'

const PageRender = ({totalPages}) => {
    const dispatch = useDispatch()

    const handlePageChange = (page) => {
        dispatch(choosePage(page))
    }

    const pages = []
    for (let page = 0; page < totalPages; page++) {
        pages.push(<button className={styles.numbers} key={page} onClick={() => handlePageChange(page)}>{page+1}</button>)
    }

    return(
        pages
    )
}
export default PageRender