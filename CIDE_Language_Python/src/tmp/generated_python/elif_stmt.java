package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class elif_stmt extends GenASTNode {
  public elif_stmt(test test, suite suite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<suite>("suite", suite)
    }, firstToken, lastToken);
  }
  public elif_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new elif_stmt(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
}
