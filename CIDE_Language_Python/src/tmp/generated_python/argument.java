package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class argument extends GenASTNode {
  public argument(AnyName anyName, test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<AnyName>("anyName", anyName),
      new PropertyOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public argument(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new argument(cloneProperties(),firstToken,lastToken);
  }
  public AnyName getAnyName() {
    return ((PropertyZeroOrOne<AnyName>)getProperty("anyName")).getValue();
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
}
