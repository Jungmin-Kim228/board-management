package com.nhnacademy.jdbc.board.compre.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FileData {

    @NotNull
    @Size(min = 1, max = 50, message = "1 ~ 50 사이의 파일 이름이여야합니다.")
    String fileName;

    @NotNull
    byte[] fileByte;
}
