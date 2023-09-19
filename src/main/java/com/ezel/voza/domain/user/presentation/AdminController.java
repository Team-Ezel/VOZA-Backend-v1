package com.ezel.voza.domain.user.presentation;

import com.ezel.voza.domain.user.service.GrantAdminService;
import com.ezel.voza.domain.user.service.RevokeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final GrantAdminService grantAdminService;

    private final RevokeAdminService revokeAdminService;

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
}
