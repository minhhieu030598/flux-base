package com.minhhieu.core.notifier;

import com.minhhieu.core.model.msg.SyncPersonMessage;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.SenderResult;

public interface PersonNotifier {
    Flux<SenderResult<Object>> sync(SyncPersonMessage message);
}
