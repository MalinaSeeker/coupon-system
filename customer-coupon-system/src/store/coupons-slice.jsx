import { createSlice } from "@reduxjs/toolkit";

const couponsSlice = createSlice({
    name: 'coupons',
    initialState: {coupons: []},
    reducers: {
        insertCoupons(state, action) {
            state.coupons = action.payload
        }
    }
});

export const {insertCoupons} = couponsSlice.actions;
export const getCoupons = state => state.coupons.coupons
export default couponsSlice.reducer;
