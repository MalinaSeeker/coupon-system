import { configureStore } from "@reduxjs/toolkit";
import pagesReducer from './pages-slice'
import authReducer from './auth-slice';
import attributeReducer from './attributes-slice'
import couponsReducer from './coupons-slice'

export const store = configureStore({
    reducer: {
        pages: pagesReducer,
        auth: authReducer,
        attributes: attributeReducer,
        coupons: couponsReducer
    }
})
export default store