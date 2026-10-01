package com.mycompany.mathutil.core;

public class AccountService {

    /**
     * Kiểm tra tính hợp lệ của địa chỉ email.
     * @param email Địa chỉ email cần kiểm tra
     * @return true nếu email đúng định dạng, ngược lại false
     */
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Đăng ký tài khoản người dùng.
     * Quy tắc:
     * - username không được rỗng hoặc null.
     * - password phải lớn hơn 6 ký tự (độ dài > 6).
     * - email phải hợp lệ.
     * 
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @param email Địa chỉ email
     * @return true nếu đăng ký thành công, ngược lại false
     */
    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (password == null || password.length() <= 6) {
            return false;
        }
        if (!isValidEmail(email)) {
            return false;
        }
        return true;
    }
}
