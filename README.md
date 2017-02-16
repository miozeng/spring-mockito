# spring-mockito
基于mockito的测试用例</br>  
包括mockito与powermock的结合使用，和spring的结合使用   </br> 
##Easymock
http://easymock.org/</br>
Easymock可以用来模拟接口和类，但是不能模拟静态类，final类等。</br>
下面是官方给出的使用例子</br>
1.1.添加jar包依赖</br>
<dependency></br>
  <groupId>org.easymock</groupId></br>
  <artifactId>easymock</artifactId></br>
  <version>3.4</version></br>
  <scope>test</scope></dependency></br>
1.2我们需要模拟的类</br>
public interface Collaborator {</br>
    void documentAdded(String title);}</br>
public class ClassTested {</br>

    private Collaborator listener;</br>
    // ...
    public void setListener(Collaborator listener) {</br>
        this.listener = listener;</br>
    }</br>
    public void addDocument(String title, String document) {</br>
        // ...</br>
    }}</br>
1.3测试</br>
import static org.easymock.EasyMock.*;import org.easymock.*;import org.junit.Rule;import org.junit.Test;</br>
public class ExampleTest extends EasyMockSupport {</br>
    @Rule</br>
    public EasyMockRule rule = new EasyMockRule(this);</br>
    @Mock</br>
    private Collaborator collaborator; // 1</br>
    @TestSubject</br>
    private final ClassTested classUnderTest = new ClassTested(); // 2</br>
    @Test</br>
    public void addDocument() {</br>
        collaborator.documentAdded("New Document"); // 3</br>
        replayAll(); // 4</br>
        classUnderTest.addDocument("New Document", "content"); // 5</br>
        verifyAll(); // 6</br>
    }}</br>
1.Create the mock</br>
2.Have it set to the tested class</br>
3.Record what we expect the mock to do</br>
4.Tell all mocks we are now doing the actual testing</br>
5.Test</br>
6.Make sure everything that was supposed to be called was called</br>

##Mockito</br>
http://mockito.org/</br>
Mockito是一个和easymock类似的模拟框架</br>
2.1添加依赖</br>
  <dependency></br>
            <groupId>org.mockito</groupId></br>
            <artifactId>mockito-core</artifactId></br>
            <version>${mockito1.version}</version></br>
            <scope>test</scope></br>
        </dependency></br>
2.2测试</br>
// you can mock concrete classes, not only interfaces </br>
LinkedList mockedList = mock(LinkedList.class); </br>
// stubbing appears before the actual execution when(mockedList.get(0)).thenReturn("first"); </br>
// the following prints "first" System.out.println(mockedList.get(0)); </br>
// the following prints "null" because get(999) was not stubbed System.out.println(mockedList.get(999));</br>
https://github.com/mockito/mockito/wiki/FAQ这里可以查看更多你对mockito的疑问</br>

##Powermock
Powermock是对easymock和mockitto的扩展</br>
https://github.com/jayway/powermock/wiki/GettingStarted从此处入手可以一步步学习powermock的使用</br>
Easymock+spring+powermock</br>
请看附件例子</br>
Mockito+spring+powermock</br>
请看附件例子。</br>

`注意`：</br>
Momockito2需要powermock1.6.5以上的版本才支持，但是目前powermock只出到1.6.5版本，然而1.6.5的版本的powermock会抛出找不到org.powermock.api.extension.reporter.MockingFrameworkReporterFactoryImpl的异常官方说可以手动导入powermock-api-mockito-common-1.6.5.jar ，可是我尝试后还是失败。所以要只能用1.6.4使用Momockito1</br>
注意2： 在集合powermock和spring的时候尽量不要使用@Rule的方式，因为他看上去有用，实际上可能没用。
