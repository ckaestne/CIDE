package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt8 extends small_stmt {
  public small_stmt8(exec_stmt exec_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<exec_stmt>("exec_stmt", exec_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt8(cloneProperties(),firstToken,lastToken);
  }
  public exec_stmt getExec_stmt() {
    return ((PropertyOne<exec_stmt>)getProperty("exec_stmt")).getValue();
  }
}
