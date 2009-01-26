package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class dictInt extends GenASTNode {
  public dictInt(test test, test test1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<test>("test1", test1)
    }, firstToken, lastToken);
  }
  public dictInt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new dictInt(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public test getTest1() {
    return ((PropertyOne<test>)getProperty("test1")).getValue();
  }
}
