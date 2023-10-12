package com.ezel.voza.domain.user.presentation;

import com.ezel.voza.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final GrantAdminService grantAdminService;
    private final RevokeAdminService revokeAdminService;
    private final UserBanService userBanService;
    private final UserUnBanService userUnBanService;
    private final StopGroupService stopGroupService;

    @PatchMapping("/grant")
    public ResponseEntity<Void> grantRole(@RequestParam String email) {
        grantAdminService.execute(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/revoke")
    public ResponseEntity<Void> revokeRole(@RequestParam String email) {
        revokeAdminService.execute(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/ban")
    public ResponseEntity<Void> banUser(@RequestParam String email) {
        userBanService.execute(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/unban")
    public ResponseEntity<Void> unBanUser(@RequestParam String email) {
        userUnBanService.execute(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/groupBan/{groupId}")
    public ResponseEntity<Void> stopGroup(@PathVariable Long groupId) {
        stopGroupService.execute(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
