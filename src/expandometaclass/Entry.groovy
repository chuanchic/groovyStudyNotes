package expandometaclass

//模拟 入口 类
class Entry {

    static void main(def args){
        println '应用程序正在启动...'
        ApplicationManager.init()//初始化
        println '应用程序初始化完成...'

        Person person = PersonManager.createPerson('haha', 18)
        println person.name
    }

}
