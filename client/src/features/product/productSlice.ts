import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getProductsService, getProductIDService } from "../../services/productService";
import { AxiosError } from "axios";
import { ProductPayload } from "../../types/ProductPayload";

export interface typeInit {
  data: ProductPayload[];
  dataID: ProductPayload | null;
  loading: boolean;
  error: null | string;
}

const initialState: typeInit = {
  data: [],
  dataID: null,
  loading: false,
  error: null as string | null,
};

export const getProduct = createAsyncThunk("product/FetchData", async (_, { rejectWithValue }) => {

  try {
    const res = await getProductsService();
    return res.data.message;

  } catch (error) {
    if (
      error instanceof AxiosError &&
      error.response &&
      error.response.status === 400
    ) {
      return rejectWithValue(error.response.data.message);
    }
    return rejectWithValue("An unexpected error occurred");
  }
});
export const getProductID = createAsyncThunk("productID/FetchData", async (id: number, { rejectWithValue }) => {
  try {
    const res = await getProductIDService(id);
    return res.data.message;
  } catch (error) {
    if (
      error instanceof AxiosError &&
      error.response &&
      error.response.status === 400
    ) {
      return rejectWithValue(error.response.data.message);
    }
    return rejectWithValue("An unexpected error occurred");
  }
});



export const productSlice = createSlice({
  name: "product",
  initialState: initialState,
  reducers: {
    add: (state) => {
      state.error = null;
      state.loading = true;
    },
    cleanError: (state) => {
      state.error = null;
    },
  },
  extraReducers(builder) {

    builder.addCase(getProduct.pending, (state) => {
      state.error = null;
      state.loading = true;
    });
    builder.addCase(getProduct.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
    });
    builder.addCase(getProduct.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
    });
    builder.addCase(getProductID.pending, (state) => {
      state.error = null;
      state.loading = true;
    });
    builder.addCase(getProductID.fulfilled, (state, action) => {
      state.loading = false;
      state.dataID = action.payload;
    });
    builder.addCase(getProductID.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
    });

  },
});

export const { add, cleanError } = productSlice.actions;
export default productSlice.reducer;
