import Navbar from "../components/Navbar";
import Card from "../components/product/Card";

export default function Home() {
  return (
    <>
      <div className="space-y-6">
        <div className="bg-[url(/assets/images/session/session.jpg)] object-cover">
          <div className="flex flex-col justify-between sm:min-h-screen">
            <Navbar />
            <div className="px-2 py-20 text-white md:px-20 lg:px-40">
              <div>
                <p className="text-gray-400">
                  Shop Online. Pickup Today. It’s Fast, Simple and Easy. Learn
                  More.
                </p>
                <h1 className="text-4xl font-medium">Fast, Simple and Easy</h1>
              </div>
            </div>
            <div className="px-2 py-4 text-white md:px-20 lg:px-40">
              <ul className="flex flex-wrap items-center gap-x-10 text-sm font-normal text-white">
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/car1.svg" alt="" /> SUV
                </li>
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/sedan.svg" alt="" /> Sedan
                </li>
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/Hatchback.svg" alt="" /> Hatchback
                </li>
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/Coupe.svg" alt="" /> Coupe
                </li>
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/Hybrid.svg" alt="" /> Hybrid
                </li>
                <li className="flex items-center gap-x-1">
                  <img src="./assets/icons/Convertible.svg" alt="" />{" "}
                  Convertible
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className="container mx-auto px-2">
        <h2 className="text-2xl font-medium">The Most Searched SUV Cars</h2>
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-4">
          {" "}
          {Array.from({ length: 8 }).map((_, index) => (
            <Card color="white" key={index} />
          ))}
        </div>
      </div>
    </>
  );
}
