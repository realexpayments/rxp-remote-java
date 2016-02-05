package com.realexpayments.remote.sdk.domain.payment;

import com.realexpayments.remote.sdk.domain.DccInfo;
import com.realexpayments.remote.sdk.domain.Payer;
import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.payment.Address.AddressType;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest.PaymentType;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.*;

/**
 * Unit test class for PaymentRequest utility methods.
 *
 * @author markstanford
 */
public class PaymentRequestTest {

    /**
     * Tests the population of a billing address for the Address Verification Service.
     */
    @Test
    public void addAddressVerificationServiceDetailsTest() {
        //test variations of address and postcode with TSS Info field null
        String addressLine = "123 Fake St";
        String postcode = "WB1 A42";
        String expectedBillingCode = "142|123";

        PaymentRequest request = new PaymentRequest();
        request.addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

        //test 2
        addressLine = "123 5 Fake St";
        postcode = "1WB 5A2";
        expectedBillingCode = "152|1235";

        request = new PaymentRequest();
        request.addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

        //test 3
        addressLine = "Apt 15, 123 Fake St";
        postcode = "ABC 5A2";
        expectedBillingCode = "52|15123";

        request = new PaymentRequest();
        request.addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

        //test 4
        addressLine = "Fake St";
        postcode = "AI10 9AB";
        expectedBillingCode = "109|";

        request = new PaymentRequest();
        request.addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

        //test 5
        addressLine = "30 Fake St";
        postcode = "";
        expectedBillingCode = "|30";

        request = new PaymentRequest();
        request.addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());
    }

    /**
     * Tests the population of a billing address for the Address Verification Service when TSS Info already exists.
     */
    @Test
    public void addAddressVerificationServiceDetailsWithTssInfoTest() {
        String addressLine = "123 Fake St";
        String postcode = "WB1 A42";
        String expectedBillingCode = "142|123";

        PaymentRequest request = new PaymentRequest();
        request.addTssInfo(new TssInfo()).addAddressVerificationServiceDetails(addressLine, postcode);

        Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
        Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());
    }

    /**
     * Tests the hash calculation for an auth payment.
     */
    @Test
    public void authHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.AUTH).addTimeStamp(TIMESTAMP).addMerchantId(MERCHANT_ID)
                .addOrderId(ORDER_ID).addAmount(AMOUNT).addCurrency(CURRENCY).addCard(new Card().addNumber(CARD_NUMBER));
        request.hash(SECRET);

        Assert.assertEquals(REQUEST_HASH, request.getHash());

    }

    /**
     * Tests the hash calculation for an auth-mobile payment.
     */
    @Test
    public void authMobileHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.AUTH_MOBILE).addTimeStamp(AUTH_MOBILE_TIMESTAMP)
                .addMerchantId(AUTH_MOBILE_MERCHANT_ID).addOrderId(AUTH_MOBILE_ORDER_ID).addToken(AUTH_MOBILE_TOKEN);
        request.hash(SECRET);

        Assert.assertEquals(AUTH_MOBILE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a settle payment.
     */
    @Test
    public void settleHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.SETTLE).addTimeStamp(SETTLE_TIMESTAMP).addMerchantId(SETTLE_MERCHANT_ID)
                .addOrderId(SETTLE_ORDER_ID).addPaymentsReference(SETTLE_PASREF).addAuthCode(SETTLE_AUTH_CODE)
                .addAmount(Long.parseLong(SETTLE_AMOUNT)).addCurrency(SETTLE_CURRENCY);
        request.hash(SECRET);

        Assert.assertEquals(SETTLE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a void payment.
     */
    @Test
    public void voidHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.VOID).addTimeStamp(VOID_TIMESTAMP).addMerchantId(VOID_MERCHANT_ID)
                .addOrderId(VOID_ORDER_ID).addPaymentsReference(VOID_PASREF).addAuthCode(VOID_AUTH_CODE);
        request.hash(SECRET);

        Assert.assertEquals(VOID_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a rebate payment.
     */
    @Test
    public void rebateHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.REBATE).addTimeStamp(REBATE_TIMESTAMP).addMerchantId(REBATE_MERCHANT_ID)
                .addOrderId(REBATE_ORDER_ID).addPaymentsReference(REBATE_PASREF).addAuthCode(REBATE_AUTH_CODE)
                .addAmount(Long.parseLong(REBATE_AMOUNT)).addCurrency(REBATE_CURRENCY).addRefundHash(REBATE_REFUND_HASH);
        request.hash(SECRET);

        Assert.assertEquals(REBATE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for an OTB request.
     */
    @Test
    public void otbHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.OTB).addTimeStamp(OTB_TIMESTAMP).addMerchantId(OTB_MERCHANT_ID)
                .addOrderId(OTB_ORDER_ID);
        request.hash(SECRET);

        Assert.assertEquals(OTB_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a credit payment.
     */
    @Test
    public void creditHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.CREDIT).addTimeStamp(CREDIT_TIMESTAMP).addMerchantId(CREDIT_MERCHANT_ID)
                .addOrderId(CREDIT_ORDER_ID).addPaymentsReference(CREDIT_PASREF).addAuthCode(CREDIT_AUTH_CODE)
                .addAmount(Long.parseLong(CREDIT_AMOUNT)).addCurrency(CREDIT_CURRENCY).addRefundHash(CREDIT_REFUND_HASH);
        request.hash(SECRET);

        Assert.assertEquals(CREDIT_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a hold payment.
     */
    @Test
    public void holdHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.HOLD).addTimeStamp(HOLD_TIMESTAMP).addMerchantId(HOLD_MERCHANT_ID)
                .addOrderId(HOLD_ORDER_ID).addPaymentsReference(HOLD_PASREF);
        request.hash(SECRET);

        Assert.assertEquals(HOLD_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a release payment.
     */
    @Test
    public void releaseHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.RELEASE).addTimeStamp(RELEASE_TIMESTAMP).addMerchantId(RELEASE_MERCHANT_ID)
                .addOrderId(RELEASE_ORDER_ID).addPaymentsReference(RELEASE_PASREF);
        request.hash(SECRET);

        Assert.assertEquals(RELEASE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a receipt-in payment.
     */
    @Test
    public void receiptInHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.RECEIPT_IN).addTimeStamp(RECEIPT_IN_TIMESTAMP).addMerchantId(RECEIPT_IN_MERCHANT_ID)
                .addOrderId(RECEIPT_IN_ORDER_ID).addAmount(Long.parseLong(RECEIPT_IN_AMOUNT)).addCurrency(RECEIPT_IN_CURRENCY).addPayerRef(RECEIPT_IN_PAYER);
        request.hash(SECRET);

        Assert.assertEquals(RECEIPT_IN_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a payment-out payment.
     */
    @Test
    public void paymentOutHashGenerationTest() {
        PaymentRequest request = new PaymentRequest().addType(PaymentType.PAYMENT_OUT).addTimeStamp(PAYMENT_OUT_TIMESTAMP).addMerchantId(PAYMENT_OUT_MERCHANT_ID)
                .addOrderId(PAYMENT_OUT_ORDER_ID).addAmount(Long.parseLong(PAYMENT_OUT_AMOUNT)).addCurrency(PAYMENT_OUT_CURRENCY).addPayerRef(PAYMENT_OUT_PAYER)
                .addRefundHash(PAYMENT_OUT_REFUND_HASH);
        request.hash(SECRET);

        Assert.assertEquals(PAYMENT_OUT_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a payer-new transaction.
     */
    @Test
    public void payerNewHashGenerationTest() {

        Payer payer = new Payer()
                .addRef(PAYER_NEW_PAYER_REF);

        PaymentRequest request = new PaymentRequest().addType(PaymentType.PAYER_NEW).addTimeStamp(PAYER_NEW_TIMESTAMP).addMerchantId(PAYER_NEW_MERCHANT_ID)
                .addOrderId(PAYER_NEW_ORDER_ID).addPayer(payer);

        request.hash(SECRET);

        Assert.assertEquals(PAYER_NEW_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a payer-edit transaction.
     */
    @Test
    public void payerEditHashGenerationTest() {

        Payer payer = new Payer()
                .addRef(PAYER_EDIT_PAYER_REF);

        PaymentRequest request = new PaymentRequest().addType(PaymentType.PAYER_EDIT).addTimeStamp(PAYER_EDIT_TIMESTAMP).addMerchantId(PAYER_EDIT_MERCHANT_ID)
                .addOrderId(PAYER_EDIT_ORDER_ID).addPayer(payer);

        request.hash(SECRET);

        Assert.assertEquals(PAYER_EDIT_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a card-new transaction.
     */
    @Test
    public void cardNewHashGenerationTest() {

        Card card = new Card()
                .addReference(CARD_ADD_REF)
                .addPayerReference(CARD_ADD_PAYER_REF)
                .addCardHolderName(CARD_ADD_CARD_HOLDER_NAME)
                .addNumber(CARD_ADD_NUMBER);


        PaymentRequest request = new PaymentRequest().addType(PaymentType.CARD_NEW)
                .addTimeStamp(CARD_ADD_TIMESTAMP)
                .addMerchantId(CARD_ADD_MERCHANT_ID)
                .addOrderId(CARD_ADD_ORDER_ID)
                .addCard(card);

        request.hash(SECRET);

        Assert.assertEquals(CARD_ADD_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a card-update transaction.
     */
    @Test
    public void cardUpdateHashGenerationTest() {

        Card card = new Card()
                .addReference(CARD_UPDATE_REF)
                .addPayerReference(CARD_UPDATE_PAYER_REF)
                .addCardHolderName(CARD_UPDATE_CARD_HOLDER_NAME)
                .addExpiryDate(CARD_UPDATE_EXP_DATE)
                .addNumber(CARD_UPDATE_NUMBER);


        PaymentRequest request = new PaymentRequest().addType(PaymentType.CARD_UPDATE)
                .addTimeStamp(CARD_UPDATE_TIMESTAMP)
                .addMerchantId(CARD_UPDATE_MERCHANT_ID)
                .addOrderId(CARD_UPDATE_ORDER_ID)
                .addCard(card);

        request.hash(SECRET);

        Assert.assertEquals(CARD_UPDATE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a card-update transaction.
     */
    @Test
    public void cardDeleteHashGenerationTest() {

        Card card = new Card()
                .addReference(CARD_DELETE_REF)
                .addPayerReference(CARD_DELETE_PAYER_REF);


        PaymentRequest request = new PaymentRequest().addType(PaymentType.CARD_CANCEL)
                .addTimeStamp(CARD_DELETE_TIMESTAMP)
                .addMerchantId(CARD_DELETE_MERCHANT_ID)
                .addCard(card);

        request.hash(SECRET);

        Assert.assertEquals(CARD_DELETE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a card-update transaction.
     */
    @Test
    public void dccInfoHashGenerationTest() {

        Card card = new Card()
                .addNumber(DCC_RATE_CARD_NUMBER)
                .addExpiryDate(DCC_RATE_CARD_EXPIRY_DATE)
                .addCardHolderName(DCC_RATE_CARD_HOLDER_NAME)
                .addType(DCC_RATE_CARD_TYPE);

        // add dccinfo. Note that the type is not set as it is already defaulted to 1
        DccInfo dccInfo =new DccInfo()
                .addDccProcessor(DCC_RATE_CCP);


        PaymentRequest request = new PaymentRequest()
                .addType(PaymentType.DCC_RATE_LOOKUP)
                .addTimeStamp(DCC_RATE_TIMESTAMP)
                .addMerchantId(DCC_RATE_MERCHANT_ID)
                .addAmount(Long.parseLong(DCC_RATE_AMOUNT))
                .addCurrency(DCC_RATE_CURRENCY)
                .addOrderId(DCC_RATE_ORDER_ID)
                .addCard(card)
                .addDccInfo(dccInfo);


        request.hash(SECRET);

        Assert.assertEquals(DCC_RATE_REQUEST_HASH, request.getHash());
    }

    /**
     * Tests the hash calculation for a card-update transaction.
     */
    @Test
    public void dccAuthHashGenerationTest() {

        Card card = new Card()
                .addNumber(DCC_AUTH_CARD_NUMBER)
                .addExpiryDate(DCC_AUTH_CARD_EXPIRY_DATE)
                .addCardHolderName(DCC_AUTH_CARD_HOLDER_NAME)
                .addType(DCC_AUTH_CARD_TYPE);

        // add dccinfo. Note that the type is not set as it is already defaulted to 1
        DccInfo dccInfo =new DccInfo()
                .addDccProcessor(DCC_AUTH_CCP);


        PaymentRequest request = new PaymentRequest()
                .addType(PaymentType.DCC_AUTH)
                .addTimeStamp(DCC_AUTH_TIMESTAMP)
                .addMerchantId(DCC_AUTH_MERCHANT_ID)
                .addAmount(Long.parseLong(DCC_AUTH_AMOUNT))
                .addCurrency(DCC_AUTH_CURRENCY)
                .addOrderId(DCC_AUTH_ORDER_ID)
                .addCard(card)
                .addDccInfo(dccInfo);


        request.hash(SECRET);

        Assert.assertEquals(DCC_AUTH_REQUEST_HASH, request.getHash());
    }
}

