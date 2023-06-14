# StaticPagesApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getStaticPageDetail**](StaticPagesApi.md#getStaticPageDetail) | **GET** static-pages/{vPageSlug} | Get Static Page Detail
[**getStaticPageList**](StaticPagesApi.md#getStaticPageList) | **GET** static-pages | Get Static Page List


<a name="getStaticPageDetail"></a>
# **getStaticPageDetail**
> StaticPageResponse getStaticPageDetail(nonce, timestamp, token, vPageSlug)

Get Static Page Detail

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.StaticPagesApi;


StaticPagesApi apiInstance = new StaticPagesApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
String vPageSlug = "vPageSlug_example"; // String | Page Slug
try {
    StaticPageResponse result = apiInstance.getStaticPageDetail(nonce, timestamp, token, vPageSlug);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling StaticPagesApi#getStaticPageDetail");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]
 **vPageSlug** | **String**| Page Slug |

### Return type

[**StaticPageResponse**](StaticPageResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="getStaticPageList"></a>
# **getStaticPageList**
> StaticPageListResponse getStaticPageList(nonce, timestamp, token)

Get Static Page List

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.StaticPagesApi;


StaticPagesApi apiInstance = new StaticPagesApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238"; // String | 
try {
    StaticPageListResponse result = apiInstance.getStaticPageList(nonce, timestamp, token);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling StaticPagesApi#getStaticPageList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 57f0cb275421171c95294764476df124ebd0b3ccebca603cab5cec795c480238]

### Return type

[**StaticPageListResponse**](StaticPageListResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

