import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
// import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import useScrollToTop from "../hooks/useScrollToTop";
// import { ReactElement, ReactHTML } from "react";
const formSchema = z.object({
  email: z.string().email({ message: "Vui lòng nhập email" }),
  password: z.string().min(6, "Mật khẩu phải có ít nhất 8 ký tự"),
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
    console.log(data);
    // try {
    //   const res = await authChange(data);
    //   if (res.success == false) {
    //     return Swal.fire({
    //       icon: "error",
    //       html: `<b>Rất tiếc! </b> <br />Bạn đổi mật khẩu không thành công <br /> Vì ${res.msg}`,
    //       showConfirmButton: false,
    //       timer: 3000,
    //     });
    //   }
    //   Swal.fire({
    //     icon: "success",
    //     html: "Chúc mừng bạn!  <br />Bạn đã đổi mật khẩu thành công!",
    //     showConfirmButton: false,
    //     timer: 1500,
    //   });
    navigate("/");
    // } catch (error) {
    //   console.log(error);
    // }
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
                  Register with boxcars
                </h1>
                <p className="text-gray-300">
                  Chào mừng bạn đến với của hàng của chúng tôi
                </p>
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
                  Bạn đã có tài khoản?
                  <Link
                    to="/auth/login"
                    className="text-blue-500 hover:text-blue-600"
                  >
                    Đăng nhập
                  </Link>
                </h4>
              </div>
              <div className="flex items-center justify-center">
                <Button className="px-28 py-6" type="primary" htmlType="submit">
                  Đăng kí
                </Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
