# UserCardsApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addUserCard**](UserCardsApi.md#addUserCard) | **POST** user-card | Add User Card
[**deleteUserCard**](UserCardsApi.md#deleteUserCard) | **DELETE** user-card/{iUserCardId} | Delete User Card
[**getUserCardList**](UserCardsApi.md#getUserCardList) | **GET** user-card | Get User Card List
[**setDefaultCard**](UserCardsApi.md#setDefaultCard) | **POST** user-card/set-default-card | Set Default Card


<a name="addUserCard"></a>
# **addUserCard**
> UserCardResponse addUserCard(nonce, timestamp, token, authorization, vCardToken)

Add User Card

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserCardsApi;


UserCardsApi apiInstance = new UserCardsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String vCardToken = "tok_visa"; // String | Stripe Token
try {
    UserCardResponse result = apiInstance.addUserCard(nonce, timestamp, token, authorization, vCardToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserCardsApi#addUserCard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **authorization** | **String**|  | [default to 3738b45dad593a0e180274bbe11ecffe]
 **vCardToken** | **String**| Stripe Token | [default to tok_visa]

### Return type

[**UserCardResponse**](UserCardResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="deleteUserCard"></a>
# **deleteUserCard**
> SuccessResponse deleteUserCard(nonce, timestamp, token, authorization, iUserCardId)

Delete User Card

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserCardsApi;


UserCardsApi apiInstance = new UserCardsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
Int iUserCardId = new Int(); // Int | User Card Id
try {
    SuccessResponse result = apiInstance.deleteUserCard(nonce, timestamp, token, authorization, iUserCardId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserCardsApi#deleteUserCard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **authorization** | **String**|  | [default to 3738b45dad593a0e180274bbe11ecffe]
 **iUserCardId** | **Int**| User Card Id |

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="getUserCardList"></a>
# **getUserCardList**
> UserCardListResponse getUserCardList(nonce, timestamp, token, authorization)

Get User Card List

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserCardsApi;


UserCardsApi apiInstance = new UserCardsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
try {
    UserCardListResponse result = apiInstance.getUserCardList(nonce, timestamp, token, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserCardsApi#getUserCardList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **authorization** | **String**|  | [default to 3738b45dad593a0e180274bbe11ecffe]

### Return type

[**UserCardListResponse**](UserCardListResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="setDefaultCard"></a>
# **setDefaultCard**
> UserCardResponse setDefaultCard(nonce, timestamp, token, authorization, iUserCardId)

Set Default Card

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserCardsApi;


UserCardsApi apiInstance = new UserCardsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
Int iUserCardId = new Int(); // Int | User Card Id
try {
    UserCardResponse result = apiInstance.setDefaultCard(nonce, timestamp, token, authorization, iUserCardId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserCardsApi#setDefaultCard");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **authorization** | **String**|  | [default to 3738b45dad593a0e180274bbe11ecffe]
 **iUserCardId** | **Int**| User Card Id | [default to 1]

### Return type

[**UserCardResponse**](UserCardResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

