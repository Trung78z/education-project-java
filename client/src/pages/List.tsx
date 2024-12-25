import { useEffect } from "react";
import Card from "../components/product/Card";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import useScrollToTop from "../hooks/useScrollToTop";
import { getProduct } from "../features/product/productSlice";

export default function List() {
  useScrollToTop();

  const { data } = useAppSelector((state) => state.product);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getProduct());
  }, [dispatch]);
  console.log(data);
  if (data.length < 1) return <>Not found</>;
  return (
    <>
      <div className="rounded-3xl bg-slate-100">
        <div className="container mx-auto space-y-6 px-2 py-20">
          <h1 className="text-3xl font-semibold">Danh mục sản phẩm</h1>
          <p>Showing 1 – 12 of 15 results</p>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-4">
            {data.map((_, index) => (
              <Card color="white" key={index} item={_} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
