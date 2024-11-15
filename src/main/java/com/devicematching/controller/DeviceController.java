package com.devicematching.controller;

import com.devicematching.model.Device;
import com.devicematching.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/match")
    public ResponseEntity<Device> matchDevice(@RequestBody String userAgentString) {
        Device device = deviceService.matchDevice(userAgentString);
        return ResponseEntity.ok(device);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") String id) {
        Device device = deviceService.getDeviceById(id);
        return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
    }

    @GetMapping("/os/{osName}")
    public ResponseEntity<List<Device>> getDevicesByOSName(@PathVariable("osName") String osName) {
        List<Device> devices = deviceService.getDevicesByOSName(osName);
        return ResponseEntity.ok(devices);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDevice(@RequestBody List<String> deviceIds) {
        deviceService.deleteDeviceById(deviceIds);
        return ResponseEntity.noContent().build();
    }
}