package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExtraKeywordValueList extends GenASTNode {
  public ExtraKeywordValueList(power power, test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<power>("power", power),
      new PropertyOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public ExtraKeywordValueList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExtraKeywordValueList(cloneProperties(),firstToken,lastToken);
  }
  public power getPower() {
    return ((PropertyOne<power>)getProperty("power")).getValue();
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
}
