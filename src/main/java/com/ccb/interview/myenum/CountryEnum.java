package com.ccb.interview.myenum;

/**
 * 枚举可以当作一个小型的数据库来使用，这样使用起来，外部就不用做很多逻辑判断写死代码，
 * 直接取枚举中的数据即可
 */
public enum CountryEnum {

    ONE(1, "齐国"),
    TWO(2, "楚国"),
    THREE(3, "燕国"),
    FOUR(4, "赵国"),
    FIVE(5, "魏国"),
    SIX(6, "韩国");

    private int code;
    private String country;

    CountryEnum(int code, String country) {
        this.code = code;
        this.country = country;
    }

    public static String get(int index) {
        CountryEnum[] countries = CountryEnum.values();
        for (CountryEnum country : countries) {
            if (country.code == index) {
                return country.country;
            }
        }
        return null;
    }
}
