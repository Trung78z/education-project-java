import { LoadingOutlined } from "@ant-design/icons";
import { Spin } from "antd";

export default function Loading() {
  return (
    <div className="flex h-screen items-center justify-center">
      <Spin indicator={<LoadingOutlined spin />} />
    </div>
  );
}
