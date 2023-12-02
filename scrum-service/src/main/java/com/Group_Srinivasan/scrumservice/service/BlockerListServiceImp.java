package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.BlockerListRepository;
import com.Group_Srinivasan.scrumservice.model.BlockerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockerListServiceImp implements BlockerListService{
    @Autowired
    private BlockerListRepository blockerListRepository;
    @Override
    public BlockerList saveBlockerList(BlockerList blockerList) {
        return blockerListRepository.save(blockerList);
    }

    @Override
    public List<BlockerList> getAllBlockerList() {
        return blockerListRepository.findAll();
    }

    @Override
    public void deleteAllBlockerList() {
        blockerListRepository.deleteAll();
    }
}