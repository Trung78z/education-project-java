import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import useScrollToTop from "../hooks/useScrollToTop";
import { useAppDispatch } from "../hooks/hook-redux";
import { login } from "../features/auth/authSlice";
const formSchema = z.object({
  username: z.string().min(4, { message: "Please enter a username" }),
  password: z
    .string()
    .min(6, { message: "Password must be at least 8 characters long" }),
});

type FormValues = z.infer<typeof formSchema>;
export default function Login() {
  useScrollToTop();
  const navigate = useNavigate();
  const {
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<FormValues>({
    resolver: zodResolver(formSchema),
  });

  const dispatch = useAppDispatch();

  const onSubmit = async (data: FormValues) => {
    try {
      const res = await dispatch(login(data));
      if (res.meta.requestStatus === "fulfilled") {
        localStorage.setItem("token", res.payload.token);
        navigate("/");
        return Swal.fire({
          icon: "success",
          html: `<b>Success! </b> <br />Login success <br />`,
          showConfirmButton: false,
          timer: 1000,
        });
      }

      return Swal.fire({
        icon: "error",
        html: `<b>ERROR! </b> <br />Login fail <br />`,
        showConfirmButton: false,
        timer: 1000,
      });
    } catch (error) {
      console.log(error);
      return Swal.fire({
        icon: "error",
        html: `<b>ERROR! </b> <br />Login fail <br />`,
        showConfirmButton: false,
        timer: 4000,
      });
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setValue(id as keyof FormValues, value);
  };
  return (
    <div>
      <div className="space-y-6">
        <div className="bg-[url(/assets/images/session/session.jpg)] object-cover">
          <Navbar />
          <div className="flex flex-col items-center justify-center sm:min-h-screen">
            <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
              <div className="space-y-2">
                <h1 className="text-3xl font-semibold text-white">
                  Login with boxcars
                </h1>
                <p className="text-gray-300">Welcome to our store!</p>
              </div>
              <div className="">
                <Input
                  id="username"
                  placeholder="username"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.username && (
                  <p className="text-red-500">{errors.username.message}</p>
                )}
              </div>
              <div className="">
                <Input
                  id="password"
                  type="password"
                  placeholder="Password"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.password && (
                  <p className="text-red-500">{errors.password.message}</p>
                )}
              </div>
              <hr />
              <div className="flex items-center justify-center text-white">
                <span>Or continue with</span>
              </div>
              <ul className="grid grid-cols-2 gap-2 py-2">
                <div className="flex items-center justify-center rounded-md border border-slate-600 bg-slate-300 bg-opacity-35 p-3">
                  <img
                    src="/assets/icons/google.png"
                    alt=""
                    className="w-11"
                    width={59}
                    height={44}
                  />
                </div>
                <div className="flex items-center justify-center rounded-md border border-slate-600 bg-slate-300 bg-opacity-35 p-3">
                  <img
                    src="/assets/icons/meta.png"
                    alt=""
                    className="w-11"
                    width={59}
                    height={44}
                  />
                </div>
              </ul>
              <div className="text-center text-white">
                <h4>
                  Don't have an account?
                  <Link
                    to="/auth/register"
                    className="text-blue-500 hover:text-blue-600"
                  >
                    Register
                  </Link>
                </h4>
              </div>
              <div className="flex items-center justify-center">
                <Button className="px-28 py-6" type="primary" htmlType="submit">
                  Login
                </Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
