package mileage;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Point_table")
public class Point {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private Long memberId;
        private Long remainPoint =1000L;
        private String memberStatus;
        private Long requirePoint;

        @PostPersist
        public void onPostPersist() {
                System.out.println("\n$$$onPostPersist");
                try {
                        Thread.currentThread().sleep((long) (400 + Math.random() * 220));
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                
                if(this.memberStatus.equals("NORMAL")) {

                        PointSaved pointSaved = new PointSaved();
                        BeanUtils.copyProperties(this, pointSaved);
                        pointSaved.publishAfterCommit();
                }else if(this.memberStatus.equals("WITHDRAWAL")){
                        PointSaved pointSaved = new PointSaved();
                        BeanUtils.copyProperties(this, pointSaved);
                        pointSaved.setMemberId(this.getId());
                        pointSaved.setMemberId(this.getMemberId());
                        pointSaved.setRemainPoint(0L);
                        pointSaved.publishAfterCommit();
                }


        }

        @PreUpdate
        public void onPreUpdate() {
                Long newPoint = remainPoint;

                PointUsed pointUsed = new PointUsed();

                if(this.memberStatus.equals("NORMAL")) {

                        newPoint = this.getRemainPoint() + this.getRequirePoint();
                        this.setRemainPoint(newPoint);
                        BeanUtils.copyProperties(this, pointUsed);
                        pointUsed.publishAfterCommit();
                }

                System.out.println("\n$$$onPreUpdate : " + newPoint);
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

        public String getMemberStatus() {
                return memberStatus;
        }

        public void setMemberStatus(String memberStatus) {
                this.memberStatus = memberStatus;
        }

        public Long getRequirePoint() {
                return requirePoint;
        }

        public void setRequirePoint(Long usePoint) {
                this.requirePoint = usePoint;
        }
}
