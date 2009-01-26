package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class flow_stmt3 extends flow_stmt {
  public flow_stmt3(return_stmt return_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<return_stmt>("return_stmt", return_stmt)
    }, firstToken, lastToken);
  }
  public flow_stmt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new flow_stmt3(cloneProperties(),firstToken,lastToken);
  }
  public return_stmt getReturn_stmt() {
    return ((PropertyOne<return_stmt>)getProperty("return_stmt")).getValue();
  }
}
