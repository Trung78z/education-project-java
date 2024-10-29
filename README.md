# Application Car Store

## Giới thiệu

**Application Car Store** là một ứng dụng web bán xe hơi với hệ thống backend mạnh mẽ sử dụng Java Spring Boot và giao diện frontend hiện đại sử dụng ReactJS. Ứng dụng cho phép người dùng tìm kiếm, lọc và lựa chọn sản phẩm xe hơi dựa trên các tiêu chí khác nhau, đồng thời cung cấp các tính năng quản lý sản phẩm và người dùng cho admin.

## Mục tiêu

- Cung cấp một nền tảng trực tuyến để giới thiệu và bán xe hơi.
- Cho phép người dùng duyệt, tìm kiếm và mua sản phẩm.
- Hỗ trợ quản lý danh sách sản phẩm và đơn hàng dành cho admin.

## Công nghệ sử dụng

- **Backend**: Java Spring Boot, Servlet, Spring Security, JWT (JSON Web Token), JPA, MySQL Driver.
- **Frontend**: ReactJS.
- **Admin Panel**: Java Swing.

## Các tính năng

### Backend (Java)

- **Quản lý người dùng**: Lưu trữ thông tin người dùng (tài khoản, các sản phẩm đã mua, v.v.).
- **Quản lý sản phẩm**: Lưu trữ thông tin chi tiết về sản phẩm (loại sản phẩm, kích thước, màu sắc, giá tiền, v.v.).
- **Xử lý đơn hàng**: Quản lý và xử lý thông tin về các đơn hàng của người dùng.
- **Xác thực và phân quyền**: Sử dụng Spring Security và JWT để xác thực người dùng, xử lý các chức năng đăng nhập, đăng ký, đăng xuất, và thu hồi token.

### Frontend (ReactJS)

- **Trang giới thiệu thông tin**: Hiển thị thông tin giới thiệu về cửa hàng và các sản phẩm.
- **Danh sách và tìm kiếm sản phẩm**: Hiển thị danh sách các sản phẩm và cung cấp tính năng tìm kiếm sản phẩm.
- **Lọc sản phẩm**: Cho phép người dùng lọc sản phẩm theo các tiêu chí như giá, hãng xe, tính năng, v.v.
- **Phân quyền người dùng**: Xử lý các chức năng đăng nhập, đăng ký, đăng xuất, quên mật khẩu, v.v.
- **Mua hàng**: Lựa chọn sản phẩm, thêm vào giỏ hàng và hoàn tất mua hàng.

### Admin Panel (Java Swing)

- **Quản lý sản phẩm**: Tạo, chỉnh sửa, và xóa sản phẩm.
- **Quản lý đơn hàng**: Xử lý và cập nhật trạng thái đơn hàng.
- **Quản lý người dùng**: Kiểm tra và xử lý thông tin người dùng.

### Tính năng phát triển thêm

- **Thanh toán**: Hỗ trợ thanh toán trực tuyến (có thể bổ sung theo yêu cầu doanh nghiệp).

## Cài đặt và triển khai

### Yêu cầu hệ thống

- **Backend**: JDK 11 trở lên, MySQL.
- **Frontend**: Node.js, npm hoặc yarn.
- **Admin Panel**: Java Runtime Environment.

### Hướng dẫn cài đặt

1. **Clone repository**:

   ```bash
   git clone https://github.com/Trung78z/education-project.git
   cd application-car-store
   ```

2. **Cấu hình Backend**:

   Cấu hình cơ sở dữ liệu MySQL.
   Cập nhật thông tin kết nối cơ sở dữ liệu trong application.properties.

3. **Chạy Backend**:

   cd server
   ./mvn spring-boot:run

4. **Cài đặt và khởi chạy Frontend**:

   cd client
   npm install
   npm run dev
