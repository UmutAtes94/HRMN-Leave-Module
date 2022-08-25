package com.umutates.izinmodule.controller;

import com.umutates.izinmodule.dto.ApproveLeaveRequest;
import com.umutates.izinmodule.dto.LeaveRequest;
import com.umutates.izinmodule.service.ILeavesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/leave")
public class LeaveController {

    private final ILeavesService leavesService;

    public LeaveController(ILeavesService leavesService) {
        this.leavesService = leavesService;
    }


    @PostMapping("/request")
    public ResponseEntity newLeaveRequest(@RequestBody LeaveRequest leaveRequest){
        leavesService.createNewLeave(leaveRequest);
        return ResponseEntity.ok("Leave request has been created successfully");
    }


    @PatchMapping("/request/approve")
    public void approveLeaveRequest(@RequestBody ApproveLeaveRequest approveLeaveRequest){
        leavesService.approveLeaveRequest(approveLeaveRequest);

    }
}
