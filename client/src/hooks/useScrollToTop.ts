import { useEffect } from "react";
import { useLocation } from "react-router-dom";

const useScrollToTop = () => {
    const location = useLocation(); // Lấy thông tin về route hiện tại

    useEffect(() => {
        window.scrollTo(0, 0); // Cuộn về vị trí (0, 0) trên trang
    }, [location]);
};

export default useScrollToTop;
