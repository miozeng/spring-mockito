# spring-mockito
基于mockito的测试用例</br>   包括mockito与powermock的结合使用，和spring的结合使用    
Easymock
http://easymock.org/
Easymock可以用来模拟接口和类，但是不能模拟静态类，final类等。
下面是官方给出的使用例子
1.1.添加jar包依赖
<dependency>
  <groupId>org.easymock</groupId>
  <artifactId>easymock</artifactId>
  <version>3.4</version>
  <scope>test</scope></dependency>
1.2我们需要模拟的类
public interface Collaborator {
    void documentAdded(String title);}
public class ClassTested {

    private Collaborator listener;
    // ...
    public void setListener(Collaborator listener) {
        this.listener = listener;
    }
    public void addDocument(String title, String document) {
        // ...
    }}
1.3测试
import static org.easymock.EasyMock.*;import org.easymock.*;import org.junit.Rule;import org.junit.Test;
public class ExampleTest extends EasyMockSupport {
    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
    @Mock
    private Collaborator collaborator; // 1
    @TestSubject
    private final ClassTested classUnderTest = new ClassTested(); // 2
    @Test
    public void addDocument() {
        collaborator.documentAdded("New Document"); // 3
        replayAll(); // 4
        classUnderTest.addDocument("New Document", "content"); // 5
        verifyAll(); // 6
    }}
1.Create the mock
2.Have it set to the tested class
3.Record what we expect the mock to do
4.Tell all mocks we are now doing the actual testing
5.Test
6.Make sure everything that was supposed to be called was called


用于在测试中做模拟
Mockito
http://mockito.org/
Mockito是一个和easymock类似的模拟框架
2.1添加依赖
  <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>${mockito1.version}</version>
            <scope>test</scope>
        </dependency>
2.2测试
// you can mock concrete classes, not only interfaces 
LinkedList mockedList = mock(LinkedList.class); 
// stubbing appears before the actual execution when(mockedList.get(0)).thenReturn("first"); 
// the following prints "first" System.out.println(mockedList.get(0)); 
// the following prints "null" because get(999) was not stubbed System.out.println(mockedList.get(999));
https://github.com/mockito/mockito/wiki/FAQ这里可以查看更多你对mockito的疑问

Powermock
Powermock是对easymock和mockitto的扩展
https://github.com/jayway/powermock/wiki/GettingStarted从此处入手可以一步步学习powermock的使用
Easymock+spring+powermock
请看附件例子
Mockito+spring+powermock
请看附件例子。

注意：
Momockito2需要powermock1.6.5以上的版本才支持，但是目前powermock只出到1.6.5版本，然而1.6.5的版本的powermock会抛出找不到org.powermock.api.extension.reporter.MockingFrameworkReporterFactoryImpl的异常官方说可以手动导入powermock-api-mockito-common-1.6.5.jar ，可是我尝试后还是失败。所以要只能用1.6.4使用Momockito1
注意2： 在集合powermock和spring的时候尽量不要使用@Rule的方式，因为他看上去有用，实际上可能没用。
