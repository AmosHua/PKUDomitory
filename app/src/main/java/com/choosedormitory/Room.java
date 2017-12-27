package com.choosedormitory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amoshua on 20/12/2017.
 */

public class Room {
    /**
     * errcode : 0
     * data : {"5":200,"13":100,"14":100,"8":0,"9":0}
     */

    private int errcode;
    private DataBean data;
    public static Room getRoom(String jsonObject){
        return Util.GetGsonInstance().fromJson(jsonObject,Room.class);
    }
    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 5 : 200
         * 13 : 100
         * 14 : 100
         * 8 : 0
         * 9 : 0
         */

        @SerializedName("5")
        private int _$5;
        @SerializedName("13")
        private int _$13;
        @SerializedName("14")
        private int _$14;
        @SerializedName("8")
        private int _$8;
        @SerializedName("9")
        private int _$9;

        public int get_$5() {
            return _$5;
        }

        public void set_$5(int _$5) {
            this._$5 = _$5;
        }

        public int get_$13() {
            return _$13;
        }

        public void set_$13(int _$13) {
            this._$13 = _$13;
        }

        public int get_$14() {
            return _$14;
        }

        public void set_$14(int _$14) {
            this._$14 = _$14;
        }

        public int get_$8() {
            return _$8;
        }

        public void set_$8(int _$8) {
            this._$8 = _$8;
        }

        public int get_$9() {
            return _$9;
        }

        public void set_$9(int _$9) {
            this._$9 = _$9;
        }
    }
}
