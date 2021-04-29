package com.dsmpear.main.user_backend_v2;

import com.dsmpear.main.user_backend_v2.entity.notice.Notice;
import com.dsmpear.main.user_backend_v2.entity.notice.NoticeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserBackendV2Application.class)
@ActiveProfiles("test")
public class NoticeControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private NoticeRepository noticeRepository;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void deleteAll(){
        noticeRepository.deleteAll();;
    }

    @Test
    public void  getNoticeList() throws Exception{

        createNotice("notice1");
        createNotice("notice2");
        createNotice("notice3");
        createNotice("notice4");

        mvc.perform(get("/notice"))
                .andExpect(status().isOk());
    }

    @Test
    public void  getNoticeContent() throws Exception{

        long noticeId = createNotice("notice");

        mvc.perform(get("/notice/"+noticeId))
                .andExpect(status().isOk());
    }

    @Test
    public void  getNoticeContent_noId() throws Exception{

        createNotice("notice1");

        mvc.perform(get("/notice/"+10000))
                .andExpect(status().isNotFound());
    }

    private Long createNotice(String str){
        return noticeRepository.save(
                Notice.builder()
                        .title(str)
                        .description(str)
                        .fileName(str)
                        .build()
        ).getId();
    }

}
