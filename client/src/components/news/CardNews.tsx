export default function CardNews() {
  return (
    <div className="card-hover relative w-full max-w-[415.98px] space-y-4 rounded-sm pb-4 shadow-md hover:shadow-xl sm:w-max">
      <img
        src="/assets/images/news/news1.png"
        alt=""
        className="h-[267.41px] w-full flex-shrink rounded-md sm:w-auto"
        width={415.98}
        height={267.41}
      />
      <div className="w-full space-y-3 px-3">
        {" "}
        <div className="flex items-center gap-x-3">
          <h6>Admin</h6>
          <span>{new Date().toLocaleDateString("vi-VN")}</span>
        </div>
        <h2 className="max-w-[80%] font-semibold">
          2024 BMW ALPINA XB7 with exclusive details, extraordinary
        </h2>
      </div>
    </div>
  );
}
