import styles from './CouponDisplay.module.css'
const CouponDisplay = (props) => {
    const { title, category, imageURL, description, startDate, endDate, price } = props;

    return (
        <div className={styles.container}>
            {title && <h2 className={styles.h2}>{title}</h2>}
            {category && <h3 className={styles.h3}>{category}</h3>}
            {imageURL && <img src={imageURL} width={150} alt={title}/>}
            {description && <p className={styles.p}>{description}</p>}
            {startDate && <p className={styles.p}>Start Date: {startDate}</p>}
            {endDate && <p className={styles.p}>End Date: {endDate}</p>}
            {price && <h3 className={styles.h3}>Price: {price}</h3>}
        </div>
    );
}

export default CouponDisplay;
