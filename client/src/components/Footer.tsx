import Logo from "./Logo";
import { FaFacebook, FaInstagram, FaTiktok } from "react-icons/fa6";

export default function Footer() {
  return (
    <div className="w-full bg-gray-700">
      <div className="mx-auto space-y-4 py-10 md:max-w-screen-sm lg:max-w-screen-xl">
        <div className="text-white">
          <Logo />{" "}
          <span className="font-medium text-yellow-500">Join BoxCar</span>
          <p>Receive pricing updates, shopping tips & more!</p>
        </div>
        <hr />
        <div className="grid w-full grid-cols-1 justify-center gap-y-4 p-2 md:grid-cols-2 xl:grid-cols-4">
          <ul className="col-span-1 text-gray-300">
            <li>
              <h3 className="text-xl font-semibold">Company</h3>
            </li>
            <li>
              <p>About Us</p>
            </li>
            <li>
              <p>Blog</p>
            </li>
            <li>
              <p>Services</p>
            </li>
            <li>
              <p>FAQs</p>
            </li>
            <li>
              <p>Terms</p>
            </li>
          </ul>
          <ul className="col-span-1 text-gray-300">
            <li>
              <h3 className="text-xl font-semibold">Quick Links</h3>
            </li>
            <li>
              <p>Get in Touch</p>
            </li>
            <li>
              <p>Help center</p>
            </li>
            <li>
              <p>Live chat</p>
            </li>
            <li>
              <p>How it works</p>
            </li>
          </ul>
          <ul className="col-span-1 text-gray-300">
            <li>
              <h3 className="text-xl font-semibold">Our Brands</h3>
            </li>
            <li>
              <p>Toyota</p>
            </li>
            <li>
              <p>Porsche</p>
            </li>
            <li>
              <p>Audi</p>
            </li>
            <li>
              <p>BMW</p>
            </li>
            <li>
              <p>Ford</p>
            </li>
          </ul>
          <ul className="col-span-1 text-gray-300">
            <li>
              <h3 className="text-xl font-semibold">Vehicles Type</h3>
            </li>
            <li>
              <p>Sedan</p>
            </li>
            <li>
              <p>Hatchback</p>
            </li>
            <li>
              <p>SUV</p>
            </li>
            <li>
              <p>Connect With Us</p>
            </li>
          </ul>
        </div>
      </div>

      <div className="flex max-w-screen-xl flex-col items-end py-2">
        <ul className="flex list-none items-center gap-x-4">
          <li>
            <FaFacebook className="h-8 w-8 text-white" />
          </li>
          <li>
            <FaInstagram className="h-8 w-8 text-white" />
          </li>
          <li>
            <FaTiktok className="h-8 w-8 text-white" />
          </li>
        </ul>
      </div>

      <hr />
      <p className="py-2 text-center text-lg font-medium text-white">
        &copy; Copyright {new Date().getFullYear()} | www.hcmuss.site. . All
        rights reserved.
      </p>
    </div>
  );
}
