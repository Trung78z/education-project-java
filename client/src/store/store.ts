import { configureStore } from "@reduxjs/toolkit";
import productSlice from "../features/product/productSlice";
import newSlice from "../features/news/newsSlice";

export const store = configureStore({
    reducer: {
        product: productSlice,
        news: newSlice

    }
});
export type appSelector = ReturnType<typeof store.getState>;
export type appDispatch = typeof store.dispatch;
export type appStore = typeof store;
