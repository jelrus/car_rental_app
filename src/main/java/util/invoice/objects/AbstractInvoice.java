package util.invoice.objects;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public abstract class AbstractInvoice {

    private String creation;
    private String number;
    private Member sender;
    private Member recipient;

    public AbstractInvoice(Member sender, Member recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getCreation() {
        LocalDate creation = LocalDate.now();
        return creation.getDayOfMonth() + "." + creation.getMonthValue() + "." + creation.getYear();
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getNumber() {
        return "#" + new Date().getTime();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public Member getRecipient() {
        return recipient;
    }

    public void setRecipient(Member recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractInvoice that = (AbstractInvoice) o;
        return Objects.equals(creation, that.creation) && Objects.equals(number, that.number) &&
                Objects.equals(sender, that.sender) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creation, number, sender, recipient);
    }

    @Override
    public String toString() {
        return "AbstractInvoice{" +
                "creation='" + creation + '\'' +
                ", number='" + number + '\'' +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }
}