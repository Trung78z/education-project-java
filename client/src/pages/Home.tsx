import { Button, ConfigProvider, Tabs } from "antd";
import Navbar from "../components/Navbar";
import Card from "../components/product/Card";
import { AndroidOutlined, AppleOutlined } from "@ant-design/icons";
import TabChildren from "../components/product/TabChildren";
import CardNews from "../components/news/CardNews";
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
                  <img src="./assets/icons/Convertible.svg" alt="" />
                  Convertible
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className="container mx-auto space-y-10 px-2 py-6">
        <div>
          <h2 className="text-2xl font-medium">The Most Searched SUV Cars</h2>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-4">
            {Array.from({ length: 8 }).map((_, index) => (
              <Card color="white" key={index} />
            ))}
          </div>
        </div>
        <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
          <div className="relative rounded-sm border bg-[#E9F2FF] p-20">
            <div className="absolute bottom-10 right-10">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="110"
                height="111"
                viewBox="0 0 110 111"
                fill="none"
              >
                <path
                  d="M43.1686 15.1042C36.3829 15.1042 30.2324 19.0967 27.4699 25.2945L16.292 50.373C9.59104 50.8334 4.29688 56.4114 4.29688 63.2292V76.1198C4.29688 82.0525 9.10636 86.862 15.0391 86.862H17.9835C17.9994 86.0353 18.0204 85.2089 18.0469 84.3824C17.3476 82.9569 16.9533 81.3546 16.9533 79.6597L16.7578 72.0378C16.7578 66.4012 17.9046 61.2241 22.0885 60.3812C24.0773 59.9806 25.7424 58.6256 26.5573 56.7676L40.3605 25.2945C43.1228 19.0967 49.2733 15.1042 56.0592 15.1042H43.1686Z"
                  fill="#CEE1F2"
                />
                <path
                  d="M94.9609 86.862C100.894 86.862 105.703 82.0525 105.703 76.1198V63.2292C105.703 56.1099 99.9318 50.3386 92.8125 50.3386L79.5736 24.5305C76.6474 18.7488 70.7184 15.1042 64.2383 15.1042H43.1686C36.3829 15.1042 30.2324 19.0967 27.4699 25.2945L16.292 50.373C9.59104 50.8334 4.29688 56.4114 4.29688 63.2292V76.1198C4.29688 82.0525 9.10636 86.862 15.0391 86.862"
                  stroke="#405FF2"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M38.0269 95.4558C42.7731 95.4558 46.6207 91.6082 46.6207 86.862C46.6207 82.1158 42.7731 78.2683 38.0269 78.2683C33.2807 78.2683 29.4332 82.1158 29.4332 86.862C29.4332 91.6082 33.2807 95.4558 38.0269 95.4558Z"
                  stroke="#405FF2"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M63.1641 86.8621H49.8433"
                  stroke="#405FF2"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M71.9727 95.4558C76.7189 95.4558 80.5664 91.6082 80.5664 86.862C80.5664 82.1158 76.7189 78.2683 71.9727 78.2683C67.2265 78.2683 63.3789 82.1158 63.3789 86.862C63.3789 91.6082 67.2265 95.4558 71.9727 95.4558Z"
                  stroke="#405FF2"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M51.5608 67.0964L63.5304 55.4899C65.9362 53.1387 64.2729 49.0512 60.9101 49.0512H49.9475C46.5786 49.0512 44.9182 44.9505 47.3367 42.6034L59.7328 30.573"
                  stroke="#FF5CF3"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
              </svg>
            </div>
            <h3 className="max-w-40 text-xl font-semibold">
              Are You Looking For a Car ?
            </h3>
            <p className="max-w-80">
              We are committed to providing our customers with exceptional
              service.
            </p>
            <Button type="primary" className="py-6">
              Get Started
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="15"
                height="15"
                viewBox="0 0 15 15"
                fill="none"
              >
                <path
                  d="M14.5509 0.505005H5.99533C5.78037 0.505005 5.60643 0.678948 5.60643 0.893906C5.60643 1.10886 5.78037 1.28281 5.99533 1.28281H13.6121L1.0537 13.8412C0.901778 13.9931 0.901778 14.2392 1.0537 14.3911C1.12964 14.467 1.22917 14.505 1.32867 14.505C1.42816 14.505 1.52766 14.467 1.60364 14.3911L14.162 1.8327V9.44948C14.162 9.66444 14.3359 9.83838 14.5509 9.83838C14.7659 9.83838 14.9398 9.66444 14.9398 9.44948V0.893906C14.9398 0.678948 14.7658 0.505005 14.5509 0.505005Z"
                  fill="white"
                />
              </svg>
            </Button>
          </div>
          <div className="relative rounded-sm border bg-[#FFE9F3] p-20">
            <div className="absolute bottom-10 right-10">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="110"
                height="111"
                viewBox="0 0 110 111"
                fill="none"
              >
                <path
                  d="M17.1875 84.5076V26.0524C17.1875 14.1918 26.779 4.5769 38.6109 4.5769H25.5664C13.7008 4.5769 4.08203 14.1956 4.08203 26.0612V84.4988C4.08203 96.3641 13.7008 105.983 25.5664 105.983H38.6109C26.779 105.983 17.1875 96.3682 17.1875 84.5076Z"
                  fill="#CEE1F2"
                />
                <path
                  d="M72.4023 104.786C70.1826 105.561 67.7967 105.983 65.3125 105.983H25.7812C13.9156 105.983 4.29688 96.3641 4.29688 84.4988V26.0612C4.29688 14.1956 13.9156 4.5769 25.7812 4.5769H65.3125C77.1779 4.5769 86.7969 14.1956 86.7969 26.0612V48.6198M54.7852 82.5652H71.1133M21.4844 82.3503L25.4341 86.4414C27.1343 88.1481 29.8912 88.1481 31.5915 86.4414L39.7461 78.0534"
                  stroke="#405FF2"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M105.047 70.3429C100.32 68.5047 97.1951 68.2422 94.8535 68.2422C90.5029 68.2422 87.0117 71.769 87.0117 76.1198C87.0117 80.4706 90.9148 83.9975 96.6917 83.9975C101.681 83.9975 105.703 87.5244 105.703 91.8752C105.703 96.2258 101.961 99.7529 97.6106 99.7529C95.5763 99.7529 91.0458 99.0924 86.582 97.318M96.6797 68.2422V61.2956M96.6797 99.7527V105.983M57.793 58.073V59.7917M34.1602 58.073V59.7917"
                  stroke="#FF5CF4"
                  strokeWidth="5"
                  strokeMiterlimit="10"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
                <path
                  d="M68.5352 37.0183H68.1835C68.1734 36.9946 68.1661 36.9702 68.1557 36.9467L64.3038 28.4003C64.3002 28.3923 64.2967 28.3846 64.2931 28.3766C62.5023 24.4667 58.9291 21.6017 54.734 20.7129C52.2427 20.1853 49.1996 19.8308 45.8829 19.8308C42.6308 19.8308 39.6816 20.1728 37.2649 20.682C33.0507 21.5702 29.4639 24.4377 27.6706 28.3528C27.6669 28.3607 27.6635 28.3687 27.6598 28.3766L23.7974 36.9465C23.7869 36.9699 23.7798 36.9944 23.7697 37.0181H23.418C21.0448 37.0181 19.1211 38.9418 19.1211 41.3149C19.1211 43.6881 21.0448 45.6118 23.418 45.6118V49.583C23.418 55.0937 27.8339 59.5769 33.2617 59.5769H58.6912C64.1193 59.5769 68.5352 55.0937 68.5352 49.5832V45.612C70.9083 45.612 72.832 43.6883 72.832 41.3152C72.832 38.942 70.9083 37.0183 68.5352 37.0183ZM35.4885 31.9215C36.1541 30.4769 37.4799 29.4193 39.0369 29.0912C40.6093 28.7599 42.9015 28.4245 45.8831 28.4245C48.9326 28.4245 51.3212 28.7745 52.953 29.1202C54.4951 29.447 55.811 30.5027 56.4755 31.9454L58.7617 37.0183H33.1914L35.4885 31.9215ZM35.0195 53.3464C32.1718 53.3464 29.8633 51.0379 29.8633 48.1902C29.8633 45.3424 32.1718 43.0339 35.0195 43.0339C37.8673 43.0339 40.1758 45.3424 40.1758 48.1902C40.1758 51.0379 37.8673 53.3464 35.0195 53.3464ZM56.9336 53.3464C54.0858 53.3464 51.7773 51.0379 51.7773 48.1902C51.7773 45.3424 54.0858 43.0339 56.9336 43.0339C59.7813 43.0339 62.0898 45.3424 62.0898 48.1902C62.0898 51.0379 59.7813 53.3464 56.9336 53.3464Z"
                  fill="#FF5CF4"
                />
              </svg>
            </div>
            <h3 className="max-w-40 text-xl font-semibold">
              Do You Want to Sell a Car ?
            </h3>
            <p className="max-w-80">
              We are committed to providing our customers with exceptional
              service.
            </p>
            <Button type="primary" className="bg-black py-6 hover:bg-black">
              Get Started
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="15"
                height="15"
                viewBox="0 0 15 15"
                fill="none"
              >
                <path
                  d="M14.5509 0.505005H5.99533C5.78037 0.505005 5.60643 0.678948 5.60643 0.893906C5.60643 1.10886 5.78037 1.28281 5.99533 1.28281H13.6121L1.0537 13.8412C0.901778 13.9931 0.901778 14.2392 1.0537 14.3911C1.12964 14.467 1.22917 14.505 1.32867 14.505C1.42816 14.505 1.52766 14.467 1.60364 14.3911L14.162 1.8327V9.44948C14.162 9.66444 14.3359 9.83838 14.5509 9.83838C14.7659 9.83838 14.9398 9.66444 14.9398 9.44948V0.893906C14.9398 0.678948 14.7658 0.505005 14.5509 0.505005Z"
                  fill="white"
                />
              </svg>
            </Button>
          </div>
        </div>
        <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
          <h4>We're BIG on what matters to you</h4>
          <ul className="grid grid-cols-1 gap-10 sm:grid-cols-2">
            <li className="space-y-4">
              <img src="/assets/images/session/a1.svg" alt="" />
              <h4 className="text-lg font-medium">Special Financing Offers</h4>
              <p>
                Our stress-free finance department that can find financial
                solutions to save you money.
              </p>
            </li>
            <li className="space-y-4">
              <img src="/assets/images/session/a2.svg" alt="" />
              <h4 className="text-lg font-medium">Trusted Car Dealership</h4>
              <p>
                Our stress-free finance department that can find financial
                solutions to save you money.
              </p>
            </li>
            <li className="space-y-4">
              <img src="/assets/images/session/a3.svg" alt="" />
              <h4 className="text-lg font-medium">Transparent Pricing</h4>
              <p>
                Our stress-free finance department that can find financial
                solutions to save you money.
              </p>
            </li>
            <li className="space-y-4">
              <img src="/assets/images/session/a4.svg" alt="" />
              <h4 className="text-lg font-medium">Expert Car Service</h4>
              <p>
                Our stress-free finance department that can find financial
                solutions to save you money.
              </p>
            </li>
          </ul>
        </div>
      </div>
      <div className="bg-[#050B20]">
        <div className="container mx-auto p-2 sm:py-20">
          <div className="flex items-center justify-between text-background">
            <h3 className="text-2xl font-semibold">Popular Makes</h3>
            <Button
              type="text"
              className="text-background hover:text-slate-100"
            >
              View all
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="14"
                height="15"
                viewBox="0 0 14 15"
                fill="none"
              >
                <path
                  d="M13.6109 0.639893H5.05533C4.84037 0.639893 4.66643 0.813836 4.66643 1.02879C4.66643 1.24375 4.84037 1.41769 5.05533 1.41769H12.6721L0.113697 13.9761C-0.0382245 14.128 -0.0382245 14.3741 0.113697 14.526C0.18964 14.6019 0.289171 14.6399 0.388666 14.6399C0.488161 14.6399 0.587656 14.6019 0.663635 14.526L13.222 1.96759V9.58436C13.222 9.79932 13.3959 9.97326 13.6109 9.97326C13.8259 9.97326 13.9998 9.79932 13.9998 9.58436V1.02879C13.9998 0.813836 13.8258 0.639893 13.6109 0.639893Z"
                  fill="white"
                />
              </svg>
            </Button>
          </div>
          <ConfigProvider
            theme={{
              token: {
                colorPrimary: "#FF5CF4",
                colorTextBase: "white",
              },
            }}
          >
            <Tabs
              defaultActiveKey="In Stock"
              items={[
                {
                  title: "In Stock",
                  icon: <AppleOutlined />,
                  children: <TabChildren />,
                },
                {
                  title: "Audi",
                  icon: <AndroidOutlined />,
                  children: <TabChildren />,
                },
                {
                  title: "Ford",
                  icon: <AndroidOutlined />,
                  children: <TabChildren />,
                },
                {
                  title: "Mercedes Benz",
                  icon: <AndroidOutlined />,
                  children: <TabChildren />,
                },
              ].map((item) => {
                return {
                  key: item.title,
                  label: item.title,
                  children: item.children,
                  icon: item.icon,
                };
              })}
            />
          </ConfigProvider>
        </div>
      </div>
      <div className="container mx-auto space-y-10 px-2 py-6">
        <div className="space-y-4">
          {" "}
          <div className="flex justify-between">
            <h3 className="text-2xl font-semibold">
              Explore Our Premium Brands
            </h3>
            <Button type="text" className="">
              Show All Brands
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="14"
                height="15"
                viewBox="0 0 14 15"
                fill="none"
              >
                <path
                  d="M13.6109 0.639893H5.05533C4.84037 0.639893 4.66643 0.813836 4.66643 1.02879C4.66643 1.24375 4.84037 1.41769 5.05533 1.41769H12.6721L0.113697 13.9761C-0.0382245 14.128 -0.0382245 14.3741 0.113697 14.526C0.18964 14.6019 0.289171 14.6399 0.388666 14.6399C0.488161 14.6399 0.587656 14.6019 0.663635 14.526L13.222 1.96759V9.58436C13.222 9.79932 13.3959 9.97326 13.6109 9.97326C13.8259 9.97326 13.9998 9.79932 13.9998 9.58436V1.02879C13.9998 0.813836 13.8258 0.639893 13.6109 0.639893Z"
                  fill="black"
                />
              </svg>
            </Button>
          </div>
          <ul className="grid grid-cols-2 items-center gap-4 sm:grid-cols-3 md:grid-cols-6">
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/audi.png" alt="" />
              Audi
            </li>
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/bmw.png" alt="" />
              BMW
            </li>{" "}
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/ford.png" alt="" />
              Ford
            </li>{" "}
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/mec.png" alt="" />
              Mercedes Benz
            </li>{" "}
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/peugeot.png" alt="" />
              Peugeot
            </li>{" "}
            <li className="flex flex-col items-center justify-center rounded-2xl border-2 p-3">
              <img src="/assets/images/session/volk.png" alt="" />
              Volkswagen
            </li>
          </ul>
        </div>
        <ul className="grid grid-cols-1 gap-6 sm:grid-cols-2">
          <li>
            <iframe
              className="h-60 w-full sm:h-80 sm:w-[500px]"
              src="https://www.youtube.com/embed/JIVp3erJDMo"
              title="The BMW M3 Competition | lost soul | 4K"
              frameBorder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              referrerPolicy="strict-origin-when-cross-origin"
              allowFullScreen
            ></iframe>
          </li>
          <li className="flex flex-col items-center justify-center">
            <div className="space-y-6">
              {" "}
              <h4 className="text-2xl font-semibold">
                Flexible finance for added shine
              </h4>
              <p>
                AA Car Finance allows you to get a quote without affecting your
                credit rating. Find a car from any dealer, and we’ll do the
                rest. With a large panel of 30+ lenders we can help most
                drivers.
              </p>
              <Button type="primary" className="py-5">
                Find Out More
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="15"
                  height="14"
                  viewBox="0 0 15 14"
                  fill="none"
                >
                  <path
                    d="M13.9709 0.000488281H5.41532C5.20036 0.000488281 5.02642 0.174431 5.02642 0.389389C5.02642 0.604347 5.20036 0.77829 5.41532 0.77829H13.0321L0.473682 13.3367C0.321761 13.4886 0.321761 13.7347 0.473682 13.8866C0.549625 13.9625 0.649156 14.0005 0.748651 14.0005C0.848146 14.0005 0.947641 13.9625 1.02362 13.8866L13.582 1.32819V8.94496C13.582 9.15992 13.7559 9.33386 13.9709 9.33386C14.1859 9.33386 14.3598 9.15992 14.3598 8.94496V0.389389C14.3598 0.174431 14.1858 0.000488281 13.9709 0.000488281Z"
                    fill="white"
                  />
                </svg>
              </Button>
            </div>
          </li>
        </ul>
        <ul className="grid grid-cols-2 items-center justify-center space-y-4 sm:grid-cols-4">
          <li className="flex flex-col items-center justify-center">
            <h5 className="text-xl font-semibold">810M</h5>
            <p className="text-xs">CARS FOR SALE</p>
          </li>{" "}
          <li className="flex flex-col items-center justify-center">
            <h5 className="text-xl font-semibold">715M</h5>
            <p className="text-xs">DEALER REVIEWS</p>
          </li>{" "}
          <li className="flex flex-col items-center justify-center">
            <h5 className="text-xl font-semibold">96M</h5>
            <p className="text-xs">VISITORS PER DAY</p>
          </li>{" "}
          <li className="flex flex-col items-center justify-center">
            <h5 className="text-xl font-semibold">230M</h5>
            <p className="text-xs">VERIFIED DEALERS</p>
          </li>
        </ul>
      </div>
      <hr />
      <div className="container mx-auto space-y-10 px-2 py-6">
        <div className="space-y-4">
          <div className="flex justify-between">
            <h3 className="text-2xl font-semibold">Latest Blog Posts</h3>
            <Button type="text" className="">
              View all
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="14"
                height="15"
                viewBox="0 0 14 15"
                fill="none"
              >
                <path
                  d="M13.6109 0.639893H5.05533C4.84037 0.639893 4.66643 0.813836 4.66643 1.02879C4.66643 1.24375 4.84037 1.41769 5.05533 1.41769H12.6721L0.113697 13.9761C-0.0382245 14.128 -0.0382245 14.3741 0.113697 14.526C0.18964 14.6019 0.289171 14.6399 0.388666 14.6399C0.488161 14.6399 0.587656 14.6019 0.663635 14.526L13.222 1.96759V9.58436C13.222 9.79932 13.3959 9.97326 13.6109 9.97326C13.8259 9.97326 13.9998 9.79932 13.9998 9.58436V1.02879C13.9998 0.813836 13.8258 0.639893 13.6109 0.639893Z"
                  fill="black"
                />
              </svg>
            </Button>
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
