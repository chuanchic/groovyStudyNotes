
// settings.gradle 对应 Settings 类
include ':app', ':xxx'
include ':xxx'
if(hasProperty('isLoadTest') ? isLoadTest.toBoolean : false){
    include ':Test'
}
