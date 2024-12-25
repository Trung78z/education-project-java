import { Swiper, SwiperSlide, useSwiper } from "swiper/react";
import { A11y, Controller, Pagination, Scrollbar } from "swiper/modules";
import Card from "./Card";
import { LeftCircleOutlined, RightCircleOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { useEffect, useState } from "react";

import { ProductPayload } from "../../types/ProductPayload";
export default function TabChildren({
  model,
  data,
}: {
  model: string;
  data: ProductPayload[];
}) {
  const swiper = useSwiper();
  useEffect(() => {
    if (swiper) {
      console.log("Swiper is initialized");
    }
  }, [swiper]);
  const [dataFilter, setDataFilter] = useState<ProductPayload[]>([]);
  useEffect(() => {
    const filter: ProductPayload[] = data.filter((item) =>
      item.productBrand.name.toLowerCase().includes(model.toLowerCase()),
    );
    setDataFilter(filter);
  }, [model, data]);

  return (
    <>
      {dataFilter.length > 0 && (
        <>
          <Swiper
            breakpoints={{
              320: { slidesPerView: 1, spaceBetween: 10 },
              480: { slidesPerView: 2, spaceBetween: 10 },
              640: { slidesPerView: 4, spaceBetween: 10 },
            }}
            pagination={{ clickable: true }}
            onSlideChange={() => console.log("slide change")}
            modules={[Pagination, Controller, Scrollbar, A11y]}
            className="mySwiper"
          >
            {dataFilter.map((_, index) => (
              <SwiperSlide key={index}>
                {" "}
                <Card color="black" item={_} />
              </SwiperSlide>
            ))}
          </Swiper>

          <div className="flex items-center justify-between gap-1 space-x-1">
            <Button
              type="text"
              className="p-0 hover:text-gray-500"
              onClick={() => swiper?.slidePrev()} // Đảm bảo swiper tồn tại
            >
              <LeftCircleOutlined className="text-2xl hover:text-gray-400 hover:opacity-90" />
            </Button>
            <Button
              type="text"
              className="p-0 hover:text-gray-500"
              onClick={() => swiper?.slideNext()} // Đảm bảo swiper tồn tại
            >
              <RightCircleOutlined className="text-2xl hover:text-gray-400 hover:opacity-90" />
            </Button>
          </div>
        </>
      )}
    </>
  );
}
