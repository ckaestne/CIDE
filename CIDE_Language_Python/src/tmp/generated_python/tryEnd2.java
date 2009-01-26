package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class tryEnd2 extends tryEnd {
  public tryEnd2(suite suite1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<suite>("suite1", suite1)
    }, firstToken, lastToken);
  }
  public tryEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new tryEnd2(cloneProperties(),firstToken,lastToken);
  }
  public suite getSuite1() {
    return ((PropertyOne<suite>)getProperty("suite1")).getValue();
  }
}
