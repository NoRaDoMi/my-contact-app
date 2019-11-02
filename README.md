# My Contact App

### Simple CRUD app using Spring Boot + Spring MVC + Spring Data JPA + Thymeleaf

### 1\. Tổng quan về ứng dụng MyContact

#### 1.1. Các chức năng

Ứng dụng bao gồm các chức năng:

*   Hiển thị danh sách liên hệ
*   Tìm kiếm liên hệ theo tên
*   Thêm liên hệ mới
*   Sửa liên hệ
*   Xóa liên hệ

#### 1.2. Các giao diện

`list.html`: màn hình danh sách liên hệ
![list](https://user-images.githubusercontent.com/38036797/68074171-cc0aed00-fdca-11e9-8003-8e24b4569945.PNG)

`form.html`: màn hình thêm/sửa liên hệ
![form](https://user-images.githubusercontent.com/38036797/68074174-d1683780-fdca-11e9-8a15-5f3209f231d8.PNG)


#### 1.3. Cơ sở dữ liệu

Cơ sở dữ liệu của ứng dụng chỉ có duy nhất 1 bảng Contact chứa tên, email và số điện thoại của liên hệ:

    CREATE TABLE `contact` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `name` varchar(50) NOT NULL,
     `email` varchar(50) NULL,
     `phone` varchar(20) NULL,
     PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    

* * *
### 2\. Xây dựng kiến trúc dự án

Ứng dụng MyContact được xây dựng theo **kiến trúc phân lớp (layered architecture)**. Với kiến trúc này, ứng dụng được bổ ngang thành các lớp (layer). Mỗi một lớp sẽ bao gồm các thành phần có cùng trách nhiệm => đảm bảo Nguyên lý đơn nhiệm (Single Responsibility) trong SOLID.

[![MyContact architecture](https://s3-ap-southeast-1.amazonaws.com/kipalog.com/6v8g5fs84a_201901272316.png)](https://s3-ap-southeast-1.amazonaws.com/kipalog.com/6v8g5fs84a_201901272316.png)

Trong đó:

*   **Entity** là các POJO mapping với các bảng CSDL.
*   **Repository** là các interface trực tiếp truy cập và thao tác với CSDL.
*   **Service** là các lớp có nhiệm vụ xử lý business logic, không trực tiếp truy cập vào CSDL mà sẽ lấy dữ liệu từ các repository, rồi chuyển dữ liệu đã xử lý cho các controller.
*   **Controller** là các lớp chỉ quan tâm đến request của người dùng: đọc input, xử lý input, lấy dữ liệu từ service rồi đổ ra view. Controller có nhiều phương thức, mỗi phương thức sẽ tương ứng với một case request.
*   **View** là các file HTML (do chúng ta sử dụng template engine là Thymeleaf), đóng vai trò giao diện người dùng.

Workflow của ứng dụng thông qua use case _Người dùng truy cập vào màn hình Danh sách liên hệ_:

1.  Spring sẽ quét tất cả các controller, lựa chọn phương thức có URI pattern phù hợp với request.
2.  Phương thức này sẽ gọi service để lấy danh sách liên hệ từ CSDL:
3.  Service không trực tiếp truy cập vào CSDL mà thông qua repository để lấy danh sách liên hệ từ CSDL. Repository trả về danh sách liên hệ tìm thấy cho service. Service chuyển lại danh sách liên hệ cho controller.
4.  Controller đổ danh sách liên hệ ra view.
