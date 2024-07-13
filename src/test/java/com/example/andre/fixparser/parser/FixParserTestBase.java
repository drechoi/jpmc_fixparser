package com.example.andre.fixparser.parser;

import java.nio.charset.StandardCharsets;

public abstract class FixParserTestBase {
    /**
     * New Order Single (35=D)
     *
     * Std Tag
     * BeginString (8) = FIX.4.4
     * BodyLength (9) = …
     * MsgType (35) = D
     * MsgSeqNum (34) = 3
     * PossDupFlag (43) =
     * SenderCompID (49) = EBR123
     * SenderSubID (50) = smithj
     * TargetCompID (56) = COIND
     * TargetSubID (57) = TEST
     *
     * New Order Single (35=D)
     *
     * Account (1) = C123
     * ClOrdID (11) = 314bb362:109f840f9c0
     * Currency (15)
     * OrderQty (38) = 100
     * OrdType (40) = 2
     * Price (44) = 1.10317
     * SendingTime (52)
     * Side (54) = 1
     * Symbol (55) =EUM20
     * TransactTime (60) = 20171102-10:15:40.383
     * CheckSum (10) = …
     *
     * 8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|11=6490907363867796|15=USD|38=7000|54=1|55=MSFT|60=20240712-22:14:19.508|10=092|
     */
    static final byte[] newOrderMessage = "8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|1=A8466653547|11=6490907363867796|15=USD|38=7000|54=1|55=MSFT|60=20240712-22:14:19.508|10=092|".getBytes(StandardCharsets.UTF_8);


    /**
     * cancel order 35=F
     * 1	Account	String(12)	Y	Unique ID representing the account.
     * 11	ClOrdID	String(20)	Y	Unique client ID representing the order. Client system must maintain uniqueness of this value for the life of the order.
     * 37	OrderID	String(17)	Y	Unique exchange ID representing the order.
     * 41	OrigClOrdID	String(20)	N	Last accepted ClOrdID in the order chain.
     * 54	Side	Char(1)	Y	Side of order. See Side (54) code set.
     * 55	Symbol	String(24)	Y	Represents details of an instrument. Future Example: EUM20
     * 167	SecurityType	String(6)	Y	Represents security type. See SecurityType (167) code set.
     * 60	TransactTime	UTCTimestamp(21)	Y	Time when the order message was submitted. UTC format YYYYMMDD-HH:MM:SS.sss in microseconds.
     */
    final byte[] cancelOrderMessage = "8=FIX.4.4|9=137|35=F|1=DUMMY_ACC|54=1|".getBytes(StandardCharsets.UTF_8);

    /**
     *
     * Other message types should be very similar. Not going to repeat here
     *
      */
}
