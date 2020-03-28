package classobject
//与 interface 不同的是可以有 默认实现
trait PersonDefaultAction {

    abstract void eat()

    void play(){
        println 'i can play'
    }

}