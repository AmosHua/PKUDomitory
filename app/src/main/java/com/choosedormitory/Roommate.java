package com.choosedormitory;

/**
 * Created by amoshua on 20/12/2017.
 */

public class Roommate {
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getVocd() {
        return vocd;
    }

    public void setVocd(String vocd) {
        this.vocd = vocd;
    }

    public Roommate() {
    }

    private String no;
    private String vocd;

    public Roommate(String no, String vocd) {
        this.no = no;
        this.vocd = vocd;
    }
}
