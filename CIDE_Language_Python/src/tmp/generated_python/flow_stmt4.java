package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class flow_stmt4 extends flow_stmt {
  public flow_stmt4(yield_stmt yield_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<yield_stmt>("yield_stmt", yield_stmt)
    }, firstToken, lastToken);
  }
  public flow_stmt4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new flow_stmt4(cloneProperties(),firstToken,lastToken);
  }
  public yield_stmt getYield_stmt() {
    return ((PropertyOne<yield_stmt>)getProperty("yield_stmt")).getValue();
  }
}
