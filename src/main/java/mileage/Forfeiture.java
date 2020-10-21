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

    @PrePersist
    public void onPrePersist(){
        System.out.println("onPre Forfeiture");
        /*
        try {
            System.out.println("Thread Sleep");
            Thread.currentThread().sleep((long) (450 + Math.random() * 230));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @PostPersist
    public void onPostPersist(){
        System.out.println("onPost Forfeiture");
        
        PointForfeited pointForfeited = new PointForfeited();
        pointForfeited.setMemberId(memberId);

        BeanUtils.copyProperties(this, pointForfeited);
        pointForfeited.setMemberId(memberId);

        pointForfeited.setRemainPoint(1000L);
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
