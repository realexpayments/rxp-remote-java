# Realex Remote Java SDK
You can sign up for a Realex account at https://www.realexpayments.com.
## Requirements
Java 1.6 and later.
## Installation
### Maven users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.realexpayments.remote.sdk</groupId>
  <artifactId>rxp-remote-java</artifactId>
  <version>1.1</version>
</dependency>
```

### Gradle users
Add this dependency to your project's build file:
```
compile "com.realexpayments.remote.sdk:rxp-remote-java:1.1"
```
## Usage

Please see https://developer.realexpayments.com for more comprehensive integration guides.

### Authorisation

```java
Card card = new Card()
	.addExpiryDate("0119")
	.addNumber("4242424242424242")
	.addType(CardType.VISA)
	.addCardHolderName("Joe Smith")
	.addCvn("123")
	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);

PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.AUTH)
	.addAmount(100)
	.addCurrency("EUR")
	.addCard(card)
	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE));

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Authorisation (With Address Verification)

```java
Card card = new Card()
	.addExpiryDate("0119")
	.addNumber("420000000000000000")
	.addType(CardType.VISA)
	.addCardHolderName("Joe Smith")
	.addCvn("123")
	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.AUTH)
	.addAmount(100)
	.addCurrency("EUR")
	.addCard(card)
	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE))
	.addAddressVerificationServiceDetails("382 The Road", "WB1 A42");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Authorisation (Mobile)

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.AUTH_MOBILE)
	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE))
	.addMobile("apple-pay")
	.addToken("{auth mobile payment token}");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```


### Settle

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.SETTLE)
	.addOrderId("Order ID from original transaction")
	.addAmount(100)
	.addCurrency("EUR")
	.addPaymentsReference("pasref from original transaction")
	.addAuthCode("Auth code from original transaction");


RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Void

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.VOID)
	.addOrderId("Order ID from original transaction")
	.addPaymentsReference("pasref from original transaction")
	.addAuthCode("Auth code from original transaction");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Rebate

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.REBATE)
	.addOrderId("Order ID from original transaction")
	.addAmount(100)
	.addCurrency("EUR")
	.addPaymentsReference("pasref from original transaction")
	.addAuthCode("Auth code from original transaction")
	.addRefundHash("Hash of rebate password shared with Realex");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### OTB

```java
Card card = new Card()
	.addExpiryDate("0119")
	.addNumber("420000000000000000")
	.addType(CardType.VISA)
	.addCardHolderName("Joe Smith")
	.addCvn("123")
	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 
 PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.OTB)
	.addCard(card);
	
RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);	
```

### Credit

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.CREDIT)
	.addAmount(100)
	.addCurrency("EUR")
	.addPaymentsReference("Pasref from original transaction")
	.addAuthCode("Auth code from original transaction")
	.addRefundHash("Hash of credit password shared with Realex");
 
RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Hold

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.HOLD)
	.addOrderId("Order ID from original transaction")
	.addPaymentsReference("Pasref from original transaction");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Release

```java
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.RELEASE)
	.addOrderId("Order ID from original transaction")
	.addPaymentsReference("Pasref from original transaction");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Receipt-In

```java
PaymentData paymentData = new PaymentData()
  	.addCvnNumber("123");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.RECEIPT_IN)
 	.addAmount(100)
 	.addCurrency("EUR")
 	.addPayerReference("payer ref from customer")
 	.addPaymentMethod("payment method ref from customer")
 	.addPaymentData(paymentData);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Payment-out

```java

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.PAYMENT_OUT) 	
 	.addAmount(100)
 	.addCurrency("EUR")
 	.addPayerReference("payer ref from customer")
 	.addPaymentMethod("payment method ref from customer")
 	.addRefundHash("Hash of rebate password shared with Realex");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Payer-new

```java

PayerAddress address = new PayerAddress()
    .addLine1("Apt 167 Block 10")
    .addLine2("The Hills")
    .addLine3("67-69 High St")
    .addCity("Hytown")
    .addCounty("Dunham")
    .addPostcode("3")
    .addCountryCode("IE")
    .addCountryName("Ireland");
    

Payer payer = new Payer()
    .addType("Business")
    .addRef("smithj01")
    .addTitle("Mr")
    .addFirstName("John")
    .addSurname("Smith")
    .addCompany("Acme")
    .addPayerAddress(address)
    .addHomePhoneNumber("+35317285355")
    .addWorkPhoneNumber("+35317433923")
    .addFaxPhoneNumber("+35317893248")
    .addMobilePhoneNumber("+353873748392")
    .addEmail("jsmith@acme.com")
    .addComment("Comment1")
    .addComment("Comment2");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.PAYER_NEW)  	
 	.addPayer(payer);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Payer-edit

```java

PayerAddress address = new PayerAddress()
    .addLine1("Apt 167 Block 10")
    .addLine2("The Hills")
    .addLine3("67-69 High St")
    .addCity("Hytown")
    .addCounty("Dunham")
    .addPostcode("3")
    .addCountryCode("IE")
    .addCountryName("Ireland");
    

Payer payer = new Payer()
    .addType("Business")
    .addRef("smithj01")
    .addTitle("Mr")
    .addFirstName("John")
    .addSurname("Smith")
    .addCompany("Acme")
    .addPayerAddress(address)
    .addHomePhoneNumber("+35317285355")
    .addWorkPhoneNumber("+35317433923")
    .addFaxPhoneNumber("+35317893248")
    .addMobilePhoneNumber("+353873748392")
    .addEmail("jsmith@acme.com")
    .addComment("Comment1")
    .addComment("Comment2");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.PAYER_EDIT)  	
 	.addPayer(payer);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Card-new

```java

Card card = new Card()
    .addReference("visa01")
    .addPayerReference("smithj01")
    .addNumber("420000000000000000")    
	.addExpiryDate("0119")	
	.addCardHolderName("Joe Smith")
	.addType(CardType.VISA)
	.addIssueNumber("1");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.CARD_NEW)  	
 	.addCard(card);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Card-edit

```java

Card card = new Card()
    .addReference("visa01")
    .addPayerReference("smithj01")
    .addNumber("420000000000000000")    
	.addExpiryDate("0119")	
	.addCardHolderName("Joe Smith")
	.addType(CardType.VISA)
	.addIssueNumber("1");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.CARD_UPDATE)  	
 	.addCard(card);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```


### Card-delete

```java

Card card = new Card()
    .addReference("visa01")
    .addPayerReference("smithj01");

PaymentRequest request = new PaymentRequest()
 	.addAccount("yourAccount")
 	.addMerchantId("yourMerchantId")
 	.addType(PaymentType.CARD_CANCEL)  	
 	.addCard(card);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### Verify Card Enrolled

```java

PaymentData paymentData = new PaymentData()
  	.addCvnNumber("123");


ThreeDSecureRequest request = new ThreeDSecureRequest()
  .addAccount("yourAccount")
  .addMerchantId("yourMerchantId")
  .addType(ThreeDSecureType.VERIFY_CARD_ENROLLED)
  .addAmount(100)
  .addCurrency("EUR")
  .addPayerReference("payer ref from customer")
  .addPaymentMethod("payment method ref from customer")
  .addPaymentData(paymentData)
  .addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE));
 	

RealexClient client = new RealexClient("shared secret");
ThreeDSecureResponse response = client.send(request);
```

### DCC Rate Lookup

```java

Card card = new Card()    
    .addNumber("420000000000000000")    
	.addExpiryDate("0119")	
	.addCardHolderName("Joe Smith")
	.addType(CardType.VISA);
	
DccInfo dccInfo = new DccInfo()
    .addDccProcessor("fexco");

PaymentRequest request = new PaymentRequest()
  .addAccount("yourAccount")
  .addMerchantId("yourMerchantId")
  .addType(PaymentType.DCC_RATE_LOOKUP)
  .addAmount(100)
  .addCurrency("EUR")
  .addCard(card)
  .addDccInfo(dccInfo);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```


### DCC + Auth

```java

Card card = new Card()    
    .addNumber("420000000000000000")    
	.addExpiryDate("0119")	
	.addCardHolderName("Joe Smith")
	.addType(CardType.VISA);
	
DccInfo dccInfo = new DccInfo()
    .addDccProcessor("fexco")
    .addRate(0.6868)
    .addAmount(13049)
    .addCurrency("GBP");

PaymentRequest request = new PaymentRequest()
  .addAccount("yourAccount")
  .addMerchantId("yourMerchantId")
  .addType(PaymentType.DCC_AUTH)
  .addAmount(19000)
  .addCurrency("EUR")
  .addCard(card)
  .addDccInfo(dccInfo);

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

### RECEIPT-IN OTB

```java
 
 PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.RECEIPT_IN_OTB)
	.addPayerReference("payer ref from customer")
    .addPaymentMethod("payment method ref from customer");
	
RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);	
```

## License
See the LICENSE file.
