1. Trace logic (diễn biến lỗi)

beginTransaction();

updateOrderStatus(orderId, "PAID");   // (1)
deductBalance(userId, amount);        // (2)

commit();
Trường hợp lỗi xảy ra:
Bước (1) chạy thành công → đơn hàng chuyển sang PAID
Bước (2) bị lỗi (ví dụ: không đủ tiền, lỗi DB, exception)
Nhưng:
Không rollback
Hoặc commit vẫn được gọi / auto-commit bật


2. Nguyên nhân cốt lõi
   Thiếu rollback khi có lỗi

Nếu không có:

transaction.rollback();

→ phần đã update trước đó vẫn giữ nguyên