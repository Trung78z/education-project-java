import CardNews from "../components/news/CardNews";
import useScrollToTop from "../hooks/useScrollToTop";

export default function News() {
  useScrollToTop();
  return (
    <>
      <div className="rounded-3xl bg-slate-100">
        <div className="container mx-auto p-2 sm:py-10">
          <h1>Tin tức</h1>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
            {" "}
            {[...Array(9)].map((_, index) => (
              <CardNews key={index} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
