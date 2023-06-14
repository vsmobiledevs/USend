# BlogApi

All URIs are relative to *http://172.16.17.123/basecode_7/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addBlog**](BlogApi.md#addBlog) | **POST** blog/create | Add Blog
[**deleteBlog**](BlogApi.md#deleteBlog) | **DELETE** blog/delete | Delete Blog
[**getBlogList**](BlogApi.md#getBlogList) | **GET** blog | Get Blog List
[**updateBlog**](BlogApi.md#updateBlog) | **PUT** blog/update | Update Blog
[**viewBlog**](BlogApi.md#viewBlog) | **GET** blog/view/{id} | View Blog


<a name="addBlog"></a>
# **addBlog**
> BlogMasterResponse addBlog(contentType, nonce, timestamp, token, authorization, vTitle, teImage, teDescription)

Add Blog

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.BlogApi;


BlogApi apiInstance = new BlogApi();
String contentType = "application/x-www-form-urlencoded"; // String | 
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "38ddb0194783f060ef9058f9239db8ad"; // String | 
String vTitle = "test"; // String | Title
File teImage = new File(""); // File | Blog image
String teDescription = "Blog description"; // String | Blog description
try {
    BlogMasterResponse result = apiInstance.addBlog(contentType, nonce, timestamp, token, authorization, vTitle, teImage, teDescription);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BlogApi#addBlog");
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
 **authorization** | **String**|  | [default to 38ddb0194783f060ef9058f9239db8ad]
 **vTitle** | **String**| Title | [default to test]
 **teImage** | **File**| Blog image | [default to ]
 **teDescription** | **String**| Blog description | [default to Blog description]

### Return type

[**BlogMasterResponse**](BlogMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="deleteBlog"></a>
# **deleteBlog**
> BlogMasterResponse deleteBlog(nonce, timestamp, token, authorization, id)

Delete Blog

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.BlogApi;


BlogApi apiInstance = new BlogApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "38ddb0194783f060ef9058f9239db8ad"; // String | 
Int id = new Int(); // Int | Blog id
try {
    BlogMasterResponse result = apiInstance.deleteBlog(nonce, timestamp, token, authorization, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BlogApi#deleteBlog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b]
 **authorization** | **String**|  | [default to 38ddb0194783f060ef9058f9239db8ad]
 **id** | **Int**| Blog id | [default to 1]

### Return type

[**BlogMasterResponse**](BlogMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a name="getBlogList"></a>
# **getBlogList**
> BlogMasterResponse getBlogList(contentType, nonce, timestamp, token, authorization)

Get Blog List

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.BlogApi;


BlogApi apiInstance = new BlogApi();
String contentType = "application/x-www-form-urlencoded"; // String | 
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "38ddb0194783f060ef9058f9239db8ad"; // String | 
try {
    BlogMasterResponse result = apiInstance.getBlogList(contentType, nonce, timestamp, token, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BlogApi#getBlogList");
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
 **authorization** | **String**|  | [default to 38ddb0194783f060ef9058f9239db8ad]

### Return type

[**BlogMasterResponse**](BlogMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="updateBlog"></a>
# **updateBlog**
> BlogMasterResponse updateBlog(nonce, timestamp, token, authorization, id, vTitle, teImage, teDescription)

Update Blog

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.BlogApi;


BlogApi apiInstance = new BlogApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "38ddb0194783f060ef9058f9239db8ad"; // String | 
Int id = new Int(); // Int | User id
String vTitle = "test"; // String | Title
File teImage = new File(""); // File | Blog image
String teDescription = "Blog description"; // String | Blog description
try {
    BlogMasterResponse result = apiInstance.updateBlog(nonce, timestamp, token, authorization, id, vTitle, teImage, teDescription);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BlogApi#updateBlog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b]
 **authorization** | **String**|  | [default to 38ddb0194783f060ef9058f9239db8ad]
 **id** | **Int**| User id | [default to 1]
 **vTitle** | **String**| Title | [default to test]
 **teImage** | **File**| Blog image | [default to ]
 **teDescription** | **String**| Blog description | [optional] [default to Blog description]

### Return type

[**BlogMasterResponse**](BlogMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="viewBlog"></a>
# **viewBlog**
> BlogMasterResponse viewBlog(nonce, timestamp, token, authorization, id)

View Blog

### Example
```java
// Import classes:
//import com.base.network.ApiException;
//import com.base.network.api.BlogApi;


BlogApi apiInstance = new BlogApi();
String nonce = "123456"; // String | 
String timestamp = "123456"; // String | 
String token = "8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b"; // String | 
String authorization = "38ddb0194783f060ef9058f9239db8ad"; // String | 
Int id = new Int(); // Int | Blog id
try {
    BlogMasterResponse result = apiInstance.viewBlog(nonce, timestamp, token, authorization, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BlogApi#viewBlog");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nonce** | **String**|  | [default to 123456]
 **timestamp** | **String**|  | [default to 123456]
 **token** | **String**|  | [default to 8d7323a8cedca2664f13a9ed4a7d6e51ee9b36878500a793545d9324e838aa3b]
 **authorization** | **String**|  | [default to 38ddb0194783f060ef9058f9239db8ad]
 **id** | **Int**| Blog id |

### Return type

[**BlogMasterResponse**](BlogMasterResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

