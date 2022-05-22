package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.domain.FileData;
import com.nhnacademy.jdbc.board.compre.exception.FileDownloadFailedException;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultFileService;
import com.nhnacademy.jdbc.board.controller.FileController;
import java.io.FileOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class FileControllerTest {
    private DefaultFileService fileService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        fileService = mock(DefaultFileService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new FileController(fileService)).build();
    }

    @Test
    void normalFileDownloadTest() throws Exception {
        FileData fileData = new FileData();
        fileData.setFileName("file");
        fileData.setFileByte(new byte[10]);

        when(fileService.fileUpload(anyInt())).thenReturn(fileData);

        mockMvc.perform(get("/filedownload/1"))
               .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void abnormalFileDownloadTest() throws Exception {
        FileData fileData = new FileData();
        fileData.setFileName("file");
        fileData.setFileByte(new byte[10]);

        FileOutputStream fos = new FileOutputStream("file");
        when(fileService.fileUpload(1)).thenReturn(fileData);

        MvcResult mvcResult = mockMvc.perform(get("/filedownload/1"))
                                     .andReturn();

        assertThatThrownBy(() -> mvcResult.getResolvedException()
                                          .getClass()
                                          .isAssignableFrom(FileDownloadFailedException.class));
    }
}
