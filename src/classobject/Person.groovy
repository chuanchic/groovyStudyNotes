package classobject

//groovy 中的类默认是 public 类型
class Person implements PersonAction{
    //groovy 中的变量默认是 public 类型
    String name
    Integer age

    @Override
    void eat() {
    }

    @Override
    void drink() {
    }

    @Override
    void play() {
    }

    //在 groovy 中，调用类的某个方法，在 运行期 执行的过程：
    //1. 类中有此方法，调用类中方法
    //   类中没有此方法，MetaClass中是否有此方法
    //2. MetaClass中有此方法，调用MetaClass中方法
    //   MetaClass中没有此方法，是否重写了 methodMissing() 方法
    //3. 重写了 methodMissing() 方法，调用 methodMissing() 方法
    //   没有重写 methodMissing() 方法，是否重写了 invokeMethod() 方法
    //4. 重写了 invokeMethod() 方法，调用 invokeMethod() 方法
    //   没有重写 invokeMethod() 方法，Throw MissingMethodException

    //name：方法名
    //args：方法参数
    def invokeMethod(String name, Object args){
        return "the method is ${name}, the params is ${args}"
    }

    //name：方法名
    //args：方法参数
    def methodMissing(String name, Object args){
        return "the method is ${name}, the params is ${args}"
    }

}
