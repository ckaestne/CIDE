package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class defaultarg extends GenASTNode {
  public defaultarg(fpdef fpdef, test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fpdef>("fpdef", fpdef),
      new PropertyZeroOrOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public defaultarg(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new defaultarg(cloneProperties(),firstToken,lastToken);
  }
  public fpdef getFpdef() {
    return ((PropertyOne<fpdef>)getProperty("fpdef")).getValue();
  }
  public test getTest() {
    return ((PropertyZeroOrOne<test>)getProperty("test")).getValue();
  }
}
