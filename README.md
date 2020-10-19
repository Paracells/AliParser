## Parser flashdeals.aliexpress.com

### How to use

---

**IMPORTANT** Need Java 11 for [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)

_Configuration_

```java
public static final int NUMBER_OF_PROPERTIES = 23; // number of properties, this you can find if open page in postman
    private static final int OFFSET = 10; // number of files in per page
    public static final int PAGES = 11; // for paginate
    public static final String FILENAME = "result.csv"; // name of result file
```

change if you need... then run

## How it work

1. Load number of `OFFSET` (products) in `sendRequest`
2. Convert to Array in `createArraysWithElements`
3. Then save to file in `saveFile`
