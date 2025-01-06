import useScrollToTop from "../hooks/useScrollToTop";

export default function About() {
  useScrollToTop();
  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="mb-6 text-center text-4xl font-bold text-gray-800">
        About Us
      </h1>
      <section className="mx-auto mb-8 max-w-4xl rounded-lg bg-white p-6 shadow-md">
        <h2 className="mb-4 text-3xl font-semibold text-gray-800">Our Story</h2>
        <p className="mb-4 text-gray-700">
          Our company was founded with a simple goal: to create an online car
          sales platform that offers convenience, reliability, and a great user
          experience. Over the years, we've expanded our product offerings and
          continuously improved our service, striving to become a trusted
          partner for car buyers.
        </p>
        <img
          src="https://via.placeholder.com/600x400"
          alt="Our story"
          className="mb-6 h-auto w-full rounded-lg shadow-md"
        />

        <h2 className="mb-4 text-3xl font-semibold text-gray-800">Vision</h2>
        <p className="mb-4 text-gray-700">
          We aim to become the leading online car sales platform, offering not
          only high-quality products but also a user-friendly and secure
          shopping experience. Our vision is to connect people with the car of
          their dreams with just a few clicks.
        </p>
        <img
          src="https://via.placeholder.com/600x400"
          alt="Our Vision"
          className="mb-6 h-auto w-full rounded-lg shadow-md"
        />

        <h2 className="mb-4 text-3xl font-semibold text-gray-800">
          Our Mission
        </h2>
        <p className="mb-4 text-gray-700">
          Our mission is to provide an exceptional car shopping experience
          through a reliable and convenient online platform. We offer detailed
          product information, secure payment options, and reliable customer
          support to ensure every customer is satisfied.
        </p>
        <img
          src="https://via.placeholder.com/600x400"
          alt="Mission"
          className="mb-6 h-auto w-full rounded-lg shadow-md"
        />

        <h2 className="mb-4 text-3xl font-semibold text-gray-800">
          Core Values
        </h2>
        <ul className="mb-4 ml-6 list-disc text-gray-700">
          <li>
            <strong>Quality:</strong> Only the best cars and services are
            provided to our customers.
          </li>
          <li>
            <strong>Trust:</strong> We are committed to providing a secure,
            transparent experience for all customers.
          </li>
          <li>
            <strong>Customer Service:</strong> We are always ready to assist
            with your needs and concerns.
          </li>
        </ul>

        <h2 className="mb-4 text-3xl font-semibold text-gray-800">Our Team</h2>
        <p className="mb-4 text-gray-700">
          Our team consists of experienced professionals in technology, sales,
          and customer support. We’re passionate about cars and strive to
          provide a seamless experience for every customer, from browsing
          products to completing purchases.
        </p>
        <img
          src="https://via.placeholder.com/600x400"
          alt="Team"
          className="mb-6 h-auto w-full rounded-lg shadow-md"
        />

        <h2 className="mb-4 text-3xl font-semibold text-gray-800">
          Contact Us
        </h2>
        <p className="mb-4 text-gray-700">
          Have any questions or need support? Reach out to us through any of the
          following ways:
        </p>
        <p className="mb-4 text-gray-700">
          Email:{" "}
          <a href="mailto:support@hcmuss.site" className="text-blue-500">
            support@hcmuss.site
          </a>
          <br />
          Phone:{" "}
          <a href="tel:+18001234567" className="text-blue-500">
            1800-1234-567
          </a>
          <br />
          Address: 12 XYZ Street, ABC City
        </p>
      </section>
    </div>
  );
}
