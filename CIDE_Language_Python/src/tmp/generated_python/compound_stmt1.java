package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt1 extends compound_stmt {
  public compound_stmt1(if_stmt if_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<if_stmt>("if_stmt", if_stmt)
    }, firstToken, lastToken);
  }
  public compound_stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt1(cloneProperties(),firstToken,lastToken);
  }
  public if_stmt getIf_stmt() {
    return ((PropertyOne<if_stmt>)getProperty("if_stmt")).getValue();
  }
}
