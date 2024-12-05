import { Link } from "react-router-dom";

export default function Logo() {
  return (
    <>
      <Link to="/">
        <img
          src="/assets/icons/logo.png"
          alt="logo-car"
          width={80}
          height={80}
          className="rounded-full min-w-11 h-auto max-w-11"
        />
      </Link>
    </>
  );
}
