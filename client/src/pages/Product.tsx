import Card from "../components/product/Card";

export default function Product() {
  return (
    <>
      <div className="rounded-3xl bg-slate-100">
        <div className="container mx-auto space-y-6 py-20">
          <h1>Danh mục sản phẩm</h1>
          <p>Showing 1 – 12 of 15 results</p>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-4">
            {[...Array(12)].map((_, index) => (
              <Card color="white" key={index} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
