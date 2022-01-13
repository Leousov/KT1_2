package com.leo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "orders")
public class COrder{
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = true, nullable = false)
    public UUID id;
    /*@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")*/
    @Column(name = "uid", updatable = true, nullable = true)
    private UUID Uid;
    /*@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")*/
    @Column(name = "gid", updatable = true, nullable = true)
    private UUID Gid;
    @Column(name = "datebirth", updatable = true, columnDefinition = "Date")
    private LocalDate date;

    //UID
    public UUID getUid() {
        return Uid;
    }
    public void setUid(UUID uid) {
        Uid = uid;
    }
    //GID
    public UUID getGid() {
        return Gid;
    }
    public void setGid(UUID gid) {
        Gid = gid;
    }
    //DATE
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public COrder(UUID user_id, UUID good_id, LocalDate date){
        setDate(date);
        setGid(good_id);
        setUid(user_id);
    }
    public COrder(){
        setUid(null);
        setGid(null);
        setDate(LocalDate.now());
        id = UUID.randomUUID();
    }
}