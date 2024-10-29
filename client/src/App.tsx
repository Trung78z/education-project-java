import { useEffect } from "react";
import axios from "axios";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Layout from "./components/Layout";
import Contact from "./pages/Contact";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Product from "./pages/Product";
import About from "./pages/About";
import Footer from "./components/Footer";
import News from "./pages/News";
import LayoutNoNavbar from "./components/LayoutNoNavbar";

function App() {
  useEffect(() => {
    const fetch = async () => {
      try {
        const res = await axios.get("/", {
          withCredentials: true,
        });
        console.log(res);
      } catch (error: any) {
        console.log(error);
      }
    };
    fetch();
  }, []);
  return (
    <>
      <div className="space-y-4">
        <Routes>
          <Route path="/" element={<LayoutNoNavbar />}>
            <Route path="" element={<Home />} />
          </Route>
          <Route path="/auth" element={<LayoutNoNavbar />}>
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
          </Route>

          <Route path="" element={<Layout />}>
            <Route path="/list" element={<Product />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/about" element={<About />} />
            <Route path="/news" element={<News />} />
          </Route>
        </Routes>
        <Footer />
      </div>
    </>
  );
}

export default App;
