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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
PaymentRequest request = new PaymentRequest()
	.addAccount("yourAccount")
	.addMerchantId("yourMerchantId")
	.addType(PaymentType.RELEASE)
	.addOrderId("Order ID from original transaction")
	.addPaymentsReference("Pasref from original transaction");

RealexClient client = new RealexClient("shared secret");
PaymentResponse response = client.send(request);
```

## License
See the LICENSE file.
