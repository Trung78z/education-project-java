import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
// import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import { useNavigate } from "react-router-dom";
// import { ReactElement, ReactHTML } from "react";
const formSchema = z.object({
  email: z.string().email({ message: "Vui lòng nhập email" }),
  topic: z.string().min(6, "Chủ đề phải có ít nhất 8 ký tự"),
  phone: z
    .string()
    .min(9, "Số điện thoại không hợp lệ")
    .refine((value) => {
      const phoneRegex = /^\d{9,15}$/;
      return phoneRegex.test(value);
    }, "Số điện thoại không hợp lệ"),
});
type FormValues = z.infer<typeof formSchema>;
export default function Contact() {
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
            <form
              onSubmit={handleSubmit(onSubmit)}
              className="space-y-6 rounded-lg bg-slate-200 bg-opacity-35 px-28 py-10"
            >
              <div className="space-y-2">
                <h1 className="text-3xl font-semibold">Contact with BoxCar</h1>
                <p className="text-gray-300">
                  Cám ơn bạn đã liên hệ cho chúng tôi
                </p>
              </div>
              <div className="">
                <Input
                  id="email"
                  placeholder="Email"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black"
                />
                {errors.email && (
                  <p className="text-red-500">{errors.email.message}</p>
                )}
              </div>
              <div className="">
                <Input
                  id="tel"
                  placeholder="Số điện thoại"
                  type="tel"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black"
                />
                {errors.phone && (
                  <p className="text-red-500">{errors.phone.message}</p>
                )}
              </div>{" "}
              <div className="">
                <Input
                  id="topic"
                  placeholder="Chủ đề bạn quan tâm"
                  type="text"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black"
                />
                {errors.topic && (
                  <p className="text-red-500">{errors.topic.message}</p>
                )}
              </div>
              <hr />
              <div className="flex items-center justify-center">
                <Button className="px-28 py-6" type="primary" htmlType="submit">
                  Submit
                </Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
