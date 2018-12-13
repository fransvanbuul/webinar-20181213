package com.example.giftcard.client;

import com.example.giftcard.command.api.IssueCmd;
import com.example.giftcard.command.api.RedeemCmd;
import com.example.giftcard.query.GiftcardSummary;
import com.example.giftcard.query.GiftcardSummaryQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("client")
public class TestRunner implements CommandLineRunner {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public void run(String... args) throws Exception {
        UUID id = UUID.randomUUID();

        log.debug("Sending issue cmd");
        commandGateway.sendAndWait(new IssueCmd(id, 100));

        log.debug("Sending redeem cmd");
        commandGateway.sendAndWait(new RedeemCmd(id, 40));

        log.debug("Sending redeem cmd");
        commandGateway.sendAndWait(new RedeemCmd(id, 30));


        Thread.sleep(500);

        log.debug("querying");

        GiftcardSummary summary = queryGateway.query(new GiftcardSummaryQuery(id),
                ResponseTypes.instanceOf(GiftcardSummary.class)).join();
        log.debug("summary queried {}", summary);


    }
}
