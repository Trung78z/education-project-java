import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getNewsService, getNewIDService } from "../../services/newService.ts";
import { AxiosError } from "axios";
import { NewPayload } from "../../types/newsPayload";

export interface typeInit {
  data: NewPayload[];
  dataID: NewPayload | null;
  loading: boolean;
  error: null | string;
}

const initialState: typeInit = {
  data: [],
  dataID: null,
  loading: false,
  error: null as string | null,
};

export const getNew = createAsyncThunk("new/FetchData", async (_, { rejectWithValue }) => {

  try {
    const res = await getNewsService();
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
export const getNewID = createAsyncThunk("newID/FetchData", async (id: number, { rejectWithValue }) => {
  try {
    const res = await getNewIDService(id);
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



export const newSlice = createSlice({
  name: "new",
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

    builder.addCase(getNew.pending, (state) => {
      state.error = null;
      state.loading = true;
    });
    builder.addCase(getNew.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
    });
    builder.addCase(getNew.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
    });
    builder.addCase(getNewID.pending, (state) => {
      state.error = null;
      state.loading = true;
    });
    builder.addCase(getNewID.fulfilled, (state, action) => {
      state.loading = false;
      state.dataID = action.payload;
    });
    builder.addCase(getNewID.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
    });

  },
});

export const { add, cleanError } = newSlice.actions;
export default newSlice.reducer;
