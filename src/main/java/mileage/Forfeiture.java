package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Forfeiture_table")
class Forfeiture {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long remainPoint;

    @PostPersist
    public void onPostPersist(){
        System.out.println("Forfeiture");

        PointForfeited pointForfeited = new PointForfeited();
        BeanUtils.copyProperties(this, pointForfeited);

        pointForfeited.setRemainPoint((long)0);
        pointForfeited.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(Long remainPoint) {
        this.remainPoint = remainPoint;
    }

}
