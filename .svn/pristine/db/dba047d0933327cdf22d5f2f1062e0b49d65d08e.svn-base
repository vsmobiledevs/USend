# UserNotificationsApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteUserNotification**](UserNotificationsApi.md#deleteUserNotification) | **POST** notification/delete | Delete User Notification
[**getUserNotificationList**](UserNotificationsApi.md#getUserNotificationList) | **GET** notification | Get User Notification List
[**readUserNotification**](UserNotificationsApi.md#readUserNotification) | **POST** notification/read | Read User Notification


<a name="deleteUserNotification"></a>
# **deleteUserNotification**
> SuccessResponse deleteUserNotification(nonce, timestamp, token, authorization, iUserNotificationIds)

Delete User Notification

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserNotificationsApi;


UserNotificationsApi apiInstance = new UserNotificationsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String iUserNotificationIds = "1"; // String | Notification Id List Like '1,2,3'
try {
    SuccessResponse result = apiInstance.deleteUserNotification(nonce, timestamp, token, authorization, iUserNotificationIds);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserNotificationsApi#deleteUserNotification");
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
 **iUserNotificationIds** | **String**| Notification Id List Like &#39;1,2,3&#39; | [optional] [default to 1]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="getUserNotificationList"></a>
# **getUserNotificationList**
> UserNotificationPaginationResponse getUserNotificationList(nonce, timestamp, token, authorization, page)

Get User Notification List

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserNotificationsApi;


UserNotificationsApi apiInstance = new UserNotificationsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
Int page = new Int(); // Int | Page No Like '0,1,2,3' , -1 for all
try {
    UserNotificationPaginationResponse result = apiInstance.getUserNotificationList(nonce, timestamp, token, authorization, page);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserNotificationsApi#getUserNotificationList");
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
 **page** | **Int**| Page No Like &#39;0,1,2,3&#39; , -1 for all | [optional] [default to -1]

### Return type

[**UserNotificationPaginationResponse**](UserNotificationPaginationResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="readUserNotification"></a>
# **readUserNotification**
> SuccessResponse readUserNotification(nonce, timestamp, token, authorization, iUserNotificationIds)

Read User Notification

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserNotificationsApi;


UserNotificationsApi apiInstance = new UserNotificationsApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String iUserNotificationIds = "1"; // String | Notification Id List Like '1,2,3'
try {
    SuccessResponse result = apiInstance.readUserNotification(nonce, timestamp, token, authorization, iUserNotificationIds);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserNotificationsApi#readUserNotification");
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
 **iUserNotificationIds** | **String**| Notification Id List Like &#39;1,2,3&#39; | [default to 1]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

