# UserApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addMobileNumber**](UserApi.md#addMobileNumber) | **POST** user/add-mobile-number | Add Mobile Number
[**changePassword**](UserApi.md#changePassword) | **POST** user/change-password | Change Password
[**contactUs**](UserApi.md#contactUs) | **POST** user/contact-us | Contact Us
[**editProfile**](UserApi.md#editProfile) | **POST** user/edit-profile | Edit Profile
[**myProfile**](UserApi.md#myProfile) | **GET** user/profile | User Profile
[**resendOTP**](UserApi.md#resendOTP) | **POST** user/resend-otp | Resend OTP
[**signOut**](UserApi.md#signOut) | **POST** user/sign-out | User Sign Out
[**verifyOTP**](UserApi.md#verifyOTP) | **POST** user/verify-otp | Verify OTP


<a name="addMobileNumber"></a>
# **addMobileNumber**
> SuccessResponse addMobileNumber(nonce, timestamp, token, authorization, vISDCode, vMobileNumber)

Add Mobile Number

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String vISDCode = "+91"; // String | ISD Code of mobile number
String vMobileNumber = "7201969694"; // String | Mobile Number of user
try {
    SuccessResponse result = apiInstance.addMobileNumber(nonce, timestamp, token, authorization, vISDCode, vMobileNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#addMobileNumber");
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
 **vISDCode** | **String**| ISD Code of mobile number | [default to +91]
 **vMobileNumber** | **String**| Mobile Number of user | [default to 7201969694]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="changePassword"></a>
# **changePassword**
> SuccessResponse changePassword(nonce, timestamp, token, authorization, currentPassword, vNewPassword, confirmPassword)

Change Password

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String currentPassword = "123456"; // String | Current Password
String vNewPassword = "123456"; // String | New Password
String confirmPassword = "1234567"; // String | Confirm Password
try {
    SuccessResponse result = apiInstance.changePassword(nonce, timestamp, token, authorization, currentPassword, vNewPassword, confirmPassword);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#changePassword");
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
 **currentPassword** | **String**| Current Password | [default to 123456]
 **vNewPassword** | **String**| New Password | [default to 123456]
 **confirmPassword** | **String**| Confirm Password | [default to 1234567]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="contactUs"></a>
# **contactUs**
> SuccessResponse contactUs(nonce, timestamp, token, authorization, vName, vEmail, vSubject, vMessage)

Contact Us

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String vName = "VB"; // String | Name
String vEmail = "yagnesh.spaceo@gmail.com"; // String | Email Id
String vSubject = "+91"; // String | Subject
String vMessage = "+91"; // String | Message
try {
    SuccessResponse result = apiInstance.contactUs(nonce, timestamp, token, authorization, vName, vEmail, vSubject, vMessage);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#contactUs");
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
 **vName** | **String**| Name | [default to VB]
 **vEmail** | **String**| Email Id | [default to yagnesh.spaceo@gmail.com]
 **vSubject** | **String**| Subject | [default to +91]
 **vMessage** | **String**| Message | [default to +91]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="editProfile"></a>
# **editProfile**
> UserMasterResponse editProfile(nonce, timestamp, token, authorization, vName, vEmail, vNewISDCode, vNewMobileNumber, vProfilePic)

Edit Profile

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
String vName = "VB"; // String | Name
String vEmail = "yagnesh.spaceo@gmail.com"; // String | Email Id
String vNewISDCode = "+91"; // String | ISD Code
String vNewMobileNumber = "7201969694"; // String | Mobile Number
File vProfilePic = new File("/path/to/file.txt"); // File | Profile Pic
try {
    UserMasterResponse result = apiInstance.editProfile(nonce, timestamp, token, authorization, vName, vEmail, vNewISDCode, vNewMobileNumber, vProfilePic);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#editProfile");
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
 **vName** | **String**| Name | [default to VB]
 **vEmail** | **String**| Email Id | [default to yagnesh.spaceo@gmail.com]
 **vNewISDCode** | **String**| ISD Code | [default to +91]
 **vNewMobileNumber** | **String**| Mobile Number | [default to 7201969694]
 **vProfilePic** | **File**| Profile Pic | [optional]

### Return type

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="myProfile"></a>
# **myProfile**
> UserMasterResponse myProfile(nonce, timestamp, token, authorization)

User Profile

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
try {
    UserMasterResponse result = apiInstance.myProfile(nonce, timestamp, token, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#myProfile");
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

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="resendOTP"></a>
# **resendOTP**
> SuccessResponse resendOTP(nonce, timestamp, token, vMobileNumber)

Resend OTP

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vMobileNumber = "7201969694"; // String | Mobile Number of user
try {
    SuccessResponse result = apiInstance.resendOTP(nonce, timestamp, token, vMobileNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#resendOTP");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vMobileNumber** | **String**| Mobile Number of user | [default to 7201969694]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="signOut"></a>
# **signOut**
> SuccessResponse signOut(nonce, timestamp, token, authorization)

User Sign Out

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String authorization = "3738b45dad593a0e180274bbe11ecffe"; // String | 
try {
    SuccessResponse result = apiInstance.signOut(nonce, timestamp, token, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#signOut");
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

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="verifyOTP"></a>
# **verifyOTP**
> UserMasterResponse verifyOTP(nonce, timestamp, token, vMobileNumber, vOTP, tiDeviceType, vDeviceToken, vDeviceName)

Verify OTP

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.UserApi;


UserApi apiInstance = new UserApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vMobileNumber = "7201969694"; // String | Mobile Number of user
String vOTP = "123456"; // String | one time pin
String tiDeviceType = "1"; // String | DeviceType 0-Web, 1-IOS, 2-Android
String vDeviceToken = "123456"; // String | Device Token of device
String vDeviceName = "iPhone X"; // String | Device Name of device
try {
    UserMasterResponse result = apiInstance.verifyOTP(nonce, timestamp, token, vMobileNumber, vOTP, tiDeviceType, vDeviceToken, vDeviceName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#verifyOTP");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vMobileNumber** | **String**| Mobile Number of user | [default to 7201969694]
 **vOTP** | **String**| one time pin | [default to 123456]
 **tiDeviceType** | **String**| DeviceType 0-Web, 1-IOS, 2-Android | [default to 1]
 **vDeviceToken** | **String**| Device Token of device | [optional] [default to 123456]
 **vDeviceName** | **String**| Device Name of device | [optional] [default to iPhone X]

### Return type

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

