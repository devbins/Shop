package com.dev.bins.shop.bean;

/**
 * Created by bin on 25/02/2017.
 */

public class Recommend {

//    {"promote1":
//        {"id":17,"name":"手机专享","imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/555c6c90Ncb4fe515.jpg"},
//        "promote2":{"id":15,"name":"闪购","imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/560a26d2N78974496.jpg"},
//        "promote3":{"id":11,"name":"团购","imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/560be0c3N9e77a22a.jpg"},
//        "id":1,"name":"超值购","campaignOne":17,"campaignTwo":15,"campaignThree":11}


    private long id;
    private String name;
    private Promote promote1;
    private Promote promote2;
    private Promote promote3;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Promote getPromote1() {
        return promote1;
    }

    public void setPromote1(Promote promote1) {
        this.promote1 = promote1;
    }

    public Promote getPromote2() {
        return promote2;
    }

    public void setPromote2(Promote promote2) {
        this.promote2 = promote2;
    }

    public Promote getPromote3() {
        return promote3;
    }

    public void setPromote3(Promote promote3) {
        this.promote3 = promote3;
    }
}
