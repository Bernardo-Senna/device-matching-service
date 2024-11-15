package com.devicematching.service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.query.Statement;
import com.devicematching.model.Device;
import com.devicematching.model.UserAgent;
import com.devicematching.utils.UserAgentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    private final AerospikeClient aerospikeClient;
    private final UserAgentParser userAgentParser;

    private static final String NAMESPACE = "test";
    private static final String SET_NAME = "devices";

    @Autowired
    public DeviceService(AerospikeClient aerospikeClient, UserAgentParser userAgentParser) {
        this.aerospikeClient = aerospikeClient;
        this.userAgentParser = userAgentParser;
    }

    public Device matchDevice(String userAgentString) {
        UserAgent userAgent = userAgentParser.parse(userAgentString);

        // Create a composite key based on user agent details
        String deviceKey = userAgent.getOsName() + "_" +
                userAgent.getOsVersion() + "_" +
                userAgent.getBrowserName() + "_" +
                userAgent.getBrowserVersion();

        Key key = new Key(NAMESPACE, SET_NAME, deviceKey);

        // Retrieve the record from Aerospike
        Record record = aerospikeClient.get(null, key);
        Device device = new Device();

        if (record != null) {
            device.setId(record.getString("id"));
            device.setOsName(record.getString("osName"));
            device.setOsVersion(record.getString("osVersion"));
            device.setBrowserName(record.getString("browserName"));
            device.setBrowserVersion(record.getString("browserVersion"));

            // Increment the hit count and update it in database
            int hitCount = record.getInt("hitCount") + 1;
            aerospikeClient.put(null, key, new Bin("hitCount", hitCount));
            device.setHitCount(hitCount);

        } else {
            // Device doesn't exist, create a new one
            device.setId(deviceKey);
            device.setHitCount(1); // Initialize hit count to 1
            device.setOsName(userAgent.getOsName());
            device.setOsVersion(userAgent.getOsVersion());
            device.setBrowserName(userAgent.getBrowserName());
            device.setBrowserVersion(userAgent.getBrowserVersion());

            // Store the new device in Aerospike
            aerospikeClient.put(null, key,
                    new Bin("id", device.getId()),
                    new Bin("hitCount", device.getHitCount()),
                    new Bin("osName", device.getOsName()),
                    new Bin("osVersion", device.getOsVersion()),
                    new Bin("browserName", device.getBrowserName()),
                    new Bin("browserVersion", device.getBrowserVersion()));
        }

        return device;
    }

    public Device getDeviceById(String id) {
        Key key = new Key(NAMESPACE, SET_NAME, id);
        Record record = aerospikeClient.get(null, key);

        if (record != null) {
            return new Device(
                    id,
                    record.getInt("hitCount"),
                    record.getString("osName"),
                    record.getString("osVersion"),
                    record.getString("browserName"),
                    record.getString("browserVersion")
            );
        }
        return null;
    }

    public List<Device> getDevicesByOSName(String osName) {
        // TODO: Performance improvement -> Create a secondary index on the bins, to avoid retrieving all devices from database.
        List<Device> allDevices = getAllDevices();
        List<Device> filteredDevices = new ArrayList<>();

        // Filter devices by the specified OS name
        for (Device device : allDevices) {
            if (device.getOsName().equalsIgnoreCase(osName)) {
                filteredDevices.add(device);
            }
        }
        return filteredDevices;
    }

    private List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();

        // Create a statement to query all records
        Statement statement = new Statement();
        statement.setNamespace(NAMESPACE);
        statement.setSetName(SET_NAME);

        QueryPolicy queryPolicy = new QueryPolicy();

        aerospikeClient.query(queryPolicy, statement, (key, record) -> {
            Device device = new Device();
            device.setId(record.getString("id"));
            device.setHitCount(record.getInt("hitCount"));
            device.setOsName(record.getString("osName"));
            device.setOsVersion(record.getString("osVersion"));
            device.setBrowserName(record.getString("browserName"));
            device.setBrowserVersion(record.getString("browserVersion"));
            devices.add(device);
        });

        return devices;
    }

    public void deleteDevicesByIds(List<String> deviceIds) {
        for (String id : deviceIds) {
            Key key = new Key(NAMESPACE, SET_NAME, id);
            aerospikeClient.delete(null, key);
        }
    }
}