import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

import { AxiosError } from "axios";
import { AuthLogin, AuthPayload, AuthRegister } from "../../types/authPayload";
import { getAuth, getLogin, postAuth } from "../../services/authService";

export interface typeInit {
  data: AuthPayload | null;
  loading: boolean;
  error: null | string;
  auth: boolean
}

const initialState: typeInit = {
  data: null,
  loading: false,
  error: null as string | null,
  auth: false
};

export const login = createAsyncThunk("auth/FetchData", async (data: AuthLogin, { rejectWithValue }) => {

  try {
    const res = await getLogin(data);
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

export const register = createAsyncThunk("auth/register", async (data: AuthRegister, { rejectWithValue }) => {
  try {
    const res = await postAuth(data);
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

export const checkAuth = createAsyncThunk("auth/checkAuth", async (_, { rejectWithValue }) => {
  try {
    const res = await getAuth()
    return res.data.message
  } catch (error) {
    if (
      error instanceof AxiosError &&
      error.response &&
      error.response.status === 401
    ) {
      return rejectWithValue(error.response.data.error);
    }
    return rejectWithValue("An unexpected error occurred");
  }
}
);




export const authSlice = createSlice({
  name: "auth",
  initialState: initialState,
  reducers: {
    add: (state) => {
      state.error = null;
      state.loading = true;
    },
    cleanError: (state) => {
      state.error = null;
    },
    logout: (state) => {
      state.auth = false;
    }
  },
  extraReducers(builder) {
    builder.addCase(login.pending, (state) => {
      state.error = null;
      state.loading = true;
      state.auth = false;
    });
    builder.addCase(login.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
      state.auth = true;
    });
    builder.addCase(login.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
      state.auth = false;
    });
    builder.addCase(checkAuth.pending, (state) => {
      state.error = null;
      state.loading = true;
    });
    builder.addCase(checkAuth.fulfilled, (state) => {

      state.auth = true;
    });
    builder.addCase(checkAuth.rejected, (state, action) => {
      state.loading = false;
      state.error = action.payload as string;
      state.auth = false;
    });
  },
});

export const { add, cleanError, logout } = authSlice.actions;
export default authSlice.reducer;
