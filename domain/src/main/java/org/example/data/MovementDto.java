package org.example.data;

public class MovementDto {

    private Long id;
    private Long accountId;
    private Long clientId;
    private double oldBalance;
    private MovementTypeEnum movementType;
    private double movementAmount;
    private MovementStatusEnum movementStatus;
    private double newBalance;

    public MovementDto() {
    }

    public MovementDto(Long id, Long accountId, Long clientId, double oldBalance, MovementTypeEnum movementType,
                       double movementAmount, MovementStatusEnum movementStatus, double newBalance) {
        this.id = id;
        this.accountId = accountId;
        this.clientId = clientId;
        this.oldBalance = oldBalance;
        this.movementType = movementType;
        this.movementAmount = movementAmount;
        this.movementStatus = movementStatus;
        this.newBalance = newBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(double oldBalance) {
        this.oldBalance = oldBalance;
    }

    public MovementTypeEnum getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementTypeEnum movementType) {
        this.movementType = movementType;
    }

    public double getMovementAmount() {
        return movementAmount;
    }

    public void setMovementAmount(double movementAmount) {
        this.movementAmount = movementAmount;
    }

    public MovementStatusEnum getMovementStatus() {
        return movementStatus;
    }

    public void setMovementStatus(MovementStatusEnum movementStatus) {
        this.movementStatus = movementStatus;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }
}
