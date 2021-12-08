package com.example.taipeizoo.listdata;

import java.io.Serializable;

public class LocationPositionData implements Serializable {
    private String KeyX_position;
    private String KeyY_position;
    private String KeyLocationLogo;


    public void setKeyLocationLogo(String keyLocationLogo) {
        KeyLocationLogo = keyLocationLogo;
    }

    public String getKeyLocationLogo() {
        if (KeyLocationLogo != null) {
            return KeyLocationLogo;
        } else {
            return "";
        }
    }

    public String getKeyX_position() {
        return KeyX_position;
    }

    public void setKeyX_position(String keyX_position) {
        KeyX_position = keyX_position;
    }


    public String getKeyY_position() {
        return KeyY_position;
    }

    public void setKeyY_position(String keyY_position) {
        KeyY_position = keyY_position;
    }


}
