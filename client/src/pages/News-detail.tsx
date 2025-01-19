import CardNews from "../components/news/CardNews";
import { FaCheckCircle } from "react-icons/fa";
import useScrollToTop from "../hooks/useScrollToTop";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import { useEffect } from "react";
import { getNew, getNewID } from "../features/news/newsSlice";
import { useLocation } from "react-router-dom";
export default function NewsDetail() {
  useScrollToTop();

  const { data, dataID } = useAppSelector((state) => state.news);
  const { pathname } = useLocation();
  const category = pathname.split("/")[2];
  const name = pathname.split("/")[3];
  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(getNew());
    dispatch(getNewID({ category, name }));
  }, [dispatch, category, name]);

  if (!dataID) return <>New not found</>;

  return (
    <>
      <div className="container mx-auto space-y-10 p-2 sm:py-10">
        <div className="space-y-2">
          <h1 className="text-3xl font-semibold">{dataID?.title}</h1>
          <div className="flex items-center gap-x-6">
            <img
              src="/assets/images/user/user1.png"
              alt="user icon"
              className="h-10 w-10 rounded-full object-cover"
            />
            <span>Admin</span>
            <span>Accessories</span>
            <span>Exterior</span>
            <span>
              {new Date(dataID.createdAt).toLocaleDateString("vi-VN")}
            </span>
          </div>
        </div>
        <div className="space-y-6">
          <img
            src={
              dataID?.image.startsWith("http")
                ? dataID.image
                : `data:image/png;base64,${dataID.image}`
            }
            alt="poster"
            width={1920}
            height={1080}
          />
          <div dangerouslySetInnerHTML={{ __html: dataID.description }}></div>
        </div>
        <div className="space-y-6 px-0 sm:px-40">
          <div className="rounded-md border border-l-8 border-l-blue-600 p-10">
            <h4>
              Aliquam hendrerit sollicitudin purus, quis rutrum mi accumsan nec.
              Quisque bibendum orci ac nibh facilisis, at malesuada orci congue.
            </h4>
            <h5 className="font-medium">Luis Pickford</h5>
          </div>
          <div className="space-y-4">
            <div className="flex justify-between">
              <h3 className="text-2xl font-semibold">What you’ll learn</h3>
            </div>
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2">
              <ul className="space-y-4">
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Become a UI/UX designer.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  You will be able to start earning money Figma skills.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Build a UI project from beginning to end.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Work with colors & fonts.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  You will create your own UI Kit.
                </li>
              </ul>
              <ul className="space-y-4">
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Build & test a complete mobile app.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Learn to design mobile apps & websites.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Design 3 different logos.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Create low-fidelity wireframe.
                </li>
                <li className="flex items-center gap-x-2">
                  <FaCheckCircle className="h-5 w-5 text-blue-400" />
                  Downloadable exercise files.
                </li>
              </ul>
            </div>
            <div
              className="overflow-x-hidden whitespace-pre-wrap break-words"
              dangerouslySetInnerHTML={{ __html: dataID.content }}
            ></div>
          </div>
        </div>
        <div className="space-y-4">
          <div className="flex justify-between">
            <h3 className="text-2xl font-semibold">Related Posts</h3>
          </div>
          <div className="grid grid-cols-1 gap-6 overflow-x-hidden sm:grid-cols-2 md:grid-cols-3">
            {data.slice(0, 3).map((_, index) => (
              <CardNews key={index} item={_} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
