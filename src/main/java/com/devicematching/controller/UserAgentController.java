package com.devicematching.controller;

import com.devicematching.model.Device;
import com.devicematching.service.UserAgentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/devices")
public class UserAgentController {

    private final UserAgentServiceImpl userAgentService;

    public UserAgentController(UserAgentServiceImpl userAgentService) {
        this.userAgentService = userAgentService;
    }

    @PostMapping("/match")
    public ResponseEntity<Device> matchDevice(@RequestBody String userAgentString) {
        Optional<Device> matchedDevice = userAgentService.getMatchingDevice(userAgentString);

        return matchedDevice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
