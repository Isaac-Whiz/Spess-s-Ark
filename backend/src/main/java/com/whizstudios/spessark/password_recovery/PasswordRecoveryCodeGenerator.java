package com.whizstudios.spessark.password_recovery;

import com.whizstudios.spessark.admin.AdminRepository;
import com.whizstudios.spessark.security.SecurityConfig;
import com.whizstudios.spessark.teacher.TeacherRepository;
import com.whizstudios.spessark.utils.EmailSender;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.whizstudios.spessark.utils.Utils.*;

@Service
public class PasswordRecoveryCodeGenerator {

    private final EmailSender emailSender;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final SecurityConfig config;
    private final Map<String, Instant> codeStore = new ConcurrentHashMap<>();


    public PasswordRecoveryCodeGenerator(EmailSender emailSender,
                                         TeacherRepository teacherRepository,
                                         AdminRepository adminRepository, SecurityConfig config) {
        this.emailSender = emailSender;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.config = config;
        this.startCleanupTask();
    }

    public boolean validateEmailAndSendCode(String toMail) {

        if (teacherRepository.existsTeacherByEmail(toMail) ||
                adminRepository.existsAdminByEmail(toMail)) {
        var code = this.generateAndStoreCode();
            return emailSender.sendEmail(toMail, code);
        }
        return false;
    }

    public String generateAndStoreCode() {
        String code = generateRandomCode();
        storeCode(code);
        return code;
    }

    public void storeCode(String code) {
        Instant now = Instant.now();
        codeStore.put(code, now);
    }

    public boolean isCodeValid(String code) {
        Instant codeTime = codeStore.get(code);
        if (codeTime == null) {
            return false;
        }

        Instant now = Instant.now();
        if (now.isBefore(codeTime.plus(EXPIRATION_MINUTES, ChronoUnit.MINUTES))) {
            codeStore.remove(code);
            return true;
        } else {
            codeStore.remove(code);
            return false;
        }
    }

    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = INDEX; i < com.whizstudios.spessark.utils.Utils.CODE_LENGTH; i++) {
            sb.append(random.nextInt(BOUND));
        }
        return sb.toString();
    }

    private void startCleanupTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this::cleanupExpiredCodes, 0, EXPIRATION_MINUTES, TimeUnit.MINUTES);
    }

    private void cleanupExpiredCodes() {
        Instant now = Instant.now();
        codeStore.entrySet().removeIf(entry -> now.isAfter(entry.getValue().plus(EXPIRATION_MINUTES, ChronoUnit.MINUTES)));
    }

    @Transactional
    public void resetPassword(String email, String password) {
        var encodedPassword = config.passwordEncoder().encode(password);
        if (teacherRepository.existsTeacherByEmail(email)) {
            teacherRepository.resetPassword(encodedPassword, email);
        } else {
            adminRepository.resetPassword(encodedPassword, email);
        }
    }

}
