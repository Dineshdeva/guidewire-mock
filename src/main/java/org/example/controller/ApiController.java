package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/messages")
    public ResponseEntity<String> postMessages(
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        log.info("""
                ──── Incoming POST /messages ────
                Headers:
                  {}
                Body:
                  {}
                ─────────────────────────────────
                """, headers, body);

        String response = "{\"status\":\"received\",\"message\":\"Message accepted by Guidewire\"}";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("businesspartner/messages")
    public ResponseEntity<String> postMessages1(
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        // Log all headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        log.info("""
                ──── Incoming POST businesspartner/messages ────
                Headers:
                  {}
                Body:
                  {}
                ─────────────────────────────────
                """, headers, body);

        String response = "{\"status\":\"received\",\"message\":\"Message accepted by Guidewire\"}";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("fleet/messages")
    public ResponseEntity<String> postMessages2(
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        // Log all headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        log.info("""
                ──── Incoming POST fleet/messages ────
                Headers:
                  {}
                Body:
                  {}
                ─────────────────────────────────
                """, headers, body);

        String response = "{\"status\":\"received\",\"message\":\"Message accepted by Guidewire\"}";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("businesspartnertest/messages")
    public ResponseEntity<String> postMessages3(
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        // Log all headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        log.info("""
                ──── Incoming POST businesspartnertest/messages ────
                Headers:
                  {}
                Body:
                  {}
                ─────────────────────────────────
                """, headers, body);

        String response = "{\"status\":\"error\",\"message\":\"Invalid request to businesspartnertest endpoint\"}";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("errortest/messages")
    public ResponseEntity<String> errorTest(
            @RequestBody(required = false) String body,
            HttpServletRequest request) {

        // Log all headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        log.info("""
                ──── Incoming POST businesspartnertest/messages ────
                Headers:
                  {}
                Body:
                  {}
                ─────────────────────────────────
                """, headers, body);

        String response = "{\"status\":\"error\",\"message\":\"Invalid request to businesspartnertest endpoint\"}";
        return ResponseEntity.status(HttpStatus.valueOf(body))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getHealth(HttpServletRequest request) {

        log.info("──── Incoming GET /ping from: {} ────", request.getRemoteAddr());

        String response = """
                {
                  "runLevelCode": 50,
                  "runLevelName": "MULTIUSER",
                  "runLevelOrdinal": 5,
                  "serverId": "PolicyCenterServer1",
                  "uptimeSeconds": 45
                }
                """;
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}