package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ct extends GenASTNode {
  public ct(test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public ct(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ct(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyZeroOrOne<test>)getProperty("test")).getValue();
  }
}
