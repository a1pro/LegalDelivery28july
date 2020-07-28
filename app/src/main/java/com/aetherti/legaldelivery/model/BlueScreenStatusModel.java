package com.aetherti.legaldelivery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BlueScreenStatusModel {

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("DeviceCode")
    @Expose
    private String deviceCode;
    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("DeviceIP")
    @Expose
    private Object deviceIP;
    @SerializedName("CreatedByUserId")
    @Expose
    private String createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedByUserId")
    @Expose
    private String modifiedByUserId;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ProductType")
    @Expose
    private String productType;
    @SerializedName("IsActive")
    @Expose
    private String isActive;
    @SerializedName("User")
    @Expose
    private Object user;
    @SerializedName("LegalDeliveries")
    @Expose
    private LegalDeliveries legalDeliveries;
    @SerializedName("LogDetails")
    @Expose
    private LogDetails logDetails;
    @SerializedName("Servers")
    @Expose
    private Servers servers;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Object getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(Object deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedByUserId() {
        return modifiedByUserId;
    }

    public void setModifiedByUserId(String modifiedByUserId) {
        this.modifiedByUserId = modifiedByUserId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public LegalDeliveries getLegalDeliveries() {
        return legalDeliveries;
    }

    public void setLegalDeliveries(LegalDeliveries legalDeliveries) {
        this.legalDeliveries = legalDeliveries;
    }

    public LogDetails getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(LogDetails logDetails) {
        this.logDetails = logDetails;
    }

    public Servers getServers() {
        return servers;
    }

    public void setServers(Servers servers) {
        this.servers = servers;
    }


    public class Servers {

        @SerializedName("$id")
        @Expose
        private String $id;
        @SerializedName("$values")
        @Expose
        private List<Object> $values = null;

        public String get$id() {
            return $id;
        }

        public void set$id(String $id) {
            this.$id = $id;
        }

        public List<Object> get$values() {
            return $values;
        }

        public void set$values(List<Object> $values) {
            this.$values = $values;
        }

    }
    public class LogDetails {

        @SerializedName("$id")
        @Expose
        private String $id;
        @SerializedName("$values")
        @Expose
        private List<Object> $values = null;

        public String get$id() {
            return $id;
        }

        public void set$id(String $id) {
            this.$id = $id;
        }

        public List<Object> get$values() {
            return $values;
        }

        public void set$values(List<Object> $values) {
            this.$values = $values;
        }

    }

    public class LegalDeliveries {

        @SerializedName("$id")
        @Expose
        private String $id;
        @SerializedName("$values")
        @Expose
        private List<Object> $values = null;

        public String get$id() {
            return $id;
        }

        public void set$id(String $id) {
            this.$id = $id;
        }

        public List<Object> get$values() {
            return $values;
        }

        public void set$values(List<Object> $values) {
            this.$values = $values;
        }

    }
}
