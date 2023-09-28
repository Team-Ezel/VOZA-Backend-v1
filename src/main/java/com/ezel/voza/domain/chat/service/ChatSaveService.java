package com.ezel.voza.domain.chat.service;

import com.ezel.voza.domain.chat.presenation.dto.request.Message;

public interface ChatSaveService {

    void execute(Message message);
}
