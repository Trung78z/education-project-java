import { Button } from "antd";
import Logo from "./Logo";
import { Link, useLocation, useNavigate } from "react-router-dom";
import clsx from "clsx";
import { useEffect, useState } from "react";
import { IoMenu } from "react-icons/io5";
import { Drawer, Space } from "antd";
import { useAppDispatch, useAppSelector } from "../hooks/hook-redux";
import { checkAuth, logout } from "../features/auth/authSlice";
import { FaUser } from "react-icons/fa6";
const excludedPath: string[] = [
  "/",
  "/auth/login",
  "/auth/register",
  "/contact",
];
const nav = [
  { title: "Home", url: "/" },
  { title: "Categories", url: "/list" },
  { title: "News", url: "/news" },
  { title: "Contact", url: "/contact" },
  { title: "About Us", url: "/about" },
];

export default function Navbar() {
  const { pathname } = useLocation();
  const [open, setOpen] = useState(false);
  const navigate = useNavigate();
  const showDrawer = () => {
    setOpen(true);
  };

  const onClose = () => {
    setOpen(false);
  };

  const { auth } = useAppSelector((state) => state.auth);

  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(checkAuth());
  }, [dispatch]);

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("token");
    navigate("/");
  };

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
        <div className="mx-auto flex max-w-screen-xl items-center justify-between px-4">
          <li className="list-none">
            <Logo />
          </li>

          <Space>
            <Button
              type="default"
              onClick={showDrawer}
              className="block sm:hidden"
            >
              <IoMenu className="h-5 w-5" />
            </Button>
          </Space>
          <Drawer
            title="Basic Drawer"
            placement="right"
            closable={false}
            onClose={onClose}
            open={open}
          >
            {nav.map((item) => (
              <li className="list-none hover:text-gray-400" key={item.url}>
                <Link to={item.url}>{item.title}</Link>
              </li>
            ))}

            <div className="flex flex-col items-center">
              {auth ? (
                <li>
                  <div className="user-menu">
                    <FaUser />
                    <Button onClick={handleLogout}>Logout</Button>
                  </div>
                </li>
              ) : (
                <li>
                  <Link to="/auth/login">
                    <Button>Login</Button>
                  </Link>
                </li>
              )}
            </div>
          </Drawer>

          <ul className="hidden list-none items-center gap-x-4 sm:flex">
            {nav.map((item) => (
              <li className="hover:text-gray-400" key={item.url}>
                <Link to={item.url}>{item.title}</Link>
              </li>
            ))}

            <div className="flex flex-col items-center">
              {auth ? (
                <li>
                  <div className="user-menu group relative flex items-center gap-x-2">
                    <FaUser />
                    <div className="absolute right-0 top-4 hidden w-32 space-y-4 rounded-md bg-white p-2 shadow-md group-hover:block">
                      <Link
                        to="/auth/change-password"
                        className="w-full flex-shrink-0 text-sm text-black"
                      >
                        Change password
                      </Link>
                      <Button onClick={handleLogout}>Logout</Button>
                    </div>
                  </div>
                </li>
              ) : (
                <li>
                  <Link to="/auth/login">
                    <Button>Login</Button>
                  </Link>
                </li>
              )}
            </div>
          </ul>
        </div>
      </div>
    </>
  );
}
