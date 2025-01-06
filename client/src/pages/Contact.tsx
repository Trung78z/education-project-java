import Navbar from "../components/Navbar";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Button, Input } from "antd";
import useScrollToTop from "../hooks/useScrollToTop";
import TextArea from "antd/es/input/TextArea";
import { postContactService } from "../services/contactService";
import Swal from "sweetalert2";
import axios, { AxiosError } from "axios";
const formSchema = z.object({
  email: z.string().email({ message: "Please enter a valid email" }),
  message: z.string().min(6, "The message must be at least 6 characters long"), // Fixed message
  phone: z
    .string()
    .min(9, "Invalid phone number")
    .refine((value) => {
      const phoneRegex = /^\d{9,15}$/;
      return phoneRegex.test(value);
    }, "Invalid phone number"),
});

type FormValues = z.infer<typeof formSchema>;
export default function Contact() {
  useScrollToTop();
  const {
    handleSubmit,
    formState: { errors },
    setValue,
    reset,
  } = useForm<FormValues>({
    resolver: zodResolver(formSchema),
  });
  const onSubmit = async (data: FormValues) => {
    try {
      const res = await postContactService(data);
      if (res.data.success === false) {
        return Swal.fire({
          icon: "error",
          html: `<b>Sorry! </b> <br />An error occurred <br /> Because ${res.data.message}`,
          showConfirmButton: false,
          timer: 3000,
        });
      }
      Swal.fire({
        icon: "success",
        html: "Thank you for contacting us!",
        showConfirmButton: false,
        timer: 1500,
      });
      reset();
    } catch (error) {
      if (error instanceof AxiosError && error.response) {
        let errorMessage = "An unexpected error occurred";

        if (axios.isAxiosError(error) && error.response) {
          errorMessage = error.response.data.error || errorMessage;
        }
        return Swal.fire({
          icon: "error",
          html: `<b>ERROR! </b> <br />${errorMessage} <br />`,
          showConfirmButton: false,
          timer: 4000,
        });
      }
    }
  };

  const handleChangeArea = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { id, value } = event.target;
    setValue(id as keyof FormValues, value);
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
                <p className="text-gray-300">Thank you for contacting us.</p>
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
                  id="phone"
                  placeholder="Phone"
                  type="tel"
                  onChange={handleChange}
                  className="sm-w[440px] h-12 border-black"
                />
                {errors.phone && (
                  <p className="text-red-500">{errors.phone.message}</p>
                )}
              </div>{" "}
              <div className="">
                <TextArea
                  id="message"
                  placeholder="The message you're interested in."
                  onChange={handleChangeArea}
                  className="sm-w[440px] min-h-80 border-black"
                />
                {errors.message && (
                  <p className="text-red-500">{errors.message.message}</p>
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
