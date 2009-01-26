package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class testcommatest extends GenASTNode {
  public testcommatest(test test, test test1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrOne<test>("test1", test1)
    }, firstToken, lastToken);
  }
  public testcommatest(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new testcommatest(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public test getTest1() {
    return ((PropertyZeroOrOne<test>)getProperty("test1")).getValue();
  }
}
