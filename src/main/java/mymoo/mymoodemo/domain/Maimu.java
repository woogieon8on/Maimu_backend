package mymoo.mymoodemo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Maimu {

    @Id @GeneratedValue
    @Column(name = "maimu_id")
    private Long id;

    @Lob
    private String message;

    private String mymooColor;

    private String writerName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public void setGroup(Group group){
        this.group = group;
        group.getMaimus().add(this);
    }


}
