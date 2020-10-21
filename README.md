## Parser flashdeals.aliexpress.com

### How to use

---



_Configuration in `Properties.java`_

```java
    private static final int OFFSET = 10; // number of files in per page
    public static final int PAGES = 10; // for paginate
    public static final String FILENAME = "result.csv"; // name of result file
```

change if you need... then run

## How it work

1. Load number of `OFFSET` (products) in `sendRequest`
2. Convert to Array in `createArraysWithElements`
3. Then save to file in `saveFile`
