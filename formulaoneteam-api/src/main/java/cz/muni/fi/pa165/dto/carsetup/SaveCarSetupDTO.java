package cz.muni.fi.pa165.dto.carsetup;

import cz.muni.fi.pa165.dto.base.BaseDTO;

/**
 * @author Ivan Dendis
 */
public class SaveCarSetupDTO extends BaseDTO {

    private long engineId;
    private long suspensionId;
    private long brakesId;
    private long transmissionId;
    private long tiresId;
    private long coverId;

    /**
     * @return the coverId
     */
    public long getCoverId() {
        return coverId;
    }

    /**
     * @param coverId the coverId to set
     */
    public void setCoverId(long coverId) {
        this.coverId = coverId;
    }

    /**
     * @return the tiresId
     */
    public long getTiresId() {
        return tiresId;
    }

    /**
     * @param tiresId the tiresId to set
     */
    public void setTiresId(long tiresId) {
        this.tiresId = tiresId;
    }

    /**
     * @return the transmissionId
     */
    public long getTransmissionId() {
        return transmissionId;
    }

    /**
     * @param transmissionId the transmissionId to set
     */
    public void setTransmissionId(long transmissionId) {
        this.transmissionId = transmissionId;
    }

    /**
     * @return the brakesId
     */
    public long getBrakesId() {
        return brakesId;
    }

    /**
     * @param brakesId the brakesId to set
     */
    public void setBrakesId(long brakesId) {
        this.brakesId = brakesId;
    }

    /**
     * @return the suspensionId
     */
    public long getSuspensionId() {
        return suspensionId;
    }

    /**
     * @param suspensionId the suspensionId to set
     */
    public void setSuspensionId(long suspensionId) {
        this.suspensionId = suspensionId;
    }

    /**
     * @return the engineId
     */
    public long getEngineId() {
        return engineId;
    }

    /**
     * @param engineId the engineId to set
     */
    public void setEngineId(long engineId) {
        this.engineId = engineId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveCarSetupDTO)) return false;

        SaveCarSetupDTO that = (SaveCarSetupDTO) o;

        return engineId == that.engineId
                && suspensionId == that.suspensionId
                && brakesId == that.brakesId
                && transmissionId == that.transmissionId
                && tiresId == that.tiresId
                && coverId == that.coverId;
    }

    @Override
    public int hashCode() {
        int result = (int) (engineId ^ (engineId >>> 32));
        result = 31 * result + (int) (suspensionId ^ (suspensionId >>> 32));
        result = 31 * result + (int) (brakesId ^ (brakesId >>> 32));
        result = 31 * result + (int) (transmissionId ^ (transmissionId >>> 32));
        result = 31 * result + (int) (tiresId ^ (tiresId >>> 32));
        result = 31 * result + (int)coverId;
        return result;
    }

    @Override
    public String toString() {
        return "RaceParticipationDTO{" +
                "engineId=" + engineId +
                ", suspensionId=" + suspensionId +
                ", brakesId=" + brakesId +
                ", transmissionId=" + transmissionId +
                ", tiresId=" + tiresId +
                ", coverId=" + coverId +
                "} " + super.toString();
    }
}
