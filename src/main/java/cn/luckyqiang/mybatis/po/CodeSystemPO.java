package cn.luckyqiang.mybatis.po;

public class CodeSystemPO {
    private Long id;

    private String systemName;

    private String systemCode;

    private String fileSystemCode;

    private String systemType;

    private String oflFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getFileSystemCode() {
        return fileSystemCode;
    }

    public void setFileSystemCode(String fileSystemCode) {
        this.fileSystemCode = fileSystemCode == null ? null : fileSystemCode.trim();
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType == null ? null : systemType.trim();
    }

    public String getOflFlag() {
        return oflFlag;
    }

    public void setOflFlag(String oflFlag) {
        this.oflFlag = oflFlag == null ? null : oflFlag.trim();
    }

    @Override
    public String toString() {
        return "CodeSystemPO{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", systemCode='" + systemCode + '\'' +
                ", fileSystemCode='" + fileSystemCode + '\'' +
                ", systemType='" + systemType + '\'' +
                ", oflFlag='" + oflFlag + '\'' +
                '}';
    }
}