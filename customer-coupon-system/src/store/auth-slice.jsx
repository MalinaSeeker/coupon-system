import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: 'auth',
    initialState: {isRegistered: true, email: null, token: null },
    reducers: {
        toggleRegistration(state) {
          state.isRegistered = !state.isRegistered
        },
        login(state, action) {
            const { email, token } = action.payload;
            state.email = email
            state.token = token
        },
        logout(state) {
            state.email = null
            state.token = null
        }
    }
});

export const { login, logout, toggleRegistration } = authSlice.actions;
export const token = state => state.auth.token;
export const email = state => state.auth.email;
export const isRegistered = state => state.auth.isRegistered
export default authSlice.reducer;
