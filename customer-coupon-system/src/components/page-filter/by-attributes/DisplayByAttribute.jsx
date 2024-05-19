import { useDispatch, useSelector } from "react-redux";
import { attributes, chooseAttribute, currentAttribute } from "../../../store/attributes-slice";
import { fetchAllCategories, fetchCouponsByCategory, fetchExpiredInWeekCoupons } from "../../../api/CustomerApi";
import { insertCoupons } from "../../../store/coupons-slice";
import { useState, useEffect } from "react";
import styles from '../PageFilter.module.css'

const DisplayByAttribute = () => {
    const customerEmail = localStorage.key(0);
    const customerToken = localStorage.getItem(customerEmail);
    const dispatch = useDispatch();
    const attributeOptions = useSelector(attributes);
    const attribute = useSelector(currentAttribute);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetchAllCategories()
            .then(response => {
            setCategories(response);
        });
    }, []);

    const handleAttributeChange = (event) => {
        const category = event.target.value;
        dispatch(chooseAttribute(category));

        if (category === 'Expired In Week'){
            fetchExpiredInWeekCoupons({ customerToken })
                .then(response => {
                    dispatch(insertCoupons(response));
                });
        }
    };

    const handleCategoryChoice = (category) => {
        fetchCouponsByCategory({ customerToken, category })
            .then(response => {
                dispatch(insertCoupons(response));
            });
    };

    const displayCategories = () => {
        return [
            <option key="" value="">Select a category</option>,
            ...categories.map(category => (
                <option key={category} value={category}>{category}</option>
            ))
        ];
    };

    const displayAttributes = () => {
        return attributeOptions.map(attribute => (
            <option key={attribute} value={attribute}>{attribute}</option>
        ));
    };

    return (
        <div className={styles.container}>
            <label className={styles.label} htmlFor="attributeSelect">Filter by:</label>
            <select className={styles.select}
                id="attributeSelect"
                value={attribute}
                onChange={handleAttributeChange}
            >
                {displayAttributes()}
            </select>
            {attribute === 'Category' && (
                <select className={styles.select}
                    id="categorySelect"
                    onChange={(event) => handleCategoryChoice(event.target.value)}
                >
                    {displayCategories()}
                </select>
            )}
        </div>
    );


};

export default DisplayByAttribute;
