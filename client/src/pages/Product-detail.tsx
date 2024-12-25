import { FaCheckCircle } from "react-icons/fa";
import CardNews from "../components/news/CardNews";
import { Dimensions, engine, information } from "../utils/data/product";
import { Button } from "antd";
import useScrollToTop from "../hooks/useScrollToTop";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import { useEffect } from "react";
import { getProductID } from "../features/product/productSlice";
import { useLocation } from "react-router-dom";
import { getNew } from "../features/news/newsSlice";

export default function ProductDetail() {
  useScrollToTop();

  const { pathname } = useLocation();
  const id = pathname.split("/")[3];

  const { dataID } = useAppSelector((state) => state.product);
  const { data } = useAppSelector((state) => state.news);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getProductID(Number(id)));
    dispatch(getNew());
  }, [dispatch, id]);

  if (!dataID) return <>Not found</>;
  const infoData = information(dataID);
  const dimensionData = Dimensions(dataID);
  const engineData = engine(dataID);
  return (
    <>
      <div className="container mx-auto space-y-10 p-2 sm:py-10">
        <div className="grid grid-cols-1 gap-8 sm:grid-cols-3">
          <div className="col-span-1 space-y-2 sm:col-span-3">
            <h1 className="text-3xl font-semibold">{dataID?.name}</h1>
          </div>
          <div className="col-span-1 sm:col-span-2">
            <div className="space-y-6">
              <div className="space-y-6">
                <img
                  src="/assets/images/product/cardetail.png"
                  alt="poster"
                  className="rounded-md"
                />
              </div>
              <div>
                <h2 className="text-2xl font-medium">Car Overview</h2>
              </div>
              <ul className="grid list-none grid-cols-1 gap-10 sm:grid-cols-2">
                {infoData.map((item) => (
                  <li key={item.group} className="space-y-4">
                    {item.data.map((row) => (
                      <ul
                        key={row.name}
                        className="flex items-center justify-between"
                      >
                        <li className="flex items-center gap-1">
                          {row.icon && (
                            <span
                              dangerouslySetInnerHTML={{ __html: row.icon }}
                            ></span>
                          )}

                          {row.name}
                        </li>
                        <li>{row.title}</li>
                      </ul>
                    ))}
                  </li>
                ))}
              </ul>
              <hr />
              <div className="space-y-4">
                <h2 className="text-2xl font-medium">Description</h2>
                <p>{dataID.description}</p>
              </div>
              <hr />
              <div className="space-y-4">
                <h2 className="text-2xl font-medium">Features</h2>
                <ul className="grid list-none grid-cols-2 gap-10 sm:grid-cols-4">
                  <div className="col-span-1 space-y-3">
                    {dataID.interior.map((item) => (
                      <ul
                        key={item.id}
                        className="flex items-center justify-between"
                      >
                        <li className="flex items-center gap-1">
                          <FaCheckCircle className="h-4 w-4 text-blue-400" />
                          {item.name}
                        </li>
                      </ul>
                    ))}
                  </div>
                  <div className="col-span-1 space-y-3">
                    {dataID.exterior.map((item) => (
                      <ul
                        key={item.id}
                        className="flex items-center justify-between"
                      >
                        <li className="flex items-center gap-1">
                          <FaCheckCircle className="h-4 w-4 text-blue-400" />
                          {item.name}
                        </li>
                      </ul>
                    ))}
                  </div>{" "}
                  <div className="col-span-1 space-y-3">
                    {dataID.safety.map((item) => (
                      <ul
                        key={item.id}
                        className="flex items-center justify-between"
                      >
                        <li className="flex items-center gap-1">
                          <FaCheckCircle className="h-4 w-4 text-blue-400" />
                          {item.name}
                        </li>
                      </ul>
                    ))}
                  </div>{" "}
                  <div className="col-span-1 space-y-3">
                    {dataID.comfortConvenience.map((item) => (
                      <ul
                        key={item.id}
                        className="flex items-center justify-between"
                      >
                        <li className="flex items-center gap-1">
                          <FaCheckCircle className="h-4 w-4 text-blue-400" />
                          {item.name}
                        </li>
                      </ul>
                    ))}
                  </div>
                  {/* {Features.map((item) => (
                    <li key={item.title} className="space-y-3">
                      <h3 className="text-sm font-medium">{item.title}</h3>
                      {item.data.map((row) => (
                        <ul
                          key={row.desc}
                          className="flex items-center justify-between"
                        >
                          <li className="flex items-center gap-1">
                            <FaCheckCircle className="h-4 w-4 text-blue-400" />
                            {row.desc}
                          </li>
                        </ul>
                      ))}
                    </li>
                  ))} */}
                </ul>
              </div>
              <hr />
              <div className="space-y-4">
                <h2 className="text-2xl font-medium">Dimensions & Capacity</h2>
                <ul className="grid list-none grid-cols-1 gap-10 sm:grid-cols-2">
                  {dimensionData.map((item) => (
                    <li key={item.group} className="space-y-4">
                      {item.data.map((row) => (
                        <ul
                          key={row.name}
                          className="flex items-center justify-between"
                        >
                          <li className="flex items-center gap-1">
                            {row.name}
                          </li>
                          <li>{row.title}</li>
                        </ul>
                      ))}
                    </li>
                  ))}
                </ul>
              </div>
              <hr />
              <div className="space-y-4">
                <h2 className="text-2xl font-medium">
                  Engine and Transmission
                </h2>
                <ul className="grid list-none grid-cols-1 gap-10 sm:grid-cols-2">
                  {engineData.map((item) => (
                    <li key={item.group} className="space-y-4">
                      {item.data.map((row) => (
                        <ul
                          key={row.name}
                          className="flex items-center justify-between"
                        >
                          <li className="flex items-center gap-1">
                            {row.name}
                          </li>
                          <li>{row.title}</li>
                        </ul>
                      ))}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
          <div className="col-span-1">
            <div className="space-y-6">
              <div className="card space-y-4 rounded-md border border-blue-400 p-10">
                <h4 className="text-lg font-medium"> Our Price</h4>
                <ul className="flex items-end">
                  <li className="text-sm">
                    <del>
                      $
                      {(
                        dataID.price +
                        (dataID.price * dataID.discount) / 100
                      ).toLocaleString("vi-VN")}
                    </del>
                  </li>
                  <li className="text-xl font-medium">
                    ${dataID.price.toLocaleString("vi-VN")}
                  </li>
                </ul>
                <li className="list-none">
                  Instant Saving: $
                  {((dataID.price * dataID.discount) / 100).toLocaleString(
                    "vi-VN",
                  )}
                </li>
                <ul className="list-none space-y-6">
                  <li>
                    <Button type="primary" className="h-10 w-full bg-[#405FF2]">
                      Mua ngay
                    </Button>
                  </li>
                  <li>
                    <Button type="default" className="h-10 w-full">
                      Thêm vào giỏ hàng
                    </Button>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div className="space-y-4">
          <div className="flex justify-between">
            <h3 className="text-2xl font-semibold">Related Posts</h3>
          </div>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
            {data.map((_, index) => (
              <CardNews key={index} item={_} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
