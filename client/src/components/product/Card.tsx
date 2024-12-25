import { Button } from "antd";
import clsx from "clsx";
import { useNavigate } from "react-router-dom";
import { ProductPayload } from "../../types/ProductPayload";
import { formatContent } from "../../utils/helpers";

export default function Card({
  color = "white",
  item,
}: {
  color: string;
  item: ProductPayload;
}) {
  const navigate = useNavigate();
  const handleNavigate = () => {
    navigate(`/list/car/${item.id}`);
  };

  return (
    <div
      className={clsx(
        `card-hover relative rounded-sm border pb-4 shadow-sm hover:shadow-md`,
        color === "black" && "bg-[rgba(255,255,255,0.07)] text-white",
      )}
    >
      <div className="absolute right-4 top-4 rounded-full bg-background p-2">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="12"
          height="13"
          viewBox="0 0 12 13"
          fill="none"
        >
          <g clipPath="url(#clip0_1_2222)">
            <path
              d="M9.40001 12.8801C9.16001 12.8801 8.93334 12.8135 8.72001 12.6801L6.12001 11.0401C6.04001 10.9868 5.96001 10.9868 5.88001 11.0401L3.28001 12.6801C3.04001 12.8401 2.77334 12.9001 2.48001 12.8601C2.18667 12.8201 1.93334 12.7068 1.72001 12.5201C1.45334 12.2801 1.32001 11.9735 1.32001 11.6001V2.16013C1.32001 1.81346 1.44667 1.51346 1.70001 1.26013C1.95334 1.00679 2.25334 0.880127 2.60001 0.880127H9.40001C9.74667 0.880127 10.0467 1.00679 10.3 1.26013C10.5533 1.51346 10.68 1.81346 10.68 2.16013V11.6001C10.68 11.9468 10.5533 12.2468 10.3 12.5001C10.0467 12.7535 9.74667 12.8801 9.40001 12.8801ZM6.00001 9.96013C6.24001 9.96013 6.46667 10.0268 6.68001 10.1601L9.28001 11.8001C9.30667 11.8268 9.34667 11.8401 9.40001 11.8401C9.45334 11.8401 9.50001 11.8201 9.54001 11.7801C9.58001 11.7401 9.60001 11.6801 9.60001 11.6001V2.16013C9.60001 2.08013 9.58001 2.02013 9.54001 1.98013C9.50001 1.94013 9.45334 1.92013 9.40001 1.92013H2.60001C2.54667 1.92013 2.50001 1.94013 2.46001 1.98013C2.42001 2.02013 2.40001 2.08013 2.40001 2.16013V11.6001C2.40001 11.6801 2.43334 11.7468 2.50001 11.8001C2.56667 11.8535 2.64001 11.8535 2.72001 11.8001L5.32001 10.1601C5.53334 10.0268 5.76001 9.96013 6.00001 9.96013Z"
              fill="#050B20"
            />
          </g>
          <defs>
            <clipPath id="clip0_1_2222">
              <rect
                width="12"
                height="12"
                fill="white"
                transform="matrix(1 0 0 -1 0 12.8801)"
              />
            </clipPath>
          </defs>
        </svg>
      </div>
      <div className="space-y-4">
        <img
          src="/assets/images/product/car1.jpg"
          alt=""
          width={327.5}
          height={218.33}
          className="h-[218.33px] w-full flex-shrink rounded-sm"
        />
        <div className="space-y-1 px-6">
          <h3 className="font-medium">{item?.name}</h3>
          <p>{formatContent(item?.description, 30)}</p>

          <ul className="flex items-center justify-center gap-x-8 rounded-sm border px-2 py-4">
            <li className="flex flex-col items-center gap-x-1">
              <img src={`/assets/images/product/S-${color}.svg`} alt="" />
              <span className="text-xs">{item.odometer}</span>
            </li>
            <li className="flex flex-col items-center gap-x-1">
              <img src={`/assets/images/product/fuel-${color}.svg`} alt="" />{" "}
              <span className="text-xs">{item.type}</span>
            </li>
            <li className="flex flex-col items-center gap-x-1">
              <img src={`/assets/images/product/hopso-${color}.svg`} alt="" />{" "}
              <span className="text-xs">{item.gearshift}</span>
            </li>
          </ul>
          <div className="flex items-center justify-between px-4">
            <span className="font-medium">
              ${item.price.toLocaleString("vi-VN")}
            </span>
            <Button type="link" onClick={handleNavigate}>
              View details{" "}
              <span>
                <img src="/assets/icons/dedot.svg" alt="" />
              </span>{" "}
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
