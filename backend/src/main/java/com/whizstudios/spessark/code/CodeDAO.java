package com.whizstudios.spessark.code;

public interface CodeDAO {
    boolean validateCode(String code);
    void updateCode(String code);
}
