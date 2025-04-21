// File : Expirable.java
/*
* Interface untuk item yang memiliki tanggal kadaluarsa 
*/ 
import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiredDate();
    boolean isExpired();
}
