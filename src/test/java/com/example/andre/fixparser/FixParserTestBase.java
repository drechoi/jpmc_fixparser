package com.example.andre.fixparser;

import java.nio.charset.StandardCharsets;

public abstract class FixParserTestBase {
    /**
     * New Order Single (35=D)
     */
    protected static final byte[] newOrderMessage = "8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|1=A8466653547|11=6490907363867796|15=USD|38=7000|40=1|44=12.501|54=1|55=S207027|60=20240712-22:14:19.508|10=092|".getBytes(StandardCharsets.UTF_8);

    /**
     * cancel order (35=F)
     *
     * 1	Account	String
     * 11	ClOrdID	String
     * 37	OrderID	String
     * 41	OrigClOrdID	String
     * 54	Side	Char
     * 55	Symbol	String
     * 60	TransactTime
     */
    protected static final byte[] cancelOrderMessage = ("8=FIX.4.4|9=137|35=F|1=A8466653547|11=6490907363867797|37=C-00000156489-1|41=6490907363867796|54=1|55=S207027|60=20240712-22:14:19.508|10=123|").getBytes(StandardCharsets.UTF_8);


    /**
     *
     * Other message types should be very similar. Not going to repeat here
     *
     * */
    protected static final byte[] loginMessage = "8=FIX.4.4|9=75|35=A|34=1092|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|98=0|108=60|10=178|".getBytes();
}