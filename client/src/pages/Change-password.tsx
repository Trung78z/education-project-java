import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import useScrollToTop from "../hooks/useScrollToTop";
import { postChangeAuth } from "../services/authService";
const formSchema = z.object({
  oldPassword: z
    .string()
    .min(6, { message: "Password must be at least 6 characters long" }),
  newPassword: z
    .string()
    .min(6, { message: "Password must be at least 6 characters long" }),
});

type FormValues = z.infer<typeof formSchema>;
export default function ChangePassword() {
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
    try {
      const res = await postChangeAuth(data);
      if (res.data.success === true) {
        Swal.fire({
          icon: "success",
          html: `<b>Success! </b> <br />Change success <br />`,
          showConfirmButton: false,
          timer: 1000,
        });
        return navigate("/");
      }

      return Swal.fire({
        icon: "error",
        html: `<b>ERROR! </b> <br />Change password fail <br />`,
        showConfirmButton: false,
        timer: 1000,
      });
    } catch (error) {
      console.log(error);
      return Swal.fire({
        icon: "error",
        html: `<b>ERROR! </b> <br />Change password  fail <br />`,
        showConfirmButton: false,
        timer: 1000,
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
                  Change password with boxcars
                </h1>
                <p className="text-gray-300">Welcome to our store!</p>
              </div>
              <div className="">
                <Input
                  id="oldPassword"
                  placeholder="oldPassword"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.oldPassword && (
                  <p className="text-red-500">{errors.oldPassword.message}</p>
                )}
              </div>
              <div className="">
                <Input
                  id="newPassword"
                  placeholder="Password new"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black bg-gray-400"
                />
                {errors.newPassword && (
                  <p className="text-red-500">{errors.newPassword.message}</p>
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
