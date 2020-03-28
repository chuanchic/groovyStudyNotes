package expandometaclass

//模拟 应用程序 管理类
class ApplicationManager {

    static void init(){
        //设置 动态 添加的属性和方法 全局可用
        ExpandoMetaClass.enableGlobally()
        //为第三方类添加方法
        Person.metaClass.static.createPerson = {String name, int age ->
            new Person(name: name, age: age)
        }
    }

}
