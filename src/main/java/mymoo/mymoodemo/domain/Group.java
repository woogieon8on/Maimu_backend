package mymoo.mymoodemo.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Group {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;
    private String groupColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @OneToMany(mappedBy = "group")
    private List<Maimu> maimus = new ArrayList<>();

    public void setLocker(Locker locker){
        this.locker = locker;
        locker.getGroups().add(this);
    }




}
