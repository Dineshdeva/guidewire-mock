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
import java.util.List;
import java.util.stream.Collectors;

// DTO Classes
class JmsProperty {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class ErrorTestPayload {
    private String payloadString;
    private List<JmsProperty> jmsCustomProperties;

    public String getPayloadString() {
        return payloadString;
    }

    public void setPayloadString(String payloadString) {
        this.payloadString = payloadString;
    }

    public List<JmsProperty> getJmsCustomProperties() {
        return jmsCustomProperties;
    }

    public void setJmsCustomProperties(List<JmsProperty> jmsCustomProperties) {
        this.jmsCustomProperties = jmsCustomProperties;
    }
}

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
            @RequestBody ErrorTestPayload payload,
            HttpServletRequest request) {

        // Log all headers
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map(h -> h + ": " + request.getHeader(h))
                .collect(Collectors.joining("\n  "));

        String jmsPropertiesStr = payload.getJmsCustomProperties() != null 
                ? payload.getJmsCustomProperties()
                        .stream()
                        .map(p -> p.getName() + "=" + p.getValue())
                        .collect(Collectors.joining("\n  "))
                : "None";

        log.info("""
                ──── Incoming POST errortest/messages ────
                Headers:
                  {}
                Payload String:
                  {}
                JMS Custom Properties:
                  {}
                ─────────────────────────────────
                """, headers, payload.getPayloadString(), jmsPropertiesStr);

        String response = "{\"status\":\"error\",\"message\":\"Invalid request to errortest endpoint\"}";
        int statusCode = Integer.parseInt(payload.getPayloadString());
        return ResponseEntity.status(HttpStatus.valueOf(statusCode))
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
