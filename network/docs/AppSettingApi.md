# AppSettingApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAppSettingList**](AppSettingApi.md#getAppSettingList) | **GET** app-setting | Get App Setting List


<a name="getAppSettingList"></a>
# **getAppSettingList**
> AppSettingResponse getAppSettingList(contentType, nonce, timestamp, token, authorization)

Get App Setting List

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.AppSettingApi;


AppSettingApi apiInstance = new AppSettingApi();
String contentType = "application/x-www-form-urlencoded"; // String | 
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "c0dda345c4713a71fd31d5d9b6d5e212"; // String | 
try {
    AppSettingResponse result = apiInstance.getAppSettingList(contentType, nonce, timestamp, token, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AppSettingApi#getAppSettingList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **contentType** | **String**|  | [default to application/x-www-form-urlencoded]
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b]
 **authorization** | **String**|  | [optional] [default to c0dda345c4713a71fd31d5d9b6d5e212]

### Return type

[**AppSettingResponse**](AppSettingResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

