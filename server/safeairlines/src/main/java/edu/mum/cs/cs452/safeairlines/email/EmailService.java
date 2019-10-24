package edu.mum.cs.cs452.safeairlines.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
