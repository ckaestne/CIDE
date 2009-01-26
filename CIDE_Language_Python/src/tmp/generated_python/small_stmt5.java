package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt5 extends small_stmt {
  public small_stmt5(flow_stmt flow_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<flow_stmt>("flow_stmt", flow_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt5(cloneProperties(),firstToken,lastToken);
  }
  public flow_stmt getFlow_stmt() {
    return ((PropertyOne<flow_stmt>)getProperty("flow_stmt")).getValue();
  }
}
