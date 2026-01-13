# Bài tập Internet + JSON

## Thông tin bài tập
**Đề bài:** Lập trình ứng dụng thực hiện các chức năng sau:

1. ✅ Kết nối và lấy dữ liệu từ địa chỉ: https://lebavui.io.vn/students
2. ✅ Hiển thị danh sách dữ liệu với mỗi đối tượng bao gồm các thông tin: **thumbnail**, **hoten**, **mssv** (Giao diện tự thiết kế)
3. ✅ Thumbnail là liên kết đến ảnh đại diện của sinh viên. Cần sử dụng thư viện Glide để hiển thị ảnh vào ImageView
4. ✅ Khi nhấn vào đối tượng thì mở activity/fragment hiển thị thông tin chi tiết: **thumbnail**, **hoten**, **mssv**, **ngaysinh**, **email**
5. ✅ Thực hiện chức năng tìm kiếm theo **hoten** hoặc **mssv** và hiển thị kết quả

## Cấu trúc Project

```
Android_Internet_JSON/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/internetjson/
│   │       │   ├── Student.kt                 # Model class cho sinh viên
│   │       │   ├── ApiService.kt              # Interface định nghĩa API
│   │       │   ├── RetrofitClient.kt          # Singleton Retrofit client
│   │       │   ├── StudentListAdapter.kt      # Adapter cho RecyclerView
│   │       │   ├── StudentListActivity.kt     # Màn hình danh sách
│   │       │   └── StudentDetailActivity.kt   # Màn hình chi tiết
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_student_list.xml      # Layout danh sách
│   │       │   │   ├── item_student_list.xml          # Layout item
│   │       │   │   └── activity_student_detail.xml    # Layout chi tiết
│   │       │   └── values/
│   │       │       ├── strings.xml
│   │       │       ├── colors.xml
│   │       │       └── themes.xml
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml              # Version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Công nghệ sử dụng

### 1. Retrofit 2.9.0
- Thư viện HTTP client mạnh mẽ
- Tự động chuyển đổi JSON thành Kotlin object
- Dễ dàng định nghĩa API endpoints

### 2. Glide 4.16.0  
- Thư viện load và cache ảnh hiệu quả
- Hỗ trợ placeholder và error handling
- Tối ưu memory và performance

### 3. Gson 2.10.1
- Parse JSON từ API
- Sử dụng annotations để map field names
- Hỗ trợ serialization/deserialization

### 4. Material Design 3
- UI/UX hiện đại
- CardView, MaterialCardView
- Theme động (Day/Night)

## Tính năng chính

### 1. Hiển thị danh sách sinh viên
- RecyclerView với LinearLayoutManager
- CardView đẹp mắt cho mỗi item
- Hiển thị: Avatar (Glide), Họ tên, MSSV
- ProgressBar khi đang tải dữ liệu

### 2. Tìm kiếm
- Tìm theo họ tên hoặc MSSV
- Case-insensitive search
- Kết quả real-time

### 3. Chi tiết sinh viên
- Màn hình full thông tin
- Avatar kích thước lớn
- Hiển thị đầy đủ: Họ tên, MSSV, Ngày sinh, Email
- Hỗ trợ back navigation

## Cách chạy

### 1. Yêu cầu
- Android Studio Hedgehog | 2023.1.1 trở lên
- Android SDK 26+ (Android 8.0)
- Kết nối Internet

### 2. Build & Run
```bash
# Clone hoặc mở project
cd Android_Internet_JSON

# Build project
./gradlew build

# Install trên device/emulator
./gradlew installDebug
```

### 3. Sử dụng
1. Mở app "Internet + JSON"
2. Danh sách sinh viên tự động load từ API
3. Nhập từ khóa vào ô tìm kiếm → Click "Tìm"
4. Click vào sinh viên để xem chi tiết

## API Endpoint

**Base URL:** `https://lebavui.io.vn/`  
**Endpoint:** `/students`  
**Method:** GET

**Response Format:**
```json
[
  {
    "_id": "string",
    "mssv": "string",
    "hoten": "string",
    "ngaysinh": "string",
    "email": "string",
    "thumbnail": "string (URL)"
  }
]
```

## Permissions
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## Screenshots

### Màn hình danh sách
- Search bar ở đầu
- Danh sách sinh viên với avatar
- Material Design Cards

### Màn hình chi tiết
- Avatar lớn ở giữa
- Thông tin đầy đủ dưới dạng label + value
- Hỗ trợ scroll

## Xử lý lỗi
- ✅ Hiển thị ProgressBar khi loading
- ✅ Toast thông báo khi load thành công/thất bại
- ✅ Xử lý lỗi network
- ✅ Placeholder khi ảnh không load được

## Điểm nổi bật
1. **Code sạch, có cấu trúc**
   - Tách biệt Model, View, Network layer
   - Sử dụng Kotlin best practices

2. **UI/UX đẹp**
   - Material Design 3
   - CardView với elevation và corner radius
   - Theme tự động (Light/Dark)

3. **Performance tốt**
   - Glide cache ảnh hiệu quả
   - RecyclerView tối ưu
   - Lazy loading

4. **Hoàn thành 100% yêu cầu**
   - ✅ Kết nối API
   - ✅ Hiển thị danh sách
   - ✅ Glide load ảnh
   - ✅ Chi tiết sinh viên
   - ✅ Tìm kiếm

## Tác giả
Bài tập Android - Internet + JSON Assignment

## License
Educational Purpose Only

