

//定义闭包
//有个隐式 it 参数，即 不声明 任何参数，在方法调用时传参，通过 it 访问
//但是只要定义了 参数，it 就不能再访问
//闭包有返回值，如果不return返回值，默认就是 return null
//def clouser = { String name, int age ->
//    println("Hello ${name}")
//    return "hello"
//}
//执行闭包
//clouser.call('groovy', 0)
//def result = clouser('groovy', 1)

//闭包在 upto 中的使用
//闭包的参数的确定需要 进入 upto 方法的源码里确认
//因为闭包的调用是在 upto 方法的源码里调用的
//求指定number的阶乘
//int fab(int number){
//    int result = 1;
//    //从1到指定的数 number 都执行一次 闭包
//    //upto 的内部会调用 for 循环来执行 闭包
//    1.upto(number, {num -> result *= num})
//    return result;
//}
//println(fab(5))//5的阶乘是 120

//闭包在 downto 中的使用
//闭包 也可以拿到 括号 外面
//int fab2(int number){
//    int result = 1
//    number.downto(1){
//        num -> result *= num
//    }
//    return result;
//}

//闭包在 times 中的使用
//如果 times 没有参数，可以省略 ()
//其内部也是 for 循环，索引从 0 开始
//int time(int number){
//    int result = 0
//    number.times{
//        num -> result += num
//    }
//    return result
//}

//闭包在 each 中的使用
//其内部也是 for 循环，索引从 0 开始
//String str = 'the 2 and 3 is 5'
//str.each {
//    String temp -> print(temp)
//}

//闭包在 find 中的使用
//String str = 'the 2 and 3 is 5'
//str.find{
//    String temp -> temp.isNumber()
//}
//def list = str.findAll{
//    String temp -> temp.isNumber()
//}
//println list.toListString()

//闭包在 any 中的使用
//String str = 'the 2 and 3 is 5'
//def result = str.any{
//    String temp -> temp.isNumber()
//}

//闭包在 every 中的使用
//String str = 'the 2 and 3 is 5'
//def result = str.every{
//    String temp -> temp.isNumber()
//}

//闭包在 collect 中的使用
//String str = 'the 2 and 3 is 5'
//def list = str.collect{
//    it.toUpperCase()
//}

//闭包关键变量：
// this  代表闭包定义处的 类
// owner  代表闭包定义处的 类 或者 对象
// delegate  代表任意 对象，默认值同 owner
//总结：
//    1.通常情况下 this owner delegate 指向的是同一个对象
//    2.闭包嵌套闭包的情况下 this 指向外层闭包，owner delegate 指向内层闭包
//    3.this owner 不能修改，可以修改 delegate 指向 innerClouser.delegate = p
//def scriptClouser = {
//    println "this:" + this // 指向 Clouser 类
//    println "owner:" + owner // 指向 Clouser 类
//    println "delegate:" + delegate // 指向 Clouser 类
//}
//class classobject.Person{
//    def static classClouser = {
//        println "this:" + this // 指向 classobject.Person 类
//        println "owner:" + owner // 指向 classobject.Person 类
//        println "delegate:" + delegate // 指向 classobject.Person 类
//    }
//    def static say(){
//        def methodClouser = {
//            println "this:" + this // 指向 classobject.Person 类
//            println "owner:" + owner // 指向 classobject.Person 类
//            println "delegate:" + delegate // 指向 classobject.Person 类
//        }
//    }
//}
//classobject.Person p = new classobject.Person()
//def nestClouser = {
//    def innerClouser = {
//        println "this:" + this // 指向 nestClouser
//        println "owner:" + owner // 指向 innerClouser
//        println "delegate:" + delegate // 指向 p (默认指向 innerClouser)
//    }
//    innerClouser.delegate = p
//}

//闭包 委托 策略
//class Student{
//    String name
//    def pretty = {
//        "My name is ${name}"
//    }
//    String toString(){
//        pretty.call()
//    }
//}
//class Teacher {
//    String name
//}
//def stu = new Student(name: 'student')
//def tea = new Teacher(name: 'teacher')
////修改 委托 指向
//stu.pretty.delegate = tea
////修改 委托 策略 为 delegate优先(默认 OWNER_FIRST)
////意思就是先从 tea 中找name，如果没找到，再从 stu 中找name
//stu.pretty.resolveStrategy = Closure.DELEGATE_FIRST
//println stu.toString()











