package br.com.rdfc.test_practice.controller.vo;

import java.time.format.DateTimeFormatter;

public interface StandardVo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
}
