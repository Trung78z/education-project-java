import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import useScrollToTop from "../hooks/useScrollToTop";
import { postAuth } from "../services/authService";
import axios from "axios";

const formSchema = z.object({
  username: z.string().min(4, { message: "Please enter a username" }),
  email: z.string().email({ message: "Please enter a valid email" }),
  password: z
    .string()
    .min(6, { message: "Password must be at least 6 characters long" }),
});

type FormValues = z.infer<typeof formSchema>;
export default function Register() {
  useScrollToTop();
  const navigate = useNavigate();
  const {
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<FormValues>({
    resolver: zodResolver(formSchema),
  });
  const onSubmit = async (data: FormValues) => {
    const dataPayload = {
      ...data,
      userRole: {
        id: 1,
      },
    };
    try {
      const res = await postAuth(dataPayload);
      if (res.data.success == false) {
        return Swal.fire({
          icon: "error",
          html: `<b>Sorry! </b> <br />Your registration was unsuccessful. <br />`,
          showConfirmButton: false,
          timer: 3000,
        });
      }
      Swal.fire({
        icon: "success",
        html: "Congratulations! <br />You have successfully registered!",
        showConfirmButton: false,
        timer: 1500,
      });
      navigate("/auth/login");
    } catch (error: unknown) {
      let errorMessage = "An unexpected error occurred";

      if (axios.isAxiosError(error) && error.response) {
        errorMessage = error.response.data.error || errorMessage;
      }

      return Swal.fire({
        icon: "error",
        html: `<b>Sorry! </b> <br />Your registration was unsuccessful. <br /> <br>${errorMessage}</br>`,
        showConfirmButton: false,
        timer: 3000,
      });
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setValue(id as keyof FormValues, value);
  };
  return (
    <div>
      <div className="space-y-4">
        <div className="bg-[url(/assets/images/session/session.jpg)] object-cover">
          <Navbar />
          <div className="flex flex-col items-center justify-center sm:min-h-[calc(100vh-64px)]">
            <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
              <div className="space-y-2">
                <h1 className="text-3xl font-semibold text-white">
                  Register with boxcars
                </h1>
                <p className="text-gray-300">Welcome to our store!</p>
              </div>
              <div className="">
                <Input
                  id="username"
                  placeholder="Username"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.username && (
                  <p className="text-red-500">{errors.username.message}</p>
                )}
              </div>
              <div className="">
                <Input
                  id="email"
                  placeholder="Email"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.email && (
                  <p className="text-red-500">{errors.email.message}</p>
                )}
              </div>
              <div className="">
                <Input
                  id="password"
                  placeholder="Password"
                  type="password"
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
                  Already have an account?
                  <Link
                    to="/auth/login"
                    className="text-blue-500 hover:text-blue-600"
                  >
                    Login
                  </Link>
                </h4>
              </div>
              <div className="flex items-center justify-center">
                <Button className="px-28 py-4" type="primary" htmlType="submit">
                  Register
                </Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
