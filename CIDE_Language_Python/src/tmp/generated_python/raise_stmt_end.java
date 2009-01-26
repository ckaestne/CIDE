package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class raise_stmt_end extends GenASTNode {
  public raise_stmt_end(test test, testcommatest testcommatest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrOne<testcommatest>("testcommatest", testcommatest)
    }, firstToken, lastToken);
  }
  public raise_stmt_end(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new raise_stmt_end(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public testcommatest getTestcommatest() {
    return ((PropertyZeroOrOne<testcommatest>)getProperty("testcommatest")).getValue();
  }
}
