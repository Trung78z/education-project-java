import CardNews from "../components/news/CardNews";
import { FaCheckCircle } from "react-icons/fa";
export default function NewsDetail() {
  return (
    <>
      <div className="container mx-auto space-y-10 p-2 sm:py-10">
        <div className="space-y-2">
          <h1 className="text-3xl font-semibold">
            BMW X6 M50i is designed to exceed your sportiest.
          </h1>
          <div className="flex items-center gap-x-6">
            <img
              src="/assets/images/user/user1.png"
              alt="user icon"
              className="h-10 w-10 rounded-full object-cover"
            />
            <span>Admin</span>
            <span>Accessories</span>
            <span>Exterior</span>
            <span>{new Date().toLocaleDateString("vi-VN")}</span>
          </div>
        </div>
        <div className="space-y-6">
          <img src="/assets/images/news/poster.png" alt="poster" />
          <p>
            Aliquam hendrerit sollicitudin purus, quis rutrum mi accumsan nec.
            Quisque bibendum orci ac nibh facilisis, at malesuada orci congue.
            Nullam tempus sollicitudin cursus. Ut et adipiscing erat. Curabitur
            this is a text link libero tempus congue.
          </p>
          <p>
            Duis mattis laoreet neque, et ornare neque sollicitudin at. Proin
            sagittis dolor sed mi elementum pretium. Donec et justo ante.
            Vivamus egestas sodales est, eu rhoncus urna semper eu. Cum sociis
            natoque penatibus et magnis dis parturient montes, nascetur
            ridiculus mus. Integer tristique elit lobortis purus bibendum, quis
            dictum metus mattis. Phasellus posuere felis sed eros porttitor
            mattis. Curabitur massa magna, tempor in blandit id, porta in
            ligula. Aliquam laoreet nisl massa, at interdum mauris sollicitudin
            et.
          </p>
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
            <img
              src="/assets/images/news/poster2.png"
              alt="user icon"
              className="object-cover"
            />
            <h3 className="text-2xl font-semibold">Requirements</h3>
            <ul className="list-inside list-disc">
              <li>
                We do not require any previous experience or pre-defined skills
                to take this course. A great orientation would be enough to
                master UI/UX design.
              </li>
              <li>A computer with a good internet connection.</li>
              <li>Adobe Photoshop (OPTIONAL)</li>
            </ul>
          </div>
        </div>
        <div className="space-y-4">
          <div className="flex justify-between">
            <h3 className="text-2xl font-semibold">Related Posts</h3>
          </div>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
            {[...Array(3)].map((_, index) => (
              <CardNews key={index} />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
