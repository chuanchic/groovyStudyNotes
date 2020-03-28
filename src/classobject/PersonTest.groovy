package classobject

Person person = new Person(name: 'haha', age: 18)

//通过 . 操作符最终调用的是 getName
println("name:${person.name}")

//设置 动态 添加的属性和方法 全局可用
ExpandoMetaClass.enableGlobally()
//通过 metaClass 为类动态(运行时)添加属性
Person.metaClass.sex = 'male'
def person2 = new Person(name: 'haode', age: 17)
println person2.sex
//为类动态添加方法
Person.metaClass.sexUpperCase = {->
    sex.toUpperCase()
}
def person3 = new Person(name: 'hehe', age: 15)
println person3.sexUpperCase()
//为类动态添加静态方法
Person.metaClass.static.createPerson = {String name, int age ->
    new Person(name: name, age: age)
}
def person4 = Person.createPerson('aa', 12)
println person4.name
