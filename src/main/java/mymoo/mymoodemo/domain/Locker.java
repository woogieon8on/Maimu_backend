package mymoo.mymoodemo.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;
    private String lockerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "locker")
    private List<Group> groups = new ArrayList<>();


    public void setUsers(Users users){
        this.users = users;
        users.getLockers().add(this);
    }



}
