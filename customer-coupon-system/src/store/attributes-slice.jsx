import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    attributes: ['Category', 'Expired In Week'],
    currentAttribute: 'Category'
}

const attributesSlice = createSlice({
    name: 'attribute',
    initialState,
    reducers: {
        chooseAttribute(state, action) {
            state.currentAttribute = action.payload
        },
    }
})

export const attributes = state => state.attributes.attributes
export const currentAttribute = state => state.attributes.currentAttribute
export const {chooseAttribute} = attributesSlice.actions
export default attributesSlice.reducer