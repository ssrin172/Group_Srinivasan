package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.BlockerList;
import java.util.List;

public interface BlockerListService {
    public BlockerList saveBlockerList(BlockerList blockerList);
    public List<BlockerList> getAllBlockerList();

    public void deleteAllBlockerList();
}

