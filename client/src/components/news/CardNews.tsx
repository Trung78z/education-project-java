import { Link } from "react-router-dom";
import { NewPayload } from "../../types/newsPayload";

export default function CardNews({ item }: { item: NewPayload }) {
  return (
    <div className="card-hover relative w-full max-w-[415.98px] space-y-4 rounded-sm pb-4 shadow-md hover:shadow-xl sm:w-max">
      <Link to={`/news/car/1`}>
        {" "}
        <img
          src="/assets/images/news/news1.png"
          alt=""
          className="h-[267.41px] w-full flex-shrink rounded-md sm:w-auto"
          width={415.98}
          height={267.41}
        />
      </Link>
      <div className="w-full space-y-3 px-3">
        {" "}
        <div className="flex items-center gap-x-3">
          <h6>Admin</h6>
          <span>{new Date(item.createdAt).toLocaleDateString("vi-VN")}</span>
        </div>
        <Link to={`/news/${item.newCategory.name}/${item.id}`}>
          <h2 className="max-w-[80%] font-semibold">{item.title}</h2>
        </Link>
      </div>
    </div>
  );
}
