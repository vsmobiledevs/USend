# AuthenticationApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**forgotPassword**](AuthenticationApi.md#forgotPassword) | **POST** oauth/forgot-password | Forgot Password
[**signIn**](AuthenticationApi.md#signIn) | **POST** oauth/signin | SignIn
[**signUp**](AuthenticationApi.md#signUp) | **POST** oauth/signup | SignUp
[**socialSignin**](AuthenticationApi.md#socialSignin) | **POST** oauth/social-signin | Social Signin


<a name="forgotPassword"></a>
# **forgotPassword**
> SuccessResponse forgotPassword(nonce, timestamp, token, vEmail, tiUserType)

Forgot Password

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vEmail = "yagnesh.spaceo@gmail.com"; // String | Email Id of user
String tiUserType = "1"; // String | User Type (1 - Customer ,2 - Business)
try {
    SuccessResponse result = apiInstance.forgotPassword(nonce, timestamp, token, vEmail, tiUserType);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#forgotPassword");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vEmail** | **String**| Email Id of user | [default to yagnesh.spaceo@gmail.com]
 **tiUserType** | **String**| User Type (1 - Customer ,2 - Business) | [default to 1]

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/xml, application/json

<a name="signIn"></a>
# **signIn**
> UserMasterResponse signIn(nonce, timestamp, token, vEmail, vPassword, tiUserType, tiDeviceType, vTimezone, vDeviceToken, vDeviceName)

SignIn

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vEmail = "yagnesh.spaceo@gmail.com"; // String | Email Id of user
String vPassword = "123456"; // String | Password of user
String tiUserType = "1"; // String | User Type (1 - Customer ,2 - Business)
String tiDeviceType = "0"; // String | DeviceType  1=>Android 2=> IOS 0=> Web
String vTimezone = ""; // String | Device Timezone
String vDeviceToken = ""; // String | Device Token of device
String vDeviceName = "iPhone X"; // String | Device Name of device
try {
    UserMasterResponse result = apiInstance.signIn(nonce, timestamp, token, vEmail, vPassword, tiUserType, tiDeviceType, vTimezone, vDeviceToken, vDeviceName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#signIn");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vEmail** | **String**| Email Id of user | [default to yagnesh.spaceo@gmail.com]
 **vPassword** | **String**| Password of user | [default to 123456]
 **tiUserType** | **String**| User Type (1 - Customer ,2 - Business) | [default to 1]
 **tiDeviceType** | **String**| DeviceType  1&#x3D;&gt;Android 2&#x3D;&gt; IOS 0&#x3D;&gt; Web | [default to 0]
 **vTimezone** | **String**| Device Timezone | [optional] [default to ]
 **vDeviceToken** | **String**| Device Token of device | [optional] [default to ]
 **vDeviceName** | **String**| Device Name of device | [optional] [default to iPhone X]

### Return type

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="signUp"></a>
# **signUp**
> UserMasterResponse signUp(nonce, timestamp, token, vName, vEmail, vPassword, tiDeviceType, vProfilePic, vTimezone, vDeviceToken, vDeviceName)

SignUp

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vName = "vb"; // String | First Name of user
String vEmail = "yagnesh.spaceo@gmail.com"; // String | Email Id of user
String vPassword = "123456"; // String | Password of user
String tiDeviceType = "0"; // String | DeviceType  1=>Android 2=> IOS 0=> Web
File vProfilePic = new File(""); // File | Profile Pic of user
String vTimezone = ""; // String | Device Timezone
String vDeviceToken = ""; // String | Device Token of device
String vDeviceName = "iPhone X"; // String | Device Name of device
try {
    UserMasterResponse result = apiInstance.signUp(nonce, timestamp, token, vName, vEmail, vPassword, tiDeviceType, vProfilePic, vTimezone, vDeviceToken, vDeviceName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#signUp");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vName** | **String**| First Name of user | [default to vb]
 **vEmail** | **String**| Email Id of user | [default to yagnesh.spaceo@gmail.com]
 **vPassword** | **String**| Password of user | [default to 123456]
 **tiDeviceType** | **String**| DeviceType  1&#x3D;&gt;Android 2&#x3D;&gt; IOS 0&#x3D;&gt; Web | [default to 0]
 **vProfilePic** | **File**| Profile Pic of user | [optional] [default to ]
 **vTimezone** | **String**| Device Timezone | [optional] [default to ]
 **vDeviceToken** | **String**| Device Token of device | [optional] [default to ]
 **vDeviceName** | **String**| Device Name of device | [optional] [default to iPhone X]

### Return type

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="socialSignin"></a>
# **socialSignin**
> UserMasterResponse socialSignin(nonce, timestamp, token, accessToken, service, tiDeviceType, vTimezone, vDeviceToken, vDeviceName)

Social Signin

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String accessToken = ""; // String | accessToken of social 
String service = "facebook"; // String | service like facebook,google
String tiDeviceType = "0"; // String | DeviceType  1=>Android 2=> IOS 0=> Web
String vTimezone = ""; // String | Device Timezone
String vDeviceToken = ""; // String | Device Token of device
String vDeviceName = "iPhone X"; // String | Device Name of device
try {
    UserMasterResponse result = apiInstance.socialSignin(nonce, timestamp, token, accessToken, service, tiDeviceType, vTimezone, vDeviceToken, vDeviceName);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#socialSignin");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **accessToken** | **String**| accessToken of social  | [default to ]
 **service** | **String**| service like facebook,google | [default to facebook]
 **tiDeviceType** | **String**| DeviceType  1&#x3D;&gt;Android 2&#x3D;&gt; IOS 0&#x3D;&gt; Web | [default to 0]
 **vTimezone** | **String**| Device Timezone | [optional] [default to ]
 **vDeviceToken** | **String**| Device Token of device | [optional] [default to ]
 **vDeviceName** | **String**| Device Name of device | [optional] [default to iPhone X]

### Return type

[**UserMasterResponse**](UserMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/xml, application/json

