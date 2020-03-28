import classobject.Person
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

//Json 操作
def list = [
        new Person(name: '张三', age: 18),
        new Person(name:'李四', age: 19),
        new Person(name:'王五', age: 20)
]
def json = JsonOutput.toJson(list)
//prettyPrint：格式化 json 字符串
println JsonOutput.prettyPrint(json)
//将数据转成 json 对象
def jsonSlurper = new JsonSlurper()
println jsonSlurper.parse(json)





