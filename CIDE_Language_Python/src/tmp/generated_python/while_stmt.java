package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class while_stmt extends GenASTNode {
  public while_stmt(test test, suite suite, suite suite1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<suite>("suite", suite),
      new PropertyZeroOrOne<suite>("suite1", suite1)
    }, firstToken, lastToken);
  }
  public while_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new while_stmt(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
  public suite getSuite1() {
    return ((PropertyZeroOrOne<suite>)getProperty("suite1")).getValue();
  }
}
