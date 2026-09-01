package com.roottrace.backend.telemetry.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogRedactionService {

    private static final String REDACTED_MARKER = "[REDACTED]";
    
    // Patterns for sensitive data
    private static final Pattern BEARER_TOKEN_PATTERN = Pattern.compile("Bearer\\s+[A-Za-z0-9\\-_\\.]+");
    private static final Pattern API_KEY_PATTERN = Pattern.compile("api[_-]?key[\"']?\\s*[:=]\\s*[\"']?([a-zA-Z0-9]+)[\"']?", Pattern.CASE_INSENSITIVE);
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("\\b(?:\\d[ -]*?){13,16}\\b");

    public String redact(String message) {
        if (message == null) {
            return null;
        }
        
        String redactedMessage = message;
        
        // Redact Bearer Tokens
        redactedMessage = BEARER_TOKEN_PATTERN.matcher(redactedMessage).replaceAll("Bearer " + REDACTED_MARKER);
        
        // Redact API Keys
        redactedMessage = API_KEY_PATTERN.matcher(redactedMessage).replaceAll("api_key: " + REDACTED_MARKER);
        
        // Redact Credit Cards
        redactedMessage = CREDIT_CARD_PATTERN.matcher(redactedMessage).replaceAll(REDACTED_MARKER);

        return redactedMessage;
    }
}
