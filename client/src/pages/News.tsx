import { useEffect } from "react";
import CardNews from "../components/news/CardNews";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import useScrollToTop from "../hooks/useScrollToTop";
import { getNew } from "../features/news/newsSlice";

export default function News() {
  useScrollToTop();
  const { data } = useAppSelector((state) => state.news);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getNew());
  }, [dispatch]);
  console.log(data);
  return (
    <>
      <div className="rounded-3xl bg-slate-100">
        <div className="container mx-auto p-2 sm:py-10">
          <h1>Tin tức</h1>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
            {" "}
            {data.map((_, index) => (
              <CardNews key={index} item={_} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
