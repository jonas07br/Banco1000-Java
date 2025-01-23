package br.ufrn.bti.banco1000.model;

public class Agency {
    private Long agencyCode;
    private String agencyName;
    public Agency(Long agencyCode, String agencyName) {
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
    }
    public Long getAgencyCode() {
        return agencyCode;
    }
    public void setAgencyCode(Long agencyCode) {
        this.agencyCode = agencyCode;
    }
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    public static String[] getHeaders() {
        return new String[] {
            "agencyCode",
            "agencyName"
        };
    }
    @Override
    public String toString() {
        return "Agencia:" + agencyName + ", Codigo=" + agencyCode;
    }
}
