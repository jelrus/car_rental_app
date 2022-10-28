package util.invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public abstract class AbstractInvoice {

    private String companyName;
    private String address;
    private String contactDetails;
    private Date invoiceDate;
    private Long invoiceNumber;

    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private String recipientEmail;

    private String productName;
    private BigDecimal totalCost;

    public AbstractInvoice(String companyName, String address, String contactDetails, Date invoiceDate,
                           Long invoiceNumber, String recipientName, String recipientAddress, String recipientPhone,
                           String recipientEmail, String productName, BigDecimal totalCost) {
        this.companyName = companyName;
        this.address = address;
        this.contactDetails = contactDetails;
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientPhone = recipientPhone;
        this.recipientEmail = recipientEmail;
        this.productName = productName;
        this.totalCost = totalCost;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractInvoice that = (AbstractInvoice) o;
        return Objects.equals(companyName, that.companyName) &&
               Objects.equals(address, that.address) &&
               Objects.equals(contactDetails, that.contactDetails) &&
               Objects.equals(invoiceDate, that.invoiceDate) &&
               Objects.equals(invoiceNumber, that.invoiceNumber) &&
               Objects.equals(recipientName, that.recipientName) &&
               Objects.equals(recipientAddress, that.recipientAddress) &&
               Objects.equals(recipientPhone, that.recipientPhone) &&
               Objects.equals(recipientEmail, that.recipientEmail) &&
               Objects.equals(productName, that.productName) &&
               Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, address, contactDetails, invoiceDate, invoiceNumber,
                            recipientName, recipientAddress, recipientPhone, recipientEmail, productName,
                            totalCost);
    }

    @Override
    public String toString() {
        return "AbstractInvoice{" +
                "companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", invoiceNumber=" + invoiceNumber +
                ", recipientName='" + recipientName + '\'' +
                ", recipientAddress='" + recipientAddress + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", productName='" + productName + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
