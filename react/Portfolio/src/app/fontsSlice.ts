import {createSlice, type PayloadAction} from "@reduxjs/toolkit";

type Font = {
    currentFont: string;
}
const initialState: Font = {
    currentFont: "FiraCode"
}

export const fontsSlice = createSlice({
    name: 'fonts',
    initialState,
    reducers: {
        setFont(state, action: PayloadAction<string>){
            state.currentFont = action.payload;
        }
    }
});

export const { setFont } = fontsSlice.actions;
export default fontsSlice.reducer;