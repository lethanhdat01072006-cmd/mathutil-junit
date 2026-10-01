package com.mycompany.mathutil.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    // =========================================================================
    // Yêu cầu 1: Đọc dữ liệu từ file test-data.csv bằng @ParameterizedTest
    // =========================================================================
    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2} -> expected={3}")
    @CsvFileSource(
        resources = "/data/test-data.csv",
        numLinesToSkip = 1
    )
    void testRegisterAccount_FromCsvFile(
            String username,
            String password,
            String email,
            boolean expected) {
        
        boolean actual = accountService.registerAccount(username, password, email);
        assertEquals(expected, actual);
    }

    // =========================================================================
    // Kiểm thử bổ trợ cho phương thức isValidEmail()
    // =========================================================================
    @Test
    void testIsValidEmail_ValidCases() {
        assertTrue(accountService.isValidEmail("john@example.com"));
        assertTrue(accountService.isValidEmail("user.name+tag@sub.domain.org"));
    }

    @Test
    void testIsValidEmail_InvalidCases() {
        assertFalse(accountService.isValidEmail("bobmail.com"));    // thiếu @
        assertFalse(accountService.isValidEmail("bob@mail"));       // thiếu tên miền cấp cao
        assertFalse(accountService.isValidEmail(null));             // null
        assertFalse(accountService.isValidEmail(""));               // rỗng
    }

    // =========================================================================
    // Kiểm thử giá trị biên cho độ dài Password (> 6 ký tự)
    // =========================================================================
    @Test
    void testRegisterAccount_PasswordBoundary() {
        // Độ dài = 6 ký tự -> thất bại (không lớn hơn 6)
        assertFalse(accountService.registerAccount("user123", "123456", "user@mail.com"));

        // Độ dài = 7 ký tự -> thành công (lớn hơn 6)
        assertTrue(accountService.registerAccount("user123", "1234567", "user@mail.com"));
    }
}
