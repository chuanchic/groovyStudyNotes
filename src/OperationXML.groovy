import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper

//xml 操作
final String xml = '''
    <response version-api="2.0">
        <value>
            <books id="1" classification="android">
                <book available="20" id="1">
                    <title>疯狂Android讲义</title>
                    <author id="1">李刚</author>
                </book>
                <book available="14" id="2">
                    <title>疯狂Android讲义2</title>
                    <author id="2">李刚2</author>
                </book>
            </books>
        </value>
    </response>       
'''
def xmlSlurper = new XmlSlurper()
def response = xmlSlurper.parseText(xml)
println response.value.books[0].book[0].title.text()
println response.value.books[0].book[0].@available

//遍历
//方式一：普通遍历
def list = []
response.value.books.each{books ->
    books.book.each{book ->
        def author = book.author.text()
        if(author.equals('李刚')){
            list.add(book)
        }
    }
}
println list.toListString()
//方式二：深度遍历，对根节点进行递归遍历
def list2 = response.depthFirst().findAll {book ->
    return book.author.text() == '李刚' ? true : false
}
println list2.toListString()
//方式三：广度遍历
def names = response.value.books.children().findAll{node ->
    node.name() == 'book' && node.@id == '2'
}.collect{node ->
    return node.title.text()
}
println names

//生成 xml 数据
class Language{
    String flavor
    String version
    String value
}
class Langs{
    String type = 'current'
    int count = 3
    boolean mainstream = true
    def language = [
            new Language(flavor: 'static', version: '1.5', value: 'Java'),
            new Language(flavor: 'static', version: '1.5', value: 'Java'),
            new Language(flavor: 'static', version: '1.5', value: 'Java')
    ]
}
def langs = new Langs()
def sw = new StringWriter()//数据会被写入 StringWriter 里
def xmlBuilder = new MarkupBuilder(sw)//生成xml数据的核心类
xmlBuilder.langs(type: langs.type, count: langs.count, mainstream: langs.mainstream){
    langs.language.each {lang ->
        language(flavor: lang.flavor, version: lang.version, lang.value)
    }
}
println sw













