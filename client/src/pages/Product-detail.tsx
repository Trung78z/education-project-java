import { FaCheckCircle } from "react-icons/fa";
import CardNews from "../components/news/CardNews";
import { Dimensions, engine, information } from "../utils/data/product";
import { Button, Spin } from "antd";
import useScrollToTop from "../hooks/useScrollToTop";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import { useEffect, useState } from "react";
import { getProductID } from "../features/product/productSlice";
import { useLocation } from "react-router-dom";
import { getNew } from "../features/news/newsSlice";
import Swal from "sweetalert2";
import { RiSubtractFill } from "react-icons/ri";
import { IoMdAdd } from "react-icons/io";
import { TransactionPayload } from "../types/transactionPayload";
import { postTransactionService } from "../services/transactionService";
import axios from "axios";
import { LoadingOutlined } from "@ant-design/icons";
import Loading from "../components/Loading";
export default function ProductDetail() {
  useScrollToTop();
  const [quantity, setQuantity] = useState<number>(1);
  const { pathname } = useLocation();
  const brand = pathname.split("/")[2];
  const name = pathname.split("/")[3];
  const [loadingBuy, setLoading] = useState<boolean>(false);
  const { dataID, loading } = useAppSelector((state) => state.product);
  const { auth } = useAppSelector((state) => state.auth);
  const { data } = useAppSelector((state) => state.news);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getProductID({ brand, name }));
    dispatch(getNew());
  }, [dispatch, brand, name]);

  if (loading)
    return (
      <>
        <Loading />
      </>
    );

  if (!dataID) return <>Not found</>;
  const infoData = information(dataID);
  const dimensionData = Dimensions(dataID);
  const engineData = engine(dataID);

  const handleIncrease = () => {
    setQuantity((prevQuantity) => prevQuantity + 1);
  };

  const handleDecrease = () => {
    setQuantity((prevQuantity) => (prevQuantity > 1 ? prevQuantity - 1 : 1));
  };
  const totalPrice = (
    dataID.price *
    quantity *
    (1 - dataID.discount / 100)
  ).toLocaleString("vi-VN");
  const handleBuyCar = async () => {
    setLoading(true);
    if (auth === false) {
      return Swal.fire({
        icon: "error",
        html: `<b>Sorry! </b> <br />You need to login to buy this car. <br /> <br>`,
        showConfirmButton: false,
        timer: 3000,
      });
    }
    const data: TransactionPayload = {
      productId: dataID.id,
      quantity: quantity,
      totalPrice: dataID.price * (1 - dataID.discount / 100) * quantity,
    };
    try {
      const res = await postTransactionService(data);
      if (res.data.success === true) {
        setLoading(false);
        return Swal.fire({
          icon: "success",
          html: `
        <b>Success!</b> <br />
      Buy success <br />
        Quantity: <span id="quantityDisplay">${quantity}</span>
        <br />
        Total Price: <span id="totalPrice">${totalPrice}</span>
      `,
          showConfirmButton: false,
          timer: 3000,
        });
      }
      setLoading(false);
    } catch (error) {
      let errorMessage = "An unexpected error occurred";

      if (axios.isAxiosError(error) && error.response) {
        errorMessage = error.response.data.error || errorMessage;
      }
      setLoading(false);
      return Swal.fire({
        icon: "error",
        html: `<b>Sorry! </b> <br />Your buy was unsuccessful. <br /> <br>${errorMessage}</br>`,
        showConfirmButton: false,
        timer: 3000,
      });
    }
  };

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
                  src={
                    dataID?.image.startsWith("http")
                      ? dataID.image
                      : `data:image/png;base64,${dataID.image}`
                  }
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
                  </div>
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
                  </div>
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

                <ul className="list-none">
                  <li>
                    <b>Instant Saving: </b>$
                    {((dataID.price * dataID.discount) / 100).toLocaleString(
                      "vi-VN",
                    )}
                  </li>
                </ul>
                <label>Quantity:</label>
                <div className="flex items-center space-x-2">
                  <button
                    className="rounded-full border"
                    onClick={handleDecrease}
                  >
                    <RiSubtractFill className="h-4 w-4 hover:text-gray-600" />
                  </button>
                  <p className="px-2">{quantity}</p>
                  <button
                    className="rounded-full border"
                    onClick={handleIncrease}
                  >
                    <IoMdAdd className="h-4 w-4 hover:text-gray-600" />
                  </button>
                </div>
                <div className="flex list-none items-center justify-between">
                  <li> Total: {totalPrice}</li>
                  <li>
                    Total saving:
                    {(
                      ((dataID.price * dataID.discount) / 100) *
                      quantity
                    ).toLocaleString("vi-VN")}
                  </li>
                </div>
                <ul className="list-none space-y-6">
                  <li>
                    <Button
                      type="primary"
                      className="h-10 w-full bg-[#405FF2]"
                      onClick={handleBuyCar}
                      disabled={loadingBuy}
                    >
                      {!loadingBuy ? (
                        " Buy now"
                      ) : (
                        <Spin indicator={<LoadingOutlined spin />} />
                      )}
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
            {data.slice(0, 3).map((_, index) => (
              <CardNews key={index} item={_} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
