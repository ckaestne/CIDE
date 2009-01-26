package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class flow_stmt5 extends flow_stmt {
  public flow_stmt5(raise_stmt raise_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<raise_stmt>("raise_stmt", raise_stmt)
    }, firstToken, lastToken);
  }
  public flow_stmt5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new flow_stmt5(cloneProperties(),firstToken,lastToken);
  }
  public raise_stmt getRaise_stmt() {
    return ((PropertyOne<raise_stmt>)getProperty("raise_stmt")).getValue();
  }
}
