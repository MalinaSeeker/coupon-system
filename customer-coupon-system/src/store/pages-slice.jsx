import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    pageSizes: [2, 4, 6],
    currentPageSize: 4,
    currentPage: 0
}

const pagesSlice = createSlice({
    name: 'page',
    initialState,
    reducers: {
        choosePageSize(state, action) {
            state.currentPageSize = action.payload
        },
        choosePage(state, action) {
            state.currentPage = action.payload
        }
    }
})

export const pageSizes = state => state.pages.pageSizes
export const currentPageSize = state => state.pages.currentPageSize
export const currentPage = state => state.pages.currentPage
export const {choosePageSize, choosePage} = pagesSlice.actions
export default pagesSlice.reducer