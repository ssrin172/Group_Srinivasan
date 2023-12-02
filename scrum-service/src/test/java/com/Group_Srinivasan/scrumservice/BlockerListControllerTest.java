package com.Group_Srinivasan.scrumservice;

import com.Group_Srinivasan.scrumservice.controller.BlockerListController;
import com.Group_Srinivasan.scrumservice.model.BlockerList;
import com.Group_Srinivasan.scrumservice.service.BlockerListService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class BlockerListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlockerListService blockerListService;

    @Test
    void testAddBlockerList() throws Exception {
        BlockerList blockerList = new BlockerList();
        blockerList.setID(1);

        Mockito.when(blockerListService.saveBlockerList(Mockito.any(BlockerList.class))).thenReturn(blockerList);

        mockMvc.perform(MockMvcRequestBuilders.post("/BlockerList/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("New Blocker added to blocker list"));
    }

    @Test
    void testGetAllBlockerList() throws Exception {
        List<BlockerList> blockerLists = Arrays.asList(new BlockerList(), new BlockerList());

        Mockito.when(blockerListService.getAllBlockerList()).thenReturn(blockerLists);

        mockMvc.perform(MockMvcRequestBuilders.get("/BlockerList/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testDeleteAllBlockerList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/BlockerList/deleteAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(blockerListService, Mockito.times(1)).deleteAllBlockerList();
    }
}

