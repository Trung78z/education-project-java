import { Button } from "antd";
import Logo from "./Logo";
import { Link, useLocation } from "react-router-dom";
import clsx from "clsx";

const excludedPath: string[] = [
  "/",
  "/auth/login",
  "/auth/register",
  "/contact",
];
const nav = [
  { title: "Trang chủ", url: "/" },
  { title: "Danh mục", url: "/list" },
  { title: "Tin tức", url: "/news" },
  { title: "Liên hệ", url: "/contact" },
  { title: "Về chúng tôi", url: "/about" },
];
export default function Navbar() {
  const { pathname } = useLocation();
  return (
    <>
      <div
        className={clsx(
          "z-[9999] w-full bg-slate-50 py-3",
          excludedPath.includes(pathname)
            ? "bg-transparent text-white"
            : "text-primary-500",
        )}
      >
        <div className="mx-auto flex max-w-screen-xl items-center justify-between">
          <li className="list-none">
            <Logo />
          </li>
          <ul className="flex list-none items-center gap-x-4">
            {nav.map((item) => (
              <li className="hover:text-gray-400" key={item.url}>
                <Link to={item.url}>{item.title}</Link>
              </li>
            ))}

            <li>
              <Link to="/auth/login">
                <Button>Login</Button>
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
}
