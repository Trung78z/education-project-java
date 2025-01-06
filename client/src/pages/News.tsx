import { useEffect } from "react";
import CardNews from "../components/news/CardNews";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import useScrollToTop from "../hooks/useScrollToTop";
import { getNew } from "../features/news/newsSlice";
import Loading from "../components/Loading";

export default function News() {
  useScrollToTop();
  const { data, loading } = useAppSelector((state) => state.news);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getNew());
  }, [dispatch]);
  if (loading)
    return (
      <>
        <Loading />
      </>
    );
  return (
    <>
      <div className="rounded-3xl bg-slate-100">
        <div className="container mx-auto space-y-4 p-2 sm:py-10">
          <h1 className="text-3xl font-semibold">Tin tức</h1>
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
