package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class list_if extends GenASTNode {
  public list_if(test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public list_if(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new list_if(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
}
